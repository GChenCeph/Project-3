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
 * This is bufferPoolTest class.
 * 
 * @author guangkai
 * @version 1 10/31/2022 9:45 pm
 */
public class BufferPoolTest extends TestCase {

    /**
     * This will test the entire BufferPool class.
     * 
     * @throws IOException if no such file in the
     *         directory.
     */
    public void testBufferPoolBlock1() throws IOException {

        ByteFile bf = new ByteFile("testing1.bin", 7, 3);

        TestableRandom.setNextInts(1, 11, 2, 22, 3, 33, 4, 44);
        bf.writeRandomRecords();

        Statistic stat = new Statistic();
        RandomAccessFile file = new RandomAccessFile("testing1.bin", "rw");
        BufferPool pool = new BufferPool(file, 1, stat, false);
        
        assertNull(pool.giveBuffer(0));
        
        assertEquals(0, stat.getDiskRead());
        
        //assertNull(pool.giveRec(10000).getKey());

        assertEquals(1, pool.giveRec(0).getKey());
        assertEquals(1, stat.getDiskRead());
        assertEquals(11, pool.giveRec(0).getValue());

        assertEquals(1, stat.getCacheHit());

        pool.swap(0, 1);
        assertEquals(1, stat.getDiskRead());
        assertEquals(3, stat.getCacheHit());

        assertEquals(1, pool.giveRec(1).getKey());
        assertEquals(11, pool.giveRec(1).getValue());
        assertEquals(1, stat.getDiskRead());
        assertEquals(5, stat.getCacheHit());

        pool.flush();
        assertEquals(1, stat.getDiskWrite());

        Statistic stat2 = new Statistic();
        BufferPool newPool = new BufferPool(file, 1, stat2, false);

        assertEquals(2, newPool.giveRec(0).getKey());
        assertEquals(22, newPool.giveRec(0).getValue());
        assertEquals(1, newPool.giveRec(1).getKey());
        assertEquals(11, newPool.giveRec(1).getValue());

        assertEquals(1, stat2.getDiskRead());
        assertEquals(3, stat2.getCacheHit());
        
        assertEquals(2, pool.giveRec(0).getKey());
        assertEquals(1, stat.getDiskRead());
        assertEquals(22, pool.giveRec(0).getValue());
        
        assertEquals(27979, pool.giveRec(2505).getKey());
        assertEquals(2, stat.getDiskRead());
        
        assertEquals(2, pool.giveRec(0).getKey());
        assertEquals(3, stat.getDiskRead());
        assertEquals(22, pool.giveRec(0).getValue());
    }
    
    
    /**
     * This will test the entire BufferPool class.
     * 
     * @throws IOException if no such file in the
     *         directory.
     */
    public void testBufferPoolBlock2() throws IOException {
        
        ByteFile bf = new ByteFile("testing4.bin", 3, 3);

        TestableRandom.setNextInts(1, 11, 2, 22, 3, 33, 4, 44);
        bf.writeRandomRecords();

        Statistic stat2 = new Statistic();
        RandomAccessFile file2 = new RandomAccessFile("testing4.bin", "rw");
        BufferPool pool2 = new BufferPool(file2, 3, stat2, false);
        
        assertEquals(1, pool2.giveRec(0).getKey());
        
        assertEquals(25639, pool2.giveRec(1200).getKey());
        
        assertEquals(9547, pool2.giveRec(2400).getKey());
        
        assertEquals(1, pool2.giveRec(0).getKey());
    }
}
