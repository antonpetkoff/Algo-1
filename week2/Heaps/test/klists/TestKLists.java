package klists;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import klists.KLists.Node;
import static org.junit.Assert.*;
import static klists.KLists.merge;

public class TestKLists {

    @Test
    public void testKLists() {
        Node a = new Node(3, new Node(5, new Node(7, new Node(9, null))));
        Node b = new Node(2, new Node(4, new Node(6, null)));
        Node c = new Node(0, new Node(1, new Node(8, new Node(10, null))));
        List<Node> lists = Arrays.asList(a, b, c);
        Node merged = merge(lists);

        int i = 0;
        while (merged != null) {
            assertEquals(i, merged.value);
            merged = merged.next;
            ++i;
        }
    }

}
