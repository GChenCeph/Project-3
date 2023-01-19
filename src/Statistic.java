// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor
// and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is Statistic file, which aim to
 * record the data.
 * 
 * @author guangkai
 * @version 1 10/25/2022 9:54 am
 */
public class Statistic {

    private int cacheHit;
    private int cacheMiss;
    private int diskRead;
    private int diskWrite;
    
    
    /**
     * This is the constructor of Statistic.
     */
    public Statistic() {
        
        cacheHit = 0;
        cacheMiss = 0;
        diskRead = 0;
        diskWrite = 0;
    }
    
    
    /**
     * This will return cacheHit.
     * 
     * @return cacheHit as int.
     */
    public int getCacheHit() {
        
        return cacheHit;
    }
    
    
    /**
     * This will return cacheMiss.
     * 
     * @return cacheMiss as int.
     */
    public int getCacheMiss() {
        
        return cacheMiss;
    }
    
    
    /**
     * This will return diskRead.
     * 
     * @return diskRead as a int.
     */
    public int getDiskRead() {
        
        return diskRead;
    }
    
    
    /**
     * This will return cacheHit.
     * 
     * @return diskWrite as a int.
     */
    public int getDiskWrite() {
        
        return diskWrite;
    }
    
    
    /**
     * This increase cacheHit.
     */
    public void cacheHitIncrease() {
        
        cacheHit += 1;
    }
    
    
    /**
     * This will return cacheHit.
     */
    public void cacheMissIncrease() {
        
        cacheMiss += 1;
    }
    
    
    /**
     * This will return cacheHit.
     */
    public void diskReadIncrease() {
        
        diskRead += 1;
    }
    
    
    /**
     * This will return cacheHit.
     */
    public void diskWriteIncrease() {
        
        diskWrite += 1;
    }
}
