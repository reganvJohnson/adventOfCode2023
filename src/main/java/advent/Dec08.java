package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import advent.util.Util;
import advent.util.Pairs;

class Dec08 {
    String filename = "dec08_demo.txt";
    Dec08() {
        filename = "dec08.txt";
    }

String directions;
Map<String, Pairs> theDirections = new HashMap<>();

    public static void main( String []args ) throws Exception {
    	Dec08 theApp = new Dec08();
    	theApp.processFile();
        theApp.getLoopy();
    }

    void getLoopy() {
        int directionLength = directions.length();
        String key = "AAA";
        int whereAmI = -1;
        int moves = 0;
        while(!"ZZZ".equals(key)) {
            ++moves;
            whereAmI = ++whereAmI % directionLength;
            char nextDirection = directions.charAt(whereAmI);
            if (nextDirection == 'L') {
                key = theDirections.get(key).getLeft();
            } else {
                key = theDirections.get(key).getRight();
            }
            System.out.println(key);
        }
        System.out.println(moves);

    }


    void processFile() throws Exception {

        List<String> theLines = Util.readFile(filename);
        directions = theLines.get(0);
        for (int i = 2; i < theLines.size(); i++) {
            String[] input = theLines.get(i).split("=");
            theDirections.put(input[0].trim(), new Pairs(input[1]));
        }
        return;
    }
}
