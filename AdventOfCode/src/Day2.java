import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
		File myFile = new File("AdventOfCode/src/puzzles/day2.csv");
        ArrayList<ArrayList<Integer>> reports = new ArrayList<ArrayList<Integer>>();
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current directory: " + currentDirectory);
		try {
			Scanner scan = new Scanner(myFile);
			while(scan.hasNext()) {
				String line = scan.nextLine();
				String[] columns = line.split(" ");
                ArrayList<Integer> report = new ArrayList<Integer>();
                for(int i = 0; i < columns.length; i++) report.add(Integer.parseUnsignedInt(columns[i]));
                reports.add(report);
			}
			
			scan.close();		
		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

        int safe = 0, damped = 0;
        for(ArrayList<Integer> report : reports){
            if(isSafe(report)) safe++;
            else if(isDampedSafe(report)) damped++;
        }
        System.out.println("The number of safe reports is " + safe);
        System.out.println("The number of damped reports is " + damped);
        System.out.println("The total number of safe reports (including damped) is " + (safe + damped));
    }
                
    public static boolean isSafe(ArrayList<Integer> report){
        int len = report.size();
        boolean increasing = true, decreasing = true;
        for(int i = 0; i < len - 1; i++){
            int num1 = report.get(i);
            int num2 = report.get(i + 1);
            if(num1 - num2 < 1 || num1 - num2 > 3) decreasing = false;
            if(num2 - num1 < 1 || num2 - num1 > 3) increasing = false;
        }
        return (increasing || decreasing);
    }

    public static boolean isDampedSafe(ArrayList<Integer> report){
        for(int i = 0; i < report.size(); i++){
            ArrayList<Integer> dampedReport = (ArrayList<Integer>) report.clone();
            dampedReport.remove(i);
            if(isSafe(dampedReport)){
                return true;
            }
        }
        return false;
    }
}
