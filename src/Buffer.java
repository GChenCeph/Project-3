import java.io.IOException;
import java.io.RandomAccessFile;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor 
// and integrity at all times.
// I will not lie, cheat, or steal, nor will I 
// accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is Buffer class.
 * 
 * @author guangkai
 * @version 1 10/14/2022 2:29 pm
 */
public class Buffer {

    private RandomAccessFile file;
    private boolean dirty;
    private Record[] rec = new Record[1024];
    private byte[] rec2 = new byte[4096];
    private int startIndexInFile;
    private int index;
    private int firstRec;
    private Statistic bStat;

    /**
     * This hold value.
     * 
     * @param inputFile
     *            as the inputFile
     * @param stat
     *            as the stat
     * @param recPos
     *            as the requested position
     * @throws IOException
     *             if no such file.
     */
    public Buffer(RandomAccessFile inputFile, 
        Statistic stat, int recPos)
        throws IOException {

        file = inputFile;
        index = recPos / 1024 + 1;
        startIndexInFile = (index - 1) * 4096;
        firstRec = (index - 1) * 1024;
        dirty = false;
        bStat = stat;
        fileRead(recPos);
    }


    /**
     * This will tell if the requested location from maxHeap
     * is in this buffer.
     * 
     * @param requestedPos
     *            is the location of the record in
     *            the file.
     * @return if requested position is in the range
     *         of this buffer.
     */
    public boolean isIn(int requestedPos) {

        return (requestedPos >= firstRec && 
            requestedPos < firstRec + 1024);
    }


    /**
     * This will set the buffer as dirty
     */
    public void setDirty() {

        this.dirty = true;
    }


    /**
     * This will return the status of this
     * buffer.
     * 
     * @return this.dirty if this buffer is
     *         dirty.
     */
    public boolean getDirty() {

        return this.dirty;
    }


    /**
     * This is getPosition() method.
     * 
     * @return rec.value as the element
     */
    public int getPosition() {

        return startIndexInFile;
    }


    /**
     * This will return block.
     * 
     * @return index of the block.
     */
    public int getBlock() {

        return index;
    }


    /**
     * This will give a record.
     * 
     * @param recPos
     *            as the requested position.
     * @return target as the requested record.
     */
    public Record giveRecord(int recPos) {

        Record target = rec[recPos - firstRec];
        return target;
    }


    /**
     * read from file by a specified offset.
     * Will use to renew the buffer.
     * 
     * @param recPos
     *            as the requested position.
     * @throws IOException
     *             if no such file.
     */
    public void fileRead(int recPos) throws IOException {

        fileWrite();
        file.seek(startIndexInFile);

        file.read(rec2);
        rec = Record.toRecArray(rec2);
    }


    /**
     * write data back to file.
     *
     * @throws IOException
     *             if nothing
     */
    public void fileWrite() throws IOException {

        if (dirty) {

            file.seek(startIndexInFile);
            file.write(rec2);
            dirty = false;
        }
    }


    /**
     * This will flush this buffer back to
     * the file.
     * 
     * @throws IOException
     *             if file not exist.
     */
    public void flush() throws IOException {

        fileWrite();
        bStat.diskWriteIncrease();
    }
}
