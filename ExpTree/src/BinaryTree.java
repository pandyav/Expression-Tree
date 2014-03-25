import java.util.*;

public class BinaryTree<E> implements Iterable<E> {

	
	protected static class Node<E> {
		// Data Fields

	
		public E data;
		
		public Node<E> left;
		
		public Node<E> right;
		
		 
		public Node(E data) {
			this.data = data;
			left = null;
			right = null;
		}

		
		@Override
		public String toString() {
			return data.toString();
		}
	}

	
	protected Node<E> root;
	protected int depth=1;

	
	public BinaryTree() {
		root = null;
	}

	public final String Ops = "+-*/";

	private boolean isOp(String ch) {
		return Ops.indexOf(ch) != -1;
	}

	

	public void buildExpTree(String exp) {
		root = buildExpTree(exp, root,depth);
		
	}

	private Node<E> buildExpTree(String exp, Node<E> node, int depth) {
		String op = SearchLowestPrec(exp);

		if (isOp(op)) {
			node = new Node<E>((E) op);

			String[] lr = exp.split("\\" + op, 2);
			String left = lr[0];
			String right = lr[1];

			node.left = buildExpTree(left, node.left,depth+1);

			node.right = buildExpTree(right, node.right,depth+1);
			
			return node;

		} else
		{
			if(this.depth<depth)
				this.depth=depth;			
			return new Node<E>((E) op);
			
		}

	}

	private String SearchLowestPrec(String exp) {
		String[] exp2 = exp.split("\\s+");

		for (String s : exp2) {
			if (s.equals("-") || s.equals("+"))
				return s;
		}

		for (String s : exp2) {
			if (s.equals("*") || s.equals("/"))
				return s;
			
		}
				
		return exp;		
		
	}

	@Override
	public Iterator<E> iterator() {

		return new BinaryTreeIterator();
	}

	private class BinaryTreeIterator implements Iterator<E> {

		Stack<Node<E>> st;
		Node<E> current;
		Node<E> InitCurrent;

		public BinaryTreeIterator() {
			st = new Stack<Node<E>>();
			current = root;

			while (current.left != null) {
				st.push(current);
				current = current.left;
			}
		}

		@Override
		public boolean hasNext() {

			return current != null;
		}
		
		//the next method follows the inorder traversal algorithm
		@Override
		public E next() {

			if (!hasNext())
				throw new NoSuchElementException();

			InitCurrent = current;

			if (current.right != null) {
				st.push(current);
				current = current.right;

				while (current.left != null) {
					st.push(current);
					current = current.left;
				}

			} else {
				while (!st.isEmpty() && (st.peek().right == current)) {
					current = st.pop();
				}

				if (st.isEmpty())
					current = null;
				else
					current = st.pop();
			}

			return InitCurrent.data;

		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"The remove() is not supported by this operation");
		}

	}

}
