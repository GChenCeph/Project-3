import student.TestCase;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is DLLTest class.
 * 
 * @author guangkai
 * @version 1 10/31/2022 10:19 pm
 */
public class DLLTest extends TestCase {

    
    /**
     * This will test insert().
     */
    public void testInsert() {
        
        DLL<String> list =  new DLL<String>();
        
        list.insert("a");
        list.insert("b");
        
        assertEquals(2, list.getSize());
    }
    
    
    /**
     * This will test remove().
     */
    public void testRemove() {
        
        DLL<String> list =  new DLL<String>();
        
        list.insert("a");
        assertEquals(1, list.getSize());
        
        list.remove(list.head.getForward());
        assertEquals(0, list.getSize());
        
        list.insert("a");
        list.insert("b");
        assertEquals(2, list.getSize());
        
        list.remove(list.head.getForward());
        assertEquals(1, list.getSize());
        
        list.insert("b");
        list.remove(list.head.getForward().getForward());
        assertEquals(1, list.getSize());
    }
}
