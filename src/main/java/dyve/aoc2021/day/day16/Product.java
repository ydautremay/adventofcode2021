package dyve.aoc2021.day.day16;

public class Product extends Operation{

    protected Product(String binString, int version, int type) {
        super(binString, version, type);
    }

    @Override
    public long evaluate() {
        return packets.stream().mapToLong(Packet::evaluate).reduce(1, Math::multiplyExact);
    }
}
