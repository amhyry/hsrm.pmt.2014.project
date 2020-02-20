package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import org.w3c.dom.Node;

public class DomTreeModell extends AbstractTreeModell<Node> {

	public DomTreeModell(Node tree) {
		super(tree);
	}

	@Override
	public Node getChildAtIndex(Node parent, int index) {
		return parent.getChildNodes().item(index);	
	}

	@Override
	public int getChildSize(Node parent) {
		return parent.getChildNodes().getLength();
	}
}
