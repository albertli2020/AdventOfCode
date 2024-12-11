import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1 {
	public static void main(String[] args) {
		File myFile = new File("puzzles/day1.csv");
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		try {
			Scanner scan = new Scanner(myFile);
			while(scan.hasNext()) {
				String line = scan.nextLine();
				String[] columns = line.split(" ");
				arr1.add(Integer.parseUnsignedInt(columns[0]));
				arr2.add(Integer.parseUnsignedInt(columns[1]));
			}
			
			scan.close();		
		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		Collections.sort(arr1);
		Collections.sort(arr2);

		int distance = 0, similarity = 0;

		for(int i = 0; i < 1000; i++){
			distance += Math.abs((int)(arr1.get(i)) - (int)(arr2.get(i)));
			int count = 0;
			int num1 = arr1.get(i);
			for(int j = 0; j < 1000; j++){
				if(num1 == arr2.get(j)) count++;
			}
			similarity += count *= num1;
		}
		System.out.println("Total distance is " + distance);
		System.out.println("Similarity is " + similarity);


	}



}




