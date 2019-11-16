import java.awt.event.ActionListener;

import javax.swing.*;

public class OptionsFrame extends JFrame {
	private int nSize = 300;
	//Sets frame parameters
	public OptionsFrame() 
	{
		createPanel();
		setSize(nSize,nSize/3);
		setTitle("GomokuSettings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	//creates a panel with two buttons for the menu frame
	public void createPanel()
	{
		ActionListener fifteen = new Listeners.ClickListener15();
		ActionListener ninteen = new Listeners.ClickListener19();
		
		JButton obFifteen = new JButton("15x15");
		JButton obNineteen = new JButton("19x19");

		JPanel obMenu = new JPanel();
		
		obFifteen.addActionListener(fifteen);
		obNineteen.addActionListener(ninteen);
		
		obMenu.add(obFifteen);
		obMenu.add(obNineteen);
		add(obMenu);
	}
}
