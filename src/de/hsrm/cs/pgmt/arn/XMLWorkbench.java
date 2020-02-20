package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.w3c.dom.Node;

@SuppressWarnings("serial")
public class XMLWorkbench extends JPanel {
	private JTextField jtfXmlFile;
	private JTextField jtfXPath;
	private JTextArea jtaLog;
	private XMLViewer view;
	public Node xpn;
	
	/**
	 * XMLWorkbench JPanel Constructor  
	 */
	public XMLWorkbench() {
		setLayout(null);

		jtfXmlFile = new JTextField();
		jtfXmlFile.setText("Insert XML Filepath here");
		jtfXmlFile.setBounds(6, 47, 756, 28);
		add(jtfXmlFile);
		jtfXmlFile.setColumns(10);
		
		jtfXPath = new JTextField();
		jtfXPath.setText("Insert XPath Expression here");
		jtfXPath.setBounds(6, 87, 756, 28);
		add(jtfXPath);
		jtfXPath.setColumns(10);
		
		JScrollPane spView = new JScrollPane();
		spView.setBounds(6, 127, 756, 281);
		add(spView);
		
		view = new XMLViewer();
		spView.setViewportView(view);
		
		JScrollPane spLog = new JScrollPane();
		spLog.setBounds(6, 415, 756, 90);
		add(spLog);
		
		jtaLog = new JTextArea();
		jtaLog.setEditable(false);
		spLog.setViewportView(jtaLog);
		
		JButton jbLoad = new JButton("Load");
		jbLoad.setBounds(645, 6, 117, 29);
		jbLoad.addActionListener(new LoadXPathListener(jtfXmlFile, jtfXPath, jtaLog, spView, this));
		add(jbLoad);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(516, 6, 117, 29);
		btnSave.addActionListener(new SaveXPathListener(jtaLog, this));
		add(btnSave);
		
		JButton btnXls = new JButton("XLS");
		btnXls.setBounds(387, 6, 117, 29);
		btnXls.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				XSLWorkbench xslw = new XSLWorkbench(jtfXmlFile.getText(), jtaLog);
				xslw.setVisible(true);
				
			}
		});
		add(btnXls);
	}
}
