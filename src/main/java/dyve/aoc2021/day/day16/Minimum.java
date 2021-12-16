package dyve.aoc2021.day.day16;

public class Minimum extends Operation{

    protected Minimum(String binString, int version, int type) {
        super(binString, version, type);
    }

    @Override
    public long evaluate() {
        return packets.stream().mapToLong(Packet::evaluate).min().orElseThrow();
    }
}
