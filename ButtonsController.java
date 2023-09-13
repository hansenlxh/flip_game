
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.*;

import javax.swing.*;

public class ButtonsController {

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            
            JButton button = (JButton) actionEvent.getSource();
            // String label = button.getLabel(); //Deprecated

            // for debugging
            String label2 = button.getText();
            int intRep = Integer.valueOf(label2) - 1;
            //System.out.println(label2);
            // System.out.println("button pressed");

            // depending on the game state...go to one of the following functions...

            // any click assumes it will flip a square!
            buttonmodel.flipSquare(intRep);
            bv.repaint();
            

            //
            
            if (buttonmodel.getGameState() == 3) { //one turn only
                bv.turnOffFlips();
                buttonmodel.aiTurnBeginNotif = true;
                buttonmodel.aitarget = buttonmodel.returnTargetFlip(intRep);
            }
            if (buttonmodel.getGameState()==6) {
                bv.turnOffFlips();
                buttonmodel.finalMove = true;
            }

        }
    };

    ButtonsModel buttonmodel;
    ButtonsView bv;

    ButtonsController(ButtonsModel gm, ButtonsView sv) {

        // use some sort of game state that increments 1, 2, 3, 4, 5, etc. here
        bv = sv;

        buttonmodel = gm;

        resetListeners();

    }

    public void resetListeners() {
        int size = buttonmodel.n;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bv.buttons[i][j].addActionListener(actionListener);
            }
        }
    }

}
