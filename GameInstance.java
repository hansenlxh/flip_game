import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameInstance {

	private static ButtonsView bv;
	private static ButtonsModel bm;
	private static ButtonsController bc;
	private static MenuView sv;
	private static MenuController sc;

	JFrame f;
	
	public static void main(String[] args) {	
		new GameInstance();
	}
	
	GameInstance()
	{
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Impossible Chessboard Puzzle Variant :)");
		f.setBackground(Color.white);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		bm = new ButtonsModel();
		bv = new ButtonsView(bm);
		bc = new ButtonsController(bm, bv);
		

		mainPanel.add(bv, BorderLayout.CENTER);
		//bv.setBorder(BorderFactory.createLineBorder(Color.green));

		sv = new MenuView(bm);
		sc = new MenuController(sv, bv, bm, bc);
	    sv.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(sv, BorderLayout.SOUTH);
		
		f.getContentPane().add(mainPanel);
		f.pack();
		//f.setLocationRelativeTo(null);
		
		
		f.setVisible(true);

//		ViewCanvas viewcanvas = new ViewCanvas(1000,600, HansenMapper);
//		ControlPanel HansenControlPanel = new ControlPanel(viewcanvas);
//
//		
//
//		myJFrame = new MainFrame("Hansen Map Graphics", viewcanvas, HansenControlPanel);
	}

}
