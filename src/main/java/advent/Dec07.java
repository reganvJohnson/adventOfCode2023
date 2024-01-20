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

class Dec07 {
    String filename = "dec07_demo.txt";
    Dec07() {
        filename = "dec07.txt";
    }

    ArrayList<Hand> allCards = new ArrayList<>();

    class Hand {
        private String rawCards;
        private int bid; 
        private int score;
        char[] sortedCards;
        String mappedOnlyCards;

        public Hand(final String inLine) {
            String[] divs = inLine.split("\\s+");
            rawCards = divs[0];
            bid = Integer.parseInt(divs[1]);
            score = calculateScore();
        }

        private char[] sortAndMap() {
            char[] cards = rawCards.toCharArray();
            char[] mappedOnlyChar = rawCards.toCharArray();

            for (int i = 0; i < cards.length; i++) {
                mappedOnlyChar[i] = map(cards[i]);

                cards[i] = map(cards[i]);
            }
            mappedOnlyCards = new String(mappedOnlyChar);
            Arrays.sort(cards);
            return cards;
        }

        private int calculateScore() {
            sortedCards = sortAndMap();
            if (isFiveOfAKind()) {
                return Integer.parseInt("6" + mappedOnlyCards, 16);
            }
            if (isFourOfAKind()) {
                return Integer.parseInt("5" + mappedOnlyCards, 16);
            }
            if (isFullHouse()) {
                return Integer.parseInt("4" + mappedOnlyCards, 16);
            }
            if (isThreeOfAKind()) {
                return Integer.parseInt("3" + mappedOnlyCards, 16);
            }
            if (isTwoPair()) {
                return Integer.parseInt("2" + mappedOnlyCards, 16);
            }
            if (isOnePair()) {
                return Integer.parseInt("1" + mappedOnlyCards, 16);
            }
            return Integer.parseInt("0" + mappedOnlyCards, 16);
        }

        boolean isFiveOfAKind() {
            return (sortedCards[0] == sortedCards[4]);
        }

        boolean isFourOfAKind() {
            return ((sortedCards[0] == sortedCards[3]) || (sortedCards[1] == sortedCards[4]));
        }

        boolean isFullHouse() {
            return ((sortedCards[0] == sortedCards[2] && sortedCards[3]==sortedCards[4]) ||
                    (sortedCards[2] == sortedCards[4] && sortedCards[0]==sortedCards[1]));
        }

        boolean isThreeOfAKind() {
            return ((sortedCards[0] == sortedCards[2]) ||
                    (sortedCards[1] == sortedCards[3]) ||
                    (sortedCards[2] == sortedCards[4]));
        }

        boolean isTwoPair() {
           return ((sortedCards[0] == sortedCards[1] && (sortedCards[2] == sortedCards[3] || sortedCards[3]== sortedCards[4]) ||
                   (sortedCards[1] == sortedCards[2]) && sortedCards[3] == sortedCards[4]));

        }

        boolean isOnePair() { 
            return ((sortedCards[0] == sortedCards[1]) ||
                (sortedCards[1] == sortedCards[2]) ||
                (sortedCards[2] == sortedCards[3]) ||
                (sortedCards[3] == sortedCards[4]));
        }

        private char map(char inCh) {
            switch(inCh) {
                case 'A':
                    return 'E';
                case 'K':
                    return 'D';
                case 'Q':
                    return 'C';
                case 'J':
                    return 'B';
                case 'T':
                    return 'A';
                default:
                    return inCh;
            }
        }

        public String toString() {
            return (String.format("%s", getScore()));
        }

        String getMappedOnlyCards() {
            return mappedOnlyCards;
        }
        int getScore() {
            return score;
        }
        int getBid() {
            return bid;
        }
    }

    public static void main( String []args ) throws Exception {
    	Dec07 theApp = new Dec07();
    	theApp.processFile();
        System.out.println(theApp.calculateScore());

    }


    long calculateScore() {
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
            allCards.add(new Hand(inLine));
            //System.out.printf("%s: score: %d in hex:%s\n", inLine, george.getScore(), george.getMappedOnlyCards());
        }
        allCards.sort((Hand o1, Hand o2)->o1.getScore()-o2.getScore());
        System.out.println(allCards);

        return;
    }
}
