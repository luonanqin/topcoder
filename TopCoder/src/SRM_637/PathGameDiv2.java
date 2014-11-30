package SRM_637;

public class PathGameDiv2 {
	
	public int calc(String[] board) {
		final char WHITE = '.';
		final char BLACK = '#';

		String str1 = board[0];
		String str2 = board[1];

		boolean flag1 = false, flag2 = false;
		int length = str1.length();
		int sum = 0;

		for (int i = 0; i < length; i++) {
			if (str1.charAt(i) == WHITE && str2.charAt(i) == WHITE) {
				sum++;
				continue;
			}

			if (str1.charAt(i) == BLACK) {
				flag1 = true;
			}
			if (str2.charAt(i) == BLACK) {
				flag2 = true;
			}
			if (flag1 && flag2) {
				sum--;
				if (str1.charAt(i) == BLACK) {
					flag2 = false;
				} else {
					flag1 = false;
				}
			}
		}
		return sum;
	}
}
