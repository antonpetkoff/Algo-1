package online.median;

import java.util.Iterator;

public class SortedSequence implements Iterable<Integer> {
    
    protected class ListNode {
        int value;
        ListNode prev;
        ListNode next;
        
        ListNode(int value) {
            this.value = value;
        }
        
        ListNode(int value, ListNode prev, ListNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    
    private ListNode first;
    private ListNode last;
    private int size;
    
    private ListNode middle;
    private int leftSize;
    private int rightSize;
    
    SortedSequence() {
        first = last = middle = null;
        size = leftSize = rightSize = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
    }
    
    public Integer getMinimum() {
        return first != null ? first.value : null;
    }
    
    public Integer getMaximum() {
        return last != null ? last.value : null;
    }
    
    public int getMedian() {
        if (size == 0) {
            return 0;
        }
        return middle.value;
    }
    
    public void add(Integer elem) {
        if (first == null) {
            first = last = middle = new ListNode(elem, null, null);
            ++size;
            leftSize = rightSize = 0;
            return;
        }
        
        // maintain sorted invariant
        ListNode temp = last;
        while ((temp != null) && (temp.value > elem)) {
            temp = temp.prev;
        }
        
        if (temp == null) {             // add before first node
            linkNodeAsFirst(elem);
        } else {                        // add after temp node or last node
            linkNodeAfter(temp, elem);
        }
        
        ++size;
        
        if (elem < middle.value) {
            ++leftSize;
        } else {
            ++rightSize;
        }
        
        // maintain middle node invariant
        if (leftSize < rightSize) {
            ++leftSize;
            --rightSize;
            middle = middle.next;
        } else if (leftSize - 1 > rightSize) {
            --leftSize;
            ++rightSize;
            middle = middle.prev;
        }
        // else invariant is maintained
        
    }
    
    private void linkNodeAsFirst(Integer elem) {
        ListNode newNode = new ListNode(elem, null, first);
        first.prev = newNode;
        first = newNode;
    }
    
    private void linkNodeAfter(ListNode pivot, Integer elem) {
        ListNode newNode = new ListNode(elem);
        newNode.prev = pivot;
        
        if (pivot.next != null) {
            newNode.next = pivot.next;
            pivot.next.prev = newNode;
        } else {
            newNode.next = null;
            last = newNode;
        }
        
        pivot.next = newNode;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new SortedListIterator();
    }
    
    private class SortedListIterator implements Iterator<Integer> {

        private ListNode head = first;
        
        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public Integer next() {
            Integer value = head.value;
            head = head.next;
            return value;
        }

        @Override
        public void remove() {
            // no need for remove
        }
        
    }
    
}
