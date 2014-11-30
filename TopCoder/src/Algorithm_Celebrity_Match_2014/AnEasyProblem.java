package Algorithm_Celebrity_Match_2014;

public class AnEasyProblem {
	
	public int solve(long sum) {
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
}
