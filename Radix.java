import java.util.ArrayList;
import java.util.Scanner;

/*
 * AED 2013-2014
 * 
 * TP3 - Ordering Algorithms
 * 
 * Task B - Quick Sort with Improvements
 * 
 * Ricardo Amaro
 */

public class Radix {
	static int BUCKETS = 10;
	static int keyLen = 10;
	static int nEntries;
	static Coordinate3[] coords;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		buildArray();
		radixSort();
		//quickSort(0, nEntries-1);
		printInfo();
		//printArray();
	}
	
	static private void buildArray(){
		String[] line;
		Scanner sc = new Scanner(System.in);
		
		nEntries = Integer.parseInt(sc.nextLine());
		coords = new Coordinate3[nEntries];
		
		for(int i=0; i<nEntries; i++){
			line = sc.nextLine().split(",");
			/**/
			//sem truncagem
			
			int lat_int = Integer.parseInt(line[3]);
			int lon_int = Integer.parseInt(line[4]);
			
			float lat_f = (float) (lat_int/100.0);
			float lon_f = (float) (lon_int/100.0);
			
			String coord_name;
			
			coord_name = String.format("%05d%05d",(lat_int+9000), (lon_int+18000));
			
			//System.out.println(coord_name);
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
			
			coords[i] = new Coordinate3(lat_f, lon_f, coord_name);
		}
	}
	
	/**
	 * Ordenamento de um intervalo do array - usado no quickSort
	 * @param low
	 * @param high
	 */
	static private void insertionSort(int low, int high){
		//System.out.println("insertionSort: " + low + " " + high);
		
		Coordinate3 tmp;
		int i, j;
		
		for(i=low + 1; i <= high; i++){
			tmp = coords[i];
			for(j = i; j > low && tmp.compareTo(coords[j-1]) < 0; j--)
				coords[j] = coords[j-1];
			
			coords[j] = tmp;
		}
		
		//printArray(low, high+1);
		
	}
	
	
	/**
	 * 
	 */
	public static void radixSort(){
		//@SuppressWarnings("unchecked")
		ArrayList<ArrayList<Coordinate3>> buckets  = new ArrayList<ArrayList<Coordinate3>>();
		int idx;
		
		for(int i = 0; i < BUCKETS; i++)
			buckets.add(i, new ArrayList<Coordinate3>());
		
		for(int pos = keyLen - 1; pos >= 0; pos--){
			for(Coordinate3 coord : coords)
				buckets.get(Integer.parseInt(coord.line.substring(pos, pos+1))).add(coord);
			
			idx = 0;
			for(ArrayList<Coordinate3> thisBucket : buckets){
				for(Coordinate3 coord : thisBucket)
					coords[idx++] = coord;
				
				thisBucket.clear();
			}
		}
	}
	
		
	static void printArray(){
		//print all elements of the array
		
		System.out.println("Index\tLat.\tLong.");
		for(int i=0; i<nEntries; i++){
			System.out.println((i+1) + "\t" + coords[i].lt + "\t" + coords[i].lg + "\t" + coords[i].line);
		}
	}
	
	static void printArray(int init, int fin){
		//print all elements of the array
		
		System.out.println("Index\tLat.\tLong.");
		for(int i=init; i<fin; i++){
			System.out.println((i+1) + "\t" + coords[i].lt + "\t" + coords[i].lg);
		}
	}
	
	static void printInfo(){
		//print the array element and the number of its occurrences
		
		int counter=1;
		Coordinate3 tmp=coords[0];
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
		System.out.printf("%.2f,%.2f,%d\n", tmp.lt, tmp.lg, counter);
		//System.out.println(tmp.lt + "," + tmp.lg + "," + counter);
	}

}

class Coordinate3{
	float lt, lg;
	String line;
	
	Coordinate3(float lt, float lg, String line){
		this.lt = lt;
		this.lg = lg;
		this.line = line;
	}
	
	private void buildLine(){
		int lt_t, lg_t;
		
		lt_t = (int) (this.lg + 90.0)*100;
		lg_t = (int) (this.lg + 180.0)*100;
		
		System.out.println(lt_t + " " + lg_t);
		String lt = String.format("%03d", lt_t);
		String lg = String.format("%03d", lg_t);
		System.out.println(lt);
		System.out.println(lg);
		line = lt;
		line.concat(lg);
		
	}
	
	public int compareTo(Coordinate3 c){
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