package BalancedParenthesisChecking;

import java.util.*;

public class parenthesis
{
  public boolean checkParentheses(ArrayList<Character> input)
  {
    // TODO Insert your method here and update the return statement

    // Initialize a stack to keep track of open parentheses
    Stack<Character> stack = new Stack<>();

    // Iterate through each character in the input array and check for parentheses matching
    for (char ch : input)
    {
      // Check if the current character is an open parenthesis ('(' or '[') and push it onto the stack
      if (ch == '(' || ch == '[')
      {
        stack.push(ch);
      }
      // If it's a closing parenthesis, check if t matches the corresponding open parenthesis at the top of the stack
      else if (ch == ')' && !stack.isEmpty() && stack.peek() == '(')
      {
        stack.pop();
      }
      else if (ch == ']' && !stack.isEmpty() && stack.peek() == '[')
      {
        stack.pop();
      }
      // If it's neither an open nor closing parenthesis, return false (mismatched parenthesis).
      else
      {
        return false;
      }
    }

    // Check if the stack is empty at the end
    return stack.isEmpty();
  }
}
