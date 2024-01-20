package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.stream.Collectors;

import advent.Dec07b_Hand;

class Dec07b {
    String filename = "dec07_demo.txt";
    Dec07b() {
        filename = "dec07.txt";
    }

    ArrayList<Dec07b_Hand> allCards = new ArrayList<>();

    public static void main( String []args ) throws Exception {
    	Dec07b theApp = new Dec07b();
    	theApp.processFile();
        System.out.println(theApp.calculateTotalBids());

    }


    long calculateTotalBids() {
        long sum = 0;
        for (int i = 0; i < allCards.size(); i++) {

            sum += (i+1)*allCards.get(i).getBid();
        }
        return sum;
    }

    void processFile() throws Exception {

        String inLine;
	    BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        while ((inLine = bufReader.readLine()) != null) {
            allCards.add(new Dec07b_Hand(inLine));
        }
        allCards.sort((Dec07b_Hand o1, Dec07b_Hand o2)->o1.getScore()-o2.getScore());
        System.out.println(allCards);

        return;
    }
}
