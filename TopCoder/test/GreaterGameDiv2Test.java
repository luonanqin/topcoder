import SRM_637.GreaterGameDiv2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreaterGameDiv2Test {

	@Test(timeout = 2000)
	public void test0() {
		int[] snuke = new int[] { 1, 3 };
		int[] sothe = new int[] { 4, 2 };
		assertEquals(1, new GreaterGameDiv2().calc(snuke, sothe));
	}

	@Test(timeout = 2000)
	public void test1() {
		int[] snuke = new int[] { 1, 3, 5, 7, 9 };
		int[] sothe = new int[] { 2, 4, 6, 8, 10 };
		assertEquals(0, new GreaterGameDiv2().calc(snuke, sothe));
	}

	@Test(timeout = 2000)
	public void test2() {
		int[] snuke = new int[] { 2 };
		int[] sothe = new int[] { 1 };
		assertEquals(1, new GreaterGameDiv2().calc(snuke, sothe));
	}

	@Test(timeout = 2000)
	public void test3() {
		int[] snuke = new int[] { 3, 5, 9, 16, 14, 20, 15, 17, 13, 2 };
		int[] sothe = new int[] { 6, 18, 1, 8, 7, 10, 11, 19, 12, 4 };
		assertEquals(6, new GreaterGameDiv2().calc(snuke, sothe));
	}
}
