package advent;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import advent.Dec07b_Hand;
class Dec07bTest {


    @Test
    void testingCountSets() {
    	assertTrue("[5, 0, 0, 0, 0]".equals(Arrays.toString(new Dec07b_Hand("22222 456").countSets())));
    	assertTrue("[3, 0, 0, 0, 0]".equals(Arrays.toString(new Dec07b_Hand("222JJ 456").countSets())));
    	assertTrue("[4, 1, 0, 0, 0]".equals(Arrays.toString(new Dec07b_Hand("32222 456").countSets())));
    	assertTrue("[2, 1, 0, 0, 0]".equals(Arrays.toString(new Dec07b_Hand("322JJ 456").countSets())));
    	assertTrue("[3, 1, 1, 0, 0]".equals(Arrays.toString(new Dec07b_Hand("35222 456").countSets())));
    	assertTrue("[1, 1, 1, 0, 0]".equals(Arrays.toString(new Dec07b_Hand("342JJ 456").countSets())));
    	assertTrue("[1, 1, 1, 0, 0]".equals(Arrays.toString(new Dec07b_Hand("JJ342 456").countSets())));
     	assertTrue("[2, 1, 1, 0, 0]".equals(Arrays.toString(new Dec07b_Hand("5J332 456").countSets())));
    }

    @Test
    void testingOfAKind() {
    	assertTrue(new Dec07b_Hand("22222 456").isFiveOfAKind());
    	assertTrue(new Dec07b_Hand("222JJ 456").isFiveOfAKind());
    	assertTrue(new Dec07b_Hand("32222 456").isFourOfAKind());
    	assertTrue(new Dec07b_Hand("322JJ 456").isFourOfAKind());
    	assertTrue(new Dec07b_Hand("35222 456").isThreeOfAKind());
    	assertTrue(new Dec07b_Hand("342JJ 456").isThreeOfAKind());
    }
    @Test
    void testFullHouse() {
    	assertTrue(new Dec07b_Hand("33322 123").isFullHouse());
    	assertTrue(new Dec07b_Hand("33J22 123").isFullHouse());
    	assertTrue(new Dec07b_Hand("33JJ2 123").isFullHouse());
    	assertTrue(new Dec07b_Hand("JJJJJ 123").isFullHouse());
    	assertTrue(new Dec07b_Hand("2JJJJ 123").isFullHouse());
    	assertFalse(new Dec07b_Hand("33225 123").isFullHouse());
    }

    @Test
    void testTwoPair() {
    	assertTrue(new Dec07b_Hand("33322 123").isTwoPair());
    	assertTrue(new Dec07b_Hand("33J22 123").isTwoPair());
    	assertTrue(new Dec07b_Hand("33JJ2 123").isTwoPair());
    	assertTrue(new Dec07b_Hand("JJJJJ 123").isTwoPair());
    	assertTrue(new Dec07b_Hand("2JJJJ 123").isTwoPair());
    	assertFalse(new Dec07b_Hand("33245 123").isTwoPair());
    }

    @Test
    void randoTest() {
    	assertTrue(new Dec07b_Hand("QAAQT 665").isTwoPair()); // 1CEECA
//5K355 312 25D355
//Q5T46 440 0C5A46
//A3AJA 461 2E3E1E
//QQ9KQ 825 2CC9DC
//QJJQQ 51 2C11CC
//7J7A5 37 2717E5
    }

}
