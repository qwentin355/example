
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
		int nCount = 0;//resets nCount
		int nCurrentNumber = naBoard[0][0];//sets nCurrentNumber to the beginning of the matrix
		for (int k = 0; k<naBoard.length*2;k++)
		{ 
			nCount = 0;
			for(int i = 0; naBoard.length>i;i++)
			{
				for (int j = 0;naBoard[i].length>j;j++)
				{
					/*
					 * sequences along the diagonal from (0,0) counting up if the next number in the sequence is 
					 * equal to the previous number in the sequence. Ends the game if 5 numbers in sequence
					 * are equal.
					 * 
					 * Resets to 1 if not equal.
					 */
					if (i+j==k)//checks from left top to bottom right
					{
						if(nCurrentNumber==naBoard[i][j] && nCurrentNumber!=0)
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
						nCurrentNumber = naBoard[i][j];
					}
				}
			}
		}
		for (int k = naBoard.length-1;k>naBoard.length*-1;k--)
		{ 
			nCount = 0;
			for(int i = 0; naBoard.length>i;i++)
			{
				for (int j = naBoard[i].length-1;j>=0;j--)
				{
					/*
					 * sequences along the diagonal from (0,14) counting up if the next number in the sequence is 
					 * equal to the previous number in the sequence. Ends the game if 5 numbers in sequence
					 * are equal.
					 * 
					 * Resets to 1 if not equal.
					 */
					if (j-i==k)//checks from right top to bottom left
					{
						if(nCurrentNumber==naBoard[i][j] && nCurrentNumber!=0)
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
						nCurrentNumber = naBoard[i][j];
					}
				}
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
}

