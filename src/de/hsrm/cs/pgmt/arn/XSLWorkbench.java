package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import java.io.*;
import java.time.LocalTime;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;


@SuppressWarnings("serial")
public class XSLWorkbench extends JFrame {

	private JPanel contentPane;
	private JTextField jtfXslPath;  // eintragung des Pfades der XSL Datei
	JTextArea textArea; //Bearbeitung und anzeige des Inhaltes der XSL Datei
	String xslPath; //
	String xmlFile;
	JTextArea jtaLog;


	/**
	 * Konstruktor des XSLWorkbenchframes, zur bearbeitung und anwendung auf einer XSL Datei auf eine übergebene XML Datei
	 * @param xf - String einer xml Datei
	 * @param jtal - JTextArea für die Logs
	 */
	public XSLWorkbench(String xf, JTextArea jtal) {
		this.xmlFile = xf;
		this.jtaLog = jtal;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton jbCancel = new JButton("Cancel");
		jbCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtaLog.append(LocalTime.now() + ": " +"XSL was closed.\n");
				dispose();
				
			}
		});
		
		/**
		 * Implementierung des Use on XML Buttons. Die XSL Datei in der Pfadangabe wird ausgelesen und auf die geladene XML Datei angewendet.
		 * und wird als eine .html datei gespeichert mit dem Namen der XML Datei. 
		 * XSL Änderungen speichern bevor diese auf XML angewendet werden sollen.
		 */
		
		JButton jbUse = new JButton("Use on XML");
		jbUse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransformerFactory cybertron = TransformerFactory.newInstance();
				
				Transformer optimusPrime;
				try {
					optimusPrime = cybertron.newTransformer(new javax.xml.transform.stream.StreamSource(xslPath));
					optimusPrime.transform(new javax.xml.transform.stream.StreamSource(xmlFile), new javax.xml.transform.stream.StreamResult( new FileOutputStream(xmlFile + ".html") ));
					jtaLog.append(LocalTime.now() + ": " +xslPath +  " XSL Successfully used on " + xmlFile );
				} catch (TransformerConfigurationException e1) {
					jtaLog.append(LocalTime.now() + ": " +e1.getMessage()+"\n");
				} catch (FileNotFoundException e1) {
					jtaLog.append(LocalTime.now() + ": " +e1.getMessage()+"\n");
				} catch (TransformerException e1) {
					jtaLog.append(LocalTime.now() + ": " +e1.getMessage() +"\n");
				}			
			}
		});
		/**
		 * Implementierung zum speichern der XSL Datei. Alte XSL Datei word überschrieben. 
		 */
		JButton jbSave = new JButton("Save XSL");
		jbSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter( new File(xslPath)));
					out.write(textArea.getText());
					out.close();
					jtaLog.append(LocalTime.now() + ": " +xslPath + " successfully saved.\n");
				} catch (IOException e1) {
					jtaLog.append(LocalTime.now() + ": " +e1.getMessage() + "\n");
				}
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(jbSave)
					.addPreferredGap(ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
					.addComponent(jbUse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbCancel))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancel)
						.addComponent(jbSave)
						.addComponent(jbUse)))
		);
		
		JPanel panelTextArea = new JPanel();
		scrollPane.setViewportView(panelTextArea);
		panelTextArea.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		panelTextArea.add(textArea, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		jtfXslPath = new JTextField();
		jtfXslPath.setText("Path of XSL Document");
		panel.add(jtfXslPath);
		jtfXslPath.setColumns(10);
		
		/**
		 * Implementierung des Laden Buttions einer XSL Datei. Der in der JTextField eingegeben Pfad zur xsl datei wird geladen und in der
		 * TextArea angezeigt.
		 */
		
		JButton jbXlsLoad = new JButton("Load XSL");
		jbXlsLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File f = new File( xslPath = jtfXslPath.getText() );
				try {
					@SuppressWarnings("resource")
					BufferedReader in = new BufferedReader( new FileReader(f) );
					String line;
					
					while ( (line = in.readLine()) != null ){
						textArea.append(line + "\n");
					}	
					jtaLog.append(LocalTime.now() + ": " +xslPath + " successfully loaded. \n");
				} catch (FileNotFoundException e1) {
					jtaLog.append(LocalTime.now() + ": " + e1.getMessage() + "\n");
				} catch (IOException e1) {
					jtaLog.append(LocalTime.now() + ": " +e1.getMessage() + "\n");
				}
			}
		});
		panel.add(jbXlsLoad, BorderLayout.EAST);
		contentPane.setLayout(gl_contentPane);
	}
}
