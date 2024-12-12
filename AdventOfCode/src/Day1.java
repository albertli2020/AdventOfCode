import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1 {
	public static void main(String[] args) {
		File myFile = new File("AdventOfCode/src/puzzles/day1.csv"); //path to file
		ArrayList<Integer> arr1 = new ArrayList<Integer>(); //arraylists to store the two lists
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		try {
			Scanner scan = new Scanner(myFile);
			while(scan.hasNext()) {
				String line = scan.nextLine();
				String[] columns = line.split(" ");
				arr1.add(Integer.parseUnsignedInt(columns[0])); //add the first column to the first array
				arr2.add(Integer.parseUnsignedInt(columns[1])); //add the second column to the second array
			}
			
			scan.close(); // stop scan
		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace(); //just in case
		}

		Collections.sort(arr1); //sort both array in ascending order so they are paired up
		Collections.sort(arr2);

		int distance = 0, similarity = 0;

		for(int i = 0; i < 1000; i++){
			distance += Math.abs((int)(arr1.get(i)) - (int)(arr2.get(i))); //get the distance between the two elements by finding absolute value
			int count = 0;
			int num1 = arr1.get(i); 
			for(int j = 0; j < 1000; j++){ //check number of occurences in the second array
				if(num1 == arr2.get(j)) count++;
			}
			similarity += count *= num1; 
		}

		System.out.println("Total distance is " + distance); //print results
		System.out.println("Similarity is " + similarity);
	}
}




