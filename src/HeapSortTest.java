import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.junit.Test;

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
 * This will test HeapSort class..
 * 
 * @author guangkai
 * @version 1 11/1/2022 11:32 pm
 */
public class HeapSortTest extends TestCase {

    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     * 
     * @throws Exception
     *             is no such file.
     */
    @Test
    public void testMain() throws Exception {

        Path source = (Path)Paths.get("beforeSorting", "sampleBlock1");
        Path target = (Path)Paths.get("sampleBlock1");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        HeapSort dum = new HeapSort();
        assertNotNull(dum);
        String[] str = new String[3];
        str[0] = "sampleBlock1";
        str[1] = "1";
        str[2] = "stat.txt";
        HeapSort.main(str);
        ByteFile file = new ByteFile(str[0], 1, -1);
        assertTrue(file.isSorted());
        assertEquals("\t   17 14365", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     *
     * @throws Exception
     *             is no such file.
     */
    @Test
    public void testMain2() throws Exception {

        Path source = (Path)Paths.get("beforeSorting", "sampleBlock3");
        Path target = (Path)Paths.get("sampleBlock3");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        String[] str = new String[3];
        str[0] = "sampleBlock3";
        str[1] = "3";
        str[2] = "stat.txt";
        HeapSort.main(str);
        ByteFile file = new ByteFile(str[0], 3, -1);
        assertTrue(file.isSorted());
        assertEquals("\t    7 22820\t10261  5233\t20085  8765" + "", systemOut()
            .getHistory());
        systemOut().clearHistory();
    }

// /**
// * An artificial test to get initial coverage for the
// * main method. Delete or modify this test.
// *
// * @throws Exception
// */
// @Test
// public void testMain2Buffer() throws Exception {
//
// ByteFile bf = new ByteFile("testing2B2B.bin", 2, 3);
//
// TestableRandom.setNextInts(1, 11, 2, 22, 3, 33);
// bf.writeRandomRecords();
//
// String[] str = new String[3];
// str[0] = "testing2B2B.bin";
// str[1] = "2";
// str[2] = "stat.txt";
// HeapSort.main(str);
// ByteFile file = new ByteFile(str[0], 2, -1);
// assertTrue(file.isSorted());
// assertEquals("\t 1 11\t15413 5200", systemOut().getHistory());
// systemOut().clearHistory();
// }


    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     * 
     * @throws Exception
     *             is no such file.
     */
    @Test
    public void testMain3() throws Exception {

        Path source = (Path)Paths.get("beforeSorting", "sampleBlock10");
        Path target = (Path)Paths.get("sampleBlock10");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        String[] str = new String[3];
        str[0] = "sampleBlock10";
        str[1] = "4";
        str[2] = "stat.txt";
        HeapSort.main(str);
        ByteFile file = new ByteFile(str[0], 10, -1);
        assertTrue(file.isSorted());
        assertEquals("\t    1  3213\t 3086 20444\t 5921  1216\t 9052  5240"
            + "\t12044 23160\t15136 30909\t18020  5744\t21088  4657\r\n"
            + "\t24136 17409\t27020 30093", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     * 
     * @throws Exception
     *             is no such file.
     */
    @Test
    public void testMain4() throws Exception {

        Path source = (Path)Paths.get("beforeSorting", "sampleBlock50");
        Path target = (Path)Paths.get("sampleBlock50");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        String[] str = new String[3];
        str[0] = "sampleBlock50";
        str[1] = "8";
        str[2] = "stat.txt";
        HeapSort.main(str);
        ByteFile file = new ByteFile(str[0], 50, -1);
        assertTrue(file.isSorted());
        assertEquals("\t    0 13264\t  568 13206\t 1191 16924\t 1785 13287"
            + "\t 2394 12538\t 2973  2674\t 3588 14607\t 4183 23785\r\n"
            + "\t 4802  3929\t 5383 30882\t 6005 12806\t 6570 27904"
            + "\t 7198 31848\t 7790  9280\t 8386 24415\t 8976 15579\r\n"
            + "\t 9558 17099\t10136 27774\t10764 28524\t11370 30380"
            + "\t11964  9504\t12579 22807\t13169 22042\t13765 29036\r\n"
            + "\t14333 22993\t14946 26109\t15549 22916\t16133  8486"
            + "\t16711  8421\t17306 29845\t17904 24085\t18511  4107\r\n"
            + "\t19089 21764\t19746  1020\t20356 16572\t20936 22694"
            + "\t21517 32059\t22104 26860\t22713 22635\t23333 28688\r\n"
            + "\t23954   851\t24536 12118\t25152  6553\t25759  9295"
            + "\t26344 29246\t26987 26925\t27608  2460\t28180 31338\r\n"
            + "\t28774  6278\t29397 12728", systemOut().getHistory());
        systemOut().clearHistory();
    }
}
