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

public class QuickSortImprove {
	static int nEntries;
	static Coordinate2[] coords;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		buildArray();
		quickSort(0, nEntries-1);
		printInfo();
	}
	
	static private void buildArray(){
		String[] line;
		Scanner sc = new Scanner(System.in);
		
		nEntries = Integer.parseInt(sc.nextLine());
		coords = new Coordinate2[nEntries];
		
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
			
			coords[i] = new Coordinate2(lat_f, lon_f);
		}
	}
	
	/**
	 * Ordenamento de um intervalo do array - usado no quickSort
	 * @param low
	 * @param high
	 */
	static private void insertionSort(int low, int high){
		//System.out.println("insertionSort: " + low + " " + high);
		
		Coordinate2 tmp;
		int i, j;
		
		for(i=low + 1; i <= high; i++){
			tmp = coords[i];
			for(j = i; j > low && tmp.compareTo(coords[j-1]) < 0; j--)
				coords[j] = coords[j-1];
			
			coords[j] = tmp;
		}
		
		//printArray(low, high+1);
		
	}
	
	static public void quickSort(){
		quickSort(0, nEntries-1);
	}
	
	/**
	 * Ordena o array coords com recurso a uma implementação Quick Sort.
	 * Abordagem recursiva.
	 * Usa particionamento com media de 3 valores
	 * Cutoff de 30
	 * @param low Index do valor mais à esquerda.
	 * @param high Index do valor mais à direita.
	 */
	static private void quickSort(int low, int high){
		//System.out.println("quickSort " + low + " " + high);
		int cutoff=30;
		
		//se sub-array com menos de 30 elementos aplica Insertion Sort
		if(low + cutoff > high){
			insertionSort(low, high);
		}
		else{
			//mediana de 3 - ordenamento dos 3 elementos
			int middle = (low + high)/2;
			if(coords[middle].compareTo(coords[low]) < 0)	//coords[middle] < coords[low]
				swapReferences(middle, low);
			if(coords[high].compareTo(coords[low]) < 0)		//coords[high] < coords[low]
				swapReferences(high, low);
			if(coords[high].compareTo(coords[middle]) < 0)	//coords[high] < coords[middle]
				swapReferences(high, middle);
			
			//coloca-se o pivot na penúltima posição -> assim já não precismoa verificar o último elemento, ele já é maior q o pivot
			swapReferences(middle, high-1);
			Coordinate2 pivot = coords[high-1];
			
			//particionamento
			int i, j;
			for(i=low, j=high-1;;){
				while(coords[++i].compareTo(pivot) < 0)	//coords[++i] < pivot  -->> viaja para a direita até encontrar um >= que o pivot
					;
				while(coords[--j].compareTo(pivot) > 0)	//coords[--j] > pivot  -->> viaja para a esquerda até encontrar um <= que o pivot
					;
				
				if(i >= j)	//quando os ponteiros se cruzarem
					break;		//termina
				
				swapReferences(i, j);
			}
			
			//coloca o pivot na posição
			swapReferences(i, high-1);
			
			quickSort(low, i-1);	//ordena os elementos menores
			quickSort(i+1, high);	//ordena os elementos maiores
			//o pivot já não é ordenado
		}
	}
	
	/**
	 * Troca dois elementos de posição no array
	 * @param index1
	 * @param index2
	 */
	static private void swapReferences(int index1, int index2){
		Coordinate2 tmp = coords[index1];
		coords[index1] = coords[index2];
		coords[index2] = tmp;
	}
	
	static void printArray(){
		//print all elements of the array
		
		System.out.println("Index\tLat.\tLong.");
		for(int i=0; i<nEntries; i++){
			System.out.println((i+1) + "\t" + coords[i].lt + "\t" + coords[i].lg);
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
		Coordinate2 tmp=coords[0];
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

class Coordinate2{
	float lt, lg;
	
	Coordinate2(float lt, float lg){
		this.lt = lt;
		this.lg = lg;
	}
	
	public int compareTo(Coordinate2 c){
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