package SRM_638;

/**
 * Division II Level Two
 *
 * Problem Statement
 *
 * There is a narrow passage. Inside the passage there are some wolves. You are given a int[] size that contains the sizes of those wolves, from left to right.
 *
 * 
 * The passage is so narrow that some pairs of wolves cannot pass by each other. More precisely, two adjacent wolves may swap places if and only if the sum of
 * their sizes is maxSizeSum or less. Assuming that no wolves leave the passage, what is the number of different permutations of wolves in the passage? Note
 * that two wolves are considered different even if they have the same size.
 * 
 *
 * Compute and return the number of permutations of wolves that can be obtained from their initial order by swapping a pair of wolves zero or more times.
 * 
 * Definition
 * 
 * Class: NarrowPassage2Easy Method: count Parameters: int[], int Returns: int Method signature: int count(int[] size, int maxSizeSum) (be sure your method is
 * public)
 *
 * Constraints - size will contain between 1 and 6 elements, inclusive. - Each element in size will be between 1 and 1,000, inclusive. - maxSizeSum will be
 * between 1 and 1,000, inclusive.
 * 
 * Examples
 *
 * 0)
 *
 * {1, 2, 3}
 * 
 * 3
 * 
 * Returns: 2
 * 
 * From {1, 2, 3}, you can swap 1 and 2 to get {2, 1, 3}. But you can't get other permutations.
 *
 * 1)
 *
 * {1, 2, 3}
 * 
 * 1000
 * 
 * Returns: 6
 * 
 * Here you can swap any two adjacent wolves. Thus, all 3! = 6 permutations are possible.
 *
 * 2)
 *
 * {1, 2, 3}
 * 
 * 4
 * 
 * Returns: 3
 * 
 * You can get {1, 2, 3}, {2, 1, 3} and {2, 3, 1}.
 *
 * 3)
 *
 * {1,1,1,1,1,1}
 * 
 * 2
 * 
 * Returns: 720
 * 
 * All of these wolves are different, even though their sizes are the same. Thus, there are 6! different permutations possible.
 *
 * 4)
 *
 * {2,4,6,1,3,5}
 * 
 * 8
 * 
 * Returns: 60
 * 
 * 5)
 * 
 * {1000}
 * 
 * 1000
 * 
 * Returns: 1
 * 
 * Created by Luonanqin on 11/5/14.
 */
public class NarrowPassage2Easy {

	public static int count(int[] array, int maxSizeSum) {
		int result = 1;

		// 从最小的元素开始查找最多有几个空位可放，然后查找次小，直到最大的元素。每个元素可放的位置数相乘即为结果
		for (int n = 0; n < array.length; n++) {
			int min = Integer.MAX_VALUE;
			int index = 0;
			// 求出最小元素，然后把对应的位置设为-1，以方便之后相加判断是否超过maxSizeSum时跳过
			for (int i = 0; i < array.length; i++) {
				if (array[i] > -1 && min > array[i]) {
					min = array[i];
					index = i;
				}
			}
			array[index] = -1;

			// 初始时，每个元素可以放在自己的位置上，即有一个空位可放
			int times = 1;
			// 从左到右遍历元素，直到和该元素相加大于maxSizeSum即停止。表示最多有几个空位可放
			for (int leftToRight = index; leftToRight < array.length; leftToRight++) {
				if (array[leftToRight] != -1) {
					if (min + array[leftToRight] > maxSizeSum) {
						break;
					}
					times++;
				}
			}
			// 从右到左遍历元素，直到和该元素相加大于maxSizeSum即停止。表示最多有几个空位可放
			for (int rightToLeft = index; rightToLeft >= 0; rightToLeft--) {
				if (array[rightToLeft] != -1) {
					if (min + array[rightToLeft] > maxSizeSum) {
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

	public static void main(String[] args) {
		int[] array1 = { 1, 2, 3 };
		System.out.println("\"{1, 2, 3} 3\"");
		System.out.println(count(array1, 3) + "\n");

		int[] array2 = { 1, 2, 3 };
		System.out.println("\"{1, 2, 3} 1000\"");
		System.out.println(count(array2, 1000) + "\n");

		int[] array3 = { 1, 2, 3 };
		System.out.println("\"{1, 2, 3} 4\"");
		System.out.println(count(array3, 4) + "\n");

		int[] array4 = { 2, 4, 6, 1, 3, 5 };
		System.out.println("\"{2,4,6,1,3,5} 8\"");
		System.out.println(count(array4, 8) + "\n");

		int[] array5 = { 1, 1, 1, 1, 1, 1 };
		System.out.println("\"{1,1,1,1,1,1} 2\"");
		System.out.println(count(array5, 2) + "\n");

		int[] array6 = { 1000 };
		System.out.println("\"{1000} 1000\"");
		System.out.println(count(array6, 1000) + "\n");

	}
}
