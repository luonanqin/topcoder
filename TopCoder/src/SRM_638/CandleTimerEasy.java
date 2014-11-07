package SRM_638;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Division II Level Three
 *
 * Problem Statement
 *
 * You have a lot of candles. The candles burn at a uniform rate: if you ignite a candle of length L, it will burn completely in L units of time. You can also
 * ignite a candle at both ends, which makes it burn twice as fast.
 *
 * You have arranged some candles into the shape of a tree. You want to use the tree to measure time. At the beginning, you will ingite some leaves of the tree
 * (all at the same time). Then you will just wait and watch the flames spread across the entire tree. (Whenever a flame reaches an inner node of the tree, it
 * spreads to all branches that meet at that node.) Note that you are not allowed to light new flames during the process. The time you will measure is the time
 * between the moment when you lighted the fire(s) and the moment when the last part of the tree finished burning.
 *
 * You are given a description of the tree as three int[]s: a, b, and len, with N elements each. The nodes of the tree are numbered 0 through N, inclusive. For
 * each valid i, there is a candle between the nodes a[i] and b[i] with length len[i].
 * 
 * Compute and return the number of different times you can measure when following the above procedure.
 * 
 * Definition
 * 
 * Class: CandleTimerEasy Method: differentTime Parameters: int[], int[], int[] Returns: int Method signature: int differentTime(int[] A, int[] B, int[] len)
 * (be sure your method is public)
 *
 * Constraints - A will contain between 1 and 19 elements, inclusive. - A, B and len will contain same number of elements. - Each element in A will be between 0
 * and |A|, inclusive. - Each element in B will be between 0 and |A|, inclusive. - Each element in len will be between 1 and 1000, inclusive. - A, B and len
 * will describe a tree.
 * 
 * Examples
 *
 * 0)
 *
 * {0,1}
 * 
 * {1,2}
 * 
 * {10,1}
 * 
 * Returns: 2
 * 
 * This tree looks the same as a single candle of length 11. If we light it on one end, we will measure the time 11. If we light it on both ends, we will
 * measure the time 5.5.
 *
 * 1)
 *
 * {0,0,0}
 * 
 * {1,2,3}
 * 
 * {1,1,1}
 * 
 * Returns: 2
 * 
 * This time we have 3 ends. If we ignite all of them the time is 1, otherwise the time is 2.
 *
 * 2)
 *
 * {0,0,0}
 * 
 * {1,2,3}
 * 
 * {1,2,3}
 * 
 * Returns: 4
 * 
 * We can get 4 different outcomes: 2.5, 3, 4, 5.
 *
 * 3)
 *
 * {0,1,1,2,3,3,2,4}
 * 
 * {1,2,3,4,5,6,7,8}
 * 
 * {5,3,2,4,6,8,7,1}
 * 
 * Returns: 7
 * 
 * 4)
 * 
 * {0,0,0,0}
 * 
 * {1,2,3,4}
 * 
 * {123,456,789,1000}
 * 
 * Returns: 8
 * 
 * 5)
 * 
 * {0}
 * 
 * {1}
 * 
 * {1000}
 * 
 * Returns: 2
 * 
 * 
 * Created by Luonanqin on 11/6/14.
 */
public class CandleTimerEasy {

	public static int differentTime(int[] A, int[] B, int[] len) {
		// 如果只有一根蜡烛，则只有两种结果
		if (A.length == 1) {
			return 2;
		}
		// 初始化每个点对关系
		Map<Integer, Set<Integer>> points1 = new HashMap<Integer, Set<Integer>>(A.length);
		Map<Integer, Set<Integer>> points2 = new HashMap<Integer, Set<Integer>>(A.length);
		// 初始化AB数组元素对应的下标集合
		Map<Integer, Set<Integer>> APointToLen = new HashMap<Integer, Set<Integer>>();
		Map<Integer, Set<Integer>> BPointToLen = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < A.length; i++) {
			if (!points1.containsKey(A[i])) {
				Set<Integer> set = new HashSet<Integer>();
				points1.put(A[i], set);
			}
			points1.get(A[i]).add(B[i]);
			if (!APointToLen.containsKey(A[i])) {
				Set<Integer> set = new HashSet<Integer>();
				APointToLen.put(A[i], set);
			}
			APointToLen.get(A[i]).add(i);
		}
		for (int i = 0; i < B.length; i++) {
			if (!points2.containsValue(B[i])) {
				Set<Integer> set = new HashSet<Integer>();
				points2.put(B[i], set);
			}
			points2.get(B[i]).add(A[i]);
			if (!BPointToLen.containsKey(B[i])) {
				Set<Integer> set = new HashSet<Integer>();
				BPointToLen.put(B[i], set);
			}
			BPointToLen.get(B[i]).add(i);
		}

		Set<Double> result = new HashSet<Double>();

		// 总长度
		double sumLength = 0;

		Set<Map<Integer, String>> permutationSet = permutation(A, B, points1, points2, APointToLen, BPointToLen);

		for (Map<Integer, String> permutation : permutationSet) {
			// 复制蜡烛长度
			int[] iCandleLen = Arrays.copyOf(len, len.length);
			double[] candleLen = new double[iCandleLen.length];
			for (int i = 0; i < iCandleLen.length; i++) {
				candleLen[i] = (double) iCandleLen[i];
			}

			// 初始化每个节点未被点燃
			boolean[] ignite = new boolean[A.length + 1];

			while (true) {
				// 已被点燃的蜡烛的下标，可进行增删
				Set<Integer> igniteSet = permutation.keySet();

				if (igniteSet.isEmpty()) {
					break;
				}

				// 在点燃的节点中，找到里相邻节点长度最短的节点，记下其长度、索引及对应元素，同时记录被点燃的节点
				double minLen = Integer.MAX_VALUE;
				for (Integer lenIndex : igniteSet) {
					double length = candleLen[lenIndex];
					if (length == 0) {
						continue;
					}
					if (ignite[A[lenIndex]] && ignite[B[lenIndex]]) {
						length = length / (double) 2;
					}
					if (minLen > length) {
						minLen = length;
					}
					// 标记已被点燃的节点
					String arrayName = permutation.get(lenIndex);
					if ("A".equals(arrayName)) {
						ignite[A[lenIndex]] = true;
					} else {
						ignite[B[lenIndex]] = true;
					}
				}

				sumLength += minLen;

				Set<Integer> remove = new HashSet<Integer>();
				Set<Integer> removeIndex = new HashSet<Integer>();
				// 每个燃烧的蜡烛减去最小时间
				for (Integer lenIndex : igniteSet) {
					if (candleLen[lenIndex] <= 0) {
						continue;
					}
					if (ignite[A[lenIndex]] && ignite[B[lenIndex]]) {
						candleLen[lenIndex] -= (minLen * 2);
					} else {
						candleLen[lenIndex] -= minLen;
					}

					if (candleLen[lenIndex] == 0) {
						String arrayName = permutation.get(lenIndex);
						if ("A".equals(arrayName)) {
							remove.add(B[lenIndex]);
						} else {
							remove.add(A[lenIndex]);
						}
						removeIndex.add(lenIndex);
					}
				}

				// 某根蜡烛燃尽后引发的别的节点燃烧
				for (Integer lenIndex : removeIndex) {
					ignite[B[lenIndex]] = true;
					ignite[A[lenIndex]] = true;
				}

				// 移除燃尽的蜡烛对应的下标
				for (Integer index : removeIndex) {
					permutation.remove(index);
				}

				// 根据节点对应的蜡烛下标，增加对应的蜡烛到已点燃的集合中
				for (Integer r : remove) {
					Set<Integer> APointLenIndex = APointToLen.get(r);
					Set<Integer> BPointLenIndex = BPointToLen.get(r);
					if (APointLenIndex != null) {
						for (Integer index : APointLenIndex) {
							if (candleLen[index] == 0) {
								continue;
							}
							permutation.put(index, "A");
						}
					}
					if (BPointLenIndex != null) {
						for (Integer index : BPointLenIndex) {
							if (candleLen[index] == 0) {
								continue;
							}
							permutation.put(index, "B");
						}
					}
				}
			}
			result.add(sumLength);
			// 恢复初始
			sumLength = 0;
		}

		return result.size();
	}

	private static Set<Map<Integer, String>> permutation(int[] A, int[] B, Map<Integer, Set<Integer>> points1, Map<Integer, Set<Integer>> points2,
			Map<Integer, Set<Integer>> APointToLen, Map<Integer, Set<Integer>> BPointToLen) {
		Set<Map<Integer, String>> permutation = new HashSet<Map<Integer, String>>();
		Map<Integer, String> canBeIgnited = new HashMap<Integer, String>();
		int[] count = new int[20];
		for (int i = 0; i < A.length; i++) {
			count[A[i]]++;
		}
		for (int i = 0; i < B.length; i++) {
			count[B[i]]++;
		}

		for (int i = 0; i < count.length; i++) {
			if (count[i] == 1) {
				if (points1.containsKey(i)) {
					canBeIgnited.put(i, "A");
				} else {
					canBeIgnited.put(i, "B");
				}
			}
		}

		Integer[] point = canBeIgnited.keySet().toArray(new Integer[0]);

		int base = canBeIgnited.size();
		int category = (1 << base) - 1;
		// 计算所有的组合情况
		for (int i = 1; i <= category; i++) {
			Map<Integer, String> map = new HashMap<Integer, String>();
			int temp = i;
			for (int j = base - 1; j >= 0; j--) {
				int tmp = temp & 1;
				if (tmp == 1) {
					int index = 0;
					if (APointToLen.containsKey(point[j])) {
						index = APointToLen.get(point[j]).iterator().next();
					} else {
						index = BPointToLen.get(point[j]).iterator().next();
					}
					map.put(index, canBeIgnited.get(point[j]));
				}
				temp >>= 1;
			}
			permutation.add(map);
		}

		return permutation;
	}

	public static void main(String[] args) {
		int[] A1 = { 0, 1 };
		int[] B1 = { 1, 2 };
		int[] len1 = { 10, 1 };
		System.out.println("{ 0, 1 }");
		System.out.println("{ 1, 2 }");
		System.out.println("{ 10, 1 }");
		System.out.println(differentTime(A1, B1, len1) + "\n");

		int[] A2 = { 0, 0, 0 };
		int[] B2 = { 1, 2, 3 };
		int[] len2 = { 1, 1, 1 };
		System.out.println("{ 0, 0, 0 }");
		System.out.println("{ 1, 2, 3 }");
		System.out.println("{ 1, 1, 1 }");
		System.out.println(differentTime(A2, B2, len2) + "\n");

		int[] A3 = { 0, 0, 0 };
		int[] B3 = { 1, 2, 3 };
		int[] len3 = { 1, 2, 3 };
		System.out.println("{ 0, 0, 0 }");
		System.out.println("{ 1, 2, 3 }");
		System.out.println("{ 1, 2, 3 }");
		System.out.println(differentTime(A3, B3, len3) + "\n");

		int[] A4 = { 0, 1, 1, 2, 3, 3, 2, 4 };
		int[] B4 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] len4 = { 5, 3, 2, 4, 6, 8, 7, 1 };
		System.out.println("{ 0, 1, 1, 2, 3, 3, 2, 4 }");
		System.out.println("{ 1, 2, 3, 4, 5, 6, 7, 8 }");
		System.out.println("{ 5, 3, 2, 4, 6, 8, 7, 1 }");
		System.out.println(differentTime(A4, B4, len4) + "\n");

		int[] A5 = { 0, 0, 0, 0 };
		int[] B5 = { 1, 2, 3, 4 };
		int[] len5 = { 123, 456, 789, 1000 };
		System.out.println("{ 0, 0, 0, 0 }");
		System.out.println("{ 1, 2, 3, 4 }");
		System.out.println("{ 123, 456, 789, 1000 }");
		System.out.println(differentTime(A5, B5, len5) + "\n");

		int[] A6 = { 0 };
		int[] B6 = { 1 };
		int[] len6 = { 1000 };
		System.out.println("{ 0 }");
		System.out.println("{ 1 }");
		System.out.println("{ 1000 }");
		System.out.println(differentTime(A6, B6, len6) + "\n");
	}
}
