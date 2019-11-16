import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Listeners implements ActionListener  {
/*  creates new GameController Frame 
	passes the value of 15 for nGridSize
*/
	public static class ClickListener15 implements ActionListener  {
		
		public void actionPerformed(ActionEvent e)
		{
			JFrame obGame = new GameController(15);
		}
	}
	/*
	 * creates new GameController Frame
	 * passes the value of 19 for nGridSize
	 */
	public static class ClickListener19 implements ActionListener  {
		
		public void actionPerformed(ActionEvent e) {
			JFrame obGame = new GameController(19);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
