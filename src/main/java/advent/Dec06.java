package advent;

import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import static java.lang.Math.pow;
import java.util.stream.Collectors;

class Dec06 {
    String filename = "dec06_demo.txt";
    Dec06() {
        filename = "dec06.txt";
    }

    List<Integer> timeList;
    List<Integer> distanceList;
    String timeLine;
    String distanceLine;

    public static void main( String []args ) throws Exception {
    	Dec06 theApp = new Dec06();
    	theApp.processFile();
        theApp.runRaces();
        theApp.runOneRace();
    }

    void runOneRace() {
        long time = deKernLine(timeLine);
        long distance = deKernLine(distanceLine);
        System.out.println(Long.MAX_VALUE);
        long winners = 0;
        for (long i = 0; i <= time; i++) {
        long holding = time - i;
            long distanceCovered = holding * (time-holding);
            if (distanceCovered > distance) {
                winners++;
           }
        }
        System.out.printf("number of winners is %d\n", winners);
        //return winners;

    }
    public void runRaces() {
        long product = 1;
        for (int i=0; i < timeList.size(); i++){
            product *= runARace(i);
        }
        System.out.printf( "final product is %d\n", product);
    }

    long runARace(int race) {
        int time = timeList.get(race);
        int distance = distanceList.get(race);
        int winners = 0;
        for (int i = 0; i <= time; i++) {
            int holding = time - i;
            int distanceCovered = holding * (time-holding);
            if (distanceCovered > distance) {
                winners++;
               System.out.printf("race %d time %d went %d\n", race, i, distanceCovered);
           }
        }
        System.out.println(winners);
        return winners;
    }




    void processFile() throws Exception {

	    BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        timeLine = bufReader.readLine();
        distanceLine = bufReader.readLine();
        timeList = parseLine(timeLine);
        distanceList = parseLine(distanceLine);
        return;
    }

    long deKernLine(final String theLine) {
        String[] division = theLine.split(":");
        String newLine = division[1].replaceAll(" ", "");
        System.out.println(theLine);
        System.out.println(newLine);
        return Long.parseLong(newLine);

    }


    List<Integer> parseLine (final String theLine) {
        String[] firstDivision = theLine.split(":");
        List<Integer> allAnswerList = Arrays.stream(firstDivision[1].trim().split("\\s+"))    // stream of String
                                    .map(Integer::valueOf) // stream of Integer
                                    .collect(Collectors.toList());
        return allAnswerList;
    }
}
