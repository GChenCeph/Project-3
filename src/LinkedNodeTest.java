import student.TestCase;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is LinkedNode class.
 * 
 * @author guangkai
 * @version 10/31/2022 10:47 pm 1
 */
public class LinkedNodeTest extends TestCase {

    /**
     * This will test the entire LinkedNode<T>
     * class.
     */
    public void testLinkedNode1() {
        
        LinkedNode<String> str1 = new LinkedNode<String>("a");
        LinkedNode<String> str2 = new LinkedNode<String>("b");
        LinkedNode<String> str3 = new LinkedNode<String>("c");
        
        assertEquals("a", str1.getBuffer());
        
        str1.setForward(str2);
        str2.setForward(str3);
        
        str2.setBackward(str1);
        str3.setBackward(str2);
        
        assertEquals("c", 
            str1.getForward().getForward().getBuffer());
        
        assertEquals("a", 
            str3.getBackward().getBackward().getBuffer());
    }
}
