package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class SaveXPathListener implements ActionListener {

	JTextArea jtaLog;
	XMLWorkbench xmlw;
	String log;
	
	/**
	 * Save Button Listener für XPath ausdruck.
	 * @param jtaLog - JTextArea
	 * @param xmlw - XMLWorkbench
	 */
	public SaveXPathListener(JTextArea jtaLog, XMLWorkbench xmlw) {
		this.jtaLog = jtaLog;
		this.xmlw = xmlw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog jd = new SaveXPathDialog(this);
		jd.setVisible(true);
		log = LocalTime.now() + ": " +xmlw.xpn + " soll gespeichert werden? \n";
		jtaLog.append(log);

	}

}
