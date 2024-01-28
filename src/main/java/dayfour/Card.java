package dayfour;

public class Card {

    private final int matches;
    private int copies = 0;

    public Card (int matches) {
        this.matches = matches;
    }

    public int getMatches() {
        return matches;
    }

    public int getCopies() {
        return copies;
    }

    public void incrCopies(int increment) {
        copies += increment;
    }

}
