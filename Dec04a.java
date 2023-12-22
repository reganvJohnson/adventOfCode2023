import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import static java.lang.Math.pow;

class Dec04a {
//	final static String filename = "dec04_demo.txt";
	final static String filename = "dec04.txt";


    public static void main( String []args ) throws Exception {
    	Dec04a theApp = new Dec04a();
    	int sum = theApp.processFile();
        System.out.printf("The sum is %d\n", sum);
 }

    int processFile() throws Exception {

        int sum = 0;

	    BufferedReader bufReader = new BufferedReader(new FileReader(filename));

    	String line = bufReader.readLine();
    	while (line != null) {
            int result = 0;
            Set<String> leftSet = new HashSet<>();
            Set<String> rightSet = new HashSet<>(); 
            parseLine(line, leftSet, rightSet);
            leftSet.retainAll(rightSet);
            System.out.println(leftSet);
            int theSize = leftSet.size()-1;
            if (theSize >= 0) {
                result = (int) Math.pow(2, theSize);
            } 
            System.out.printf("%d matches for %d points\n", theSize, result);

            sum += result;
            line = bufReader.readLine();
        }
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