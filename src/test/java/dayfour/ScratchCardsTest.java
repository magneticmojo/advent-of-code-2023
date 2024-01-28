package dayfour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScratchCardsTest {

    String data = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
            """;

    private List<String> small;
    private List<String> big;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        String filename = "scratchcards.txt";
        big = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));


        String[] d = data.split("\n");
        small = new ArrayList<>(Arrays.asList(d));
    }


    @Test
    void testCopiesSmall() {
        ScratchCards s = new ScratchCards();
        assertEquals(30, s.getCopies(small));
    }

    @Test
    void testCopiesBig() {
        ScratchCards s = new ScratchCards();
        assertEquals(11024379, s.getCopies(big));
    }



/*    @Test
    void testWinningsSmall() {
        ScratchCards s = new ScratchCards();
        assertEquals(13, s.getWinnings(small));
    }*/

/*    @Test
    void testCalcSumLarge() {
        ScratchCards s = new ScratchCards();
        assertEquals(21485, s.getWinnings(big));
    }*/
}