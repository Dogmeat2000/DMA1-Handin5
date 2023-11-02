package Program_5_extendedEA;
import java.util.Scanner;
import java.io.*;
import java.util.*;

// This is a dependent class whose methods are used by
// extendedEA

public class GCD
{

  public static int findGCD(int a, int b)
  {
    // Copy your favorite GCD method from Program 1 here

    if (b == 0) // if 'b' is equal to 0, means that 'a' is our gcd.
      /** [Time complexity = O(1), constant time] */
    {
      return a; /** [Time complexity = O(1), constant time] */
    }

    return findGCD(b,a % b); // Making a recursive call to find the gcd,using Euclidean algorithm
    /**[For every recursive call here, we have that [a,b] calculates into [b, a % b] and results in one of these two possibilities:
     * Case 1. 'b1' becomes a value larger than, or equal, to 'a0 / 2' -> The division by 2 is because this is the smallest value of division that will be applied, since division with '1' would result in an endless loop of modulus divisions. The next recursive iteration will call a new value where b1 = a0 / 2, or where the largest value of b1 is half the size of 'a0'.
     * Case 1. Thus for this case we get T(n) = O(b/2)

     * Case 2. 'b1' becomes a value smaller than 'a0 / 2' -> this would result in the next iteration to calculate 'a1 = b0', since prior iterations 'b' values in the euclidean algorithm turn into the next iterations 'a' values.
     * Case 2. Thus for this case we get T(n) = O(a/2)
     *
     * From the above 2 cases we can begin to establish a Big-Oh notation for this program. First we throw away all constant time, since we have identified the above non-constant time factors. We can express Big-Oh as:
     * O(n) = O(a/2) + O(b/2)
     *
     * Observing the above Big-Oh expression, we can also see that the worst-case decrease (division by 2, which results in the most recursive iterations), is similar to a logarithmic function - as opposed to an exponential one.
     * We can thus also express the Big-Oh notation as:
     * T(n) = O(log(a)) + O(log(b)).
     *
     * Using basic math principles [log(x * y) = log(x) + log(y).] we can reduce this to a single expression, such that:
     * Final Big-Oh Notation -> T(n) = O(log(a * b))
     * ] */
  }

  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    int x, y;
    System.out.println("Give two integers to calculate their gcd: ");
    x = input.nextInt();
    y = input.nextInt();
    System.out.println("GCD of " + x + " and " + y + " is: " + findGCD(x, y));
    input.close();
  }

}
