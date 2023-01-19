import java.io.IOException;
import java.io.RandomAccessFile;
import student.TestCase;
import student.TestableRandom;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is bufferTest class.
 * 
 * @author guangkai
 * @version 1 10/31/2022 9:45 pm
 */
public class BufferTest extends TestCase {

    
    /**
     * This will test the entire Buffer class.
     * 
     * @throws IOException if no such file in the
     *         directory.
     */
    public void testFullbuffer() throws IOException {

        ByteFile bf = new ByteFile("testing3.bin", 1, 4);

        TestableRandom.setNextInts(1, 11, 2, 22, 3, 33, 4, 44);
        bf.writeRandomRecords();
        
        Statistic stat = new Statistic();
        RandomAccessFile file = new RandomAccessFile("testing3.bin", "rw");
        
        Buffer buf = new Buffer(file, stat, 0);
        
        assertFalse(buf.isIn(1024));
        assertFalse(buf.isIn(-1));
        assertTrue(buf.isIn(2));
        
        assertEquals(0, buf.getPosition());
        assertEquals(1, buf.getBlock());
        buf.setDirty();
        assertEquals(true, buf.getDirty());
    }
}
