import java.util.ArrayList;
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

        Pattern pattern = Pattern.compile(mulPattern);
        Matcher matcher = pattern.matcher(puzzle);
        
        int sum = 0;
        while (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            sum += num1 * num2;
        }

        int conditionalSum = 0;
        boolean currentState = true;
        int i = 0;

        while (i < puzzle.length()) {
            if (puzzle.startsWith("do()", i)) {
                currentState = true;
                i += 4;
            } 
            else if (puzzle.startsWith("don't()", i)) {
                currentState = false;
                i += 7;
            } 
            else {
                Matcher mulMatcher = pattern.matcher(puzzle.substring(i));
                if (mulMatcher.find()) {
                    if (currentState) {
                        int num1 = Integer.parseInt(mulMatcher.group(1));
                        int num2 = Integer.parseInt(mulMatcher.group(2));
                        conditionalSum += num1 * num2;
                    }
                    i += mulMatcher.end();
                }
                else i++;
            }
        }

        System.out.println("Sum of multiplications (Part 1): " + sum);
        System.out.println("Sum of multiplications (Part 2): " + conditionalSum);
    }

    public static ArrayList<String> extractNumbers(String input) {
        ArrayList<String> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            numbers.add(matcher.group());
        }
        
        return numbers;
    }

}