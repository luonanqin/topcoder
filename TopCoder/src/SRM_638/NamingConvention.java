package SRM_638;

public class NamingConvention {

	public String toCamelCase(String variableName) {
		String res = "";
		int i = 0;
		while (i < variableName.length()) {
			if (variableName.charAt(i) == '_') {
				res = res + Character.toUpperCase(variableName.charAt(i + 1));
				i += 2;
			} else {
				res = res + variableName.charAt(i);
				i++;
			}
		}
		return res;
	}
}
