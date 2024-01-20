package advent;


import java.util.Arrays;

    public class Dec07b_Hand {
        final static char PROCESSED_CARD = 'X';
        private String rawCards;
        private int bid; 
        private int score;
        char[] sortedCards;
        String mappedOnlyCards;

        public Dec07b_Hand(final String inLine) {
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
            String digitOne = "0";
            if (isFiveOfAKind()) {
                digitOne = "6";
            } else if (isFourOfAKind()) {
                digitOne = "5";
            } else if (isFullHouse()) {
                digitOne = "4";
            } else if (isThreeOfAKind()) {
                digitOne = "3";
            } else if (isTwoPair()) {
                digitOne = "2";
            } else if (isOnePair()) {
                digitOne = "1";
            }
            System.out.printf ("%s %s %s\n", rawCards, bid, digitOne+mappedOnlyCards);
            return Integer.parseInt(digitOne + mappedOnlyCards, 16);
        }

        boolean isFiveOfAKind() {
            // count the hightest number of duplicates (eg: kings, queens, etc, but not jacks). Add in Jacks.
            // if there are 5, we have five of a kind!
            return (countDupes() == 5);
        }


        int countDupes() {
            int highestCount = 0;
            int localHigh = 0;
            int jackCount = 0;
            char tester;
            for (int i = 0; i < rawCards.length(); i++) {
                if (rawCards.charAt(i) == 'J') {
                    jackCount++;
                    continue;
                }
                localHigh = 0;
                for (int j = i; j < rawCards.length();j++) {
                    if (rawCards.charAt(i) == rawCards.charAt(j)) {
                        localHigh++;
                    }
                }
                if (localHigh > highestCount) {
                    highestCount = localHigh;
                }
            }
            return (highestCount+jackCount);
        }

        int countJacks() {
            int theCount = 0;
            for (int i = 0; i < rawCards.length(); i++)  {
                if (rawCards.charAt(i) == 'J') {
                    theCount++;
                }
            }
            return theCount;
        }

        int[] countSets() {
            char rawDupe[] = rawCards.toCharArray();
            int counts[] = new int[rawCards.length()];
            for (int i = 0; i < rawCards.length(); i++) {
                for (int j = i; j < rawDupe.length; j++)
                    if ((rawDupe[j] != PROCESSED_CARD) && (rawCards.charAt(i) != 'J') && (rawCards.charAt(i) == rawDupe[j])) {
                        counts[i] += 1;
                        rawDupe[j] = PROCESSED_CARD;
                    }
            }
            Arrays.sort(counts);
            int tmp = counts[0]; counts[0] = counts[4];counts[4] = tmp;
            tmp = counts[1]; counts[1] = counts[3]; counts[3] = tmp;   
            return counts;
        }

        boolean isFourOfAKind() {
            return (countDupes() == 4);
        }

        boolean isFullHouse() {
            int sets[] = countSets();
            int jackCount = countJacks();
            if (sets[2] != 0) {
                return false;
            }
            int spareJacks = jackCount+ sets[0] -3;
            return (2-spareJacks -sets[1] == 0);
        }

        boolean isThreeOfAKind() {
            return (countDupes() == 3);
        }

        boolean isTwoPair() {
            int sets[] = countSets();
            int jackCount = countJacks();
            int spareJacks = jackCount+sets[0] -2;
            return (spareJacks + sets[1] -2 >= 0);
        }

        boolean isOnePair() { 
            return (countDupes() == 2);
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
                    return '1';
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

