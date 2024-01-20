package advent.util;



public class Pairs {

	String left;
	String right;

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	public Pairs(String inStr) {
		inStr = inStr.trim().replace("(", "").replace(")", "").replaceAll(" ","");
		String values[] = inStr.split(",");
		left = values[0];
		right = values[1];
	}

}
