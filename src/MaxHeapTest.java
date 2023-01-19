import java.io.IOException;
import student.TestCase;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This will test MaxHeap.
 * 
 * @author guangkai
 * @version 1 10/31/2022 11:45 pm
 */
public class MaxHeapTest extends TestCase {

    /**
     * This will use fake bufferPool to test
     * MaxHeap.
     * 
     * @throws IOException
     *             if no such file exist.
     */
    public void testMaxHeapFakeBP() throws IOException {

        Record[] recs = new Record[10];

        recs[0] = new Record(4, 44);
        recs[1] = new Record(2, 22);
        recs[2] = new Record(3, 33);
        recs[3] = new Record(9, 99);
        recs[4] = new Record(5, 55);
        recs[5] = new Record(6, 66);
        recs[6] = new Record(10, 100);
        recs[7] = new Record(7, 77);
        recs[8] = new Record(8, 88);
        recs[9] = new Record(1, 11);

        FBP fPool = new FBP(recs);

        MaxHeap heap = new MaxHeap(10, fPool);

        for (int i = 0; i < 10; i++) {

            System.out.println(fPool.giveRec(i).toString());
        }
        assertEquals(10, fPool.giveRec(0).getKey());

        assertFalse(heap.isLeaf(11));
        assertFalse(heap.isLeaf(1));
        assertFalse(heap.isLeaf(-1));

//        try {
//
//            heap.siftDown(12);
//        }
//        catch (AssertionError e) {
//
//            System.out.println("Invalid heap position");
//        }
//
//        try {
//
//            heap.siftDown(-1);
//        }
//        catch (AssertionError e) {
//
//            System.out.println("Invalid heap position");
//        }

        assertTrue(heap.isLeaf(6));
        //assertEquals(10, fPool.giveRec(0).getKey());

        heap.heapSort();

//        assertEquals(10, fPool.giveRec(0).getKey());
    }
}
