package brackets;

import java.util.Stack;

public class Brackets {

    public static boolean isOpening(char brace) {
        return brace == '{' || brace == '[' || brace == '(';
    }
    
    public static boolean isClosing(char brace) {
        return brace == ')' || brace == ']' || brace == '}';
    }
    
    public static void sumUntilOpening(Stack<String> stack) {
        int sum = 0, base = 1;
        String temp = stack.pop();
        
        while (!isOpening(temp.charAt(0))) {
            if (temp.charAt(0) == '+') {                // already evaluated   
                sum += Integer.valueOf(temp);
                base = 1;                               // reset base
            } else {                                    // unevaluated digit
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
        System.out.println(evaluate("[123(145)38(37)812]"));
    }
    
}
