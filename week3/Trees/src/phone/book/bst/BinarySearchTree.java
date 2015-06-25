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
    
    private Node<T> findNode(T elem) {
        Node<T> current = root;
        
        while (current != null) {
            if (elem.compareTo(current.item) < 0) {
                current = current.left;
            } else if (elem.compareTo(current.item) > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        
        return null;
    }    
    
    public T search(T elem) {
        Node<T> temp = findNode(elem);
        return temp == null ? null : temp.item;
    }
    
    private Node<T> leftMostChild(Node<T> root) {
        Node<T> current = root;
        
        while (current != null) {
            current = current.left;
        }
        
        return current;
    }
    
    private boolean hasNoChildren(Node<T> node) {
        return node.left == null && node.right == null;
    }
    
    private boolean hasOneChild(Node<T> node) {
        return (node.left != null && node.right == null) || (node.left == null && node.right != null);
    }
    
    private Node<T> onlyChild(Node<T> node) {
        return node.left != null ? node.left : node.right;
    }
    
    private Node<T> getParent(Node<T> node) {
        Node<T> current = root, parent = null;
        
        while (current != null) {
            if (node.item.compareTo(current.item) < 0) {
                parent = current;
                current = current.left;
            } else if (node.item.compareTo(current.item) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }
        
        return parent;
    }
    
    private void removeFromParent(Node<T> node) {
        Node<T> parent = getParent(node);
        
        if (parent.left == node) {
            parent.left = null;
        } else if (parent.right == node) {
            parent.right = null;
        }
    }
    
    private void replaceInParent(Node<T> unneeded, Node<T> replacement) {
        Node<T> parent = getParent(unneeded);
        
        if (parent.left == unneeded) {
            parent.left = replacement;
        } else if (parent.right == unneeded) {
            parent.right = replacement;
        }
    }
    
    private void removeNode(Node<T> node) {
        if (node == null) {
            return;
        }
        
        if (hasNoChildren(node)) {
            removeFromParent(node);
        } else if (hasOneChild(node)) {
            replaceInParent(node, onlyChild(node));
        } else {
            Node<T> replacement = leftMostChild(node.right);
            node.item = replacement.item;
            removeNode(replacement);
        }
    }
    
    public void remove(T elem) {
        Node<T> current = findNode(elem);
        removeNode(current);
    }
    
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(5);
        bst.insert(2);
        bst.insert(7);
        bst.insert(3);
        bst.traverse();
        System.out.println();
        bst.remove(2);
        bst.traverse();
        System.out.println(bst.search(4));
    }
    
}
