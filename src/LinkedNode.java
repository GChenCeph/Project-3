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
 * @param <T> Generic Type
 */
public class LinkedNode<T> {

    private LinkedNode<T> forward;
    private LinkedNode<T> backward;
    private T rec;

    
    /**
     * This hold value.
     * 
     * @param elem as the rec.
     */
    public LinkedNode(T elem) {
        rec = elem;
    }
    
    
    /**
     * This is element() method.
     * 
     * @return rec.value as the element
     */
    public T getBuffer() {
        return rec;
    }


    /**
     * This is getForward() method.
     * 
     * @return forward as record/element.
     */
    public LinkedNode<T> getForward() {
        return this.forward;
    }
    
    
    /**
     * This is setForward() method.
     * 
     * @param node  The node
     */
    public void setForward(LinkedNode<T> node) {
        forward = node;
    }


    /**
     * This is getForward() method.
     * 
     * @return forward as record/element.
     */
    public LinkedNode<T> getBackward() {
        return backward;
    }
    
    
    /**
     * This is setForward() method.
     * 
     * @param node  The node
     */
    public void setBackward(LinkedNode<T> node) {
        backward = node;
    }
}
