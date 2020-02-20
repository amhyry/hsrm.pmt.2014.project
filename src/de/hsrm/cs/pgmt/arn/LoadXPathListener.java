package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class LoadXPathListener implements ActionListener {

	JTextField filePath;
	JTextField jtfXPath;
	JTextArea jtaLog;
	javax.swing.JScrollPane view;
	XMLWorkbench xmlw;
	

	/**
	 * Konstruktor für den Load Button Action Listener eines XPATH ausdruckes
	 * @param filePath - JTextField Dateipfad einer XML Datei
	 * @param jtfXPath - JTextField XPATH Ausdruck
	 * @param jtaLog - JTextArea für die Logs
	 * @param view - JScrollPanel des XMLViewer für die Anzeige der XML Datei
	 * @param xmlw - XMLWorkbench JPanel
	 */
	public LoadXPathListener(JTextField filePath, JTextField jtfXPath, JTextArea jtaLog, javax.swing.JScrollPane view, XMLWorkbench xmlw) {
		// TODO Auto-generated method stub
		this.filePath = filePath;
		this.jtfXPath = jtfXPath;
		this.jtaLog = jtaLog;
		this.view = view;
		this.xmlw = xmlw;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		XPath xp = XPathFactory.newInstance().newXPath();
		
		Node xpn = null;
		try {
			Node n = DomTreeView.readFromXML(new java.io.File(filePath.getText()));
			xpn = (Node) xp.compile(jtfXPath.getText()).evaluate(n,XPathConstants.NODE);
			
			System.out.println("Root: " + n);
			System.out.println("Compiled: " + xpn);
			jtaLog.append(LocalTime.now() + ": " +filePath.getText() + " with XPath-Expression: " + jtfXPath.getText() + " is successfully loaded. \n");
			view.repaint();
			view.setViewportView(new XMLViewer(new DomTreeView(xpn)));
		} catch (NullPointerException e1) {
			String errMessage = LocalTime.now() + ": " +"FilePath is Null" + e1.getMessage();
			System.out.println(errMessage);
			jtaLog.append(errMessage);
		} catch (IOException e1) {
			String errMessage = LocalTime.now() + ": " +"" + e1.getMessage()+ "\n";
			System.out.println(errMessage);
			jtaLog.append(errMessage);
		} catch (FactoryConfigurationError e1) {
			String errMessage = LocalTime.now() + ": " +"" + e1.getMessage()+ "\n";
			System.out.println(errMessage);
			jtaLog.append(errMessage);
		} catch (ParserConfigurationException e1) {
			String errMessage = LocalTime.now() + ": " +"" + e1.getMessage()+ "\n";
			System.out.println(errMessage);
			jtaLog.append(errMessage);
		} catch (SAXException e1) {
			String errMessage = LocalTime.now() + ": " +"" + e1.getMessage()+ "\n";
			System.out.println(errMessage);
			jtaLog.append(errMessage);
		} catch (IllegalArgumentException e1) {
			String errMessage = LocalTime.now() + ": " +"" + e1.getMessage()+ "\n";
			System.out.println(errMessage);
			jtaLog.append(errMessage);
		} catch (XPathExpressionException e1) {
			String errMessage = LocalTime.now() + ": " +"XPath Expression is defect: " + e1.getMessage() + "\n";
			System.out.println(errMessage);
			jtaLog.append(errMessage);
		} 
		xmlw.xpn = xpn;
		System.out.println(xmlw.xpn);	
	}
}
