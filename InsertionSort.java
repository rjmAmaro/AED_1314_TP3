/*
 * AED 2013-2014
 * 
 * TP3 - Ordering Algorithms
 * 
 * Task A - Insertion Sort
 * 
 * Ricardo Amaro
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class InsertionSort {
	static int nEntries;
	static Coordinate[] coords;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		buildArray();
		//buildArrayFile(args[0]);
		insertionSort();
		//printInfo();
	}
	
	static private void buildArrayFile(String filename){
		File file = new File(filename);
		
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader(new FileReader(file));
			String[] line = null;
			
			nEntries = Integer.parseInt(reader.readLine());
			coords = new Coordinate[nEntries];
			
			for(int i=0; i<nEntries; i++){
				line = reader.readLine().split(",");
				
				/**/
				//sem truncagem
				
				float lat_f = (float) (Integer.parseInt(line[3])/100.0);
				float lon_f = (float) (Integer.parseInt(line[4])/100.0);
				
				/*/
				//com truncagem

				int lat_i = (int) (Float.parseFloat(line[3])*100);	//latitude
				int lon_i = (int) (Float.parseFloat(line[4])*100);	//longitude
				
				//System.out.println(lat_i + " " + lon_i);

				float lat_f = (float) (lat_i/100.0);
				float lon_f = (float) (lon_i/100.0);
				/*/
				
				//System.out.println(lat_f);
				//System.out.println(lon_f);
				
				coords[i] = new Coordinate(lat_f, lon_f);
			}
			
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try{
				if(reader != null){
					reader.close();
				}
			} catch (IOException e){
				
			}
		}
		
	}
	
	static private void buildArray(){
		String[] line;
		Scanner sc = new Scanner(System.in);
		
		nEntries = Integer.parseInt(sc.nextLine());
		coords = new Coordinate[nEntries];
		
		for(int i=0; i<nEntries; i++){
			line = sc.nextLine().split(",");
			/**/
			//sem truncagem
			
			float lat_f = (float) (Integer.parseInt(line[3])/100.0);
			float lon_f = (float) (Integer.parseInt(line[4])/100.0);
			
			/*/
			//com truncagem

			int lat_i = (int) (Float.parseFloat(line[3])*100);	//latitude
			int lon_i = (int) (Float.parseFloat(line[4])*100);	//longitude
			
			//System.out.println(lat_i + " " + lon_i);

			float lat_f = (float) (lat_i/100.0);
			float lon_f = (float) (lon_i/100.0);
			/*/
			
			//System.out.println(lat_f);
			//System.out.println(lon_f);
			
			coords[i] = new Coordinate(lat_f, lon_f);
		}
	}
	
	static private void insertionSort(){
		Coordinate tmp;
		int i, j;
		long startTime, endTime;
		
		startTime = System.nanoTime();
		for(i=1; i<nEntries; i++){
			tmp = coords[i];
			for(j=i; j>0 && tmp.compareTo(coords[j-1]) < 0; j--)
				coords[j] = coords[j-1];
			
			coords[j] = tmp;
		}
		endTime = System.nanoTime();
		
		System.out.println("time: " + (endTime - startTime));
	}
	
	static void printArray(){
		//print all elements of the array
		
		System.out.println("Index\tLat.\tLong.");
		for(int i=0; i<nEntries; i++){
			System.out.println((i+1) + "\t" + coords[i].lt + "\t" + coords[i].lg);
		}
	}
	
	static void printInfo(){
		//print the array element and the number of its occurrences
		
		int counter=1;
		Coordinate tmp=coords[0];
		for(int i=1; i<nEntries; i++){
			if(tmp.compareTo(coords[i]) != 0){
				System.out.printf("%.2f,%.2f,%d\n", tmp.lt, tmp.lg, counter);
				//System.out.println(tmp.lt + "," + tmp.lg + "," + counter);
				tmp=coords[i];
				counter=1;
			}
			else
				counter++;
			
		}
		System.out.println(tmp.lt + "," + tmp.lg + "," + counter);
	}

}

class Coordinate{
	float lt, lg;
	
	Coordinate(float lt, float lg){
		this.lt = lt;
		this.lg = lg;
	}
	
	public int compareTo(Coordinate c){
		/*
		 * return
		 * 		-1 if this < c
		 * 		 0 if this == c
		 * 		 1 if this > c
		 */
		
		if(this.lt < c.lt)
			return -1;
		else if(this.lt > c.lt)
			return 1;
		else{	//this.lt == c.lt
			if(this.lg < c.lg)
				return -1;
			else if(this.lg > c.lg)
				return 1;
			else		//this.lg == c.lg
				return 0;
		}
	}
}
