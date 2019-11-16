import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameController extends JFrame implements ActionListener
{
	int [][] naBoard;

	private JButton[][] oaButtonArray;
	private JButton obButton;

	int [] naUser;
	int  nPlayer = 2;
	
	//sets Frame parametes
	public GameController(int nGridSize) 
	{
		createPanel(nGridSize);
		setSize(nGridSize*50,nGridSize*50);
		setTitle("Gomoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/*
	 * builds fills GridLayout with elements from
	 * oaButtonArray each element is given a name and 
	 * a listener
	 */
	private void createPanel(int nGridSize)
	{
		naBoard = new int[nGridSize][nGridSize];

		oaButtonArray = new JButton[nGridSize][nGridSize];
		JPanel obPanel = new JPanel();
		obPanel.setBorder(new EmptyBorder(5,5,5,5));
		obPanel.setLayout(new GridLayout(nGridSize,nGridSize));		

		for(int i=0;i<oaButtonArray.length;i++)
		{
			for(int j = 0;j<oaButtonArray[i].length;j++)
			{
				obButton = oaButtonArray[i][j];
				obButton = new JButton();
				obButton.setName(String.valueOf(i)+":"+String.valueOf(j));
				obButton.addActionListener(this);
				
				obPanel.add(obButton);
			}
		}
		add(obPanel);
	}
 
	/*
	 * handles button presses changes color depending on nPlayer
	 * removes listener, sets naUser = to button name, passes naUser
	 * to Update board
	 */
	public void actionPerformed(ActionEvent event)
	{
		nPlayer = GomokuGameLogic.PlayerToken(nPlayer);
		//Updates obButton colors and removes listener
		if(nPlayer == 1)
		{
			((JButton) event.getSource()).setBackground(Color.BLACK);
			((JButton) event.getSource()).removeActionListener(this);
		}
		else
		{
			((JButton) event.getSource()).setBackground(Color.RED);
			((JButton) event.getSource()).removeActionListener(this);
		}
		//gets coordinates from obButton name
		String [] UserString = (((JButton) event.getSource()).getName().split(":"));
		naUser = new int[UserString.length];
		for(int i = 0;i<UserString.length;i++)
		{
			naUser[i] =Integer.parseInt(UserString[i]);
		}
		//updates naBoard and checks for win condition
		naBoard = GomokuGameLogic.Update(naBoard, naUser, nPlayer);
		GomokuGameLogic.PrintBoard(naBoard);
		
		if(GomokuGameLogic.WinCondition(GomokuGameLogic.ColCheck(naBoard), GomokuGameLogic.RowCheck(naBoard),
				GomokuGameLogic.DiagCheck(naBoard)))
		{
			GameWin();
		}
		//prints coordinates to console for debugging
		System.out.println(((JButton) event.getSource()).getName());
	}
	//creates an optionpane to display game winner 
	private void GameWin()
	{
		JFrame obWinFrame = new JFrame(); 
		JOptionPane.showMessageDialog(obWinFrame,"Player "+nPlayer+ " Wins");
		System.out.print("Player "+nPlayer+ " Wins");
		setVisible(false);
	}
	//creates optionpane to display stalemate
	public static void StaleMate()
	{
		JFrame obWinFrame = new JFrame(); 
		JOptionPane.showMessageDialog(obWinFrame,"Draw");
		System.out.print("Draw");
	}
}

