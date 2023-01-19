import java.nio.ByteBuffer;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is Record.
 * 
 * Record implementation by Patrick Sullivan.
 * 
 * @author guangkai
 * @version 1 11/2/2022 12:07 am
 */
public class Record implements Comparable<Record> {

    /**
     * This is static value.
     */
    public static final int SIZE_IN_BYTES = 4;
    /**
     * This is static value.
     */
    public static final int BYTE_INDEX_KEY = 0;
    /**
     * This is static value.
     */
    public static final int BYTE_INDEX_VALUE = 2;
    /**
     * This is static value.
     */
    public static final int KEY_MAXIMUM = 30000;

    // This tiny ByteBuffer holds both the key and value as bytes
    private ByteBuffer bb;

    // makes a record and its backing ByteBuffer, useful for testing
    /**
     * This is the constructor.
     * 
     * @param key
     *            as a short.
     * @param val
     *            as a short.
     */
    public Record(short key, short val) {
        bb = ByteBuffer.allocate(SIZE_IN_BYTES);
        bb.putShort(BYTE_INDEX_KEY, key);
        bb.putShort(BYTE_INDEX_VALUE, val);
    }


    /**
     * This will return the key.
     * 
     * @return bb.getShort(BYTE_INDEX_KEY) as the key.
     */
    public short getKey() {
        return bb.getShort(BYTE_INDEX_KEY);
    }


    /**
     * This will return the value.
     * 
     * @return bb.getShort(BYTE_INDEX_VALUE) as the key.
     */
    public short getValue() {
        return bb.getShort(BYTE_INDEX_VALUE);
    }


    // makes quick testing even easier
    /**
     * This is the constructor.
     * 
     * @param key
     *            as a short.
     * @param val
     *            as a short.
     */
    public Record(int key, int val) {
        this((short)key, (short)val);
    }


    // Constructs using a given byte array. Does NOT copy but refers
    /**
     * This will setup byte array.
     * 
     * @param bytes
     *            as the byte array.
     */
    public Record(byte[] bytes) {
        bb = ByteBuffer.wrap(bytes);
    }


    // Makes a whole array of records that are backed by the given byte array
    // Caution: Changing the array will change records and vice versa!
    /**
     * This will turn byte array to record array.
     * 
     * @param binaryData
     *            as the byte array.
     * @return recs as the record array.
     */
    public static Record[] toRecArray(byte[] binaryData) {
        int numRecs = binaryData.length / SIZE_IN_BYTES;
        Record[] recs = new Record[numRecs];
        for (int i = 0; i < recs.length; i++) {
            int byteOffset = i * SIZE_IN_BYTES;
            ByteBuffer bb = ByteBuffer.wrap(binaryData, byteOffset,
                SIZE_IN_BYTES);
            recs[i] = new Record(bb.slice());
        }
        return recs;
    }


    // Constructs using a given byte buffer. Does NOT copy but refers
    /**
     * This will setup bb
     * 
     * @param bb
     *            as the input.
     */
    private Record(ByteBuffer bb) {
        this.bb = bb;
    }


    // copies the contents of another record. This is a DEEP copy.
    /**
     * This will set one record's key and value to
     * another record.
     * 
     * @param other
     *            as another record.
     */
    public void setTo(Record other) {
        bb.putShort(BYTE_INDEX_KEY, other.getKey());
        bb.putShort(BYTE_INDEX_VALUE, other.getValue());
    }


    @Override
    /**
     * This will compare current record's key
     * to another.
     * 
     * @param o
     *            as another record.
     * @return Short.compare(this.getKey(), o.getKey()) if current record's key
     *         is greater than other's.
     */
    public int compareTo(Record o) {
        return Short.compare(this.getKey(), o.getKey());
    }


    // A nice overview of the Record's contents.
    /**
     * This will print out the record.
     * 
     * @return sb.toString() as the printout of the
     *         record.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(this.getKey());
        sb.append(" ");
        sb.append(this.getValue());
        return sb.toString();
    }

}
