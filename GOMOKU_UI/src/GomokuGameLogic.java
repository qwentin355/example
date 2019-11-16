
public class GomokuGameLogic {

	/* checks is 5 in a row is found either vertically, Horizontally, or Diagonally
	 * if any of these are true, then the game ends
	 * @checks for win conditions
	 */
	public static boolean WinCondition(boolean bX, boolean bY, boolean bDiagon)
	{
		if(bX==false&&bY==false&&bDiagon==false)
			return false;
		else
			return true;
	}

	/*Column Check
	 * @Checks the columns for 5 sequential inputs that are equal
	 */
	public static boolean ColCheck(int[][]naBoard)
	{
		for(int i = 0;i<naBoard.length;i++)
		{
			int nCount =0;//resets nCount when moving to a new column
			int nCurrentNumber=naBoard[0][i];//sets nCount to the top of the new column
			for(int j = 0; j<naBoard[i].length;j++)//sequences down the column
			{
				if(naBoard[j][i]==nCurrentNumber && nCurrentNumber !=0)
				{
					nCount++;
					if(nCount == 5)
					{
						return true;
					}
				}
				else
				{
					nCount=1;
				}
				nCurrentNumber=naBoard[j][i];//updates the previously checked number
			}
		}
		return false;
	}
	/*Row Check
	 * @Checks Rows for sequence of 5
	 */
	public static boolean RowCheck(int[][]naBoard)
	{
		int nStalemate=0;
		for(int i = 0;i<naBoard.length;i++)
		{
			int nCount =0;
			int nCurrentNumber=naBoard[i][0];
			
			for(int j = 0; j<naBoard[i].length;j++)
			{
				if(naBoard[i][j]==0)
				{
					nStalemate++;
				}
				if(naBoard[i][j]==nCurrentNumber && nCurrentNumber!=0)
				{
					nCount++;
					if(nCount == 5)
					{
						return true;
					}
				}
				else
				{
					nCount=1;
				}
				nCurrentNumber= naBoard[i][j];
			}
		}
		if(nStalemate==0)
		{
			GameController.StaleMate();
		}
		return false;
	}
	/*Diagonal Check
	 * @Checks diagonals for sequence of 5
	 */
	public static boolean DiagCheck(int[][]naBoard)
	{
		int nCount = 0;
		int nCurrentNumber = naBoard[0][0];
		//diagonal
		for (int i =0;i<naBoard.length*2-1;i++)
		{ 
			int j = i;
			int k = 0;
			if(i>naBoard.length-1)
			{
				j=naBoard.length-1;
				k=i-j;
			}
			nCount=0;  
			nCurrentNumber = naBoard[j][k];
			for(int l = k;j>=l;j--, k++)
			{
				if(naBoard[j][k]==nCurrentNumber && nCurrentNumber!=0)
				{
						nCount++;
						if(nCount==5)
						{
							return true;
						}		
				}
				else
				{
					nCount =1;
				}
				nCurrentNumber = naBoard[j][k];
			}
		}
		//AntiDiagonal
		for (int i =0;i<naBoard.length*2-1;i++)
		{ 
			int j = naBoard.length-1;
			int k = i;
			if(i>naBoard.length-1)
			{			
				k= naBoard.length-1;
				j=k*2-i;
			}
			nCount=0;  
			nCurrentNumber = naBoard[j][k];
			for(int l = i-k;k>=l;j--, k--)
			{
				if(naBoard[j][k]==nCurrentNumber && nCurrentNumber!=0)
				{
						nCount++;
						if(nCount==5)
						{
							return true;
						}		
				}
				else
				{
					nCount =1;
				}
				nCurrentNumber = naBoard[j][k];
			}
		}
		return false;
	}
	/* naBoard Updater
	 * @Updates the naBoard array with users input
	 */
	public static int[][] Update(int[][]naBoard,int[] naUser,int nPlayer)
	{
		naBoard[naUser[0]][naUser[1]]=nPlayer;
		return naBoard;

	}
	/*nPlayer selector
	 * @Passes a virtual token between players
	 */
	public static int PlayerToken(int nPlayer)
	{//passes the turn
		if(nPlayer == 1)
			return 2;
		else
			return 1;
	}
	
	/*Prints naBoard in console
	 * @Console debugging naBoard
	 */
	public static void PrintBoard(int[][] naBoard)
	{
		for(int i=0;naBoard.length>i;i++)
		{
			if(i>9)
			{ 
				System.out.print("  "+ i);//prints top index
			}
			else
				System.out.print("   "+ i);
		}
		System.out.println();
		for(int i=0;naBoard.length>i;i++)
		{
			if(i>9)
			{
				System.out.print(i + " | ");  //prints side index
			}
			else
				System.out.print(i + "  | ");
			//prints the naBoard
			for(int j=0;naBoard[i].length>j;j++)
			{
				if(naBoard[i][j]!=0)
				{
					System.out.print(naBoard[i][j]+" | ");
				}
				else
				{
					System.out.print("  | ");
				}
			}
			//prints horizontal gridlines
			System.out.print("\n    ");
			for(int k=0;k<naBoard.length;k++)
			{
				System.out.print("----");
			}
			System.out.print("\n");
		}
	}
}

