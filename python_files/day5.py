import os
import csv

puzzle_loc = "python_files/puzzles/"
rules = []
updates = []
with open(os.path.join(puzzle_loc, "day5.csv"), newline='') as csvfile:
    lists = csv.reader(csvfile, delimiter=' ')
    for row in lists:
        if not row:
            continue
        if row[0].find("|") != -1:
            rules.append(row[0].split("|"))
        else:
            updates.append(row[0].split(","))

def check_rules(page1, page2):
    for idx, rule in enumerate(rules):
        if(page2 == rule[0] and page1 == rule[1]):
            return False
    return True    

approved = []
for idx, update in enumerate(updates):
    correct = True
    for i in range(len(update)):
        for j in range(len(update) - i):
            if not check_rules(update[i], update[i + j]):
                correct = False
                break
            
        if not correct:
            break
    
    if correct:
        approved.append(update)


middle_sum = 0
for idx, update in enumerate(approved):
    middle_sum += int(update[len(update) // 2])

print(middle_sum)

fixed = []
for idx, update in enumerate(updates):
    correct = True
    for i in range(len(update)):
        for j in range(i + 1, len(update)):
            if not check_rules(update[i], update[j]):
                correct = False
                break
        if not correct:
            break

    if not correct:
        reordered = []
        while update:
            for i in range(len(update)):
                if all(check_rules(update[i], other) for other in update if other != update[i]):
                    reordered.append(update.pop(i))
                    break
        fixed.append(reordered)

fixed_sum = 0
for idx, update in enumerate(fixed):
    fixed_sum += int(update[len(update) // 2])

print(fixed_sum)