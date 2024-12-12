import csv
import os

def search_horizontal(puzzle):
    horizontal_count = 0
    for idx, row in enumerate(puzzle):
        for i in range(len(row) - 3):
            if row[i:(i + 4)] == "XMAS" or row[i:(i + 4)] == "SAMX":
                horizontal_count += 1
    return horizontal_count
        
def search_vertical(puzzle):
    vertical_count = 0
    for idx in range(len(puzzle[0])):
        for i in range(len(puzzle) - 3):
            substr = ""
            for j in range(4):
                substr += puzzle[i + j][idx]
            if(substr == "XMAS" or substr == "SAMX"):
                vertical_count += 1
    return vertical_count
    
def search_diagonal(puzzle):
    diagonal_count = 0
    for i in range(len(puzzle[0]) - 3):
        for j in range(len(puzzle) - 3):
            left_substr = ""
            right_substr = ""
            for k in range(4):
                left_substr += puzzle[i + k][j + k]
                right_substr += puzzle[i + 3 - k][j + k]
            if(left_substr == "XMAS" or left_substr =="SAMX"):
                diagonal_count += 1
            if(right_substr == "XMAS" or right_substr =="SAMX"):
                diagonal_count += 1
    return diagonal_count
    
def search_x_mas(puzzle):
    xmas_counter = 0
    for i in range(len(puzzle[0]) - 2):
        for j in range(len(puzzle) - 2):
            left_substr = ""
            right_substr = ""
            for k in range(3):
                left_substr += puzzle[i + k][j + k]
                right_substr += puzzle[i + 2 - k][j + k]
            if(left_substr == "MAS" or left_substr =="SAM"):
                if(right_substr == "MAS" or right_substr =="SAM"):
                    xmas_counter += 1
                    
    return xmas_counter

puzzle_loc = "python_files/puzzles/"
crossword = []
with open(os.path.join(puzzle_loc, "day4.csv"), newline='') as csvfile:
    lists = csv.reader(csvfile, delimiter=' ')
    for row in lists:
        crossword.append(row[0])
        
print(f"Total number of XMAS is {search_diagonal(crossword) + search_vertical(crossword) + search_horizontal(crossword)}")
print(f"Total number of X-MAS is {search_x_mas(crossword)}")