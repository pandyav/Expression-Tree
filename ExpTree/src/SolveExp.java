import java.util.EmptyStackException;
import java.util.Stack;

public class SolveExp {
	private String expression;
	private Stack<Double> numbers;
	private Stack<String> operator;

	private static final String Ops = "+-*/";

	public SolveExp(String exp) {

		expression = exp;

	}

	private boolean isOp(String ch) {
		return Ops.indexOf(ch) != -1;
	}

	public double solve() {
		numbers = new Stack<Double>();
		operator = new Stack<String>();

		String[] st = expression.split("\\s+");

		for (int x = 0; x < st.length; x++) {

			
			if (isNum(st[x])) {

				numbers.push(Double.parseDouble(st[x]));
				//
			} else if (isOp(st[x])) {
				if (!operator.isEmpty()) {

					while (CheckPrio(operator.peek()) >= CheckPrio(st[x])) {
						calc();
						if(operator.isEmpty())
							break;
					}
				}

				operator.push(st[x]);

			} else
				throw new IllegalArgumentException("Invalid Character: "
						+ st[x]);

		}

		while (!operator.isEmpty()) {			
				calc();
		}
		
		if(numbers.size()>1)
			throw new EmptyStackException();
		else
			return numbers.pop();

	}

	private int CheckPrio(String ch) {

		switch (ch) {
		case "*":
			return 2;
		case "/":
			return 2;
		case "+":
			return 1;
		case "-":
			return 1;
		}

		return 0;

	}

	private double evalOp(char ch, double left, double right) {
		double result = 0;

		switch (ch) {
		case '+':
			result = left + right;
			break;
		case '-':
			result = left - right;
			break;
		case '/':
			result = left / right;
			break;
		case '*':
			result = left * right;
			break;

		}

		return result;

	}
	
	private static boolean isNum(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	private void calc() {
		double op2 = numbers.pop();
		double op1 = numbers.pop();

		double result = evalOp(operator.pop().charAt(0), op1, op2);

		numbers.push(result);

	}

}
