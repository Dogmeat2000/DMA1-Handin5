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
    //Start by checking if GCD is equal to 1. If not, we simply return 0. However, I think returning null would be better, if the proper error/exception handling had been set up.
    /*if ((GCD.findGCD(a, b)) != 1)
    {
      return new int[] {0, 0};
    }*/

    //Next we need to check if the b value is zero. As once we have iterated our way to the end of the EEA algorithm the b value should be zero for the gcd to return 1.
    //If b is zero, it means that this is the last iteration of this EEA call:
    int[] downwardRecurringIterationResult;

    /**[Note: For the time analysis we only look at the more complex factor of a and b.This being b, which we equate with n for time analysis]*/
    if (b == 0) /** [1 time unit (base case), 1 from == ]*/
    {
      //we know that x must be 1 if b is zero, since this also means that y is zero. We also know that a must equal the GCD, which we also know must be 1 at the lowest level.
      return new int[] {1, 0}; /** [1 time unit (base case), 1 from return ]*/
    }
    else
    {
      //Since we are not allowed to pass x and y values downward, we will instead need to find the bottom values and work our way upward in a recursive manner.
      //So at this point we simply call the next iteration of this EEA method, using the values we know will be a and b one level down:

      downwardRecurringIterationResult = EAA(b, (a % b)); /** [3 time unit (recursive case),1 from recursive call, 1 from = and 1 from % ----> This is T(n)=1+1*T(n) ]*/
    }

    /**Let us use this calculation example for the remainder of this method, to illustrate what actually happens at each step:
     * We use this case as the example: EEA(102,53) -> We want to find 102 mod 53.
       * 1st iteration: Value of downwardRecurringIterationResults is EEA(53, (102 % 53)) = EEA(53,49)
       * 2nd iteration: Value of downwardRecurringIterationResults is EEA(49, (53 % 49)) = EEA(49,4)
       * 3rd iteration: Value of downwardRecurringIterationResults is EEA(4, (49 % 4)) = EEA(49,1)
       * 4th iteration: Value of downwardRecurringIterationResults is EEA(1, (4 % 1)) = EEA(1,0) <----- HERE THE RECURSIVE LOOP DOWNWARD BREAKS AND BEGINS WORKING UPWARD AGAIN, SINCE b = 0!
     * We know that for a to be 1 and b to be 0, x and y must each respectively be x = 1 and y = 0. -> And the product of these values should equal the GCD of 1.
     *
     * At the 4th iteration the nested calls begin working their way up the recursive ladder. We get these return values:
       * 4th iteration returns [x=1, y=0]
       * 3rd iteration proceeds with the below code, using x4=1 and y4=0.*/

    //Since we have reached this line in the code, it means we are now working our way back upwards in our backward recurring sequence.

    //We now declare and initialize the relevant values for this iterations calculations:
    //We extract our x and y values from our downward recurring iteration.
    int x_PriorIteration = downwardRecurringIterationResult[0]; /** [1 time unit (recursive case), 1 from = ]*/
    int y_PriorIteration = downwardRecurringIterationResult[1]; /** [1 time unit (recursive case), 1 from = ]*/

    //Going back upwards to the next EEA iteration we know that:
    /** Here we utilize the knowledge we have from how the EEA recursive algorithm works.
     * We know that b*y in the current iteration becomes a*x in the next.
        * Example:
        * 0th iteration: a*x + b*y = 1
        * 1st iteration: a1*x1 + b1*y1 = 1
        * From the above relationship equate these two iterations in a recurring manner: a*x + b*y = a1*x1 + b1*y1 = ..... = 1

        * Thus we can express this relationship between the 4th and 3rd iteration:

        * 4th iteration returns [x4=1, y4=0], a4=1 and b4=0.

        * In order to express the x and y values for the current iteration we work our known values a bit:
        * a3*x3 + b3*y3 = a4*x4 + b4*y4
        * a3*x3 + b3*y3 = b3*x4 + (a3 % b3)*y4
        * a3*x3 + b3*y3 = b3*x4 + (a3 - b3*(a3|b3)) * y4
        * a3*x3 + b3*y3 = b3*x4 + a3*y4 - b3*(a3-(a3|b3)*y4
        * a3*x3 + b3*y3 = a3*y4 + b3*x4 - b3*(a3-(a3|b3)*y4
        * a3*x3 + b3*y3 = a3*y4 + b3*(x4 - (a3|b3)*y4)
        * Comparing the above coefficents using bezouts theorem we see that for a3, both x3 and y4 are coefficients, and these are equal to each other.
          * thus x3 = y4 (or x_current will equal y_prior_iteration)

        * we also see for b3 that the coefficients are either y3 or (x4-(a3|b3)*y4), and that these also both are equal each other.
          * thus y_current = (x_prior_iteration - (a_current|b_current)*y_prior_iteration)
     * */

    int x_CurrentIteration = y_PriorIteration; /** [1 time unit (recursive case), 1 from = ]*/
    int y_CurrentIteration = x_PriorIteration - (a / b) * y_PriorIteration; /** [4 time units (recursive case), 1 from =, 1 from -, 1 from / and 1 from * ]*/
    //The division operation ignores decimal results since it is working on integers. This is intentional here, since we want the divisor without remainder.

    //We have now calculated the current iterations x and y values, and can return these upward to the next recursive call, until we reach the initial call.
    return new int[] {x_CurrentIteration, y_CurrentIteration};

      /** Time Complexity Analysis / Algorithm Analysis:
       * Please refer to documentation marked in the code that are encapsulated with [ ] for the relevant parts relating to the time analysis.
       * For this analysis we assume n to be equal to b, since the recurring relationship only ends once b == 0.
       * We choose to use a the case where both the entered integers are either primes or relative primes to each other, as this will be the cases that cause the most recursions / consume the most time.
       * Base case = T(0) = 2           <--- When b=n=0, only 2 time units are spent.
       * T(n) = 1 + 1*T(n) + 1 + 1 + 1 + 4
       * Now we figure out a way to write T(n) as an explicit function of n:
       * T(n) = n + 6
       * Now we convert this to Big-Oh by ignoring the constants:
       * T(n) = O(n)
      */

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
