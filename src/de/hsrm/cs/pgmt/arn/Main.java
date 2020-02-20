package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com>
 * @version 1.0
 * @since 2014-07-31
 */


public class Main {

	public static void main(String[] args) {
		XMLWorkbench w = new XMLWorkbench();
		showInFrame(w);
	}
	
	public static void showInFrame(javax.swing.JPanel pane){
		javax.swing.JFrame f = new javax.swing.JFrame();
		f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		//f.setLayout(null);
		f.add(pane);
		f.setBounds(100, 100, 765, 530);
		f.setVisible(true);
	}

}
