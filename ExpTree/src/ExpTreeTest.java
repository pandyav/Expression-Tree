import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

import org.junit.Before;



public class ExpTreeTest {

		SolveExp se;
	//test method for empty stack
		@Test (expected = EmptyStackException.class)
		public void testEmptyStackException1()
		{
			se = new SolveExp("5 +");
			se.solve();
		}
		
		@Test (expected = EmptyStackException.class)
		public void testEmptyStackException2()
		{
			se = new SolveExp("5 + 4 * 3 /");
			se.solve();
		}
		
		@Test (expected = EmptyStackException.class)
		public void testEmptyStackException3()
		{
			se = new SolveExp("* 5 + 2");
			se.solve();
		}
		
		@Test (expected = EmptyStackException.class)
		public void testEmptyStackException4()
		{
			se = new SolveExp("5 * 3 * - 2");
			se.solve();
		}
		
		@Test (expected = EmptyStackException.class)
		public void testEmptyStackException5()
		{
			se = new SolveExp("5 + + 6");
			se.solve();
		}
		
		
		@Test (expected = IllegalArgumentException.class)
		public void testIllegalArgException1()
		{
			se = new SolveExp("5 + 2 * ?");
			
			se.solve();
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void testIllegalArgException2()
		{
			se = new SolveExp("5 + 2 * 6 - a");
			
			se.solve();
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void testIllegalArgException3()
		{
			se = new SolveExp("5+2*3-8");//should have at least one space between
			
			se.solve();
		}
		
		
		@Test
		public void testExpressionEquals() {
			se = new SolveExp("5 - 3 * 2 + 10 / 2 - 4 * 4");
			double x = se.solve();
			assertEquals(-12.0, x,.05);
			
		}
		//all negatives
		@Test
		public void testExpressionEquals2() {
			se = new SolveExp("-5 - -3 * -2 + -10 / -2 - -4 * -4");
			double x = se.solve();
			assertEquals(-22.0, x,.05);
			
		}
		
		//all multiplication
		@Test
		public void testExpressionEquals3() {
			se = new SolveExp("5 * 3 * 2 * -10 * 2 * 4 * -4");
			double x = se.solve();
			assertEquals(9600.0, x,.05);
			
		}
		
		//all division
		@Test
		public void testExpressionEquals4() {
			se = new SolveExp("50 / 30 / 20");
			double x = se.solve();
			assertEquals(.083, x,.05);
			
		}
		

}
