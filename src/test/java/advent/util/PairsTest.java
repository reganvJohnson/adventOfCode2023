package advent.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class PairsTest {


    @Test
    void testingCountSets() {
        Pairs bob = new Pairs(" (AAA, BBB)");
        assertEquals("AAA", bob.getLeft());
        assertEquals("BBB", bob.getRight());
    }
}
