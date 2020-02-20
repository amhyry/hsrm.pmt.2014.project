package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
/**
 * XMLViewer, Simple JSplitPane for displaying XML Trees.
 * @author Arni
 * 
 */
@SuppressWarnings("serial")
public class XMLViewer extends JSplitPane {

	JFrame f;
	
	
	public XMLViewer(){
		this(new JTree());
	}
	
	
	/**
	 * Constructor of JSlpitPane with a Horizontal Split, where left is the shown DomTreeView and Right an JTextArea, where text content of given Nodes is shown.
	 * @param left - DomTreeView
	 */
	public XMLViewer(JTree left){
		this(left, new JTextArea(""));
	}
	
	/**
	 * Constructor of JSplitpane with an Horizontal Split, where left is the Shown DomTreeView and Right the JTextArea, where text content of given Nodes is shown.
	 * @param left - DomTreeView
	 * @param right - JTextArea
	 */
	public XMLViewer(JTree left, JTextArea right) {
		super(JSplitPane.HORIZONTAL_SPLIT, left, right);	
		//java.awt.Dimension minSize = new java.awt.Dimension(200,200);
		//left.setMinimumSize(minSize);
		//right.setMinimumSize(minSize);
		setResizeWeight(0.25);
		setOneTouchExpandable(true);
		left.addTreeExpansionListener(new javax.swing.event.TreeExpansionListener(){

			/*
			 * change framesize if tree is expanded
			 * @see javax.swing.event.TreeExpansionListener#treeExpanded(javax.swing.event.TreeExpansionEvent)
			 */
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				//resetToPreferredSizes();
				//f.pack();
			}
			/*
			 * change framesize if tree is collapsed
			 * @see javax.swing.event.TreeExpansionListener#treeExpanded(javax.swing.event.TreeExpansionEvent)
			 */
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				//resetToPreferredSizes();
				//f.pack();
			}
		});
		/*
		 * If Node selected in Tree, and selected Node is an Textnode, show Text in right Splitpanel/textarea
		 */
		left.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				//TODO 
				TreePath  tp = e.getPath();
				Object[] elements = tp.getPath();
				
				for (Object object : elements) {
					if(object instanceof Node) {
						Node no = (Node)object;
						//String nol = (no.getFirstChild() instanceof Text)?no.getFirstChild().getTextContent():""; 
						String nol = "";
						if(no.getFirstChild() instanceof Text) {
							org.w3c.dom.NodeList nl = no.getChildNodes();
							for(int i = 0; i<nl.getLength() ; i++ ){
								nol = nol + nl.item(i).getTextContent();
							}
						}
						//String nol = (no instanceof Text)?no.getTextContent():"";
						right.setText(nol.trim());
						//resetToPreferredSizes();
						//f.pack();
					}	
				}	
			}
		});
		System.out.println("XMLViewer created.");
	}
	
	
	/**
	 * Creates a Window out of given Object of this Class
	 */
	public void showInFrame(){
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		
		f.pack();
		f.setVisible(true);
	}

	
	public static void main(String[] args) {
		DomTreeView dtv = null;
		JTextArea jta = new JTextArea("");
		try {
			dtv = new DomTreeView(DomTreeView.readFromXML(new java.io.File("datei.xml")));

			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
			//return;
		}
		
		XMLViewer xv = new XMLViewer(dtv,jta);	
		xv.showInFrame();	
	}	
}
