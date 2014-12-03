package SRM_639;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardFoldingDiv2 {

	private Map<String, Set<String>> record = new HashMap<String, Set<String>>();

	public int howMany(String[] paper) {
		int row = paper.length;
		int column = paper[0].length();

		int count = 1;
		int h = 1, v = 1;
		// 水平折叠
		while (h < row) {
			count += foldHorizontal(paper, h++, 1, 1, row, column);
		}

		// 垂直折叠
		while (v < column) {
			count += foldVertical(paper, v++, 1, 1, row, column);
		}

		return count;
	}

	// 深度优先方式遍历垂直折叠的结果，当遍历完成后再遍历其水平折叠
	private int foldVertical(String[] paper, int fold, int bRow, int bColumn, int row, int column) {
		if (fold == column) {
			return 0;
		}
		int checkSymmetry = judgeColumnSymmetry(paper, fold, bRow, bColumn, row, column);
		if (checkSymmetry == 0) {
			return 0;
		}

		int count = 0;
		boolean before = false, after = false;
		if (checkSymmetry == 1) {
			before = true;
		} else if (checkSymmetry == 2) {
			after = true;
		} else if (checkSymmetry == 3) {
			before = true;
			after = true;
		}
		if (before) {
			count++;
			int v = 1;
			while (v < fold) {
				count += foldVertical(paper, v++, bRow, bColumn, row, fold);
			}
			int h = 1;
			while (h < row) {
				count += foldHorizontalForVertical(paper, h++, bRow, bColumn, row, fold);
			}
		}
		if (after) {
			count++;
			int v = 1;
			int nbColumn = bColumn + fold;
			while (v < (column - fold)) {
				count += foldVertical(paper, v++, bRow, nbColumn, row, column - fold);
			}
			int h = 1;
			while (h < row) {
				count += foldHorizontalForVertical(paper, h++, bRow, nbColumn, row, column - fold);
			}
		}
		return count;
	}

	private int foldHorizontalForVertical(String[] paper, int fold, int bRow, int bColumn, int row, int column) {
		if (fold == row) {
			return 0;
		}
		int checkSymmetry = judgeRowSymmetry(paper, fold, bRow, bColumn, row, column);
		if (checkSymmetry == 0) {
			return 0;
		}

		int count = 0;
		boolean up = false, down = false;
		if (checkSymmetry == 1) {
			up = true;
		} else if (checkSymmetry == 2) {
			down = true;
		} else if (checkSymmetry == 3) {
			up = true;
			down = true;
		}
		if (up) {
			count++;
			int h = bRow;
			while (h < fold) {
				count += foldHorizontalForVertical(paper, h++, bRow, bColumn, fold, column);
			}
		}
		if (down) {
			count++;
			int h = 1;
			int nbRow = bRow + fold;
			while (h < (row - fold)) {
				count += foldHorizontalForVertical(paper, h++, nbRow, bColumn, row - fold, column);
			}
		}
		return count;
	}

	// 以深度优先方式遍历水平折叠结果，当遍历完成后再遍历其垂直折叠
	private int foldHorizontal(String[] paper, int fold, int bRow, int bColumn, int row, int column) {
		if (fold == row) {
			return 0;
		}
		int checkSymmetry = judgeRowSymmetry(paper, fold, bRow, bColumn, row, column);
		if (checkSymmetry == 0) {
			return 0;
		}

		int count = 0;
		boolean up = false, down = false;
		if (checkSymmetry == 1) {
			up = true;
		} else if (checkSymmetry == 2) {
			down = true;
		} else if (checkSymmetry == 3) {
			up = true;
			down = true;
		}
		if (up) {
			count++;
			int h = 1;
			while (h < fold) {
				count += foldHorizontal(paper, h++, bRow, bColumn, fold, column);
			}
			int v = 1;
			while (v < column) {
				count += foldVerticalForHorizontal(paper, v++, bRow, bColumn, fold, column);
			}
		}
		if (down) {
			count++;
			int h = 1;
			int nbRow = bRow + fold;
			while (h < (row - fold)) {
				count += foldHorizontal(paper, h++, nbRow, bColumn, row - fold, column);
			}
			int v = 1;
			while (v < column) {
				count += foldVerticalForHorizontal(paper, v++, nbRow, bColumn, row - fold, column);
			}
		}
		return count;
	}

	private int foldVerticalForHorizontal(String[] paper, int fold, int bRow, int bColumn, int row, int column) {
		if (fold == column) {
			return 0;
		}
		int checkSymmetry = judgeColumnSymmetry(paper, fold, bRow, bColumn, row, column);
		if (checkSymmetry == 0) {
			return 0;
		}

		int count = 0;
		boolean before = false, after = false;
		if (checkSymmetry == 1) {
			before = true;
		} else if (checkSymmetry == 2) {
			after = true;
		} else if (checkSymmetry == 3) {
			before = true;
			after = true;
		}
		if (before) {
			count++;
			int v = bColumn;
			while (v < fold) {
				count += foldVerticalForHorizontal(paper, v++, bRow, bColumn, row, fold);
			}
		}
		if (after) {
			count++;
			int v = 1;
			int nbColumn = bColumn + fold;
			while (v < (column - fold)) {
				count += foldVerticalForHorizontal(paper, v++, bRow, nbColumn, row, column - fold);
			}
		}
		return count;
	}

	// 如果对称，则返回1/2/3，分别代表上侧保留/下侧保留/上下都保留。如果不对称，则返回0
	private int judgeRowSymmetry(String[] paper, int fold, int bRow, int bColumn, int row, int column) {
		// fold离上下最近的为比较是否对称的范围
		int flag, rowBounds;
		int upDistance = fold;
		int downDistance = row - fold;

		if (downDistance < upDistance) {
			rowBounds = downDistance;
			flag = 1;
		} else if (downDistance > upDistance) {
			rowBounds = upDistance;
			flag = 2;
		} else {
			rowBounds = upDistance;
			flag = 3;
		}

		// 计算fold的绝对行号
		fold += bRow - 1;
		// 检查是否对称
		for (int i = 0; i < rowBounds; i++) {
			for (int j = 0; j < column; j++) {
				if (paper[fold - i - 1].charAt(j) != paper[fold + i].charAt(j)) {
					return 0;
				}
			}
		}

		int result = 0; // 代表哪一部分保留
		if (flag == 1) {
			if (judgeRepeat(bRow, bColumn, upDistance, column)) {
				result += 1;
			}
		}
		if (flag == 2) {
			if (judgeRepeat(bRow + upDistance, bColumn, downDistance, column)) {
				result += 2;
			}
		}
		if (flag == 3) {
			if (judgeRepeat(bRow, bColumn, upDistance, column)) {
				result += 1;
			}
			if (judgeRepeat(bRow + upDistance, bColumn, downDistance, column)) {
				result += 2;
			}
		}
		return result;
	}

	// 如果对称，则返回1/2/3，分别代表左侧保留/右侧保留/左右都保留。如果不对称，则返回0
	private int judgeColumnSymmetry(String[] paper, int fold, int bRow, int bColumn, int row, int column) {
		// fold离左右最近的为比较是否对称的范围
		int flag, columnBounds;
		int beforeDistance = fold;
		int afterDistance = column - fold;

		if (afterDistance < beforeDistance) {
			columnBounds = afterDistance;
			flag = 1;
		} else if (beforeDistance < afterDistance) {
			columnBounds = beforeDistance;
			flag = 2;
		} else {
			columnBounds = beforeDistance;
			flag = 3;
		}

		// 计算fold的绝对列号
		fold += bColumn - 1;
		// 检查是否对称
		for (int i = 0; i < columnBounds; i++) {
			for (int j = 0; j < row; j++) {
				if (paper[j].charAt(fold - i - 1) != paper[j].charAt(fold + i)) {
					return 0;
				}
			}
		}

		int result = 0; // 代表哪一部分保留
		if (flag == 1) {
			if (judgeRepeat(bRow, bColumn, row, beforeDistance)) {
				result += 1;
			}
		}
		if (flag == 2) {
			if (judgeRepeat(bRow, fold + 1, row, afterDistance)) {
				result += 2;
			}
		}
		if (flag == 3) {
			if (judgeRepeat(bRow, bColumn, row, beforeDistance)) {
				result += 1;
			}
			if (judgeRepeat(bRow, fold + 1, row, afterDistance)) {
				result += 2;
			}
		}
		return result;
	}

	// 重复返回false
	private boolean judgeRepeat(int bRow, int bColumn, int row, int column) {
		String coordinate = "" + bRow + bColumn;
		String lengthWidth = "" + row + column;

		if (!record.containsKey(coordinate)) {
			record.put(coordinate, new HashSet<String>());
		}

		return record.get(coordinate).add(lengthWidth);
	}
}
