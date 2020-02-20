package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.xml.stream.XMLStreamException;

@SuppressWarnings("serial")
public class SaveXPathDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfSavePath;
	public SaveXPathListener sxpl;

	/**
	 * JDialog zur speicherung des XPATH ausdruckes als XML unter eingegeben Dateinamen.
	 * @param sxpl - SaveXPathListener
	 */
	public SaveXPathDialog(SaveXPathListener sxpl) {
		this.sxpl = sxpl;
		setBounds(100, 100, 450, 108);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			jtfSavePath = new JTextField();
			jtfSavePath.setText("dateixpath.xml");
			jtfSavePath.setBounds(6, 10, 438, 28);
			contentPanel.add(jtfSavePath);
			jtfSavePath.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
							try {
								DomTreeView.writeAsXML(jtfSavePath.getText(), sxpl.xmlw.xpn);
								sxpl.jtaLog.append(LocalTime.now() + ": " +sxpl.xmlw.xpn + " wurde unter " + jtfSavePath.getText() + " gespeichert. \n");
							} catch (IOException e1) {
								sxpl.jtaLog.append(LocalTime.now() + ": " +e1.getMessage() + "\n");
							} catch (XMLStreamException e1) {
								sxpl.jtaLog.append(LocalTime.now() + ": " +e1.getMessage()+ "\n");
							}	
						dispose();
						
					}
				});
				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						sxpl.jtaLog.append(LocalTime.now() + ": " +"Speichervorgang wurde abgebrochen!" + "\n");
						dispose();
						
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	


}
