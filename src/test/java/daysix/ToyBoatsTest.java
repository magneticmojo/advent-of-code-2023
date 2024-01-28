package daysix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToyBoatsTest {


    private List<String> lines;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        String filename = "day6.txt";

        lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));
    }

    @Test
    void test() {
        ToyBoats tb = new ToyBoats();
        assertEquals(1, tb.getPermutations(lines));
    }

    /*ou will get a fixed amount of time during which your
    boat has to travel as far as it can, and you win if your
    boat goes the farthest

    INPUT:
    lists the time allowed for each race
    race.timeLimit
    the best distance ever recorded in that race.

     To guarantee you win the grand prize, you need to make sure you
     -> go farther in each race than the current record holder.



    Race.timeLeft()
    Race.bestTime()
    Boat.travelledDistance();
    Boat.hasRaceTime()
    Boat.wentFartherThan(race.recordholder)

    GrandPrice.win(Boat.races)

    Toyboats -> button:
    ifPressed ==> Charge + ifNotHasStarted()
    ifReleased ==> Move

    if charginglevel/timeSpentCharging > 0 ==> move faster???

    1 Millisekunder = 0.001 Sekunder

    Millimeter
               r1 r2  r3
    Time:      7  15   30
    Distance:  9  40  200


    START SPEED:
    0 mm per millisecond

    while notStarted
        foreach millisecond button.isPressed
            speed += 1 millimeter


    */


}