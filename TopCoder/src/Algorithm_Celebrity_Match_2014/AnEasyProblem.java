package Algorithm_Celebrity_Match_2014;

/**
 * Division I Level One
 *
 * Problem Statement
 *
 * NOTE: This problem statement contains subscripts/superscripts that may not display properly if viewed outside of the applet.
 * 
 * For positive integers h and r (1 <= r <= h) we define sequence Fh,r as {1, 2, 3, ..., h-1, h, h-1, h-2, ..., r+1, r}. Let S(Fh,r) be the the sum of all
 * numbers in Fh,r and N(Fh,r) - the length of Fh,r.
 * 
 * For example, F3,2 is {1, 2, 3, 2}, S(F3,2) = 1 + 2 + 3 + 2 = 8, and N(F3,2) = 4. F5,5 is {1, 2, 3, 4, 5}, S(F5,5) = 15, and N(F5,5) = 5.
 * 
 * You are given a long sum. Return minimal possible N(Fi,j) such that S(Fi,j) = sum. If there is no such sequence Fi,j, return -1 instead.
 * 
 * Definition
 * 
 * Class: AnEasyProblem Method: solve Parameters: long Returns: int Method signature: int solve(long sum) (be sure your method is public)
 *
 * Constraints - sum will be between 1 and 1,000,000,000,000(10^12).
 * 
 * Examples
 *
 * 0)
 *
 * 6
 * 
 * Returns: 3
 * 
 * When h = r = 3, S(Fh,r) = 6 and N(Fh,r) = 3. There is no other h,r satisfing S(Fh,r) = 6. So you should return 3.
 *
 * 1)
 *
 * 7
 * 
 * Returns: -1
 * 
 * 2)
 *
 * 100
 * 
 * Returns: 15
 * 
 * 3)
 *
 * 1000000000000
 * 
 * Returns: 1428571
 *
 * Created by Luonanqin on 11/21/14.
 */
public class AnEasyProblem {

	public static int solve(long sum) {
		long tempSum = 0;
		long i = 1;
		for (; tempSum < sum; i++) {
			tempSum += i;
		}

		i--;

		// 加和等于输入参数，则i就是最小的N(Fr,i);
		if (tempSum == sum) {
			return (int) i;
		}

		long afterH = i - 2;
		long hToR = 0; // 用于缓存已经加和过得h-2 ~ r，以避免多次循环
		while (true) {
			long previousSum = (tempSum -= i);
			i--;
			previousSum += hToR;
			while (afterH >= 1) {
				previousSum += afterH;
				hToR += afterH;
				afterH--;
				if (!(previousSum < sum)) {
					break;
				}
			}
			if (previousSum == sum) {
				return (int) (i + (i - afterH - 1));
			}
			// 如果r已经等于1，但是总和还是小于入参，则表明不可能再有符合的序列了，遂退出
			if (previousSum < sum) {
				return -1;
			}
			// 每个h测试过后，缓存要清理h-1，避免重复加和，因为下一次测试是从h-1开始
			hToR = hToR - (i - 1);
		}
	}

	public static void main(String[] args) {
		long num1 = 6;
		System.out.println("Num: " + num1);
		System.out.println(solve(num1));
		long num2 = 7;
		System.out.println("Num: " + num2);
		System.out.println(solve(num2));
		long num3 = 100;
		System.out.println("Num: " + num3);
		System.out.println(solve(num3));
		long num4 = 1000000000000L;
		System.out.println("Num: " + num4);
		System.out.println(solve(num4));
	}
}
