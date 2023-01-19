import java.io.IOException;
import java.io.RandomAccessFile;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is BufferPool class.
 * 
 * @author guangkai
 * @version 1 10/14/2022 2:19 pm
 */
public class BufferPool {

    private RandomAccessFile file;
    private DLL<Buffer> pool;
    private int size = 0;
    private int maxSize = 0;
    private boolean isSizeMax;
    private Statistic bPStat;

    /**
     * This is the constructor of BufferPool.
     * 
     * @param inputFile
     *            use when need to get new block.
     * @param numberOfBuffer
     *            as the max size of buffers.
     * @param stat
     *            as the stat
     * @param is
     *            as the boolean.
     * @throws IOException
     *             if file is not found.
     */
    public BufferPool(
        RandomAccessFile inputFile,
        int numberOfBuffer,
        Statistic stat,
        boolean is)
        throws IOException {

        isSizeMax = is;
        file = inputFile;
        maxSize = numberOfBuffer;
        pool = new DLL<Buffer>();
        bPStat = stat;
    }


    /**
     * This will create a new buffer base on the recPos
     * and insert into the bufferPool.
     * 
     * @param recPos
     *            as the requested position.
     * @return newBuffer.giveRecord(recPos) as the requested record.
     * @throws IOException
     *             if no such file.
     */
    public Record createBuffer(int recPos) 
        throws IOException {

        Buffer newBuffer = 
            new Buffer(file, bPStat, recPos);
        pool.insert(newBuffer);
        return newBuffer.giveRecord(recPos);
    }


    /**
     * This will give the maxHeap the rec it requested.
     * 
     * @param recPos
     *            as the requested position.
     * @return requested either find the record or didn't.
     * @throws IOException
     *             if no such file.
     */
    public Record giveRec(int recPos) throws IOException {

        Record requested;
        if (size == 0) {

            bPStat.cacheMissIncrease();
            bPStat.diskReadIncrease();
            requested = createBuffer(recPos);
            size++;

            // End the process immediately
            return requested;
        }
        else {

            LinkedNode<Buffer> target = pool.head;

            // Go through the DLL/bufferPool see if
            // requested position is in current buffers.
            while (target.getForward() != null) {

                target = target.getForward();
                if (target.getBuffer().isIn(recPos)) {

                    bPStat.cacheHitIncrease();
                    requested = target.getBuffer().
                        giveRecord(recPos);
                    if (size != 1) {
                        pool.remove(target);
                        pool.insert(target.getBuffer());
                    }

                    // End the process immediately
                    return requested;
                }
            }

            // If requested position is NOT in current
            // buffer.

            // Now target should point to the last node/buffer
            // of the DLL/bufferPool.

            // First: If bufferPool isn't reach maxSize limit.
            if (size < maxSize) {

                bPStat.cacheMissIncrease();
                bPStat.diskReadIncrease();
                requested = createBuffer(recPos);
                size++;

                // End the process immediately
                return requested;
            }
            // Second: If bufferPool reaches maxSize limit.
            else if (isSizeMax == false) {
                // false if block size > buffer number.

                // Now target should already point to the last
                // node/buffer of the DLL/bufferPool.

                // First: write the buffer back to the file
                // if it's dirty.
                target.getBuffer().fileWrite();
                bPStat.diskWriteIncrease();

                // Second: remove the last node/buffer
                pool.remove(target);

                // Third: create new buffer base on the
                // requested position.
                bPStat.cacheMissIncrease();
                bPStat.diskReadIncrease();
                requested = createBuffer(recPos);

                // End the process immediately
                return requested;
            }
        }

        return null;
    }


    /**
     * This is flush() method.
     * 
     * @throws IOException
     *             if no such file.
     */
    public void flush() throws IOException {

        LinkedNode<Buffer> target = pool.head;
        while (target.getForward() != null) {

            target = target.getForward();
            target.getBuffer().flush();
        }
    }


    /**
     * This will return the buffer by the specified
     * position.
     * 
     * Helper method for swap().
     * 
     * @param recPos
     *            as the requested position
     *            from MaxHeap.
     * @return targetBuffer if such buffer exist.
     */
    public Buffer giveBuffer(int recPos) {

        LinkedNode<Buffer> target = pool.head;
        Buffer targetBuffer;
        while (target.getForward() != null) {

            target = target.getForward();
            if (target.getBuffer().isIn(recPos)) {

                targetBuffer = target.getBuffer();
                return targetBuffer;
            }
        }
        return null;
    }


    /**
     * This is swap()
     * 
     * @param recPos1
     *            as the first position.
     * @param recPos2
     *            as the second position.
     * @throws IOException
     *             if no such file.
     */
    public void swap(int recPos1, int recPos2) throws IOException {

        Record rec1 = giveRec(recPos1);
        Record rec2 = giveRec(recPos2);
        Record temp = new Record(0, 0);
        temp.setTo(rec1);
        rec1.setTo(rec2);
        rec2.setTo(temp);

        Buffer buf1 = giveBuffer(recPos1);
        Buffer buf2 = giveBuffer(recPos2);
        if (buf1 != null)
            buf1.setDirty();
        if (buf2 != null)
            buf2.setDirty();

// Record rec1 = giveRec(recPos1);
// Record temp1 = new Record(0, 0);
// temp1.setTo(rec1);
//
// Record rec2 = giveRec(recPos2);
// Record temp2 = new Record(0, 0);
// temp2.setTo(rec2);
//
// rec2.setTo(temp1);
// Buffer buf2 = giveBuffer(recPos2);
//
// if (buf2 != null)
// buf2.setDirty();
//
// rec1 = giveRec(recPos1);
// rec1.setTo(temp2);
// Buffer buf1 = giveBuffer(recPos1);
// if (buf1 != null)
// buf1.setDirty();
    }
}
