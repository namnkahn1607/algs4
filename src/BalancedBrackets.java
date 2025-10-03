import edu.princeton.cs.algs4.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BalancedBrackets {
    // 1. with stack
    public boolean isBalanced(String brackets) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (char c : brackets.toCharArray()) {
            if (map.containsKey(c)) {
                if (!stack.isEmpty() && stack.peek() == map.get(c))
                    stack.pop();
                else
                    return false;

            } else
                stack.push(c);
        }

        return stack.isEmpty();
    }

    // 2. without stack
    public boolean isBalanced2(String brackets) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        char[] bracketsArray = brackets.toCharArray();
        int i = -1;

        for (char c : bracketsArray) {
            if (map.containsKey(c)) {
                if (i >= 0 && bracketsArray[i] == map.get(c))
                    --i;
                else
                    return false;

            } else {
                bracketsArray[++i] = c;
            }
        }

        return i == -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String brackets = scanner.next();
        System.out.println(new BalancedBrackets().isBalanced(brackets));
        System.out.println(new BalancedBrackets().isBalanced2(brackets));
        scanner.close();
    }
}
