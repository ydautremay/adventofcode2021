package dyve.aoc2021.day.day16;

public class Operation extends Packet{

    protected Operation(String binString, int version, int type){
        super(binString, version, type);
    }

    @Override
    protected int decodeInternal() {
        int cursor = 6;
        String operation = binString.substring(6);
        char lengthType = operation.charAt(0);
        cursor++;
        if(lengthType == '0'){
            String totalLengthStr = operation.substring(1, 16);
            cursor += 15;
            int totalLength = Integer.parseInt(totalLengthStr, 2);
            String toRead = operation.substring(16, 16 + totalLength);
            int decodedLength = 0;
            while(decodedLength < totalLength){
                Packet packet = Packet.of(toRead);
                packets.add(packet);
                int read = packet.decode();
                toRead = toRead.substring(read);
                decodedLength += read;
            }
            cursor += decodedLength;
            return cursor;
        }else{
            String nbSubStr = operation.substring(1, 12);
            cursor += 11;
            int nbSub = Integer.parseInt(nbSubStr, 2);
            String nextPackets = operation.substring(12);
            int read = 0;
            for(int i = 0; i < nbSub; i++){
                Packet packet = Packet.of(nextPackets);
                packets.add(packet);
                read += packet.decode();
                nextPackets = operation.substring(12 + read);
            }
            return cursor + read;
        }
    }

    @Override
    public long evaluate(){return 0;}

    @Override
    public String toString() {
        return super.toString();
    }
}
