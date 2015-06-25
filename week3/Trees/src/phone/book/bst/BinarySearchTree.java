package phone.book.bst;

/**
 *  left sub-tree has elements <= root
 *  right sub-tree has element > root
 */
public class BinarySearchTree<T extends Comparable<T>> {

    public static class Node<E> {
        public E item;
        public Node<E> left;
        public Node<E> right;
        
        public Node(E item, Node<E> left, Node<E> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }
    
    private Node<T> root;
    private int size;
    
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }
    
    public int size() {
        return size;
    }
    
    private void insertNode(Node<T> root, Node<T> newNode) {
        if (newNode.item.compareTo(root.item) <= 0) {
            if (root.left == null) {
                root.left = newNode;
            } else {
                insertNode(root.left, newNode);
            }
        } else {
            if (root.right == null) {
                root.right = newNode;
            } else {
                insertNode(root.right, newNode);
            }
        }
    }
    
    public void insert(T elem) {
        Node<T> newNode = new Node<T>(elem, null, null);
        
        if (root == null) {
            root = newNode;
        } else {
            insertNode(root, newNode);            
        }
        
        ++size;
    }
    
    private void traverseNodes(Node<T> root) {
        if (root == null) {
            return;
        }
        
        traverseNodes(root.left);
        System.out.println(root.item);
        traverseNodes(root.right);
    }
    
    public void traverse() {
        traverseNodes(root);
    }
    
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(5);
        bst.insert(2);
        bst.insert(7);
        bst.traverse();
    }
    
}
