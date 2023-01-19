import student.TestCase;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is Statistic class.
 * 
 * @author guangkai
 * @version 10/31/2022 10:47 pm 1
 */
public class StatisticTest extends TestCase {
    
    /**
     * This will test entire Statistic class.
     */
    public void testStatistic() {
        
        Statistic stat = new Statistic();
        stat.cacheHitIncrease();
        stat.cacheMissIncrease();
        stat.diskReadIncrease();
        stat.diskWriteIncrease();
        
        assertEquals(1, stat.getCacheHit());
        assertEquals(1, stat.getCacheMiss());
        assertEquals(1, stat.getDiskRead());
        assertEquals(1, stat.getDiskWrite());
    }
}
