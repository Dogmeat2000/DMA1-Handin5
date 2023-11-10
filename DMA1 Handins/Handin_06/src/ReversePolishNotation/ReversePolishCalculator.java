package ReversePolishNotation;

import java.util.*;

public class ReversePolishCalculator
{
  private final Deque<Integer> stack = new ArrayDeque<>();

  public void push(int n) {
    // adds the number 'n' to the stack
    stack.push(n); // Time complexity: O(1) = constant time
  }

  public void plus() {
    //deletes the last number from the stack and put it in the variable "topelement"
    //stack.pop(), returns the top element and deletes it from the stack after that.
    int topelement = stack.pop(); // Time complexity: O(1) = constant time
    //deletes the last number from the stack and put it in the variable "topelement2", we deleted the last number before, so the second last is now the last number in the stack
    int topelement2 = stack.pop(); // Time complexity: O(1) = constant time
    //adds to sum of the 2 numbers to the stack
    stack.push(topelement2+topelement); // Time complexity: O(2) = constant time
  }

  public void minus() {
    //deletes the last number from the stack and put it in the variable "topelement"
    int topelement = stack.pop(); // Time complexity: O(1) = constant time
    //deletes the last number from the stack and put it in the variable "topelement2", we deleted the last number before, so the second last is now the last number in the stack
    int topelement2 = stack.pop(); // Time complexity: O(1) = constant time
    //adds the difference between the 2nd last number and the last number. we want to make it the correct order:
    //example: if we have a stack: 12345, and we want to add a minus method which takes the 2 last numbers and minus them, then we want it to say 4 - 5, and not 5 - 4, so we say topelement2 - topelement
    stack.push(topelement2-topelement); // Time complexity: O(2) = constant time
  }

  public void times() {
    //deletes the last number from the stack and put it in the variable "topelement"
    int topelement = stack.pop(); // Time complexity: O(1) = constant time
    //deletes the last number from the stack and put it in the variable "topelement2", we deleted the last number before, so the second last is now the last number in the stack
    int topelement2 = stack.pop(); // Time complexity: O(1) = constant time
    //adds the produkt of the two top elements to the stack.
    stack.push(topelement2*topelement); // Time complexity: O(2) = constant time
  }

  public int read() {
    //stack.peek(), returns the topelement, without deleting it.
    return stack.peek(); // Time complexity: O(1) = constant time
  }

  /*
  * To identify the worst case we can see in each code that:
  * All the methods plus(), minus() and times() has a worst case big Oh O(2), which means it remains constant.
  * the methods push(int n) and read(), has a worst case big Oh O(1), which also is constant.
  * We can see that O(2) is the one taking longest to perform which means our final big Oh expression is
  * T(n) = O(2)
  *
  */
}
