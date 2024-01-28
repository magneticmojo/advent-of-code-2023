package dayfive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlmanacTest {

    private List<String> small;
    private List<String> large;


/*    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        String filenameA = "almanac-small.txt";
        String filenameB = "almanac-large.txt";

        small = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(filenameA).toURI()));
        large = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(filenameB).toURI()));

    }*/

/*    @Test
    void testGetSmallestLocationSmall() {
        Almanac a = new Almanac();
        long startTime = System.nanoTime();
        long result = a.getSmallestLocation(large);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Execution time in seconds: " + duration / 1_000_000_000.0);

        assertEquals(35, result);
    }

    @Test
    void testGetSmallestLocationSmallB() {
        Almanac a = new Almanac();
        long startTime = System.nanoTime();
        long result = a.getSmallestLocation(small);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Execution time in seconds: " + duration / 1_000_000_000.0);
        assertEquals(46, result);
    }*/

/*    @Test
    void testGetSmallestLocationLarge() {
        Almanac a = new Almanac();
        assertEquals(910845529, a.getSmallestLocation(large));
    }*/

}