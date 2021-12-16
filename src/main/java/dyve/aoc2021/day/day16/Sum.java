package dyve.aoc2021.day.day16;

public class Sum extends Operation{

    protected Sum(String binString, int version, int type) {
        super(binString, version, type);
    }

    @Override
    public long evaluate() {
        return packets.stream().mapToLong(Packet::evaluate).sum();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
