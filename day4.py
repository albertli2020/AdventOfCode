import csv
import os

def search_horizontal(puzzle):
    for idx in enumerate(puzzle):
        

puzzle_loc = "puzzles/"
crossword = []
with open(os.path.join(puzzle_loc, "day4.csv"), newline='') as csvfile:
    lists = csv.reader(csvfile, delimiter=' ')
    for row in lists:
        crossword.append(list(row[0]))

print(crossword)