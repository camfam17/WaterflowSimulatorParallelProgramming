import java.util.concurrent.RecursiveAction;
import java.util.ArrayList;

public class ForkJoinTest extends RecursiveAction{
	
	int hi, lo;
	static int rows, columns;
	float[][] data;
	static final int SEQ_CUT=100;

	public ForkJoinTest(float data[][], int rows, int columns, int hi, int lo){
		this.data = data;
		this.hi = hi;
		this.lo = lo;
		this.rows = rows;
		this.columns = columns;
	}
	public ForkJoinTest(float data[][], int hi, int lo){
		this.data = data;
		this.hi = hi;
		this.lo = lo;
	}
	
	public void compute(){
		if((hi-lo) < SEQ_CUT){
		
			for(int i = lo; i < hi; i++){
				for(int j = 1; j < columns - 1; j++){
						
					float thisBlock = data[i][j];
				
					float[] ring = new float[8];
					ring[0] = data[i-1][j-1]; // top left
					ring[1] = data[i-1][j]; // top middle
					ring[2] = data[i-1][j+1]; // top right
					ring[3] = data[i][j-1]; // middle left
					// middle middle (i.e. the block we are studying)
					ring[4] = data[i][j+1]; // middle right
					ring[5] = data[i+1][j-1]; // bottom left
					ring[6] = data[i+1][j]; // bottom middle
					ring[7] = data[i+1][j+1]; // bottom right
					
					boolean b = true;
					for(int k = 0; k < ring.length; k++){
						if(!(ring[k] >= thisBlock + 0.01)){
							b = false;
						}
					}
					Main.setBasin(i, j, b);
						
				}
			}
			
			
		}else{
      
			ForkJoinTest left = new ForkJoinTest(data, (hi+lo)/2, lo);
			ForkJoinTest right = new ForkJoinTest(data, hi, (hi+lo)/2);

			left.fork();
			right.compute();
			left.join();
         
		}
	}
	
}
