
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsView extends JPanel {
	ButtonsModel parent;
    JButton [][] buttons;
    int size;
	ButtonsView(ButtonsModel game)
	  { 
        parent = game;
        resetView();
	  }

    public void resetView(){
        //call from constructor, but also when changing layout to 8x8
        size = parent.n;
        //System.out.println(size);
        buttons = new JButton [size][size];
        this.removeAll();
        this.setLayout(new GridLayout(size,size, 1, 1));
		setPreferredSize(new Dimension((int)parent.rightBound,(int)parent.bottomBound));		
		
        for (int i = 0; i < size; i ++) {
             for (int j = 0; j < size; j ++) {
                 JButton b = new JButton(String.valueOf(i*size + j + 1));
                 b.setEnabled(false);
                 this.add(b);
                 buttons[i][j] = b;
             }
        }
    }

    public void turnOnFlips(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                JButton button = buttons[i][j];
                button.setEnabled(true);
            }
        }
    }

    public void turnOffFlips(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                JButton button = buttons[i][j];
                button.setEnabled(false);
            }
        }
    }
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                JButton button = buttons[i][j];
                boolean x = parent.boardStates[i][j];
                button.setBackground(x ? Color.RED : Color.BLUE);
                button.setOpaque(true); 
                button.setBorderPainted(false);
            }		
		}

        //some other if conditions to ensure ...
	}

}
