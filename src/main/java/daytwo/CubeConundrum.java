package daytwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CubeConundrum {

    private int maxRed = 12;
    private int maxGreen = 13;
    private int maxBlue = 14;

    public int sumOfGameIDs(List<String> games) {
        int sum = 0;
        for (String game : games) {

            String[] gameID = game.split(": ");

            int gameIDNumber = getNumber(gameID[0]);

            String[] hands = gameID[1].split("; ");

            List<String> allCubes = new ArrayList<>();
            for (String s : hands) {
                String[] cubes = s.split(", ");
                allCubes.addAll(Arrays.asList(cubes));
            }

            // sumOfGamesID
            /*if (isPossible(allCubes)) {
                sum += gameIDNumber;
            }*/

            Comparator<String> colorComparator = (str1, str2) -> {
                String[] parts1 = str1.split(" ");
                String[] parts2 = str2.split(" ");

                int colorCompare = parts1[1].compareTo(parts2[1]);
                if (colorCompare != 0) {
                    return colorCompare;
                }

                int num1 = Integer.parseInt(parts1[0]);
                int num2 = Integer.parseInt(parts2[0]);
                return Integer.compare(num2, num1);

            };

            allCubes.sort(colorComparator);


            for (String c : allCubes) {
                System.out.println(c);
            }
            System.out.println(allCubes);

            sum += power(allCubes);
        }
        return sum;
    }

    private int power(List<String> allCubes) {
        int blue = 0;
        int green = 0;
        int red = 0;

        for (String s : allCubes) {
            if (s.contains("blue")) {
                blue = getNumber(s);
                break;
            }

        }


        for (String s : allCubes) {
            if (s.contains("red")) {
                red = getNumber(s);
                break;
            }

        }

        for (String s : allCubes) {
            if (s.contains("green")) {
                green = getNumber(s);
                break;
            }

        }
        return blue * green * red;
    }

    private boolean isPossible(List<String> allCubes) {
        for (String c : allCubes) {
            if (c.contains("red") && getNumber(c) > maxRed) {
                return false;
            }

            if (c.contains("green") && getNumber(c) > maxGreen) {
                return false;
            }

            if (c.contains("blue") && getNumber(c) > maxBlue) {
                return false;
            }
        }
        return true;
    }

    private int getNumber(String s) {
        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                char c = s.charAt(i);
                number = number * 10 + (c - '0');
            }
        }
        return number;
    }
}
