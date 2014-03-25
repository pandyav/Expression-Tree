import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Scanner;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ExpTreeDriver extends JApplet {
	
	JTextField j = new JTextField("",30);
	
	int x=0;

	JButton submit = new JButton("Submit");
	JButton incLevel = new JButton("Right");
	JButton decLevel = new JButton("Left");
	JLabel lb = new JLabel("Enter Expression");
	
	
	GUIExpressionTree gt = new GUIExpressionTree();
	
	SolveExp se;
	String exp;
	double answer;
	
	Scanner sc = new Scanner(System.in);
	

	public static void main(String[] args) throws IOException{
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Expression Tree");
		JApplet applet = new ExpTreeDriver();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);		
		
		
	}
	
	public void init() {

		JPanel panel2 = new JPanel();

		submit.addActionListener(new clickListener());//
		incLevel.addActionListener(new incListener());
		decLevel.addActionListener(new decListener());
		lb.setForeground(Color.yellow);
		panel2.add(lb);
		panel2.add(j);
		panel2.add(submit);
		panel2.add(incLevel);
		panel2.add(decLevel);
//		
		gt.setBackground(Color.black);
		panel2.setBackground(Color.black);
		getContentPane().add(gt);
		getContentPane().add(panel2, BorderLayout.NORTH);
		

	}
	
	private class clickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String str;
			
			str = j.getText();
			str = str.replaceAll("[()]", "");
			
			se = new SolveExp(str);
			
			
			try
			{
				
				if(str.equals("")||str==null)
					JOptionPane.showMessageDialog(null, "Please Enter Something","Oops!",JOptionPane.INFORMATION_MESSAGE);
				else
				{
					answer = se.solve();				
					gt.setExpression(str,answer);
				}
					
			}catch(IllegalArgumentException I)
			{
				JOptionPane.showMessageDialog(null, I.getMessage(),"Invalid Input",JOptionPane.ERROR_MESSAGE);
			}
			catch(EmptyStackException E)
			{
				JOptionPane.showMessageDialog(null, "Is the expression complete?","Empty Stack",JOptionPane.ERROR_MESSAGE);
			}
			
			
		
		}

	}
	private class incListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
				x++;
				gt.setXCoord(x);
				
			
		}

	}
	private class decListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			x--;
			gt.setXCoord(x);
		}

	}

}
