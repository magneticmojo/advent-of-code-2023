package dayfive;

import java.util.*;
import java.util.stream.LongStream;

public class Almanac {

    HashMap<Integer, long[]> result;

    public long getSmallestLocation(List<String> lines) {

        String seedsStr = lines.get(0);
        long[] s = Arrays.stream(seedsStr.substring(seedsStr.indexOf(' ') + 1).split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();

/*        for (int i = 0; i < s.length; i+=2) {
            System.out.print("[" + s[i]);
            System.out.print("," + s[i + 1] + "]" + "\n");
        }*/

        result = buildMap(lines);
/*        System.out.println("Mapped:");
        result.forEach((k, v) -> System.out.println("Key: " + k + ", Value: " + Arrays.toString(v)));*/

        // seeds[n] - seeds[n + 1] -->
        // 37,909,983,373 seeds -> No fit in single collection + exponential increase with searching for ranches
/*        if (true) {
            return -1;
        }*/

        return getSeedsFromRange(s);


        /*long smallestLocation = Long.MAX_VALUE; // Simulating infinity*//*
        long i = 0;
        for (long source : s) {

            System.out.println("NEW seed: " + source);

            boolean shouldProcess = false;
            for (String line : lines) {
                if (line.endsWith(":")) {
                    shouldProcess = true;
                    continue;
                }

                if (shouldProcess && !line.isBlank()) {

                    *//*System.out.println(line);*//*

                    long[] mapping = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
                    long sourceRangeStart = mapping[1];
                    long rangeLength = mapping[2];

                    if (inRange(source, sourceRangeStart, rangeLength)) {
                        long destinationRangeStart = mapping[0];
                        source = calculateNewSource(source, sourceRangeStart, destinationRangeStart);
                        shouldProcess = false;
                    }


                }
            }

            smallestLocation = smallestLocation(smallestLocation, source);
        }
        return smallestLocation;*/
    }

/*    private HashMap<Integer, long[]> buildMap(List<String> lines) {
        HashMap<Integer, long[]> map = new HashMap<>();
        int[] key = {0};  // Array to allow modification inside lambda
        List<Long> currentNumbers = new ArrayList<>();

        lines.forEach(line -> {
            if (line.contains(":")) {
                if (!currentNumbers.isEmpty()) {
                    map.put(key[0]++, currentNumbers.stream().mapToLong(l -> l).toArray());
                    currentNumbers.clear();
                }
            } else if (!line.trim().isEmpty()) {
                Stream.of(line.split("\\s+")).mapToLong(Long::parseLong).forEach(currentNumbers::add);
            }
        });

        // Add the last set of numbers to the map
        if (!currentNumbers.isEmpty()) {
            map.put(key[0], currentNumbers.stream().mapToLong(l -> l).toArray());
        }

        return map;
    }*/

    //TODO
    private HashMap<Integer, long[]> buildMap(List<String> lines) {
        HashMap<Integer, long[]> map = new HashMap<>();
        int key = 0;
        List<Triplet> currentTriplets = new ArrayList<>();

        for (String line : lines) {
            if (line.contains(":")) {
                if (!currentTriplets.isEmpty()) {
                    map.put(key++, convertAndSortTriplets(currentTriplets));
                    currentTriplets.clear();
                }
            } else if (!line.trim().isEmpty()) {
                long[] nums = Arrays.stream(line.split("\\s+")).mapToLong(Long::parseLong).toArray();
                currentTriplets.add(new Triplet(nums[0], nums[1], nums[2]));
            }
        }

        // Add the last set of numbers to the map
        if (!currentTriplets.isEmpty()) {
            map.put(key, convertAndSortTriplets(currentTriplets));
        }

        return map;
    }

    private long[] convertAndSortTriplets(List<Triplet> triplets) {
        Collections.sort(triplets);
        return triplets.stream()
                .flatMapToLong(t -> LongStream.of(t.first, t.second, t.third))
                .toArray();
    }

    private long getSeedsFromRange(long[] seeds) {
        long smallestLocation = Long.MAX_VALUE;

        for (int i = 0; i < seeds.length; i+=2) {
            long startSeed = seeds[i];
            long rangeLength = seeds[i + 1];
            System.out.println("[" + startSeed + ", " + rangeLength + "]");
            for (long j = startSeed; j < startSeed + rangeLength; j++) {
                //long location = getLocation(j);
                long location = getLocationBinary(j);




                /*System.out.println("location: " + location);*/
                if (location < smallestLocation) {
                    smallestLocation = location;
                }
            }
        }
        //System.out.println("Smallest location: " + smallestLocation);
        return smallestLocation;
    }

    private long getLocation(long source) {
        for (int i = 0; i < result.size(); i++) {
            long[] possibleDestinations = result.get(i);

            /*for (long d : possibleDestinations) {
                //System.out.println("d" + d);
            }*/

            for (int j = 0; j < possibleDestinations.length; j+=3) {
                long destinationStart = possibleDestinations[j];
                long sourceStart = possibleDestinations[j + 1];
                long rangeLength = possibleDestinations[j + 2];


                if (source < sourceStart || source > sourceStart + rangeLength - 1) {
                    continue;
                }

                if (inRange(source, sourceStart, rangeLength)) {
                    source = calcNewSource(source, sourceStart, destinationStart);
                    //System.out.println("new source" + source);
                    break;
                }
            }
        }

        return source;
    }

    private long getLocationBinary(long source) {

        for (int i = 0; i < result.size(); i++) {
            /*System.out.println("FOR");*/
            long[] intervalls = result.get(i);
            long low = 1;
            long high = intervalls.length - 2;
            long mid;
            while (low <= high) {
                /*System.out.println("WHILE");*/

                // TODO old if
                /*if (high % 2 == 0) {*/


                if (low == high) {
                    /*System.out.println("LOW == HIGH");*/
                    mid = high;
                } else if ((low + high) % 2 == 0) {
                    mid = ((low + high) / 2);
                } else {
                    mid = ((low + high) / 2) + 2;
                }


                long rangeStart = intervalls[(int) mid];
                long rangeLength = intervalls[(int) mid + 1];

                /*System.out.println("source: " + source);
                System.out.println("rangeStart: " + rangeStart);
                System.out.println("rangeLength: " + rangeLength);*/
                if (isAboveRange(source, rangeStart, rangeLength)) {
                    /*System.out.println("ABOVE");*/

                    low = mid + 3;
                    // TODO old low
                    //low = mid + 3;
                } else if (isBelowRange(source, rangeStart)) {
                    /*System.out.println("BELOW");*/
                    high = mid - 3;
                    // TODO old high
                    //high = mid - 3;
                } else {
                    source = calcNewSource(source, rangeStart, intervalls[(int) mid - 1]);
                    low = high + 1;
                    /*System.out.println("NEW SOURCE: " + source);*/
                }
            }
        }
        return source;
    }

    private boolean isAboveRange(long source, long rangeStart, long rangeLength) {
        return source > rangeStart + rangeLength - 1;
    }

    private boolean isBelowRange(long source, long rangeStart) {
        return source < rangeStart;
    }

    private long calcNewSource(long source, long sourceStart, long destinationStart) {
        return ( - sourceStart) + (destinationStart + source);
    }

    private boolean inRange(long source, long rangeStart, long rangeLength) {
        return source >= rangeStart && source <= rangeStart + rangeLength - 1;
    }

    private long calculateNewSource(long previousSource, long sourceRangeStart, long destinationRangeStart) {
        return previousSource + (( - sourceRangeStart) + destinationRangeStart);
    }

    private long smallestLocation(long smallestLocation, long newLocation) {
        return Math.min(smallestLocation, newLocation);
    }
}
