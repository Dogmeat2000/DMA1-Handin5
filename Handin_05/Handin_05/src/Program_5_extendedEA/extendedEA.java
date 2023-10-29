package Program_5_extendedEA;

import java.util.Arrays;
import java.util.Scanner;

public class extendedEA
{
  public static int[] EAA(int a, int b)
  {
    // Return x and y in form a*x + b*y = gcd(a,b)
    // x is the inverse of a
    // There is only an inverse if gcd(a,b) == 1



    // Implement your method here

    /** I commented this part out, since I was failing the pre-built tests. Basically my intention here is to catch when no inverse exists instead of performing the calculation. However I have decided to comment this out for now.*/
    //Start by checking if GCD is equal to '1'. If not, we simply return '0'. However, I think returning 'null' would be better, if the proper error/exception handling had been set up.
    /*if ((GCD.findGCD(a, b)) != 1)
    {
      return new int[] {0, 0};
    }*/

    //Declare the variables we will be using below:
    int[] downwardRecurringIterationResult; /* [0 time units.]*/
    int x_CurrentIteration; /* [0 time units.]*/
    int y_CurrentIteration; /* [0 time units.]*/

    //Next we need to check if the 'b' value is zero. Since once, we have iterated our way to the end of the EEA algorithm the 'b' value should be zero for the gcd to return '1'.
    //If 'b' is zero, it means that this is the last iteration of this EEA call:

    if (b == 0) /** [Time complexity = O(1), constant time (base case)] */
    {
      //we know that 'x' must be 1 if 'b' is zero, since this also means that 'y' is zero. We also know that 'a' must equal the GCD, which we also know must be '1' at the lowest level - hence x must equal 1*a.
      x_CurrentIteration = 1; /** [Time complexity = O(1), constant time (base case)] */
      y_CurrentIteration = 0; /** [Time complexity = O(1), constant time (base case)] */
    }
    else
    {
      //Since we are not allowed to pass 'x' and 'y' values downward as arguments in this EEA method, we will instead need to find the bottom values and work our way upward in a recursive manner.
      //So at this point we simply call the next iteration of this EEA method, using the values we know will be 'a' and 'b' one level down:

      downwardRecurringIterationResult = EAA(b, (a % b)); /** [Time Complexity:
     [For every recursive call here, we have that [a0,b0] calculates into [b0, a0 % b0] = [a1, b1] and results in one of these two possibilities:
     * Case 1. 'b1' becomes a value larger than, or equal, to 'a0 / 2' -> The division by 2 is because this is the smallest value of division that will be applied, since division with '1' would result in an endless loop of modulus divisions.
     * The next recursive iteration will call a new value where b1 = a0 / 2, or where the largest value of b1 is half the size of 'a0'.
     * Case 1. Thus for this case we get T(n) = O(b/2)

     * Case 2. 'b1' becomes a value smaller than 'a0 / 2' -> this would result in the next iteration to calculate into 'a1 = b0', since prior iterations 'b' values in the euclidean algorithm turn into the next iterations 'a' values.
     * Case 2. Thus for this case we get T(n) = O(a/2)]

     * Thus we can express the Big-Oh notation for this recursive step as:
     * T(n) = O(a/2) + O(b/2)
     * */


      /**Let us use this calculation example for the remainder of this method, to illustrate what actually happens at each step:
       * We use this case as the example: EEA(102,53) -> We want to find 102 mod 53.
       * 1st iteration: Value of downwardRecurringIterationResults is EEA(53, (102 % 53)) = EEA(53,49)
       * 2nd iteration: Value of downwardRecurringIterationResults is EEA(49, (53 % 49)) = EEA(49,4)
       * 3rd iteration: Value of downwardRecurringIterationResults is EEA(4, (49 % 4)) = EEA(49,1)
       * 4th iteration: Value of downwardRecurringIterationResults is EEA(1, (4 % 1)) = EEA(1,0) <----- HERE THE RECURSIVE LOOP DOWNWARD BREAKS AND BEGINS WORKING UPWARD AGAIN, SINCE b = 0!

       * We know that for 'a' to be '1' and 'b' to be '0', 'x' and 'y' must each respectively be 'x = 1' and 'y = 0'. -> And the product of these values should equal the GCD of '1'.

       * At the 4th iteration the nested calls begin working their way up the recursive ladder. We get these return values:
       * 4th iteration returns [x=1, y=0]
       * 3rd iteration proceeds with the below code, using 'x4' = 1 and 'y4' = 0.*/

      //Since we have reached this line in the code, it means we are now working our way back upwards in our backward recurring sequence.

      /** Here we utilize the knowledge we have from how the EEA recursive algorithm works.
       * We know that 'b * y' in the current iteration becomes 'a * x' in the next, and that every iteration equals 1 (which is the GCD).
       * Example:
       * 0th iteration: a*x + b*y = 1
       * 1st iteration: a1*x1 + b1*y1 = 1
       * From the above relationship we equate these two iterations in a recurring manner: a*x + b*y = a1*x1 + b1*y1 = ..... = 1

       * Thus we can express this relationship between the 4th and 3rd iteration:
       * 4th iteration returns [x4=1, y4=0], where 'a4' = 1 and 'b4' = 0.

       * In order to express the 'x' and 'y' values for the current iteration we work our known values a bit:
       * a3*x3 + b3*y3 = a4*x4 + b4*y4
       * a3*x3 + b3*y3 = b3*x4 + (a3 % b3)*y4
       * a3*x3 + b3*y3 = b3*x4 + (a3 - b3*(a3|b3)) * y4
       * a3*x3 + b3*y3 = b3*x4 + a3*y4 - b3*(a3-(a3|b3)*y4
       * a3*x3 + b3*y3 = a3*y4 + b3*x4 - b3*(a3-(a3|b3)*y4
       * a3*x3 + b3*y3 = a3*y4 + b3*(x4 - (a3|b3)*y4)

       * Comparing the above coefficients using Bezout's theorem we see that for 'a3', both 'x3' and 'y4' are coefficients, and these are equal to each other. These coefficients are highlighted with [] in the below line:
       * a3*[x3} + b3*[y3] = a3*[y4} + b3*[(x4 - (a3|b3)*y4)}
       * thus 'x3' = 'y4' (or x_current_iteration will equal y_prior_iteration)

       * we also see for 'b3' that the coefficients are either 'y3' or '(x4-(a3|b3)*y4)', and that these also both are equal each other.
       * thus y3 = (x_prior_iteration - (a_current_iteration | b_current_iteration) * y_prior_iteration) */

      x_CurrentIteration = downwardRecurringIterationResult[1]; /** [Time complexity = O(1), constant time (recursive case)] */
      y_CurrentIteration = downwardRecurringIterationResult[0] - (a / b) * downwardRecurringIterationResult[1]; /** [Time complexity = O(4), constant time (recursive case)] */
      //The division operation above ignores decimal results since it is working on integers. This is intentional here, since we want the divisor without remainder.

    }

    //We have now calculated the current iterations x and y values, and can return these upward to the next recursive call, until we reach the initial call.
    return new int[] {x_CurrentIteration, y_CurrentIteration}; /** [Time complexity = O(1), constant time (base case)] */

    /** Final Time Complexity Calculation:
     * Since we want to express the time complexity as the worst-case value (Big-Oh), we can ignore constant time if we have non-constant time elements in our code. Let us review our lines of code:
     * From line 33 we have: T(n) = O(1), constant time (base case)
     * From line 36 we have: T(n) = O(1), constant time (base case)
     * From line 37 we have: T(n) = O(1), constant time (base case)
     * From line 44 we have: T(n) = O(a/2) + O(b/2)
     * From line 98 we have: T(n) = O(1), constant time (recursive case)
     * From line 105 we have: T(n) = O(4), constant time (recursive case)
     * From line 105 we have: T(n) = O(1), constant time (base case)

     * From the above lines, we see that in line 44, we have a non-constant time factor. Thus we ignore constant time in the other lines, and express our Big-Oh time complexity as:
     * T(n) = O(a/2) + O(b/2)

     * Observing the above Big-Oh expression, we can also see that the worst-case decrease (division by 2, which results in the most recursive iterations), is similar to a logarithmic function [x/n] - as opposed to an exponential one [x^n].
     * We can thus also express the Big-Oh notation as:
     * T(n) = O(log(a)) + O(log(b)).

     * Using basic math principles [log(x * y) = log(x) + log(y).] we can reduce this to a single expression, such that:
     * Final Big-Oh Notation -> T(n) = O(log(a * b))
     * */

  }

  // Do not change methods below;

  private static int mod(int a, int b)
  {
    return ((a % b) + b) % b;
  }

  public static int moduloInverse(int a, int b)
  {
    int[] result = EAA(a, b);
    return mod(result[0], b);
  }

  public static void main(String[] args)
  {
    // input
    System.out.println("Extended Euclidian Algorithm");
    Scanner scanner = new Scanner(System.in);
    System.out.println("Expression calculator for gcd(a, b)");
    System.out.print("Give value for a: ");
    int a = scanner.nextInt();
    System.out.print("Give value for b: ");
    int b = scanner.nextInt();
    System.out.println();
    System.out.println(Arrays.toString(EAA(a, b)));
    System.out.println(
        GCD.findGCD(a, b) + " = " + a + "(" + EAA(a, b)[0] + ")" + ((b < 0) ?
            " - " :
            " + ") + Math.abs(b) + "(" + EAA(a, b)[1] + ")");
    System.out.println((GCD.findGCD(a, b) != 1 ?
        "No inverse exists" :
        "The inverse of " + a + " mod " + b + " is " + EAA(a, b)[0]));

  }
}
