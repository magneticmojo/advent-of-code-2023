package dayfour;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScratchCards {

    public int getCopies(List<String> cards) {

        Card[] scratchedCards = new Card[cards.size()];

        // CARD
        int i = 0;
        for (String card : cards) {

            String[] idSplit = card.split(":");
            String[] numberSplit = idSplit[1].split("\\|");

            int[] cardNumbers = Arrays.stream(numberSplit[0].trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] winningNumbers = Arrays.stream(numberSplit[1].trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();


            Set<Integer> cardSet = Arrays.stream(cardNumbers).boxed().collect(Collectors.toSet());
            Set<Integer> winningSet = Arrays.stream(winningNumbers).boxed().collect(Collectors.toSet());

            cardSet.retainAll(winningSet);

            System.out.println("Matches: " + cardSet.size());

            int copies = calculateCopies(cardSet);
            scratchedCards[i] = new Card(copies);
            i++;
        }

        printAll(scratchedCards);
        addCopies(scratchedCards);
        printAll(scratchedCards);
        incrementCopies(scratchedCards);
        printAll(scratchedCards);
        return getTotalNumCopies(scratchedCards);
    }

    private void printAll(Card[] scratched) {
        System.out.print("[ ");
        for (Card c : scratched) {
            System.out.print(c.getMatches());
            System.out.print(c.getCopies());
            System.out.print(", ");
        }
        System.out.println("]");
    }

    private void print(Card c) {
        System.out.print("[");
        System.out.print(c.getMatches());
        System.out.print(c.getCopies());
        System.out.println("]");
    }

    private void addCopies(Card[] scratched) {
        for (int i = 0; i < scratched.length; i++) {
            printAll(scratched);
            int m = scratched[i].getMatches();
            print(scratched[i]);
            for (int j = i + 1; j < scratched.length && j <= i + m; j++) {
                scratched[j].incrCopies(1);
                print(scratched[j]);
            }
        }
    }
    
    private void incrementCopies(Card[] scratched) {
        for (int i = 0; i < scratched.length; i++) {
            int c = scratched[i].getCopies();
            int m = scratched[i].getMatches();
            for (int j = i + 1; j < scratched.length && j <= i + m; j++) {
                scratched[j].incrCopies(c);
            }
        }
    }

    private int getTotalNumCopies(Card[] scratched) {
        int numCopies = 0;
        for (Card card : scratched) {
            numCopies += card.getCopies() + 1;
        }
        return numCopies;
    }

    private int calculateCopies(Set<Integer> intersection) {
        return intersection.size();
    }

/*    private int calculateCardPoints(Set<Integer> intersection) {

        int matches = intersection.size();
        int winnings = 0;

        if (matches == 1) {
            return 1;
        }

        if (matches > 1) {
            winnings = 1;
            for (int i = 1; i < matches; i++) {
                winnings += winnings;
                System.out.println("winnings: " + winnings);
            }
        }

        System.out.println("Tot winnings per card: " + winnings);

        return winnings;
    }*/

/*    public int getWinnings(List<String> cards) {
        int sumPoints = 0;
        for (String card : cards) {

            String[] idSplit = card.split(":");
            String[] numberSplit = idSplit[1].split("\\|");

            int[] cardNumbers = Arrays.stream(numberSplit[0].trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] winningNumbers = Arrays.stream(numberSplit[1].trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();


            Set<Integer> cardSet = Arrays.stream(cardNumbers).boxed().collect(Collectors.toSet());
            Set<Integer> winningSet = Arrays.stream(winningNumbers).boxed().collect(Collectors.toSet());

            cardSet.retainAll(winningSet);

            System.out.println("Matches: " + cardSet.size());

            sumPoints += calculateCardPoints(cardSet);
        }
        return sumPoints;
    }*/

}























