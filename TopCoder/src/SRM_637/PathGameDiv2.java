package SRM_637;

/**
 * Division II Level Two
 *
 * Problem Statement
 *
 * Cat Snuke is playing the Path Game.
 * 
 * 
 * The Path Game is played on a rectangular grid of square cells. The grid has 2 rows and some positive number of columns. Each cell is either black or white.
 * 
 * 
 * A left-to-right path in the grid is a sequence of white cells such that the first cell in the sequence is in the leftmost column, the last cell in the
 * sequence is in the rightmost column, and each pair of consecutive cells shares a common side.
 * 
 * 
 * The initial coloring of the grid is such that there is at least one left-to-right path. You are given this initial coloring as a String[] board with two
 * elements. For each i and j, board[i][j] is either '#' (representing a black cell) or '.' (representing a white cell).
 * 
 * 
 * Snuke may color some of the white cells black. After he does so, there must still be at least one left-to-right path left on the board. The goal of the game
 * is to color as many cells black as possible. Compute and return the largest number of cells Snuke can color black. (Note that the cells that are already
 * black do not count.)
 * 
 * Definition
 * 
 * Class: PathGameDiv2 Method: calc Parameters: String[] Returns: int Method signature: int calc(String[] board) (be sure your method is public)
 * 
 * 
 * Constraints - board will contain 2 elements. - Each element in board will contain between 1 and 50 characters, inclusive. - All elements in board will have
 * the same length. - Each character in board will be '#' or '.'. - The grid described by board will contain a left-to-right path.
 * 
 * Examples
 *
 * 0)
 * 
 * {"#...." ,"...#."}
 * 
 * Returns: 2
 * 
 * Snuke can color at most two white cells black. One possible final state of the board looks as follows:
 * 
 * #.... ..###
 * 
 * 1)
 * 
 * {"#" ,"."}
 * 
 * Returns: 0
 * 
 * Snuke can't color any cells.
 *
 * 2)
 *
 * {"." ,"."}
 * 
 * Returns: 1
 * 
 * 3)
 *
 * {"....#.##.....#..........." ,"..#......#.......#..#...."}
 * 
 * Returns: 13
 *
 * Created by Luonanqin on 11/1/14.
 */
public class PathGameDiv2 {

	public static int calc(String[] board) {
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

	public static void main(String[] args) {
		System.out.println("{\"....#.##.....#...........\",\n \"..#......#.......#..#....\"}");
		String[] str1 = { "....#.##.....#...........", "..#......#.......#..#...." };
		System.out.println(calc(str1) + "\n");

		System.out.println("{\".\",\n \".\"}");
		String[] str2 = { ".", "." };
		System.out.println(calc(str2) + "\n");

		System.out.println("{\"#\",\n \".\"}");
		String[] str3 = { "#", "." };
		System.out.println(calc(str3) + "\n");

		System.out.println("{\"#....\",\n \"...#.\"}");
		String[] str4 = { "#....", "...#." };
		System.out.println(calc(str4));
	}
}
