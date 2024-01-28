import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trebuchet {

    public int solve(List<String> calibrationDocument) {


        char first = '\0';
        char last = '\0';
        int sum = 0;

        for (String line : calibrationDocument) {
            System.out.println();
            System.out.print("LINE: " + line);

            int j = 0;
            for (; j < line.length(); j++) {
                if (Character.isDigit(line.charAt(j))) {
                    first = line.charAt(j);
                    break;
                }
            }

            for (int k = line.length() - 1; k >= j; k--) {
                if (Character.isDigit(line.charAt(k))) {
                    last = line.charAt(k);
                    break;
                }
            }
            System.out.print("FL: " + first + last);
            sum += Integer.parseInt(first + "" + last);
        }

        return sum;
    }

    public int solveWords(List<String> calibrationDocument) {
        List<String> converted = convertLetterNumbers(calibrationDocument);
        return solve(converted);
    }

    private Map<String, Integer> numbers3 = Map.of("one", 1,"two", 2,"six", 6);
    private Map<String, Integer> numbers4 = Map.of("four", 4,"five", 5, "nine", 9);
    private Map<String, Integer> numbers5 = Map.of("three", 3,"seven", 7,"eight", 8);

    public List<String> convertLetterNumbers(List<String> calibrationDocument) {
        List<String> convertedLines = new ArrayList<>();

        for (String line : calibrationDocument) {
            StringBuilder sb = new StringBuilder();

            /*System.out.println("line: " + line);*/
            int i = 0;
            while (i < line.length()) {
                /*System.out.print(" i=" + i);
                System.out.print("; char=" + line.charAt(i));
                System.out.print("; sb=" + sb);*/

                if (i + 3 <= line.length() && isThree(line.substring(i, i + 3))) {
                    /*System.out.println("; TH");*/

                    sb.append(convertThreeLetterNumber(line.substring(i, i + 3)));

                    i += 2;

                } else if (i + 4 <= line.length() && isFour(line.substring(i, i + 4))) {
                    /*System.out.println("; FO");*/

                    sb.append(convertFourLetterNumber(line.substring(i, i + 4)));

                    i += 3;

                } else if (i + 5 <= line.length() && isFive(line.substring(i, i + 5))) {
                    /*System.out.println("; FI");*/
                    sb.append(convertFiveLetterNumber(line.substring(i, i + 5)));

                    i += 4;
                } else {
                    sb.append(line.charAt(i));
                }

                i++;


            }/*
            System.out.print("; convert=" + sb);
            System.out.println("; line=" + line);
            System.out.println();*/
            convertedLines.add(sb.toString());
        }
        return convertedLines;
    }

    private String convertThreeLetterNumber(String line) {
        for (String key : numbers3.keySet()) {
            if (line.equals(key)) {
                return String.valueOf(numbers3.get(key));
            }
        }
        return line;
    }

    private String convertFourLetterNumber(String line) {
        for (String key : numbers4.keySet()) {
            if (line.equals(key)) {
                return String.valueOf(numbers4.get(key));
            }
        }
        return line;
    }

    private String convertFiveLetterNumber(String line) {
        for (String key : numbers5.keySet()) {
            if (line.equals(key)) {
                return String.valueOf(numbers5.get(key));
            }
        }
        return line;
    }

    private boolean isThree(String line) {
        return numbers3.containsKey(line);
    }

    private boolean isFour(String line) {
        return numbers4.containsKey(line);
    }

    private boolean isFive(String line) {
        return numbers5.containsKey(line);
    }

}
