import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * External HeapsSort starter kit.
 * 
 * @author guangkai
 * @version 1 11/1/2022 11:31 pm
 */
public class HeapSort {

    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Look at the spec to see what arguments are used for!
        // String filename = args[0].trim();
        long starting;
        long ending;
        starting = System.currentTimeMillis();

        Statistic stat = new Statistic();

        File inputFile = new File(args[0]);
        String number = args[1];
        int numberOfBuffer = Integer.parseInt(number);

        RandomAccessFile file = new RandomAccessFile(inputFile, "rw");
        File statFile = new File(args[2]);

        int num = (int)file.length();
        int numberOfRecord = (int)(num / 4);
        int maxSize = 0;
        maxSize = numberOfRecord / 1024;
        boolean is = false;
        if (maxSize == numberOfBuffer) {

            is = true;
        }
        BufferPool pool = new BufferPool(file, numberOfBuffer, stat, is);
        MaxHeap heap = new MaxHeap(numberOfRecord, pool);
        heap.heapSort();
        pool.flush();

        ending = System.currentTimeMillis();
        long elapsed = ending - starting;

        // Print statements here
        for (int i = 0; i < file.length() / 4096; i++) {

            // Make sure to start new line every 8 records
            // Print first record for each block
            if (i != 0 && i % 8 == 0)
                System.out.println();

            file.seek(i * 4096);
            short key = file.readShort();
            file.seek((i * 4096) + 2);
            short value = file.readShort();
            System.out.printf("\t%5d %5d", key, value);

        }

        try (FileWriter fw = new FileWriter(statFile, true);
            BufferedWriter writer = new BufferedWriter(fw);) {

            writer.write("------  STATS ------\n");
            writer.write("File name: " + args[0] + "\n");
            writer.write("Cache Hits: " + stat.getCacheHit() + "\n");
            writer.write("Cache Misses: " + stat.getCacheMiss() + "\n");
            writer.write("Disk Reads: " + stat.getDiskRead() + "\n");
            writer.write("Disk Writes: " + stat.getDiskWrite() + "\n");
            writer.write("Time to sort: " + elapsed + "\n");
            writer.close();
        }
    }
}
