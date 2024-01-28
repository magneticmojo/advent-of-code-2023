package daysix;

import java.util.List;

public class ToyBoats {


    public long getPermutations(List<String> lines) {

       /* String[] times = lines.get(0).split("\\s+");
        String[] distances = lines.get(1).split("\\s+");
        ArrayList<Race> races = new ArrayList<>();*/


        /*for (int i = 1; i < times.length; i++) {
            long time = Long.parseLong(times[i]);
            long distance = Long.parseLong(distances[i]);
            races.add(new Race(time, distance));
        }


        // calculate possible strategies


        // FOR EACH RACE
        StringBuilder sb = new StringBuilder();
        for (Race r : races) {
            long time = r.time();
            long dist = r.recordDistance();
            long permutations = 0;

            // int i == btnP
            for (int i = 0; i < time; i++) {
                long totDist = (time - i) * i;
                if (totDist > dist) {
                    permutations++;
                }
            }
            sb.append(permutations);
            sb.append(" ");

        }

        System.out.println(sb);
        String[] values = sb.toString().split("\\s+");

        // Neutral element of multiplication is 1
        long factor = 1;

        for (String value : values){
            factor *= Long.parseLong(value);
        }
*/

        System.out.println(test());

        return 1;
    }

    private long test() {
        // FOR EACH RACE
        StringBuilder sb = new StringBuilder();

        long time = 40929790;
        long dist = 215106415051100L;
        long permutations = 0;

        // int i == btnP
        for (int i = 0; i < time; i++) {
            long totDist = (time - i) * i;
            if (totDist > dist) {
                permutations++;
            }
        }
        sb.append(permutations);
        sb.append(" ");

        String[] values = sb.toString().split("\\s+");

        // Neutral element of multiplication is 1
        long factor = 1;

        for (String value : values){
            factor *= Long.parseLong(value);
        }

        return factor;
    }

}