import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class GUIExpressionTree extends JPanel {
	String expression = "";
	BinaryTree<String> bt;
	Graphics g;
	String inOrder="";
	String test="";

	int xCoord = 0;
	double answer;

	public GUIExpressionTree() {
		setPreferredSize(new Dimension(500, 500));
	}

	public void setExpression(String str, double answer) {
		this.answer=answer;
		expression = str;
		inOrder="";
		repaint();

	}

	public void setXCoord(int x) {
		xCoord = x;
		inOrder="";
		repaint();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		bt = new BinaryTree<String>();
		
		g2.translate(getWidth() / 2, 10);
		Font fn = new Font("Serif", Font.BOLD, 20);
		g2.setColor(Color.YELLOW);
		g2.setFont(fn);	
		g2.drawString("Inorder Traversal: "+inOrder, -320, 8);		
		g2.drawString("Answer: "+answer, -320, 30);

		if(expression!="")
		{
			bt.buildExpTree(expression);
			
			//the inorder traversal of the tree
			for(String s:bt)
				inOrder+=s;
			;
			g2.drawString("Inorder Traversal: "+inOrder, -320, 8);
			inOrder="";
			new draw().drawTree(bt.root, xCoord, 20, g2, 60 * bt.depth);
		}
		

	}

	private class draw<E> extends BinaryTree<E> {

		private void drawTree(Node<E> node, int x, int y,
				Graphics2D g2, int gap) {

			if (node != null) {
				g2.setColor(Color.YELLOW);
				if (node.left != null)
					g2.drawLine(x + 10, y + 20, (x - gap) + 10, y + 110 + 20);
				if (node.right != null)
					g2.drawLine(x + 10, y + 20, (x + gap) + 10, y + 110 + 20);

				Rectangle2D e = new Rectangle2D.Double(x, y, 30, 30);
				
				g2.setColor(Color.red);
				g2.fill(e);
				g2.setColor(Color.yellow);
				g2.draw(e);
				g2.setColor(Color.black);
				
				g2.drawString(node.data.toString(), (x) + 7, y + 20);


				drawTree(node.left, x - gap, y + 110, g2, gap / 2);

				drawTree(node.right, x + gap, y + 110, g2, gap / 2);

			}

		}

	}

}
