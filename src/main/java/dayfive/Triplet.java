package dayfive;

class Triplet implements Comparable<Triplet> {
    long first;
    long second;
    long third;

    Triplet(long first, long second, long third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public int compareTo(Triplet other) {
        return Long.compare(this.second, other.second);
    }
}
