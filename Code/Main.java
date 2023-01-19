import java.util.Scanner;
import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main{

	static int rows, columns;
	static float[][] data;
   static int coef = 1; //max = 19
	
	static boolean[][] isBasin;
	
	static final ForkJoinPool fjp = new ForkJoinPool();

	static long initialTime = 0;

	public static void main(String[] args){

		loadData();
      
		testSerial();
      	testForkJoin();
      	
      	count();
      	for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(isBasin[i][j]){
					System.out.println(i + " " + j);
				}
			}
		}

	}
	
	public static void testForkJoin(){//uncomment these lines to see time trials
		
		//tick();
		fjp.invoke(new ForkJoinTest(data, rows, columns, rows-1, 1));
		//System.out.println("ForkJoin time: " + tock());
		
		//count();
		
		
	}
	
	public static void testSerial(){//uncomment these lines to see time trials
		
		//tick();
		SerialTest serial = new SerialTest(data, rows, columns);
		serial.execute();
		//System.out.println("Serial time: " + tock());
		
		//count();
		
	}
	
	public static void count(){
		int count = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(isBasin[i][j]){
					count++;
				}
			}
		}
		System.out.println(count);
	}
	
	public static void tick(){
		initialTime = System.currentTimeMillis();
	}
	public static long tock(){
		return System.currentTimeMillis() - initialTime;
	}
	
	public static void loadData(){ //loads data from text files, initializes isBasin to false

		Scanner s = new Scanner(System.in);
		Scanner c = new Scanner(s.nextLine());

		rows = coef*c.nextInt();
		columns = coef*c.nextInt();

		data = new float[rows][columns];
		
		String line = s.nextLine();
		c = new Scanner(line);
		for(int i = 0; i < rows/coef; i++){
			for(int j = 0; j < columns/coef; j++){
				float num = Float.parseFloat(c.next());
            for(int k = 0; k < coef; k++){
               data[(k*(rows/coef))+i][(k*(columns/coef))+j] = num;  
            }
			}
		}

		isBasin = new boolean[rows][columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				isBasin[i][j] = false;	
			}
		}
		
	}
	
	public static void setBasin(int row, int column, boolean basin){
	   isBasin[row][column] = basin;	
	}

}
