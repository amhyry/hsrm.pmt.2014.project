package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


@SuppressWarnings("serial")
public class DomTreeView extends JTree {
	
	DomTreeView(Node n) {
		super(new DomTreeModell(n));
		setCellRenderer( (jt, node, selected, expanded, leaf, row, hasFocus) -> 
						{ 
							JLabel jl = new JLabel();
							if (node instanceof Node){
								Node nd = (Node)node;
								if(nd instanceof Element && !(((Element)node).getAttribute("titel").equals(""))){
									jl.setText(((Element)node).getAttribute("titel"));
									//if(selected) jl.setForeground(java.awt.Color.RED);
									return jl;	
								}
								
								
								String l = (nd instanceof Text)?"":nd.getNodeName();
								return new JLabel(l);
							}
							return new JLabel();
						}
						);
	}

	public static void writeAsXML(String s, Node n) throws IOException, XMLStreamException {
		java.io.File file = new java.io.File(s);
		
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(file));
		
		writeAsXML(writer,  n);
	}
	
	public static void writeAsXML(XMLStreamWriter out, Node n) throws IOException, XMLStreamException {
		if (n == null) {
			out.writeStartDocument();
			out.writeEmptyElement("empty");
			out.writeEndDocument();
			return;
		}
		out.writeStartDocument();
		elementsWriteAsXML(out, n);
		out.writeEndDocument();
		out.close();
	}
	
	private static void elementsWriteAsXML(XMLStreamWriter out, Node n)
			throws IOException, XMLStreamException {
		
		if(n instanceof Element){
			out.writeStartElement(n.getNodeName());
		}
		if(n instanceof Text){
			
			out.writeEmptyElement(n.getNodeValue());
		}
		//(if(n.hasAttributes()) out.writeAttribute(n.getLocalName(), n.getAttributes());
		
		if(n.hasChildNodes()){
			NodeList nl = n.getChildNodes();
			for(int i = 0; i < nl.getLength(); i++){
				elementsWriteAsXML(out, nl.item(i));
			}
		}
		if(n instanceof Element){
			out.writeEndElement();
		}
	}
	
	
	public static Node readFromXML(java.io.File xmlFile) throws FactoryConfigurationError, ParserConfigurationException, IOException, SAXException, IllegalArgumentException  {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		Document doc = parser.parse(xmlFile);
		Element root = doc.getDocumentElement();
		return root;
	}
}
