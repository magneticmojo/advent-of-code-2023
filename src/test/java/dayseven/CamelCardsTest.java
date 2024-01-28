package dayseven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CamelCardsTest {

    private List<String> lines;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        String filename = "day7.txt";

        lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));
    }

/*    @Test
    void test() {
        CamelCards cc = new CamelCards();
        assertEquals(1, cc.getTotalWinnings(lines));
    }*/

}