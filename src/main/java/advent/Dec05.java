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

class Dec05 {
    String filename = "dec05_demo.txt";
    Dec05() {
        filename = "dec05.txt";
    }

    public class Range {
        public Range(final String input) {
            String[] values = input.split(" ");
            destRange = new   BigInteger(values[0]);
            sourceRange = new BigInteger(values[1]);
            rangeLength = new BigInteger(values[2]);
            upperLimit = sourceRange.add(rangeLength);
        }
        BigInteger destRange;
        BigInteger sourceRange;
        BigInteger rangeLength;
        BigInteger upperLimit; 
    
        boolean covered(final BigInteger testValue) {
           return ((sourceRange.compareTo(testValue) <= 0) && (upperLimit.compareTo(testValue)>0));
        }

        BigInteger calculate(final BigInteger source) {
            return source.subtract(sourceRange).add(destRange);
        }

        public String toString() {

            return String.format("%d %d %d %d", destRange, sourceRange, rangeLength, upperLimit);
        }
    }

    List<BigInteger> seeds = new ArrayList<>();
    List<Range> seedToSoil = new ArrayList<>();
    List<Range> soilToFertilizer = new ArrayList<>();
    List<Range> fertilizerToWater = new ArrayList<>();
    List<Range> waterToLight = new ArrayList<>();
    List<Range> lightToTemperature = new ArrayList<>();
    List<Range> temperatureToHumidity = new ArrayList<>();
    List<Range> humidityToLocation = new ArrayList<>();


    public static void main( String []args ) throws Exception {
    	Dec05 theApp = new Dec05();
    	theApp.processFile();
        theApp.checkita();
        theApp.checkitb();
    }


    void checkita() {
        BigInteger smallest = new BigInteger("9999999999");


        for (BigInteger traveller: seeds) {
            traveller = findTarget(traveller, seedToSoil);
            traveller = findTarget(traveller, soilToFertilizer);
            traveller = findTarget(traveller, fertilizerToWater);
            traveller = findTarget(traveller, waterToLight);
            traveller = findTarget(traveller, lightToTemperature);
            traveller = findTarget(traveller, temperatureToHumidity);
            traveller = findTarget(traveller, humidityToLocation);
            if (smallest.compareTo(traveller)> 0) {
                smallest = traveller;
            }
        }
        System.out.println("final answer is " + smallest);
    }

    void checkitb() {
        BigInteger smallest = new BigInteger("9999999999");

        for (int i = 0; i < seeds.size(); i+=2) {
            System.out.printf("Seed %d for %d ", seeds.get(i), seeds.get(i+1));
            for (long j = 0; j < seeds.get(i+1).intValue(); j++) {
                BigInteger traveller = seeds.get(i).add(new BigInteger(String.valueOf(j)));
                traveller = findTarget(traveller, seedToSoil);
                traveller = findTarget(traveller, soilToFertilizer);
                traveller = findTarget(traveller, fertilizerToWater);
                traveller = findTarget(traveller, waterToLight);
                traveller = findTarget(traveller, lightToTemperature);
                traveller = findTarget(traveller, temperatureToHumidity);
                traveller = findTarget(traveller, humidityToLocation);
                //System.out.println("we found " + traveller);
                if (smallest.compareTo(traveller)> 0) {
                    smallest = traveller;
                }
            }
            System.out.printf(" Smallest is %d\n", smallest);
        }
        System.out.println("final answer is " + smallest);
    }

    BigInteger findTarget(BigInteger traveller, List<Range> ranges) {
        for (Range theRange: ranges) {
            //System.out.println(traveller + "covered in  " + theRange + " " + theRange.covered(traveller));
            if (theRange.covered(traveller)) {
                return theRange.calculate(traveller);
            }
        } 
        return traveller;
    }


    void processFile() throws Exception {

        int sum = 0;

	    BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        String line;
        int lineCount = 0;
        String state = "";     
    	while ((line = bufReader.readLine()) != null) {
            System.out.println(line);
            lineCount++;
            if (line.trim().length() == 0) 
                continue;
            // check for state change:
            if (!Character.isDigit(line.charAt(0))) {
                if (line.startsWith("seeds:")) {
                    System.out.println("it is seeds");
                    state = "seeds";
                    String[] seedLine = line.split(" ");
                    for (String seed: seedLine) {
                        if(seed.startsWith("seed"))
                            continue;
                        seeds.add(new BigInteger(seed));
                    }
                }
                if (line.startsWith("seed-to-soil")) {
                    state = "seed-to";
                    continue;
                }
                if (line.startsWith("soil-to-fertilizer")) {
                    state = "soil-to";
                    continue;
                }
                if (line.startsWith("fertilizer-to-water")) {
                    state = "fertilizer-to";
                    continue;
                }
                if (line.startsWith("water-to-light")) {
                    state = "water-to";
                    continue;
                }
                if (line.startsWith("light-to-temperature")) {
                    state = "light-to";
                    continue;
                }
                if (line.startsWith("temperature-to-humidity")) {
                    state = "temperature-to";
                    continue;
                }
                if (line.startsWith("humidity-to-location")) {
                    state = "humidity-to";
                    continue;
                }
            }
            switch (state) {
                case "seeds":
                    break;
                case "seed-to":
                    loadRange(line, seedToSoil);
                    break;
                case "soil-to":
                    loadRange(line, soilToFertilizer);
                    break;
                case "fertilizer-to":
                    loadRange(line, fertilizerToWater);
                    break;
                case "water-to":
                    loadRange(line, waterToLight);
                    break;
                case "light-to":
                    loadRange(line, lightToTemperature);
                    break;
                case "temperature-to": 
                    loadRange(line, temperatureToHumidity);
                    break;
                case "humidity-to":
                    loadRange(line, humidityToLocation);
                    break;
            }
        }
    }

    void loadRange(final String line, List<Range> list) {
        list.add(new Range(line));
    }
        
/*

            int result = 0;
            Set<Strin
                    break;
                case "g> leftSet = new HashSet<>();
            Set<String> rightSet = new HashSet<>(); 
            parseLine(line, leftSet, rightSet);
            leftSet.retainAll(rightSet);
            //System.out.println(leftSet);
            int theSize = leftSet.size();
            //System.out.printf("%d matches for %d points\n", theSize, result);
            cardScores.add(theSize);
            cardCounts!.add(1);
            lineCoun()) {
    ++;
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
  */
    

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