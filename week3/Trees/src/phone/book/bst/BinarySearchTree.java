package phone.book.bst;

public class BinarySearchTree<T extends Comparable<T>> {

    public static class Node<E> {
        public E item;
        public Node<E> left;
        public Node<E> right;
        
        public Node() {
        	this(null, null, null);
        }
        
        public Node(E item, Node<E> left, Node<E> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }
    
    public Node<T> root;
    public int size;
    private StringBuilder sorted;
    
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }
    
    public int size() {
        return size;
    }
    
    /*
     * this 3-way if-else block rewrites equal elements
     * equal elements can be supported if the last else block is removed
     * and equality is added to one of the first two conditions
     * and don't forget to handle the size counter!
     */
    private void insertNode(Node<T> root, Node<T> newNode) {
        if (newNode.item.compareTo(root.item) < 0) {
            if (root.left == null) {
                root.left = newNode;
                ++size;
            } else {
                insertNode(root.left, newNode);
            }
        } else if (newNode.item.compareTo(root.item) > 0) {
            if (root.right == null) {
                root.right = newNode;
                ++size;
            } else {
                insertNode(root.right, newNode);
            }
        } else {
            root.item = newNode.item;   // rewrite equal items
        }
    }
    
    public void insert(T elem) {
        Node<T> newNode = new Node<T>(elem, null, null);
        
        if (root == null) {
            root = newNode;
            ++size;
        } else {
            insertNode(root, newNode);            
        }
    }
    
    private void traverseNodes(Node<T> root) {
        if (root == null) {
            return;
        }
        
        traverseNodes(root.left);
        sorted.append(root.item + "\n");
        traverseNodes(root.right);
    }
    
    public String traverse() {
        sorted = new StringBuilder();
    	traverseNodes(root);
    	return sorted.toString().trim();
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
        
        while (current.left != null) {
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
    
    private void replaceInParent(Node<T> unneeded, Node<T> replacement) {
        Node<T> parent = getParent(unneeded);
        
        if (parent == null) {
        	root = replacement;
        } else {
        	if (parent.left == unneeded) {
        		parent.left = replacement;
        	} else if (parent.right == unneeded) {
        		parent.right = replacement;
        	}
        }
    }
    
    private void removeNode(Node<T> node) {
        if (node == null) {
            return;
        }
        
        if (hasNoChildren(node)) {
            replaceInParent(node, null);
        } else if (hasOneChild(node)) {
            replaceInParent(node, onlyChild(node));
        } else {
            Node<T> replacement = leftMostChild(node.right);
            if (replacement == null) {
            	node.right.left = node.left;
            	replaceInParent(node, node.right);
            } else {
            	removeNode(replacement);
            	node.item = replacement.item;
            }
        }
    }
    
    public void remove(T elem) {
        Node<T> current = findNode(elem);
        removeNode(current);
        --size;
    }
    
}
