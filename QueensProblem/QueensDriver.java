package QueensProblem;

public class QueensDriver {

	public static void main(String[] args) {
		Queens castle = new Queens();
		for(int i = 0; i < 8; i++){
			castle.firstQueen(i);
			castle.placeQueens(1);
			System.out.println("Solution #" + (i + 1) + " to the 8 Queens Problem: \n");
			castle.displayBoard();
			castle.displayData();
			castle.clearBoard();
		}
	}

}
