/*
@author: Vladut Madalin Druta
*/

public class PuzzleBoard {
	
	private int size;
	private int board[][];
	
	// create a new puzzle board 
	// pass in the size of the board
	public PuzzleBoard(int size){
		this.size = size;
		board = new int[size][size]; // create an empty board of that specified size
		randomize(); // and randomize the board
	}
	
	// method to get the size
	public int getSize(){
		return size;
	}
	
	// method to get the actuall board
	public int[][] get2D(){
		return board;
	}
	
	// method to generate a new random position of the tiles in the board
	public void randomize(){ 
		int[] numbers = new int[size*size]; // create an empty array of the size of the board
		for (int i = 0; i < size*size; i ++) numbers[i] = i; // assign the numbers in the array (from 0 to size*size-1) 
		for (int i = 0; i < size*size; i ++) {
		    int idx = (int) (Math.random()*size*size);  // generate a number that multiplied by the maximum size will generate the replace to
		    int temp = numbers[idx]; // replace the number from the generated index with the current index
		    numbers[idx] = numbers[i];
		    numbers[i] = temp;
		} // basically the for loop presented above shuffles the numbers from 0 to size*size-1 
		
		
		// once the array is shuffled the numbers can be added to the 2D array representing the board
		for(int i=0;i<size*size;i++){ 
			int x = i/size;
			int y = i%size;
			if(numbers[i]==0)// also if the number is 0 i would change that to -1 representing the space in the board
				numbers[i]=-1;
			board[x][y]=numbers[i];
		}
	}
	
	
	// this method will return a boolean to tell if the puzzle generated is solvable or not
	public boolean isSolvable(){
		if(calculateInversions(board.length)%2==0) // if the number of inversions is even that means the puzzle is solvable
			return true; // return true
		else // otherwise the puzzle is not solvable
			return false; // return false
	}
		
	
	// method to sum up all the inversions that are on the board
	private int calculateInversions(int size){
		int total=0;
		int c=0;
		
//		copy the board from a 2D array into a 1D array <--
		int[] theBoardinLine = new int[size*size]; 
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[0].length;j++){
				theBoardinLine[c] = board[i][j];
				c++;
			}
//		-->
		
		for(int i=0;i<theBoardinLine.length;i++) // run through the created 1D array with one index
			for(int j=i;j<theBoardinLine.length;j++)  // with another index run from the previous item to add up inversions if necessary
				if(theBoardinLine[i]==-1 || theBoardinLine[j]==-1){
					if(theBoardinLine[i]==-1)
						j=theBoardinLine.length; // if the i is -1 i want to skip all the checks that have to be done
					else if(theBoardinLine[j]==-1) // if the j is -1 i want to skip it and continue the look for the rest of them
							j++;
				}
				else{
					if(theBoardinLine[i]>theBoardinLine[j])
						total++; // if there is an inversion i want to add that to the total of inversions
				}
		return total; // return the number of inverstions
	}
	
	
//	method to move the blank tile up
	public void moveEmptyUp(){
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[0].length;j++)
				if(board[i][j]==-1 && i>0){ // if the blank tile was reached and its index is not on the top line
					int temp = board[i-1][j]; // exchange the tile values
					board[i-1][j]=board[i][j];
					board[i][j]=temp;
					// no return needed because the indexes will never reach back to the empty tile
				}
	}
	
//	method to move the blank tile down
	public void moveEmptyDown(){
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[0].length;j++)
				if(board[i][j]==-1 && i<board.length-1){ // run thorugh the board until we find the empty tile and its position is not on the bottom
					int temp = board[i+1][j]; // exchange it's palce with whatever value is underneath it 
					board[i+1][j]=board[i][j];
					board[i][j]=temp;
					return; // also return from the method without doing anything else because if not the index will run and hit the blank again and 
//					again until it gets to the bottom of the board  
				}
	}
	
//	method to move the blank tile right
	public void moveEmptyRight(){
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[0].length;j++) // the same running through the board as in the previous methods
				if(board[i][j]==-1 && j<board.length-1){ // also when the empty tile was found and is not in the most right column 
					int temp = board[i][j+1]; // interchange the empty tile and the next right value
					board[i][j+1]=board[i][j];
					board[i][j]=temp;
					return; // return from the method because the index will hit the empty tile again and will not stop until it moves it to the most 
//					right column
//					the break statement will work in these case aswell because it will break the inner loop that runs through the colums so it will
//					increase the index of the rows so it will never hit the empty tile
				}
	}
	
//	method to move the blank left 
	public void moveEmptyLeft(){
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[0].length;j++)
				if(board[i][j]==-1 && j>0){ // when the empty tile is found and is not at the most left column
					int temp = board[i][j-1]; // interchange the values
					board[i][j-1]=board[i][j];
					board[i][j]=temp;
//					no break needed in this because the indexes are running thorugh the right and then down in the rows so the empty tile will not be 
//					hitted again
				}
	}
	
	
//	method to know if the puzzle is solved
	public boolean isSolved(){ 
		if(calculateInversions(board.length)==0) // the puzzle is solved once there are no inversions left
			return true; // therefore the method will return true - because the player fully finished the puzzle
		else
			return false;  // otherwise returns false
	}
	
	
	
//	method to be able to print the board as a string to the console (used for testing / debuging purposes)
	public String toString(){
		String str="";
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++)
				str = str + board[i][j] + " ";
			str = str + "\n";
		}
		return str;
	}
	
}
