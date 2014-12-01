package SRM_639;

/**
 * Created by Luonanqin on 11/30/14.
 */
public class AliceGameEasy {

	public static long findMinimumValue(long x, long y) {
		long min = 0;

		if (x == 0 && y == 0) {
			return min;
		}

		long sum = x + y;
		sum <<= 1;

		long sqrt = (long) Math.sqrt(sum);

		if (sqrt * (sqrt + 1) != sum) {
			return -1;
		}

		if (y == 0) {
			return sqrt;
		}

		while (x > sqrt) {
			x = x - sqrt;
			sqrt--;
			min++;
		}

		if (x != 0) {
			min++;
		}

		return min;
	}

	public static void main(String[] args) {
		System.out.println(findMinimumValue(932599670050L, 67400241741L));
		System.out.println(findMinimumValue(7, 14));
		System.out.println(findMinimumValue(10, 0));
		System.out.println(findMinimumValue(7, 13));
		System.out.println(findMinimumValue(0, 0));
		System.out.println(findMinimumValue(100000, 400500));
	}
}
