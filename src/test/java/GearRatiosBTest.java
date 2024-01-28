import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GearRatiosBTest {

    private char[][] schematicSmall = {
            {'4', '6', '7', '.', '.', '1', '1', '4', '.', '.'},
            {'.', '.', '.', '*', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '3', '5', '.', '.', '6', '3', '3', '.'},
            {'.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
            {'6', '1', '7', '*', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '+', '.', '5', '8', '.'},
            {'.', '.', '5', '9', '2', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '7', '5', '5', '.'},
            {'.', '.', '.', '$', '.', '*', '.', '.', '.', '.'},
            {'.', '6', '6', '4', '.', '5', '9', '8', '.', '.'}
    };

    private char[][] schematic;

    @BeforeEach
    void setUP() throws URISyntaxException, IOException {
        String fileName = "gear-ratios.txt";

        // Read all lines from the file
        List<String> lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));

        // Assuming all lines have the same length and there's at least one line
        schematic = new char[lines.size()][lines.get(0).length()];

        // Fill the char array with characters from the file
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                schematic[i][j] = lines.get(i).charAt(j);
            }
        }

/*        for (char[] row : schematic) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }*/
    }

    @Test
    void testCalculatesLarge() {
        GearRatiosB gR = new GearRatiosB(schematic);
        System.out.println("RESULT: " + gR.sumPartNumbers());
        /*assertEquals(539590, gR.sumPartNumbers());*/
    }

    @Test
    void testCalculatesSmall() {
        GearRatiosB gR = new GearRatiosB(schematicSmall);
        assertEquals(467835, gR.sumPartNumbers());
    }


}