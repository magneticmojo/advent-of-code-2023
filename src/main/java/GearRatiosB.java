import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GearRatiosB {

    private final char[][] schematic;
    private Map<Cell, List<Cell>> possibleGears = new HashMap<>();

    private final int maxCol;
    private final int minCol = 0;

    private final int maxRow;
    private final int minRow = 0;


    public GearRatiosB(char[][] schematic) {
        this.schematic = schematic;
        maxRow = schematic.length - 1;
        maxCol = schematic[0].length - 1;
    }

    public int sumPartNumbers() {

        int sumOfProducts = 0;

        for (int row = 0; row < schematic.length; row++) {
            for (int col = 0; col < schematic[row].length; col++) {


                if (isDigit(row, col)) {

                    Cell startCell = new Cell(row, col);
                    col++;
                    while (colInBounds(col) && isDigit(row, col)) {
                        col++;
                    }


                    Cell endCell = new Cell(row, col - 1);


                    int upperRow = row - 1;
                    int lowerRow = row + 1;
                    int startIdx = startCell.col() - 1;
                    int endIdx = endCell.col() + 1;
                    Cell gearOperator = null;

                    if (rowInBounds(upperRow)) {
                        gearOperator = starSearch(upperRow, startIdx, endIdx);
                    }

                    if (gearOperator == null) {
                        gearOperator = starSearch(row, startIdx, endIdx);
                    }

                    if (gearOperator == null) {
                        if (rowInBounds(lowerRow)) {
                            gearOperator = starSearch(lowerRow, startIdx, endIdx);
                        }
                    }

                    if (gearOperator != null) {
                        if (possibleGears.containsKey(gearOperator)) {
                            List<Cell> cells = possibleGears.get(gearOperator);
                            cells.add(startCell);
                            cells.add(endCell);
                        } else {
                            List<Cell> cells = new ArrayList<>();
                            cells.add(startCell);
                            cells.add(endCell);
                            possibleGears.put(gearOperator, cells);
                        }
                    }
                }
            }
        }


        for (Map.Entry<Cell, List<Cell>> entry : possibleGears.entrySet()) {
            List<Cell> value = entry.getValue();
            if (value.size() == 4) {
                System.out.println("NEXT GEAR!!!");
                int product = getProduct(value);
                sumOfProducts += product;
            }
        }

        return sumOfProducts;
    }

    private int getProduct(List<Cell> value) {
        int firstFactorRow = value.get(0).row();
        int firstFactorStartIdx = value.get(0).col();
        int firstFactorEndIdx = value.get(1).col();

        int firstFactor = getFactor(firstFactorRow, firstFactorStartIdx, firstFactorEndIdx);

        int secondFactorRow = value.get(2).row();
        int secondFactorStartIdx = value.get(2).col();
        int secondFactorEndIdx = value.get(3).col();

        int secondFactor = getFactor(secondFactorRow, secondFactorStartIdx, secondFactorEndIdx);

        return firstFactor * secondFactor;
    }

    private int getFactor(int row, int colStartIdx, int colEndIdx) {
        int factor = 0;
        for (int i = colStartIdx; i <= colEndIdx ; i++) {
            char c = schematic[row][i];
            factor = factor * 10 + (c - '0');
        }
        return factor;
    }

    private boolean rowInBounds(int row) {
        return row >= minRow && row <= maxRow;
    }

    private boolean colInBounds(int col) {
        return col >= minCol && col <= maxCol;
    }

    private boolean colOutOfBounds(int col) {
        return col < minCol || col > maxCol;
    }

    private Cell starSearch(int row, int startIdx, int endIdx) {

        if (colOutOfBounds(startIdx)) {
            startIdx++;
        }

        if (colOutOfBounds(endIdx)) {
            endIdx--;
        }

        for (int frameCol = startIdx; frameCol <= endIdx; frameCol++) {

            if (schematic[row][frameCol] == '*') {
                return new Cell(row, frameCol);
            }
        }
        return null;
    }


    private boolean isDigit(int row, int col) {
        return Character.isDigit(schematic[row][col]);
    }
}
