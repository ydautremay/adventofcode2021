package dyve.aoc2021.day.day16;

public class Literal extends Packet{

    long value = -1;

    protected Literal(String binString, int version, int type){
        super(binString, version, type);
    }

    @Override
    protected int decodeInternal() {
        int cursor = 6;
        String litteral = binString.substring(6);
        String valueStr = "";
        char readAfter = litteral.charAt(0);
        cursor++;
        int index = 0;
        while (readAfter == '1'){
            valueStr += litteral.substring(index + 1, index + 5);
            index += 5;
            readAfter = litteral.charAt(index);
            cursor += 5;
        }
        valueStr += litteral.substring(index + 1, index + 5);
        cursor += 4;
        value = Long.parseLong(valueStr, 2);
        return cursor;
    }

    @Override
    public long evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return "Litteral{" +
                "value=" + value +
                ", binString='" + binString + '\'' +
                ", version=" + version +
                ", typeId=" + typeId +
                ", packets=" + packets +
                '}';
    }
}
