import java.io.IOException;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This class purpose is to test MaxHeap.
 * 
 * @author guangkai
 * @version 1 10/31/2022 11:45 pm
 */
public class FBP extends BufferPool {

    private Record[] recs;

    
    /**
     * This will mimic bufferPool.
     * 
     * @param inputRecs as the records.
     * @throws IOException if no such file.
     */
    public FBP(Record[] inputRecs) throws IOException {

        super(null, 1, null, false);
        recs = inputRecs;
    }


    /**
     * This will mimic bufferPool's
     * giveRec() method.
     * 
     * @param recPos is the requested
     *        position from MaxHeap.
     * @return recs[recPos] as the requested
     *         record.
     */
    public Record giveRec(int recPos) {

        assert (0 <= recPos && 
            recPos < recs.length) : "Invalid heap position";
        return recs[recPos];
    }


    /**
     * This will mimic swap() in bufferPool.
     * 
     * @param recPos1 as the 1st position.
     * @param recPos2 as the 2nd position.
     */
    public void swap(int recPos1, int recPos2) {
        
        Record rec1 = giveRec(recPos1);
        Record rec2 = giveRec(recPos2);

        Record temp = new Record(0, 0);
        temp.setTo(rec1);
        rec1.setTo(rec2);
        rec2.setTo(temp);
    }
}
