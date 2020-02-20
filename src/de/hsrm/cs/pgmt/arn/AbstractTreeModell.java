package de.hsrm.cs.pgmt.arn;
/**
 * 
 * @author ArnOld Riemer <arnold.riemer.hsrm@googlemail.com
 * @version 1.0
 * @since 2014-07-31
 */
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public abstract class AbstractTreeModell<T> implements TreeModel{
  T tree;

  public AbstractTreeModell(T tree) {
    super();
    this.tree = tree;
  }
  
  @Override
  public T getRoot() {
    return tree;
  }

  @Override
  public T getChild(Object parent, int index) {
    T t = (T)parent;
    return getChildAtIndex(t,index);    
  }

  abstract public T getChildAtIndex(T parent, int index);

  
  @Override
  public int getChildCount(Object parent) {
    T t = (T)parent;
    return getChildSize(t);    
  }

  abstract public int getChildSize(T t);

  @Override
  public boolean isLeaf(Object node) {
    return getChildCount(node)==0;    
  }

  @Override
  public void valueForPathChanged(TreePath path, Object newValue) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getIndexOfChild(Object parent, Object child) {
    T t = (T)parent;
    T c = (T)child;
    return getChildIndex(t,c);    
  }

  public int getChildIndex(T t, T c){
    for (int i=0; i<getChildCount(t);i++){
      if (c == getChild(t, i)) return i;
    }
    return -1;
  }

  @Override
  public void addTreeModelListener(TreeModelListener l) {}

  @Override
  public void removeTreeModelListener(TreeModelListener l) { }
}
