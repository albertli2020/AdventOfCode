import os
import re

pattern = r"mul\(\d+,\d+\)"

f = open(os.path.join("puzzles/", "day3.txt"))
puzzle = f.read()

valid_mul_sections = re.findall(pattern, f.read())

sum = 0
for idx in range(len(valid_mul_sections)):
    numbers = re.findall(r"\d+", valid_mul_sections[idx])
    sum += int(numbers[0]) * int(numbers[1])   

stops = [m.start() for m in re.finditer("don't()", puzzle)]
gos = [m.start() for m in re.finditer("do()", puzzle)]
gos = [item for item in gos if item not in stops]
gos.insert(0, 0)
print(gos)
print(stops)

cond_puzzle = ""


print(f"Sum of multiplications ignoring conditional statements is {sum}")
print(f"Sum of multiplications including conditional statements is {sum}")
