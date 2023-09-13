
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.*;

import javax.swing.*;




public class MenuController implements ActionListener {
	

	JPanel buttonPanel = new JPanel();
	ButtonsModel bm;
	ButtonsView bv;
	MenuView statsview;
	ButtonsController bc;
	MenuController( MenuView sv, ButtonsView buttonv, ButtonsModel buttonm, ButtonsController buttonc) {
		statsview = sv;
		buttonPanel.setLayout(new GridLayout(2,1));  
		bm = buttonm;
		bv = buttonv;
		bc = buttonc;

		statsview.button1.addActionListener(this);
		statsview.button2.addActionListener(this);

		Timer tick = new Timer(200, new Refresher());
		tick.start();
	}
	
	class Refresher implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			//statsview.repaint();
			//roundaboutway to get around not having to pass buttonscontroller menu buttons.
			
			if (bm.aiTurnBeginNotif) {
				try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                bm.flipSquare(bm.aitarget);
                bv.repaint();
				bm.aiTurnBeginNotif = false;
				statsview.button1.setVisible(true);
				bm.setGameState(5);
				statsview.repaint();
			}

			if (bm.getGameState() == 4) {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				statsview.t1.setText("The AI has finished flipping a square");
				statsview.t2.setText("Your turn to flip a square");
				statsview.repaint();
				bv.turnOnFlips();
				bv.repaint();
				bm.setGameState(6);//special number
			}
			if (bm.finalMove) {
				bm.finalMove = false;
				bm.setGameState(5);
				statsview.repaint();
				statsview.button1.setVisible(true);

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClicked = (JButton) e.getSource();

		switch (bm.getGameState()){
			case 0:
				//this only happens in one specific case: the reset! Do you want to play?
				if (buttonClicked == statsview.button1) {
					bm.easy= !bm.easy;
					bm.initializeStates();
					bv.resetView();
					bv.repaint();
					bc.resetListeners();
				}
				if (buttonClicked == statsview.button2) {
					bv.turnOnFlips();
					bm.incrementGameState();
					statsview.button2.setVisible(false);
					statsview.repaint();
				}
				break;
			case 1:
				if (buttonClicked == statsview.button1) {
					bv.turnOffFlips();
					bm.incrementGameState();
					statsview.button2.setVisible(true);
					statsview.repaint();
				}
				break;
			case 2:
				if (buttonClicked == statsview.button1) {
					bv.turnOnFlips();
					bm.incrementGameState(); //2-->3
					statsview.t1.setText("Your turn! Please select one tile");
					statsview.t2.setText("");
				}
				if (buttonClicked == statsview.button2) {
					//bv.turnOnFlips();
					bm.flipSquare(bm.returnTargetFlip(0));

					

					bm.setGameState(4);

					
				}
				statsview.button2.setVisible(false);
				statsview.button1.setVisible(false);
				statsview.repaint();
			break;
			case 5:
				bm.setGameState(0); //only one option...reset game
				bm.easy= true;
				bm.initializeStates();
				bv.resetView();
				bv.repaint();
				bc.resetListeners();
				statsview.repaint();
			break;

		}

		
	}
}

