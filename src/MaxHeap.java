import java.io.IOException;

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
 * Max-heap implementation by Patrick Sullivan, 
 * based on OpenDSA Heap code.
 * 
 * @author guangkai
 * @version 1 11/1/2022 9:59 pm
 */
class MaxHeap {

    private int n; // Number of things currently in heap
    private BufferPool pool; // BufferPool

    
    // Constructor supporting preloading of heap contents
    /**
     * This is the constructor.
     * 
     * @param heapSize
     *            as the number of the record.
     * @param bp
     *            as the bufferPool.
     * @throws IOException
     *             if no such file exist.
     */
    MaxHeap(int heapSize, BufferPool bp) throws IOException {

        n = heapSize;
        pool = bp;
        buildHeap();
    }


    /**
     * This will start to move the max value
     * in the front to the back.
     * 
     * @throws IOException if no such file
     */
    public void heapSort() throws IOException {

        while (n >= 0) {

            removeMax();
        }
    }


    // Return position for left child of pos
    /**
     * This will return the index of left child
     * of the position.
     * 
     * @param pos
     *            as the requested position.
     * @return 2 * pos + 1 as the left child.
     */
    public static int leftChild(int pos) {

        return 2 * pos + 1;
    }


    // Return position for the parent of pos
    /**
     * This will return the index of parent
     * of the giving position.
     * 
     * @param pos as the requested position.
     * @return (pos - 1) / 2 as the parent.
     */
    public static int parent(int pos) {

        return (pos - 1) / 2;
    }


    // Return true if pos a leaf position, false otherwise
    /**
     * This will return if such index is a leaf.
     * 
     * @param pos the requested position.
     * @return (n / 2 <= pos) && (pos < n) if
     *         given index is a leaf.
     */
    public boolean isLeaf(int pos) {

        return (n / 2 <= pos) && (pos < n);
    }


    // Organize contents of array to satisfy the heap structure
    /**
     * This will build the heap base on the
     * bufferPool
     * 
     * @throws IOException if no such file.
     */
    public void buildHeap() throws IOException {

        for (int i = parent(n - 1); i >= 0; i--) {
            
            siftDown(i);
        }
    }


    // Moves an element down to its correct place
    /**
     * This will siftDown pos.
     * 
     * @param pos as a int.
     * @throws IOException if no such file.
     */
    public void siftDown(int pos) throws IOException {

        assert (0 <= pos && pos < n) : "Invalid heap position";
        while (!isLeaf(pos)) {

            int child = leftChild(pos);
            if ((child + 1 < n) && 
                isGreaterThan(child + 1, child)) {

                child = child + 1; 
                // child is now index with the smaller value
            }
            if (!isGreaterThan(child, pos)) {

                return; // stop early
            }
            swap(pos, child);
            pos = child; // keep sifting down
        }
    }


    // Remove and return maximum value
    /**
     * This will swap 0 index and last index.
     * 
     * @throws IOException if no such file.
     */
    public void removeMax() throws IOException {

//        assert n > 0 : "Heap is empty; cannot remove";
        n--;
        if (n > 0) {

            // Swap maximum with last value
            swap(0, n); 
            // Put new heap root val in correct place
            siftDown(0);
        }
    }


    // swaps the elements at two positions
    /**
     * This will swap 2 pos.
     * 
     * @param pos1 as a int.
     * @param pos2 as a int.
     * @throws IOException if no such file.
     */
    private void swap(int pos1, 
        int pos2) throws IOException {

        pool.swap(pos1, pos2);
    }


    // does fundamental comparison used for checking heap validity
    /**
     * This will compare the key.
     * 
     * @param pos1 as the first position.
     * @param pos2 as the second position.
     * @return true if pos2's key is greater than pos2's
     * @throws IOException if no such file.
     */
    private boolean isGreaterThan(int pos1, 
        int pos2) throws IOException {

        Record rec1 = pool.giveRec(pos1);
        Record rec2 = pool.giveRec(pos2);

        return rec1.compareTo(rec2) > 0;
    }
}
