import csv
import os

def is_safe(report):
    increasing = all(1 <= int(report[i + 1]) - int(report[i]) <= 3 for i in range(len(report) - 1))
    decreasing = all(1 <= int(report[i]) - int(report[i + 1]) <= 3 for i in range(len(report) - 1))
    return increasing or decreasing

def is_damped_safe(report):
    for i in range(len(report)):
        damped_report = report[:i] + report[i + 1:]
        if is_safe(damped_report):
            return True
    return False

puzzle_loc = "python_files/puzzles/"
reports = []

with open(os.path.join(puzzle_loc, "day2.csv"), newline='') as csvfile:
    lists = csv.reader(csvfile, delimiter=' ')
    for row in lists:
        reports.append(row)

safe = 0
damped = 0

for report in reports:
    if not all(item.isdigit() for item in report):
        continue
    
    if is_safe(report):
        safe += 1
    elif is_damped_safe(report):
        damped += 1

print(f"The number of safe reports is {safe}")
print(f"The number of damped reports is {damped}")
print(f"The total number of safe reports (including damped) is {safe + damped}")
