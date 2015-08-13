package phone.book.bst;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import phone.book.bst.BinarySearchTree.Node;

public class TestBinarySearchTree {
	
	public static class BstPrinter {

	    public static <T extends Comparable<?>> void printNode(Node<T> root) {
	        int maxLevel = BstPrinter.maxLevel(root);

	        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
	    }

	    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
	        if (nodes.isEmpty() || BstPrinter.isAllElementsNull(nodes))
	            return;

	        int floor = maxLevel - level;
	        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
	        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
	        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

	        BstPrinter.printWhitespaces(firstSpaces);

	        List<Node<T>> newNodes = new ArrayList<Node<T>>();
	        for (Node<T> node : nodes) {
	            if (node != null) {
	                System.out.print(node.item);
	                newNodes.add(node.left);
	                newNodes.add(node.right);
	            } else {
	                newNodes.add(null);
	                newNodes.add(null);
	                System.out.print(" ");
	            }

	            BstPrinter.printWhitespaces(betweenSpaces);
	        }
	        System.out.println("");

	        for (int i = 1; i <= endgeLines; i++) {
	            for (int j = 0; j < nodes.size(); j++) {
	                BstPrinter.printWhitespaces(firstSpaces - i);
	                if (nodes.get(j) == null) {
	                    BstPrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
	                    continue;
	                }

	                if (nodes.get(j).left != null)
	                    System.out.print("/");
	                else
	                    BstPrinter.printWhitespaces(1);

	                BstPrinter.printWhitespaces(i + i - 1);

	                if (nodes.get(j).right != null)
	                    System.out.print("\\");
	                else
	                    BstPrinter.printWhitespaces(1);

	                BstPrinter.printWhitespaces(endgeLines + endgeLines - i);
	            }

	            System.out.println("");
	        }

	        printNodeInternal(newNodes, level + 1, maxLevel);
	    }

	    private static void printWhitespaces(int count) {
	        for (int i = 0; i < count; i++)
	            System.out.print(" ");
	    }

	    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
	        if (node == null)
	            return 0;

	        return Math.max(BstPrinter.maxLevel(node.left), BstPrinter.maxLevel(node.right)) + 1;
	    }

	    private static <T> boolean isAllElementsNull(List<T> list) {
	        for (Object object : list) {
	            if (object != null)
	                return false;
	        }

	        return true;
	    }

	}

	public static Random rand = new Random();
	
	public static int randomRange(int lo, int hi) {
		return lo + (int) (rand.nextDouble() * (hi - lo));
	}

	/**
	 * Generates a random binary search tree with unique values (no equality).
	 */
	public static class RandomBst {
		private List<Integer> values;
		private Node<Integer> root;
		
		public RandomBst(int lo, int hi) {
			this.values = new ArrayList<>();
			Node<Integer> root = new Node<>();
			generateChildren(root, lo, hi);
			this.root = root;
		}

		public Node<Integer> getRoot() {
			return root;
		}
		
		public List<Integer> getValues() {
			return values;
		}
		
		private void generateChildren(Node<Integer> root, int lo, int hi) {
			int rand = randomRange(lo, hi);
			values.add(rand);
			root.item = rand;

			if ((root.item - 1) - lo > 0) {
				root.left = new Node<Integer>();
				generateChildren(root.left, lo, root.item - 1);
			}

			if (hi - (root.item + 1) > 0) {
				root.right = new Node<Integer>();
				generateChildren(root.right, root.item + 1, hi);
			}
		}
	}

	public static class BstChecker {
		public static boolean isBST(Node<Integer> root) {
			return isBSTRange(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}

		public static boolean isBSTRange(Node<Integer> root, int left, int right) {
			if (root == null || root.item == null) {
				return true;
			}

			if (root.item >= left && root.item <= right) {	// >= and <= supports equal elements in the BST
				return isBSTRange(root.left, left, root.item) && isBSTRange(root.right, root.item, right);
			}

			return false;
		}
	}

	@Test
	public void testGenerator() {
		for (int i = 0; i < 1000; i++) {
			assertTrue(BstChecker.isBST(new RandomBst(-1000, 1000).getRoot()));
		}
	}
	
	@Test
	public void testInsertSearchSize() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertTrue(BstChecker.isBST(bst.root));
		assertEquals(0, bst.size());
		
		for (int i = 0; i < 1000; ++i) {
			testInsertValueAndSearch(bst, null);
			assertEquals(i + 1, bst.size());
		}
	}
	
	public static void testInsertValueAndSearch(BinarySearchTree<Integer> bst, List<Integer> values) {
		int item = rand.nextInt();
		bst.insert(item);
		assertTrue(BstChecker.isBST(bst.root));
		assertEquals(Integer.valueOf(item), bst.search(item));
		
		if (values != null) {
			values.add(item);
		}
	}
	
	public static void testRemoveSearchSize(int lo, int hi) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		RandomBst randomTree = new RandomBst(lo, hi);
		List<Integer> values = randomTree.getValues();
		bst.root = randomTree.getRoot();
		bst.size = values.size();
//		BstPrinter.printNode(bst.root);
		
		while (bst.root != null) {
			testRemoveRandomValue(bst, values);
		}
		
		assertEquals(0, bst.size());
		assertTrue(BstChecker.isBST(bst.root));
	}
	
	@Test
	public void testRemoveSearchSizeBulk() {
		for (int i = 0; i < 100; i++) {
			testRemoveSearchSize(-1000, 1000);
		}
	}

	@Test
	public void testEqualElements() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		final int COUNT = 9;

		assertEquals(null, bst.search(1));
		bst.insert(1);
		assertEquals(Integer.valueOf(1), bst.search(1));
		assertTrue(BstChecker.isBST(bst.root));
		
		for (int i = 0; i < COUNT; ++i) {
			bst.insert(1);
			assertEquals(Integer.valueOf(1), bst.search(1));
			assertTrue(BstChecker.isBST(bst.root));
		}
		
		for (int i = 0; i < COUNT; ++i) {
			bst.remove(1);
			assertEquals(Integer.valueOf(1), bst.search(1));
			assertTrue(BstChecker.isBST(bst.root));
		}
		
		assertEquals(Integer.valueOf(1), bst.search(1));
		bst.remove(1);
		assertEquals(null, bst.search(1));
		assertTrue(BstChecker.isBST(bst.root));
	}
	
	public static void testRemoveRandomValue(BinarySearchTree<Integer> bst, List<Integer> values) {
		int index = randomRange(0, values.size() - 1);
//		System.out.println("After removing " + values.get(index) + ": ");
		assertEquals(values.get(index), bst.search(values.get(index)));
		assertEquals(values.size(), bst.size());
		bst.remove(values.get(index));
//		BstPrinter.printNode(bst.root);
		assertTrue(BstChecker.isBST(bst.root));
		assertEquals(null, bst.search(values.get(index)));
		values.remove(index);
		assertEquals(values.size(), bst.size());
	}
	
	public static void testTreeLifecycle(int lo, int hi, double removeProb, int maxSize) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertTrue(BstChecker.isBST(bst.root));
		RandomBst randomTree = new RandomBst(lo, hi);
		List<Integer> values = randomTree.getValues();
		bst.root = randomTree.getRoot();
		bst.size = values.size();
		
		while (bst.root != null && bst.size() < maxSize) {
			if (rand.nextDouble() < removeProb) {	// remove
				testRemoveRandomValue(bst, values);
			} else {	// insert
				testInsertValueAndSearch(bst, values);
			}
		}
	}
	
	@Test
	public void testAllOperations() {
		for (int i = 0; i < 100; ++i) {
			testTreeLifecycle(-1000, 1000, 0.8, 100_000);
		}
	}
	
}
