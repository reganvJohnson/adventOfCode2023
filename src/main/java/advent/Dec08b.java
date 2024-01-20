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

class Dec08b {
    String filename = "dec08b_demo.txt";
    Dec08b() {
        filename = "dec08.txt";
    }
    ArrayList<String> currentNodes = new ArrayList<>();
int nodeCount;
String directions;
Map<String, Pairs> theDirections = new HashMap<>();

    public static void main( String []args ) throws Exception {
    	Dec08b theApp = new Dec08b();
    	theApp.processFile();
        theApp.getLoopy();
    }

    void getLoopy() {
        int directionLength = directions.length();
        int whereAmI = -1;
        int moves = 0;
        boolean done = false;
        int startNum = 5;
        // 66
        while(!done) {
            int countOfFinals = 0;
            ++moves;
            whereAmI = ++whereAmI % directionLength;
            char nextDirection = directions.charAt(whereAmI);
            for (int i = startNum; i < startNum+1;i++) {
                nodeCount = 1;
                //currentNodes.size(); i++) {
                if (nextDirection == 'L') {
                    currentNodes.set(i, theDirections.get(currentNodes.get(i)).getLeft());
                } else {
                    currentNodes.set(i, theDirections.get(currentNodes.get(i)).getRight());
                }
                if (currentNodes.get(i).endsWith("Z")) {
                    countOfFinals++;
                    System.out.printf("%s %d\n", currentNodes.get(i), moves);
                }
//                if (countOfFinals == nodeCount) {
//                   done = true;
//                }
            }
            //System.out.printf("%d: %s %d %d\n", moves, currentNodes, countOfFinals, nodeCount);
        }
        System.out.println(moves);
        System.out.println(nodeCount);
    }


    void processFile() throws Exception {

        List<String> theLines = Util.readFile(filename);
        directions = theLines.get(0);
        for (int i = 2; i < theLines.size(); i++) {
            String[] input = theLines.get(i).split("=");
            theDirections.put(input[0].trim(), new Pairs(input[1]));
            if (theLines.get(i).contains("A =")) {
                currentNodes.add(input[0].trim());
                nodeCount++;
            }

        }
        return;
    }
}
