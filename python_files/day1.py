import csv
import os

puzzle_loc = "python_files/puzzles/"
list1 = []
list2 = []
with open(os.path.join(puzzle_loc, "day1.csv"), newline='') as csvfile:
    lists = csv.reader(csvfile, delimiter=' ')
    for row in lists:
        list1.append(row[0])
        list2.append(row[1])        
        
list1 = sorted(list1)
list2 = sorted(list2)


distance = 0
similarity = 0
for idx in range(1000):
    distance += abs(int(list1[idx]) - int(list2[idx]))
    similarity += list2.count(list1[idx]) * int(list1[idx])

    
print(f"Total distance is {distance}")
print(f"Similarity  is {similarity}")