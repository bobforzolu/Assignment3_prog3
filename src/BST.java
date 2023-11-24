import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;
import java.util.Iterator;


import org.w3c.dom.Node;

public class BST<T extends Comparable<T>> implements Iterable<T> {
	private Comparator<T> comparator;
	class BSTNode implements Comparable<BSTNode> {
		private T data;
		private BSTNode left;
		private BSTNode right;

		public BSTNode(T d) {
			setLeft(null);
			setRight(null);
			setData(d);
		}
		public T getData() { return data; }
		public void setData(T d) { data = d; }
		public void setLeft(BSTNode l) { left = l; }
		public void setRight(BSTNode r) { right = r; }
		public BSTNode getLeft() { return left; }
		public BSTNode getRight() { return right; }
		public boolean isLeaf() {
			return (getLeft() == null) && (getRight() == null); }
		public int compareTo(BSTNode o) {
			return this.getData().compareTo(o.getData());
		}
	}
	

	private BSTNode root;
	private int size;

	public static final int INORDER=0;
	public static final int PREORDER=1;
	public static final int POSTORDER=2;
	public static final int LEVELORDER=3;
	public static final int REVINORDER=4;
	public BST() {
		root = null;
		size = 0;
	}
	public BST(	 Comparator<T> comparator) {
		size = 0;
		root=null;
		this.comparator = comparator;
	}

	/**
	 * Return the number of nodes in the tree.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return true if element d is present in the tree.
	 */
	public T find(T d) {
		return find(d, root);
	}

	/**
	 * Add element d to the tree.
	 */
	public void add(T d) {
		BSTNode n = new BSTNode(d);
		if (root == null) {
			size++;
			root = n;
		} else {
			add(root, n);
		}
	}

	/**
	 * Return the height of the tree.
	 */
	public int height() {
		return height(root);
	}

	public void printInOrder() {
		inOrderTraversal(root);
	}
	
	public void printPreOrder() {
		preOrderTraversal(root);
	}
	
	public void printPostOrder() {
		postOrderTraversal(root);
	}
	
	public void printLevelOrder() {
		levelOrderTraversal(root);
	}
	
	
	
	// Private methods.

	private T find(T d, BSTNode r) {
		if (r == null)
			return null;
		int c = d.compareTo(r.getData());
		if (c == 0)
			return r.getData();
		else if (c < 0)
			return find(d, r.getLeft());
		else
			return find(d, r.getRight());
	}


	private void add(BSTNode r, BSTNode n) {
		int c=0;
		if(comparator !=null) {
        	
        	
        	
        }
        else {
        	
        	 c = n.compareTo(r);
        }
        if (c < 0) {
            if(r.getLeft() == null) {
                r.setLeft(n);
                size++;

            }
            else
                add(r.getLeft(), n);
        }
        if(c > 0) {
            if(r.getRight() == null) {
                r.setRight(n);
                size++;
            }
            else
                add(r.getRight(), n);
        }
    }
	public void delete(T d) {
		root = delete(root, d);
	}
	
	private BSTNode deleteNode(BSTNode r) {
		if(isLeaf()) {
			return BSTNode;
		}else if(isLefty(r)) {
			return r.getLeft();
		}else if(isRighty(r)) {
			return r.getRight()
		}else {
			
		}
		
	}
	
	private  T findMin(BSTNode r) {
		T min = null;
		if(r != null) {
			if(r.getLeft() == null) {
				min = r.getData();
				
			}else {
				min = findMin(r.getLeft());
			}
			return min;
		}
	}
	
	/* Implement a height method. */
	private int height(BSTNode r) {
		if(r == null){
			return 0;
		}

		int leftHeight = height(r.getLeft());
		int rightHeight = height(r.getRight());
	
		return Math.max(leftHeight, rightHeight) + 1;

		
	}

	private Queue<T> queue = new LinkedList<T>();
	private void visit(BSTNode r) {
		if (r != null)
			queue.add(r.getData());
	}


	
	
	private void inOrderTraversal(BSTNode r) {
		if (r == null)
			return;
		else {
			inOrderTraversal(r.getLeft());
			visit(r);
			inOrderTraversal(r.getRight());

		}
	}
	
	private void preOrderTraversal(BSTNode r) {
		// TODO:
		if(r != null){
			visit(r);
			preOrderTraversal(r.getLeft());

			preOrderTraversal(r.getRight());
		}else{
			return;
		}

	}
	
	private void postOrderTraversal(BSTNode r) {
		// TODO:
		if(r != null){
			postOrderTraversal(r.getLeft());
			postOrderTraversal(r.getRight());

			visit(r);
		}else{
			return;
		}
	}
	
	private void levelOrderTraversal(BSTNode r) {
		// TODO:
		Queue<BSTNode> q = new LinkedList<>();

		if(r != null){
			q.add(r);
		}
		while (!q.isEmpty()) {
			// get front element
			// visti the this element
			// add children of this element
			BSTNode curr = q.remove();
			visit(curr);
			
			if (curr.getLeft() != null) {
				q.add(curr.getLeft());
			}
	
			if (curr.getRight() != null) {
				q.add(curr.getRight());
			}
		

		}
	}
	public void traverse(int travType) {
		traverse(root, travType);
	}
	private void traverse(BSTNode r, int travType) {
		if(r==null)
			return;
		else {
			switch(travType) {
			case INORDER:
				traverse(r.getLeft(), travType);
				visit(r);
				traverse(r.getRight(), travType);
				break;
			case PREORDER:
				visit(r);
				traverse(r.getLeft(), travType);
				traverse(r.getRight(), travType);
				break;
			case POSTORDER:
				traverse(r.getLeft(), travType);
				traverse(r.getRight(), travType);
				visit(r);
				break;

				
			}
		}
	}
	private class BSTIteratorInOrder implements Iterator{

		public  BSTIteratorInOrder() {
			queue.clear();
			traverse(root,INORDER);
		}
		@Override
		public boolean hasNext() {
			
			return !queue.isEmpty();
		}

		@Override
		public Object next() {
			
			return queue.remove();
		}
		
	}
	

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new BSTIteratorInOrder();
	}
}
