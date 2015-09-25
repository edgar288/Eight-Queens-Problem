package QueensProblem;

public class Queens {
	
	// squares per row or column
	public static final int BOARD_SIZE = 8;

	// used to indicate an empty square
	public static final int EMPTY = 0;

	// used to indicate square contains a queen
	public static final int QUEEN = 1;
	private int board[][]; // chess board
	private int backtrack, attacked, placed;
	
	public Queens() {
		// -------------------------------------------------
		// Constructor: Creates an empty square board.
		// -------------------------------------------------
		
		board = new int[BOARD_SIZE][BOARD_SIZE];
		backtrack = 0;
		attacked = 0;
		placed = 0;
	} // end constructor

	public void clearBoard() {
		// -------------------------------------------------
		// Clears the board.
		// Precondition: None.
		// Postcondition: Sets all squares to EMPTY.
		// -------------------------------------------------
		
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				board[i][j] = EMPTY;
			}
		}
		
	} // end clearBoard

	public void displayBoard() {
		// -------------------------------------------------
		// Displays the board.
		// Precondition: None.
		// Postcondition: Board is displayed as follows
		// with a blank indicating an EMPTY square, and a Q 
		// is a square containing a queen.
		// 
		// +---+---+---+---+---+---+---+---+
		// | Q |   |   |   |   |   |   |   |
		// +---+---+---+---+---+---+---+---+
		// |   |   |   |   |   |   | Q |   |
		// +---+---+---+---+---+---+---+---+
		// |   |   |   |   | Q |   |   |   |
		// +---+---+---+---+---+---+---+---+
		// |   |   |   |   |   |   |   | Q |
		// +---+---+---+---+---+---+---+---+
		// |   | Q |   |   |   |   |   |   |
		// +---+---+---+---+---+---+---+---+
		// |   |   |   | Q |   |   |   |   |
		// +---+---+---+---+---+---+---+---+
		// |   |   |   |   |   | Q |   |   |
		// +---+---+---+---+---+---+---+---+
		// |   |   | Q |   |   |   |   |   |
		// +---+---+---+---+---+---+---+---+
		//
		// -------------------------------------------------
		
		int arr = 0;
		int inArr = 0;
		for(int i = 0; i < 17; i++){
			if(i % 2 == 0){
				System.out.print("+");
				for(int j = 0; j < BOARD_SIZE; j++){
					System.out.print("---+");
				}
			}
			else{
				System.out.print("|");
				for(int j = 0; j < BOARD_SIZE; j++){
					if(board[arr][inArr] == QUEEN){
						System.out.print(" Q |");
					}else{
						System.out.print("   |");
					}
					inArr++;
				}
				inArr = 0;
				arr++;
			}
			System.out.println();
		}
		
	} // end displayBoard

	public boolean placeQueens(int column) {
		// -------------------------------------------------
		// Places queens in columns of the board beginning
		// at the column specified.
		// Precondition: Queens are placed correctly in
		// columns 1 through column-1.
		// Postcondition: If a solution is found, each
		// column of the board contains one queen and method
		// returns true; otherwise, returns false (no
		// solution exists for a queen anywhere in column
		// specified).
		// -------------------------------------------------
		
		if (column == BOARD_SIZE) {
			return true; // base case
		} else {
			boolean queenPlaced = false;
			int row = 0; // number of square in column
			while (!queenPlaced && (row < BOARD_SIZE)) {
				// if square can be attacked
				if (isUnderAttack(row, column)) {
					++row; // consider next square in column
				} // end if
				else { // place queen and consider next column
					setQueen(row, column);
					queenPlaced = placeQueens(column + 1);
					// if no queen is possible in next column,
					if (!queenPlaced) {
						// backtrack: remove queen placed earlier
						// and try next square in column
						removeQueen(row, column);
						++row;
					} // end if
				} // end if
			} // end while
			return queenPlaced;
		} // end if
		
	} // end placeQueens

	private void setQueen(int row, int column) {
		// --------------------------------------------------
		// Sets a queen at square indicated by row and
		// column.
		// Precondition: None.
		// Postcondition: Sets the square on the board in a
		// given row and column to QUEEN.
		// --------------------------------------------------
		
		board[row][column] = QUEEN;
		placed++;
	} // end setQueen

	private void removeQueen(int row, int column) {
		// --------------------------------------------------
		// Removes a queen at square indicated by row and
		// column.
		// Precondition: None.
		// Postcondition: Sets the square on the board in a
		// given row and column to EMPTY.
		// --------------------------------------------------
		
		board[row][column] = EMPTY;
		backtrack++;
	} // end removeQueen

	private boolean isUnderAttack(int row, int column) {
		// --------------------------------------------------
		// Determines whether the square on the board at a
		// given row and column is under attack by any queens
		// in the columns 1 through column-1.
		// Precondition: Each column between 1 and column-1
		// has a queen placed in a square at a specific row.
		// None of these queens can be attacked by any other
		// queen.
		// Postcondition: If the designated square is under
		// attack, returns true; otherwise, returns false.
		// --------------------------------------------------
		
		attacked++;
		boolean underAttack = false;
		//checks horizontal
		for(int i = 0; i < column; i++){
			if(board[row][i] == QUEEN){
				underAttack = true;
				break;
			}
		}
		
		//checks up and down
		if(underAttack != true){
			for(int i = 0; i < BOARD_SIZE; i++){
				if(board[i][column] == QUEEN){
					underAttack = true;
					break;
				}
			}
		}

		//checks diagonal going up from Queen
		if(underAttack != true){
			if(row > column){
				int stRow = row - column;
				for(int i = 0; i < column; i++){
					if(board[stRow + i][i] == QUEEN){
						underAttack = true;
						break;
					}
				}
				//
			}
			else{
				int stCol = column - row;
				for(int i = 0; i < row; i++){
					if(board[i][stCol + i] == QUEEN){
						underAttack = true;
						break;
					}
				}
				//
			}
		}

		//checks diagonal going down from Queen
		if(underAttack != true){
			if((row + column) <= 7){
				int stRow = row + column;
				for(int i = 0; i < column; i++){
					if(board[stRow - i][i] == QUEEN){
						underAttack = true;
						break;
					}
				}
				//
			}
			else{
				int stCol = (row + column) - 7;
				for(int i = 7; i > row; i--){
					if(board[i][stCol++] == QUEEN){
						underAttack = true;
						break;
					}
				}
				//
			}
		}

		return underAttack;
		
	} // end isUnderAttack

	public void displayData(){
		System.out.println("\nBacktracks: " + backtrack);
		System.out.println("isUnderAttack calls: " + attacked);
		System.out.println("Placed Queens: " + placed + "\n");
	}
	
	public void firstQueen(int row){
		board[row][0] = QUEEN;
		backtrack = 0;
		attacked = 0;
		placed = 0;
	}
	
//	private int index(int number) {
//		// --------------------------------------------------
//		// Returns the array index that corresponds to
//		// a row or column number.
//		// Precondition: 1 <= number <= BOARD_SIZE.
//		// Postcondition: Returns adjusted index value.
//		// --------------------------------------------------
//		
//	} // end index
} // end Queens
