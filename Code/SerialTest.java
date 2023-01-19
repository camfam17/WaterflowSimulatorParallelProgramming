public class SerialTest{

	float[][] data;
	int rows, columns;

	public SerialTest(float[][] data, int rows, int columns){
		this.data = data;
		this.rows = rows;
		this.columns = columns;
	}	

	public void execute(){

		for(int i = 1; i < rows - 1; i++){
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
		
	}

}
