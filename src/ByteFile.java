
/**
 * Basic handling of binary data files.
 * Uses a single byte array as a buffer for disc operations
 * Assumes that Records are composed of a short key, and
 * a short value.
 * 
 * @author CS Staff
 * @version 2022 Oct 10
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Random;
import student.TestableRandom;

// Max-heap implementation by Patrick Sullivan, based on OpenDSA Heap code
// Can use `java -ea` (Java's VM arguments) to Enable Assertions
// These assertions will check for valid heap positions

// Many of these methods are not going to be useful for ExternalSorting...
// Prune those methods out if you don't want to test them.

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is MaxHeap.
 * 
 * ByteFile implementation by Patrick Sullivan.
 * 
 * @author guangkai
 * @version 1 11/1/2022 9:59 pm
 */
public class ByteFile {

    private File theFile;
    private int numBlocks;
    private long seed;

    /**
     * RECORDS_PER_BLOCKS as static value.
     */
    public static final int RECORDS_PER_BLOCK = 1024;

    /**
     * BYTES_PER_BLOCK as static value.
     */
    public static final int BYTES_PER_BLOCK = 
        RECORDS_PER_BLOCK * Record.SIZE_IN_BYTES; // 4096

    /**
     * This is the constructor of ByteFile.
     * 
     * @param filename
     *            as the name of the input file.
     * @param numBlocks
     *            as the number of the block.
     * @param setSeed
     *            as the number of seed.
     */
    public ByteFile(String filename, int numBlocks, int setSeed) {

        theFile = new File(filename);
        this.numBlocks = numBlocks;
        this.seed = setSeed;
    }


    // checks if a file of records is sorted or not
    /**
     * This will check if the file is sorted.
     * 
     * @return true if file is sorted.
     * @throws IOException
     *             if no such file exist.
     */
    public boolean isSorted() throws IOException {
        byte[] basicBuffer = new byte[BYTES_PER_BLOCK];

        RandomAccessFile raf = new RandomAccessFile(theFile, "r");
        try {
            short currKey = Short.MIN_VALUE;

            for (int block = 0; block < numBlocks; block++) {
                raf.read(basicBuffer);
                // ^^^ the slow operation! Buffer helps here.

                Record[] recsInBlock = Record.toRecArray(basicBuffer);
                for (int rec = 0; rec < RECORDS_PER_BLOCK; rec++) {
                    short nextKey = recsInBlock[rec].getKey();
                    if (currKey > nextKey) {
                        raf.close();
                        return false;
                    }
                    else {
                        currKey = nextKey;
                    }
                }
            }
        }
        finally {
            raf.close();
        }
        return true;
    }


    // creates a file of randomly generated records
    /**
     * This will provide a random file
     * based on designated block number.
     * 
     * @throws IOException
     *             if no such file.
     */
    public void writeRandomRecords() throws IOException {
        Random rng = new TestableRandom();
        if (seed != -1) {
            rng.setSeed(seed);
        }

        byte[] basicBuffer = new byte[BYTES_PER_BLOCK];
        ByteBuffer bb = ByteBuffer.wrap(basicBuffer);

        theFile.delete();
        // Deletes whatever content was there! This is important for if
        // you try using a file that already has lots of data, and you
        // don't reach the end of it. That old data would still be there
        // otherwise!

        RandomAccessFile raf = new RandomAccessFile(theFile, "rw");
        try {
            for (int block = 0; block < numBlocks; block++) {
                for (int rec = 0; rec < RECORDS_PER_BLOCK; rec++) {
                    short key = (short)rng.nextInt(Record.KEY_MAXIMUM);
                    short val = (short)rng.nextInt(Short.MAX_VALUE);
                    // puts the data in the basicBuffer...
                    bb.putShort(key);
                    bb.putShort(val);
                }
                raf.write(bb.array());
                // ^^^ the slow, costly operation!!! Good thing we use buffer
                bb.clear(); // resets the position of the buffer in array
            }
        }
        finally {
            raf.close();
        }
    }

}
