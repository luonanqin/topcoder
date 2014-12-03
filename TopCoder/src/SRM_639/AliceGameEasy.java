package SRM_639;

public class AliceGameEasy {

	public long findMinimumValue(long x, long y) {
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
}
