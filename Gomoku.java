import java.util.Scanner;
public class Gomoku {

	public static void main(String[] args) 
	{

		int Player = 2;

		Scanner userInput = new Scanner(System.in);
		System.out.println("[1]For PvP\n[2]For PvC\n[3]For Debug");
		int menuChoice = userInput.nextInt();
		System.out.println("choose the size of board");
		int size = userInput.nextInt();
		int [][] board = new int [size][size];
		GameLoop(Player,board,userInput,menuChoice);
	}


	public static void GameLoop(int Player, int[][]board, Scanner userInput, int menuChoice)
	{
		while (WinCondition(ColCheck(board),RowCheck(board),DiagCheck(board))==false)
		{
			Player = PlayerToken(Player);//updates current player

			int[] User = UserInput(userInput,board,Player,menuChoice);//take user input validates user input

			board = Update(board,User,Player);//updates the board

			PrintBoard(board); //print updated board 
		}
		System.out.print("Player"+Player+"wins!!");
	}
	/*
	 * checks is 5 in a row is found either vertically, Horizontally, or Diagonally
	 * if any of these are true, then the game ends
	 */
	public static boolean WinCondition(boolean X, boolean Y, boolean Diagon)
	{
		if(X==false&&Y==false&&Diagon==false)
			return false;
		else
			return true;
	}

	/*
	 * Checks the columns for 5 sequential inputs that are equal
	 */
	public static boolean ColCheck(int[][]board)
	{
		for(int i = 0;i<board.length;i++)
		{
			int count =0;//resets count when moving to a new column
			int currentNumber=board[0][i];//sets count to the top of the new column
			for(int j = 0; j<board[i].length;j++)//sequences down the column
			{
				/*
				 * sequences down the columns counting up if the next number in the sequence is 
				 * equal to the previous number in the sequence. Ends the game if 5 numbers in sequence
				 * are equal.
				 * 
				 * Resets to 0 if not equal.
				 */
				if(board[j][i]==currentNumber && currentNumber !=0)
				{
					count++;
					if(count == 5)
					{
						return true;
					}
				}
				else
				{
					count=1;
				}
				currentNumber=board[j][i];//updates the previously checked number
			}
		}
		return false;
	}

	public static boolean RowCheck(int[][]board)
	{

		for(int i = 0;i<board.length;i++)
		{
			int count =0;
			int currentNumber=board[i][0];
			for(int j = 0; j<board[i].length;j++)
			{
				if(board[i][j]==currentNumber && currentNumber!=0)
				{
					count++;
					if(count == 5)
					{
						return true;
					}
				}
				else
				{
					count=1;
				}
				currentNumber= board[i][j];
			}
		}
		return false;
	}
	public static boolean DiagCheck(int[][]board)
	{
//		for (int i =0;i<board.length*2;i++)
//		{ 
//			int TempI=i;
//			for(int j = 0 ;j<=i;j++,TempI--)
//			{
//					System.out.println(TempI+"L"+j);	
//			}
//			
//		}
		return false;//needs conditions
	}

		public static int[][] Update(int[][]board,int[] User,int Player)
		{
			board[User[0]][User[1]]=Player;//uses 1-15 instead of 0-14
			return board;

		}
		public static int PlayerToken(int Player)
		{//passes the turn
			if(Player == 1)
				return 2;
			else
				return 1;
		}

		public static void PrintBoard(int[][] board)
		{
			for(int i=0;board.length>i;i++)
			{
				if(i>9)
				{ 
					System.out.print("  "+ i);//prints top index
				}
				else
					System.out.print("   "+ i);
			}

			System.out.println();
			for(int i=0;board.length>i;i++)
			{
				if(i>9)
				{
					System.out.print(i + " | ");  //prints side index
				}
				else
					System.out.print(i + "  | ");
				//prints the board
				for(int j=0;board[i].length>j;j++)
				{
					if(board[i][j]!=0)
					{

						System.out.print(board[i][j]+" | ");
					}
					else
					{
						System.out.print("  | ");
					}

				}
				//prints horizontal gridlines
				System.out.print("\n    ");
				for(int k=0;k<board.length;k++)
				{
					System.out.print("----");
				}
				System.out.print("\n");

			}
		}

		public static int[] UserInput(Scanner userInput, int[][]board, int player, int menuChoice)
		{   
			int[]User= {0,0};
			do
			{

				if(player == 2 && menuChoice == 2)
				{
					System.out.println("Computer Choose a Position");//prompts user to make a choice

					int X=(int)(Math.random()*board.length);
					int Y =(int)(Math.random()*board.length);

					User[0] = X; //stores selection of x position
					User[1] = Y;   //store selection of y position
				}
				else if(menuChoice ==3)
				{
					System.out.println("Computer Choose a Position");//prompts user to make a choice

					int X=(int)(Math.random()*board.length);
					int Y =(int)(Math.random()*board.length);

					User[0] = X; //stores selection of x position
					User[1] = Y;   //store selection of y position   
					System.out.println(X+":"+Y);
				}
				else 
				{
					System.out.println("Player "+player+ " Choose a Position");//prompts user to make a choice

					int X = userInput.nextInt();//take x position from user
					int Y = userInput.nextInt();//take y position from user 
					User[0] = X; //stores selection of x position
					User[1] = Y;   //store selection of y position
				}


			}while(isValid(User,board)==false);//validates selection

			return User;//return validated user input
		}

		public static boolean isValid(int[] User, int[][] board)
		{
			/*
			 * if user choice is greater than the size of the board or
			 * previously chosen it is considered invalid, the user has to 
			 * pick again.
			 */
			if(User[0]<board.length&&User[1]<board.length)
			{
				if(board[User[0]][User[1]]==0)
				{
					return true;
				}
			}
			return false;
		}
	}