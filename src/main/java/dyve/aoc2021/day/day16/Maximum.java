package dyve.aoc2021.day.day16;

public class Maximum extends Operation{

    protected Maximum(String binString, int version, int type) {
        super(binString, version, type);
    }

    @Override
    public long evaluate() {
        return packets.stream().mapToLong(Packet::evaluate).max().orElseThrow();
    }
}
