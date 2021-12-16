package dyve.aoc2021.day.day16;

import java.util.ArrayList;
import java.util.List;

public abstract class Packet {

    String binString;

    int version;

    int typeId;

    List<Packet> packets = new ArrayList<>();

    protected Packet(String binString, int version, int type){
        this.binString = binString;
        this.version = version;
        this.typeId = type;
    }

    public static Packet of(String binString){
        String versionStr = binString.substring(0, 3);
        int version = Integer.parseInt(versionStr, 2);
        String typeStr = binString.substring(3, 6);
        int typeId = Integer.parseInt(typeStr, 2);
        return switch (typeId){
            case 0 -> new Sum(binString, version, typeId);
            case 1 -> new Product(binString, version, typeId);
            case 2 -> new Minimum(binString, version, typeId);
            case 3 -> new Maximum(binString, version, typeId);
            case 4 -> new Literal(binString, version, typeId);
            case 5 -> new GreaterThan(binString, version, typeId);
            case 6 -> new LessThan(binString, version, typeId);
            case 7 -> new EqualTo(binString, version, typeId);
            default -> new Operation(binString, version, typeId);
        };
    }

    abstract int decodeInternal();

    public abstract long evaluate();

    public int decode(){
        return decodeInternal();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "binString='" + binString + '\'' +
                ", version=" + version +
                ", typeId=" + typeId +
                ", packets=" + packets +
                '}';
    }
}
