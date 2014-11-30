import SRM_638.CandleTimerEasy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CandleTimerEasyTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] A = new int[] {0,1};
		int[] B = new int[] {1,2};
		int[] len = new int[] {10,1};
		assertEquals(2, new CandleTimerEasy().differentTime(A, B, len));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] A = new int[] {0,0,0};
		int[] B = new int[] {1,2,3};
		int[] len = new int[] {1,1,1};
		assertEquals(2, new CandleTimerEasy().differentTime(A, B, len));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] A = new int[] {0,0,0};
		int[] B = new int[] {1,2,3};
		int[] len = new int[] {1,2,3};
		assertEquals(4, new CandleTimerEasy().differentTime(A, B, len));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] A = new int[] {0,1,1,2,3,3,2,4};
		int[] B = new int[] {1,2,3,4,5,6,7,8};
		int[] len = new int[] {5,3,2,4,6,8,7,1};
		assertEquals(7, new CandleTimerEasy().differentTime(A, B, len));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] A = new int[] {0,0,0,0};
		int[] B = new int[] {1,2,3,4};
		int[] len = new int[] {123,456,789,1000};
		assertEquals(8, new CandleTimerEasy().differentTime(A, B, len));
	}
	
	@Test(timeout=2000)
	public void test5() {
		int[] A = new int[] {0};
		int[] B = new int[] {1};
		int[] len = new int[] {1000};
		assertEquals(2, new CandleTimerEasy().differentTime(A, B, len));
	}
}
