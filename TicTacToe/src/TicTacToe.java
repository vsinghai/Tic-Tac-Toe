import java.util.Scanner;

public class TicTacToe  {
	 
	private char[][] board; 
	private boolean urTurn; 
	private Scanner keyboard; 
	
	
	
	public TicTacToe()  {
		board = new char[3][3];
		for(int r = 0; r < 3; r++)  {
			
			for(int c = 0; c < 3; c++)
				board[r][c] = ' ';
		}
		urTurn = true;
		keyboard = new Scanner(System.in);
	}
	
	
	
	private void numberRow(int row)  {
	
		System.out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);
	}
	
	private void displayBoard()  {
	
		numberRow(0);
		System.out.println("-----------");
		numberRow(1);
		System.out.println("-----------");
		numberRow(2);
	}
	private void displayMenu()  {
	
		
		if(urTurn)
			System.out.println("X's Turn!");
		else
			System.out.println("O's Turn!");
		System.out.println("What would you like to do?");
		System.out.println("1: Make a move");
		System.out.println("2: Start Over");
		System.out.println("3: Quit");
		System.out.print("Choice: ");
	}
	
	
	
	private boolean getMove()  {
	
		boolean invalid = true;
		int row = 0, column = 0;
		
	
		while(invalid)  {
	
			System.out.println("Which row, column would you like to move to? Enter two numbers between 0-2 separated by a space to indicate position.");
			row = keyboard.nextInt();
			column = keyboard.nextInt();
			
			
			if(row >= 0 && row <= 2 && column >= 0 && column <= 2)  {
			
		
				if(board[row][column] != ' ')
					System.out.println("That position is already taken");
				else
					invalid = false;
			}
			else
				System.out.println("Invalid position");
		}
		
		
		if(urTurn)
			board[row][column] = 'X';
		else
			board[row][column] = 'O';
		
		return winner(row,column);
	}
	
	
	private void restart()  {
	

		for(int r = 0; r < 3; r++)  {
			
			for(int c = 0; c < 3; c++)
				board[r][c] = ' ';
		}

			
	
		urTurn = true;
	}
	
	
	private boolean winner(int lastR, int lastC)  {
	
		boolean winner = false; 
		char symbol = board[lastR][lastC]; 
		
		
		int numFound = 0;
		for(int c = 0; c < 3; c++)  {
			if(board[lastR][c] == symbol)
				numFound++;
		}
		
		if(numFound == 3)
			winner = true;
	
		
		numFound = 0;
		for(int r = 0; r < 3; r++)  {
			if(board[r][lastC] == symbol)
				numFound++;
		}
		
		if(numFound == 3)
			winner = true;

		numFound = 0;
		for(int i = 0; i < 3; i++)  {
			if(board[i][i] == symbol)
				numFound++;
		}
		
		if(numFound == 3)
			winner = true;
		
		numFound = 0;
		for(int i = 0; i < 3; i++)  {
			if(board[i][2-i] == symbol)
				numFound++;
		}

		if(numFound == 3)
			winner = true;
			
		return winner;
	}
	

	
	private boolean boardFull()  {
	

		int numSpotsFilled = 0;
		
		for(int r = 0; r < 3; r++)  {
			
			for(int c = 0; c < 3; c++)  {
				if(board[r][c] == 'X' || board[r][c] == 'O')
					numSpotsFilled++;
			}
		}
		
		return numSpotsFilled == 9;
	}
	
	
	
	public void play()  {
	
		while(true)  {
		
			displayBoard();
			displayMenu();
			
			int choice = keyboard.nextInt();
		
			if(choice == 1)  {
			
				if(getMove())  {
					
					displayBoard();	
					
					if(urTurn)
						System.out.println("X Wins!");
					else
						System.out.println("O Wins!");
						
					System.exit(0);
				}
				else if(boardFull())  {
				
					displayBoard(); 
					
					System.out.println("Draw!");
					
					System.exit(0);
				}
				else  {
					
					urTurn = !urTurn;  
				}
			}
			else if(choice == 2)
				restart();
			else if(choice == 3)
				System.exit(0);	
			else
				System.out.println("Invalid Option");
		}	
	}
	
	public static void main(String[] args)  {
	
		TicTacToe game = new TicTacToe();
		
		game.play();
	}
}
