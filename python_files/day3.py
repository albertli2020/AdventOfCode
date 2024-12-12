import os
import re

pattern = r"mul\(\d+,\d+\)"

f = open(os.path.join("python_files/puzzles/", "day3.txt"))
puzzle = f.read()

valid_mul_sections = re.findall(pattern, puzzle)

sum = 0
for idx in range(len(valid_mul_sections)):
    numbers = re.findall(r"\d+", valid_mul_sections[idx])
    sum += int(numbers[0]) * int(numbers[1])   

cond_sum = 0
current_state = True

i = 0
while i < len(puzzle):
    if puzzle[i:i+4] == "do()":
        current_state = True
        i += 4
    elif puzzle[i:i+7] == "don't()":
        current_state = False
        i += 7
    else:
        mul_match = re.match(pattern, puzzle[i:])
        if mul_match and current_state:
            section = mul_match.group()
            numbers = re.findall(r"\d+", section)
            cond_sum += int(numbers[0]) * int(numbers[1])
            i += len(section)
        else:
            i += 1

print(f"Sum of multiplications ignoring conditional statements is {sum}")
print(f"Sum of multiplications including conditional statements is {cond_sum}")
