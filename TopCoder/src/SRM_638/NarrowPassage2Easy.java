package SRM_638;

public class NarrowPassage2Easy {
	
	public int count(int[] size, int maxSizeSum) {
		int result = 1;

		// 从最小的元素开始查找最多有几个空位可放，然后查找次小，直到最大的元素。每个元素可放的位置数相乘即为结果
		for (int n = 0; n < size.length; n++) {
			int min = Integer.MAX_VALUE;
			int index = 0;
			// 求出最小元素，然后把对应的位置设为-1，以方便之后相加判断是否超过maxSizeSum时跳过
			for (int i = 0; i < size.length; i++) {
				if (size[i] > -1 && min > size[i]) {
					min = size[i];
					index = i;
				}
			}
			size[index] = -1;

			// 初始时，每个元素可以放在自己的位置上，即有一个空位可放
			int times = 1;
			// 从左到右遍历元素，直到和该元素相加大于maxSizeSum即停止。表示最多有几个空位可放
			for (int leftToRight = index; leftToRight < size.length; leftToRight++) {
				if (size[leftToRight] != -1) {
					if (min + size[leftToRight] > maxSizeSum) {
						break;
					}
					times++;
				}
			}
			// 从右到左遍历元素，直到和该元素相加大于maxSizeSum即停止。表示最多有几个空位可放
			for (int rightToLeft = index; rightToLeft >= 0; rightToLeft--) {
				if (size[rightToLeft] != -1) {
					if (min + size[rightToLeft] > maxSizeSum) {
						break;
					}
					times++;
				}
			}
			// 该元素可放的位置总数与上一次的元素放置之后的排列数相乘，最后可得到结果
			result *= times;
		}

		return result;
	}
}
