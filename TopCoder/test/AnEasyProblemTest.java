import Algorithm_Celebrity_Match_2014.AnEasyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnEasyProblemTest {
	
	@Test(timeout=2000)
	public void test0() {
		long sum = 6L;
		assertEquals(3, new AnEasyProblem().solve(sum));
	}
	
	@Test(timeout=2000)
	public void test1() {
		long sum = 7L;
		assertEquals(-1, new AnEasyProblem().solve(sum));
	}
	
	@Test(timeout=2000)
	public void test2() {
		long sum = 100L;
		assertEquals(15, new AnEasyProblem().solve(sum));
	}
	
	@Test(timeout=2000)
	public void test3() {
		long sum = 1000000000000L;
		assertEquals(1428571, new AnEasyProblem().solve(sum));
	}
}
