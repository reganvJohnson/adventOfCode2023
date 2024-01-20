package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import static java.lang.Math.pow;

class Dec04b {
//	final static String filename = "dec04_demo.txt";
	final static String filename = "dec04.txt";


    public static void main( String []args ) throws Exception {
    	Dec04b theApp = new Dec04b();
    	int sum = theApp.processFile();
        System.out.printf("The sum is %d\n", sum);
 }

    int processFile() throws Exception {
        List<Integer>cardScores = new ArrayList<>();
        List<Integer>cardCounts = new ArrayList<>();

        int sum = 0;

	    BufferedReader bufReader = new BufferedReader(new FileReader(filename));

    	String line = bufReader.readLine();
        int lineCount = 1;
    	while (line != null) {
            int result = 0;
            Set<String> leftSet = new HashSet<>();
            Set<String> rightSet = new HashSet<>(); 
            parseLine(line, leftSet, rightSet);
            leftSet.retainAll(rightSet);
            //System.out.println(leftSet);
            int theSize = leftSet.size();
            //System.out.printf("%d matches for %d points\n", theSize, result);
            cardScores.add(theSize);
            cardCounts.add(1);
            lineCount++;
            line = bufReader.readLine();
        }
        System.out.println(cardCounts);

        --lineCount;
        String outStr = "";
        System.out.println("cardcounts= " + cardCounts);
        for (int i = 0; i < lineCount; i++) {
            int numberToAdd = cardCounts.get(i);
            outStr += String.format("\nadding %d cards to ", numberToAdd);
            //System.out.printf("i is %d linecount is %d\n", i, lineCount);
            //System.out.printf("cardScores is %d\n", cardScores.get(i));
            for (int j = i+1; j < Math.min(lineCount, i+1 + cardScores.get(i)); j++) {
                cardCounts.set(j, Integer.valueOf((int)cardCounts.get(j) + numberToAdd));
                outStr =  String.format("%s%d, ", outStr,  j);
                System.out.println("After 1 add " + cardCounts);
            }
            outStr += " giving " + cardCounts;
        }
        System.out.println(outStr);
        System.out.println(cardScores);
        System.out.println(cardCounts);
        for (int val: cardCounts) 
            sum += val;

        return sum;
    }

    void parseLine (final String theLine, Set<String> leftSet, Set<String> rightSet) {
        String[] firstDivision = theLine.split(":");

        String[] secondDivision = firstDivision[1].split("\\|");
        extractSet(secondDivision[0], leftSet);
        extractSet(secondDivision[1], rightSet);
    }

    void extractSet(String division, Set<String>retSet) {
        String trimmed = division.replaceAll("  ", " ");
        trimmed = trimmed.trim();
        String[] splitted = trimmed.split(" ");
        for (int i = 0; i < splitted.length; i++) {
            retSet.add(splitted[i]);
        }
    }
}