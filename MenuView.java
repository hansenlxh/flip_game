import java.awt.Graphics;
import java.awt.*;    
import javax.swing.*;    

import javax.swing.JPanel;

public class MenuView extends JPanel {

	ButtonsModel bm;
	JLabel t1;
	JLabel t2;
	JPanel statPanel;
	JPanel clickingPanel = new JPanel();
	JButton button1 = new JButton("Toggle");
	JButton button2 = new JButton("Next");
	MenuView(ButtonsModel bm2) {
		bm = bm2;
		setLayout(new GridLayout(1,2));

		statPanel = new JPanel();
		statPanel.setLayout(new GridLayout(2,1));
		


		statPanel.add (t1 = new JLabel());
		
		statPanel.add (t2 = new JLabel());
		statPanel.setPreferredSize(new Dimension(100, 100));
		
		
		
		add(statPanel);
		clickingPanel.setLayout(new GridLayout(2,1));  
		clickingPanel.add(button1);
		clickingPanel.add(button2);
		add(clickingPanel);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		switch (bm.getGameState()) {
			case 0:
				t1.setText("Welcome! Click 'next' to start");
				t2.setText("'Toggle' between 4x4 and 8x8 grids");
				button1.setText("Toggle");
				button2.setText("Next");
				button2.setVisible(true);
				break;
			case 1:
				t1.setText("Arrange the grid by tapping squares to change colors");
				t2.setText("When satisfied, finalize grid by clicking 'next'");
				button1.setText("Next");
				break;
			case 2:
				t1.setText("You and the AI will now swap one tile each");
				t2.setText("Choose who will swap first");
				button1.setText("I choose first");
				button2.setText("The AI chooses first");
				break;
			case 3: //don't do anything or else it will default to this
			case 4:
				break;
			case 5:
				t1.setText("You can now invite the guesser back!");
				t2.setText("Is it possible to know which tile you clicked?");
				button1.setText("Click to play again");
				break;
		}

		

		
	}

}
