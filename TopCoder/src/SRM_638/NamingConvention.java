package SRM_638;

import java.util.HashMap;
import java.util.Map;

/**
 * Division II Level One
 *
 * Problem Statement
 *
 * In most programming languages variable names cannot contain spaces. If we want a variable name that consists of two or more words, we have to encode the
 * spaces somehow. In this problem, we will look at two ways of doing so: Snake Case and Camel Case. When using Snake Case, we just replace each space by an
 * underscore ('_'). When using Camel Case, we capitalize the first letter of each word except for the first one, and then we remove all spaces.
 * 
 * 
 *
 * For example, suppose that we want to declare a variable called "good morning world" (quotes for clarity). In Snake Case, we would write this variable as
 * "good_morning_world", while in Camel Case it would be "goodMorningWorld".
 * 
 *
 * You are given a String variableName. This String contains a valid variable name written in Snake Case. Return the same variable name in Camel Case.
 * 
 * Definition
 * 
 * Class: NamingConvention Method: toCamelCase Parameters: String Returns: String Method signature: String toCamelCase(String variableName) (be sure your method
 * is public)
 * 
 * 
 * Constraints - variableName will contain between 1 and 50 characters. - Each character of variableName will be 'a'-'z' or '_'. - The first and last character
 * of variableName will not be '_'. - variableName will not contain two consecutive underscores ('_').
 * 
 * Examples
 * 
 * 0)
 * 
 * "sum_of_two_numbers"
 * 
 * Returns: "sumOfTwoNumbers"
 * 
 * We have 4 words: "sum", "of", "two", "numbers". So the answer is "sum" + "Of" + "Two" + "Numbers".
 *
 * 1)
 * 
 * "variable"
 * 
 * Returns: "variable"
 * 
 * Note that if we have only 1 word, then the varaible name will remain same.
 *
 * 2)
 * 
 * "t_o_p_c_o_d_e_r"
 * 
 * Returns: "tOPCODER"
 * 
 * 3)
 *
 * "the_variable_name_can_be_very_long_like_this"
 * 
 * Returns: "theVariableNameCanBeVeryLongLikeThis"
 *
 * Created by Luonanqin on 11/5/14.
 */
public class NamingConvention {

	private static Map<Character, Character> LOWER_TO_UPPER_CASE = new HashMap<Character, Character>() {
		{
			put('a', 'A');
			put('b', 'B');
			put('c', 'C');
			put('d', 'D');
			put('e', 'E');
			put('f', 'F');
			put('g', 'G');
			put('h', 'H');
			put('i', 'I');
			put('j', 'J');
			put('k', 'K');
			put('l', 'L');
			put('m', 'M');
			put('n', 'N');
			put('o', 'O');
			put('p', 'P');
			put('q', 'Q');
			put('r', 'R');
			put('s', 'S');
			put('t', 'T');
			put('u', 'U');
			put('v', 'V');
			put('w', 'W');
			put('x', 'X');
			put('y', 'Y');
			put('z', 'Z');
		}
	};

	public static String toCamelCase(String variableName) {
		if (variableName == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < variableName.length(); i++) {
			char c = variableName.charAt(i);
			if (c == '_') {
				sb.append(LOWER_TO_UPPER_CASE.get(variableName.charAt(++i)));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String str1 = "sum_of_two_numbers";
		System.out.println("\"sum_of_two_numbers\"");
		System.out.println(toCamelCase(str1) + "\n");

		String str2 = "variable";
		System.out.println("\"variable\"");
		System.out.println(toCamelCase(str2) + "\n");

		String str3 = "t_o_p_c_o_d_e_r";
		System.out.println("\"t_o_p_c_o_d_e_r\"");
		System.out.println(toCamelCase(str3) + "\n");

		String str4 = "the_variable_name_can_be_very_long_like_this";
		System.out.println("\"the_variable_name_can_be_very_long_like_this\"");
		System.out.println(toCamelCase(str4));
	}
}
