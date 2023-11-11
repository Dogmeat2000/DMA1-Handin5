package ReversePolishNotation;

import java.util.*;

public class ReversePolishCalculator
{
  private final Deque<Integer> stack = new ArrayDeque<>();

  public void push(int n) {
    stack.push(n);
  }

  public void plus() {
    int topelement = stack.pop();
    int topelement2 = stack.pop();
    stack.push(topelement2+topelement);
  }

  public void minus() {
    int topelement = stack.pop();
    int topelement2 = stack.pop();
    stack.push(topelement2-topelement);
  }

  public void times() {
    int topelement = stack.pop();
    int topelement2 = stack.pop();
    stack.push(topelement2*topelement);
  }

  public int read() {
    return stack.peek();
  }
}
