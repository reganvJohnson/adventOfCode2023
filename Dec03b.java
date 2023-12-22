import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;
import static java.lang.Math.max;
import static java.lang.Math.min;

class Dec03b {
//	final static String filename = "dec03_demo.txt";
	final static String filename = "dec03.txt";

	char[][] theInput;
	int width = 0;
	int height = 0;

    public static void main( String []args ) throws Exception {
    	Dec03b theApp = new Dec03b();
    	theApp.readFileToMatrix();
    	theApp.examineMatrix();
    }


    void examineMatrix() {
    	int sum = 0;
    	for (int y = 0; y < height; y++) {
    		for (int x = 0; x < width; x++) {
    			if (isGear(x, y)) {
    				sum += searchForRatios(x, y);
    			}
    		}
    	}
    	System.out.println(String.format("The sum is %d", sum));
    }

    boolean isGear(final int x, final int y) {
    	return  theInput[y][x] == '*';
    }

    // we have a gear, now look around for numbers.
	// if we have two, multiply them, and return that.
    int searchForRatios(final int xPos, final int yPos) {
		List<Integer> theRatios = new ArrayList<>();
    	int sum = 0;
    	//System.out.printf("checking [%d %d %d] [%d %d %d]\n", max(0, yPos-1), yPos, min(yPos+1, height), max(0, xPos-1), xPos,  min(xPos+1, width));
    	for (int y = max(0, yPos-1); y <= min(yPos+1, height); y++) {
	    	for (int x = max(0, xPos-1); x <= min(xPos+1, width); x++) {
	    		System.out.printf("checking at %d %d\n", x, y);
	    		if (isDigit(theInput[y][x])) {
					theRatios.add(determineValue(x, y));
	    		}
	    	}
	    }
		if (theRatios.size() == 2) {
			return theRatios.get(0) * theRatios.get(1);
		} else {
			return 0;
		}
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