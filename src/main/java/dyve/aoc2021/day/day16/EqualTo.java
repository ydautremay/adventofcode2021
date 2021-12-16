package dyve.aoc2021.day.day16;

public class EqualTo extends Operation{

    protected EqualTo(String binString, int version, int type) {
        super(binString, version, type);
    }

    @Override
    public long evaluate() {
        if(packets.get(0).evaluate() == packets.get(1).evaluate()){
            return 1;
        }
        return 0;
    }
}
