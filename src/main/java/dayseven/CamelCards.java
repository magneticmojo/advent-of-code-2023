package dayseven;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelCards {

    private static final Map<Character, Integer> cardStrength = new HashMap<>();

    // Static initializer block -> Executed when class is loaded + thread-safe + guaranteed execution once
    static {
        cardStrength.put('A', 14);
        cardStrength.put('K', 13);
        cardStrength.put('Q', 12);
        cardStrength.put('J', 11);
        cardStrength.put('T', 10);
        cardStrength.put('9', 9);
        cardStrength.put('8', 8);
        cardStrength.put('7', 7);
        cardStrength.put('6', 6);
        cardStrength.put('5', 5);
        cardStrength.put('4', 4);
        cardStrength.put('3', 3);
        cardStrength.put('2', 2);
    }

    public long getTotalWinnings(List<String> hands) {
        List<String> sortedHands = hands.stream()
                .sorted(Comparator.comparing((String hand) -> handType(hand).getStrength())
                        .thenComparing(this::handStrength, (list1, list2) -> {
                            for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
                                int comparison = Integer.compare(list2.get(i), list1.get(i)); // reversed order for higher card strength first
                                if (comparison != 0) {
                                    return comparison;
                                }
                            }
                            return 0; // if all compared cards are equal, consider hands equal
                        }))
                .toList();




        // Now, you need to define how to calculate total winnings from the sorted hands.
        // This is a placeholder logic assuming you sum the "strengths" of the sorted hands.
        // You need to replace it with the actual game logic.

        for (String s : sortedHands) {
            System.out.println(s);
        }

        return sortedHands.stream()
                .mapToLong(hand -> cardStrength.get(hand.charAt(0)))
                .sum();
    }

    private HandType handType(String hand) {
        Map<Character, Long> freqMap = freqMap(hand);

        /*for (Map.Entry<Character, Long> entry : freqMap.entrySet()) {
            System.out.println("K=" + entry.getKey() + ", V=" + entry.getValue());
        }*/

        if (freqMap.containsValue(4L)) {
            return HandType.FOUR_OF_A_KIND;
        } else if (freqMap.containsValue(3L) && freqMap.containsValue(2L)) {
            return HandType.FULL_HOUSE;
        } else if (freqMap.containsValue(3L)) {
            return HandType.THREE_OF_A_KIND;
        } else if (freqMap.containsValue(2L) && freqMap.containsValue(2L)) {
            return HandType.TWO_PAIR;
        } else if (freqMap.containsValue(2L)) {
            return HandType.ONE_PAIR;
        }
        return HandType.HIGH_CARD;
    }

    private List<Integer> handStrength(String hand) {
        return hand.chars()
                .mapToObj(c -> (char) c)
                .filter(cardStrength::containsKey)
                .map(cardStrength::get)
                .collect(Collectors.toList());
    }

    private Map<Character, Long> freqMap(String hand) {
        return hand.chars()
                .mapToObj(c -> (char) c)
                .filter(cardStrength::containsKey)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private enum HandType {
        FOUR_OF_A_KIND(1),
        FULL_HOUSE(2),
        THREE_OF_A_KIND(3),
        TWO_PAIR(4),
        ONE_PAIR(5),
        HIGH_CARD(6);

        private final int strength;

        HandType(int strength) {
            this.strength = strength;
        }

        public int getStrength() {
            return strength;
        }
    }

}
