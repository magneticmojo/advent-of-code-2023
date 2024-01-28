package daytwo;

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

class CubeConundrumTest {


    private final String smallConundrum = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """;

    private List<String> gamesSmall;
    private List<String> games;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        String[] s = smallConundrum.split("\n");
        gamesSmall = new ArrayList<>(Arrays.asList(s));
        games = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource("cube-conundrum.txt").toURI()));
    }


/*    @Test
    void sumOfGameIDs() {
        CubeConundrum cc = new CubeConundrum();
        assertEquals(8, cc.sumOfGameIDs(gamesSmall));
    }*/

    @Test
    void power() {
        CubeConundrum cc = new CubeConundrum();
        assertEquals(2286, cc.sumOfGameIDs(gamesSmall));
    }

    @Test
    void sumOfGameIDsLarge() {
        CubeConundrum cc = new CubeConundrum();
        assertEquals(66909, cc.sumOfGameIDs(games));
    }




}
