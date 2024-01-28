public class GearRatios {

    private final char[][] schematic;
    private final int maxCol;
    private int maxRow;
    private int currentCol;
    private boolean shouldAdd;

    public GearRatios(char[][] schematic) {
        this.schematic = schematic;
        maxRow = schematic.length - 1;
        maxCol = schematic[0].length - 1;
    }

    public int sumPartNumbers() {
        int sum = 0;
        for (int row = 0; row < schematic.length; row++) {
            for (int col = 0; col < schematic[row].length; col++) {

                if (isDigit(row, col)) {

                    checkBefore(row, col);
                    String num = getNumber(new StringBuilder(), row, col).toString();
                    col = currentCol;
                    checkAfter(row, col);

                    if (shouldAdd) {
                        sum += Integer.parseInt(num);
                    }
                }
                shouldAdd = false;
            }
        }
        return sum;
    }

    private boolean isDigit(int row, int col) {
        return Character.isDigit(schematic[row][col]);
    }

    private void checkBefore(int row, int col) {
        hasSymbol(row - 1, col - 1);
        hasSymbol(row, col - 1);
        hasSymbol(row + 1, col - 1);
    }

    private StringBuilder getNumber(StringBuilder sb, int row, int col) {
        if (!isInBounds(row, col) || !isDigit(row, col)) {
            setCurrentCol(col);
            return sb;
        }

        sb.append(schematic[row][col]);
        hasSymbol(row - 1, col);
        hasSymbol(row + 1, col);

        return getNumber(sb, row, col + 1);
    }

    private void setCurrentCol(int col) {
        currentCol = col - 1;
    }

    private void checkAfter(int row, int col) {
        hasSymbol(row - 1, col + 1);
        hasSymbol(row, col + 1);
        hasSymbol(row + 1, col + 1);
    }

    private void hasSymbol(int row, int col) {
        if (isInBounds(row, col)) {
            if (isNotDigitOrDot(row, col)) {
                shouldAdd = true;
            }
        }
    }

    private boolean isInBounds(int row, int col) {
        return (row >= 0 && row <= maxRow) && (col >= 0 && col <= maxCol);
    }

    private boolean isNotDigitOrDot(int row, int col) {
        return schematic[row][col] != '.' && !isDigit(row, col);
    }

}
