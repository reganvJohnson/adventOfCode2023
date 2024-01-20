package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Character.isDigit;
import static java.lang.Character.getNumericValue;

class Dec03a {
//	final static String filename = "dec03_demo.txt";
	final static String filename = "dec03.txt";

	char[][] theInput;
	int width = 0;
	int height = 0;

    public static void main( String []args ) throws Exception {
    	Dec03a theApp = new Dec03a();
    	theApp.readFileToMatrix();
    	theApp.examineMatrix();
    }


    void examineMatrix() {
    	int sum = 0;
    	for (int y = 0; y < height; y++) {
    		for (int x = 0; x < width; x++) {
    			if (isSymbol(x, y)) {
    				sum += searchForParts(x, y);
    			}
    		}
    	}
    	System.out.println(String.format("The sum is %d", sum));
    }

    boolean isSymbol(final int x, final int y) {
    	char examinedChar = theInput[y][x];
    	//System.out.printf("%c", examinedChar);
    	if (isDigit(examinedChar))
    		return false;
    	if (examinedChar == '.') 
    		return false;
    	System.out.printf("Symbol is %c\n", examinedChar);
    	return true;
    }

    // we have a symbol, now look around for digits.
    int searchForParts(final int xPos, final int yPos) {
    	int sum = 0;
    	System.out.printf("checking [%d %d %d] [%d %d %d]\n", max(0, yPos-1), yPos, min(yPos+1, height), max(0, xPos-1), xPos,  min(xPos+1, width)); 
    	for (int y = max(0, yPos-1); y <= min(yPos+1, height); y++) {
	    	for (int x = max(0, xPos-1); x <= min(xPos+1, width); x++) {
	    		System.out.printf("checking at %d %d\n", x, y);
	    		if (isDigit(theInput[y][x])) {
	    			sum += determineValue(x, y);
	    		}
	    	}
	    }
    	return sum;
    }

    // move to the left through the matrix, until we hit a boundary, OR we stop hitting digits.
    // move to the right, calculating a vaule, AND turn digits to dots, so that they aren't used again.
    int determineValue(final int xPos, final int y) {
    	int retValue = 0;
    	int newStart;
    	for (newStart = xPos; (newStart >= 0) && isDigit(theInput[y][newStart]); newStart--);
    	//for(newStart = xPos; ((newStart >= 0) && (isDigit(theInput[y][newStart]))); --newStart){System.out.println(String.format("[%d][%d]", y, newStart));}
    	newStart++;
    	for(; (newStart < width) && isDigit(theInput[y][newStart]); newStart++) {
    		retValue = retValue*10 +  getNumericValue(theInput[y][newStart]);
    		theInput[y][newStart] = '.';
    	}
    	System.out.println("returnng " + retValue);
    	return retValue;
    }


    void readFileToMatrix() throws Exception {

	    BufferedReader bufReader = new BufferedReader(new FileReader(filename));
    	ArrayList<String> listOfLines = new ArrayList<>();

    	String line = bufReader.readLine();
    	while (line != null) {
      		listOfLines.add(line);
      		line = bufReader.readLine();
    	}

    	bufReader.close();
    	height = listOfLines.size();
    	width = listOfLines.get(0).length();
    	System.out.println(String.format("%d lines", height));
    	System.out.println(String.format("each is %d characters wide", width));
    	theInput = new char[height][width];
    	int y = -1;
    	for (String thisLine: listOfLines) {
    		y++;
    		for (int x=0; x < thisLine.length(); x++) {
    			theInput[y][x] = thisLine.charAt(x); 
    		}
    	}

    }
}