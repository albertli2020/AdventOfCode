import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
		File myFile = new File("AdventOfCode/src/puzzles/day2.csv"); //path to file
        ArrayList<ArrayList<Integer>> reports = new ArrayList<ArrayList<Integer>>();
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current directory: " + currentDirectory);
		try {
			Scanner scan = new Scanner(myFile); //create scanner
			while(scan.hasNext()) {
				String line = scan.nextLine();
				String[] columns = line.split(" "); //split rows by spaces
                ArrayList<Integer> report = new ArrayList<Integer>(); 
                for(int i = 0; i < columns.length; i++) report.add(Integer.parseUnsignedInt(columns[i])); //create new report
                reports.add(report); //add report to reports
			}
			
			scan.close(); //end scan
		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace(); //just in case
		}

        int safe = 0, damped = 0;
        for(ArrayList<Integer> report : reports){ //iterate through all reports 
            if(isSafe(report)) safe++; //first check if it's safe without damper (just for part 1)
            else if(isDampedSafe(report)) damped++; //if not safe without damper, check for safe with damped (for part 2)
        }
        System.out.println("The number of safe reports is " + safe); //print results
        System.out.println("The number of damped reports is " + damped);
        System.out.println("The total number of safe reports (including damped) is " + (safe + damped));
    }
                
    public static boolean isSafe(ArrayList<Integer> report){
        int len = report.size(); //just for efficiency
        boolean increasing = true, decreasing = true;
        for(int i = 0; i < len - 1; i++){ //iterate through report to check for decreasing or increasing
            int num1 = report.get(i);
            int num2 = report.get(i + 1);
            if(num1 - num2 < 1 || num1 - num2 > 3) decreasing = false; //check if decreasing by less than 1 or more than 3
            if(num2 - num1 < 1 || num2 - num1 > 3) increasing = false; //check if increasing by less than 1 or more than 3
        }
        return (increasing || decreasing); //if fail both cases, return not safe
    }

    public static boolean isDampedSafe(ArrayList<Integer> report){
        for(int i = 0; i < report.size(); i++){ //iterate through every element in the report, removing one at a time until you remove one at each possible position
            ArrayList<Integer> dampedReport = (ArrayList<Integer>) report.clone(); //create a copy of the report
            dampedReport.remove(i); //remove one element from the report
            if(isSafe(dampedReport)){ //run through isSafe to check if new damped report with removed element is safe
                return true;
            }
        }
        return false;
    }
}
