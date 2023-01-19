// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor
// and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
//
// -- guangkai

/**
 * This is DLL class, DLL stands for
 * "Double-linked List".
 * 
 * @author guangkai guangkai@vt.edu
 * @version 1 9/21/2022 7:15 pm
 * @param <T>
 *            Generic Type
 */
public class DLL<T> {

    /**
     * Here classify LinkListedNode as protected.
     */
    protected LinkedNode<T> head;
    private int size;
    

    /**
     * Basic structure of SkipList.
     */
    public DLL() {
        head = new LinkedNode<T>(null);
        size = 0;
    }
    
    
    /**
     * This will return DLL's size.
     * 
     * @return size as the size of this DLL.
     */
    public int getSize() {
        
        return size;
    }


    /**
     * Insert a object to the list.
     * 
     * @param elem as the rec.
     */
    public void insert(T elem) {

        LinkedNode<T> newNode = (LinkedNode<T>)new LinkedNode<T>(elem);

        if (head.getForward() == null) {

            head.setForward(newNode);
            newNode.setBackward(head);
        }
        else {

            LinkedNode<T> update = head.getForward();

            head.setForward(newNode);
            newNode.setForward(update);

            update.setBackward(newNode);
            newNode.setBackward(head);
        }

        size++;
    }


    /**
     * Remove a object to the list.
     * And when call this method, we confirmed
     * that this node we used is the not the first one
     * in the DLL.
     * 
     * @param node as the node.
     */
    public void remove(LinkedNode<T> node) {

        if (size != 1) {

            LinkedNode<T> temp = node.getBackward();
            if (node.getForward() != null) {

                temp.setForward(node.getForward());
                node.getForward().setBackward(temp);
            }
            else {

                temp.setForward(null);
            }
        }
        else {

            head.setForward(null);
        }
        size--;
    }
}
