package brackets;

import java.util.Scanner;
import java.util.Stack;

public class Brackets {

    public static class Pair {
        char closing;
        char opening;

        public Pair() {
            closing = '\0';
            opening = '\0';
        }
    }

    private static final String PRIORITY = "([{";

    public static char matchingClosing(char opening) {
        if (opening == '{') {
            return '}';
        } else if (opening == '[') {
            return ']';
        } else if (opening == '(') {
            return ')';
        }
        throw new IllegalArgumentException("invalid opening brace!");
    }

    public static boolean isOpening(char brace) {
        return brace == '{' || brace == '[' || brace == '(';
    }

    public static boolean isClosing(char brace) {
        return brace == ')' || brace == ']' || brace == '}';
    }

    public static boolean isBrace(char brace) {
        return isOpening(brace) || isClosing(brace);
    }

    public static boolean isValidExpression(String expr) {
        if (expr.length() < 2 || !isBrace(expr.charAt(0)) || !isBrace(expr.charAt(expr.length() - 1))) {
            return false;
        }

        Stack<Pair> stack = new Stack<Pair>();
        char symbol;

        for (int i = 0; i < expr.length(); ++i) {
            symbol = expr.charAt(i);
            if (Character.isDigit(symbol)) {
                continue;
            }

            if (!stack.isEmpty()) {             // validate expected element
                if (!(stack.peek().opening == symbol || stack.peek().closing == symbol)) {
                    return false;
                }
            }

            if (isClosing(symbol)) {            // handle stack invariant
                if (stack.isEmpty() || (stack.size() == 1 && i < expr.length() - 1)) {
                    return false;
                }
                stack.pop();
            } else if (isOpening(symbol)) {
                Pair newExpected = new Pair();
                if (PRIORITY.indexOf(symbol) > 0) {
                    newExpected.opening = PRIORITY.charAt(PRIORITY.indexOf(symbol) - 1);
                }
                newExpected.closing = matchingClosing(symbol);
                stack.push(newExpected);
            } else {
                throw new IllegalArgumentException("Invalid expression format!");
            }
        }

        return stack.isEmpty();
    }

    public static void sumUntilOpening(Stack<String> stack) {
        int sum = 0, base = 1;
        String temp = stack.pop();

        while (!isOpening(temp.charAt(0))) {
            if (temp.charAt(0) == '+') {            // already evaluated
                sum += Integer.valueOf(temp);
                base = 1;
            } else {                                // unevaluated digit
                sum += base * Integer.valueOf(temp);
                base *= 10;
            }

            if (stack.isEmpty()) {
                break;
            } else {
                temp = stack.pop();
            }
        }

        sum = stack.isEmpty() ? sum : 2 * sum;
        stack.push("+" + sum);
    }

    public static int evaluate(String expr) {
        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < expr.length(); ++i) {
            if (isOpening(expr.charAt(i)) || Character.isDigit(expr.charAt(i))) {
                stack.push(String.valueOf(expr.charAt(i)));
            } else if (isClosing(expr.charAt(i))) {
                sumUntilOpening(stack);
            }
        }

        return Integer.valueOf(stack.pop());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (isValidExpression(input)) {
            System.out.println(evaluate(input));
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }

}
