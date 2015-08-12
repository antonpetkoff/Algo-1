package phone.book.bst;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import phone.book.bst.BinarySearchTree.Node;

public class TestBinarySearchTree {

	public static class BstGenerator {
		public static Random rand = new Random();

		public static int randomRange(int lo, int hi) {
			return lo + (int) (rand.nextDouble() * (hi - lo));
		}

		public static Node<Integer> generateTree(int lo, int hi) {
			Node<Integer> root = new Node<Integer>(randomRange(lo, hi), new Node<Integer>(null, null, null),
					new Node<Integer>(null, null, null));
			generateChildren(root.left, lo, root.item - 1);
			generateChildren(root.right, root.item + 1, hi);
			return root;
		}

		public static void generateChildren(Node<Integer> root, int lo, int hi) {
			if (hi - lo < 1) {
				return;
			}
			root.item = randomRange(lo, hi);

			if ((root.item - 1) - lo > 0) {
				root.left = new Node<Integer>(0, null, null);
				generateChildren(root.left, lo, root.item - 1);
			}

			if (hi - (root.item + 1) > 0) {
				root.right = new Node<Integer>(0, null, null);
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

			if (root.item > left && root.item < right) {
				return isBSTRange(root.left, left, root.item) && isBSTRange(root.right, root.item, right);
			}

			return false;
		}
	}

	public static final int LO = -100000;
	public static final int HI = 100000;
	public static final int TESTS = 1000;
	
	@Test
	public void testGenerator() {
		for (int i = 0; i < TESTS; i++) {
			assertTrue(BstChecker.isBST(BstGenerator.generateTree(LO, HI)));
		}
	}

}
