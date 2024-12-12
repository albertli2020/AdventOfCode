import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day3 {
    public static void main(String[] args) {
        File myFile = new File("AdventOfCode/src/puzzles/day3.txt"); //path to file
        String puzzle = "";
        try {
			Scanner scan = new Scanner(myFile); //create scanner
			while(scan.hasNext()) {
                puzzle += scan.nextLine();
			}
			
			scan.close(); //end scan
		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace(); //just in case
		}

        String mulPattern = "mul\\((\\d+),(\\d+)\\)";

        Pattern pattern = Pattern.compile(mulPattern); //create the pattern finder
        Matcher matcher = pattern.matcher(puzzle); //all matches to the pattern in the puzzle
        
        int sum = 0;
        while (matcher.find()) { //iterate through all matches
            int num1 = Integer.parseInt(matcher.group(1)); //group 1 is the first int in the pattern
            int num2 = Integer.parseInt(matcher.group(2)); //group 2 is the second int in the pattern
            sum += num1 * num2;
        }

        int conditionalSum = 0;
        boolean currentState = true;
        int i = 0;

        while (i < puzzle.length()) {
            if (puzzle.substring(i).startsWith("do()")) { //if there's a do, turn on and iterate by length of do
                currentState = true;
                i += "do()".length();
            } else if (puzzle.substring(i).startsWith("don't()")) { //if there's a don't, turn off and iterate by length of don't
                currentState = false;
                i += "don't()".length();
            } else {
                Matcher mulMatcher = pattern.matcher(puzzle.substring(i)); //create matcher for everything past the current point in the string
                if (mulMatcher.find() && mulMatcher.start() == 0) { //take first pattern found in substring
                    if (currentState) {
                        int num1 = Integer.parseInt(mulMatcher.group(1)); //group1 is first int in pattern
                        int num2 = Integer.parseInt(mulMatcher.group(2)); //group2 is second int in pattern
                        conditionalSum += num1 * num2;
                    }
                    i += mulMatcher.end(); //iterate by length of pattern
                } else {
                    i++; //if no pattern, iterate by 1
                }
            }
        }

        System.out.println("Sum of multiplications (Part 1): " + sum);
        System.out.println("Sum of multiplications (Part 2): " + conditionalSum);
    }
}