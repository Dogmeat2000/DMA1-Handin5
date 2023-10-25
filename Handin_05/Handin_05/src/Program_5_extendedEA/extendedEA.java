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
    if (b == 0)
    {
      //we also overload our return value here with the remaining 3 values (a, x and y) so that we might push these upward in our nested iteration stack.
      //we know that x must be 1 if b is zero, since this also means that y is zero. We also know that a must equal the GCD, which we also know must be 1 at the lowest level.
      return new int[] {1, 0};
    }

    //Since we are not allowed to pass x and y values downward, we will instead need to find the bottom values and work our way upward in a recurring manner.
    //So at this point we simply call the next iteration of this EEA method, using the values we know will be a and b one level down:
    int[] downwardRecurringIterationResult = EAA(b, (a % b));

    //Since we have reached this line in the code, it means we are now working our way back upwards in our backward recurring sequence.
    //We now declare and initialize the relevant values for this iterations calculations:

    //We extract our x and y values from our downward recurring iteration.
    int x_CurrentIteration = downwardRecurringIterationResult[0];
    int y_CurrentIteration = downwardRecurringIterationResult[1];

    //Going back upwards to the next EEA iteration we know that:
    /** Here we utilize the knowledge that the value a in the current iteration would become the value b in the next downward iteration.
     * Since there is a multiplicative relationship between ax and by we simply treat the known x/y values as if they were a/b values*/
    //x, one level up is equal to the current iterations y value.
    int x_OneIterationUp = y_CurrentIteration;
    int y_OneIterationUp = x_CurrentIteration - (a / b) * y_CurrentIteration; //The division operation ignores decimal results since it is working on integers. This is intentional here.

    return new int[] {x_OneIterationUp, y_OneIterationUp};




















    /* We now know that there must be a multiplicative inverse. So we begin the algorithm calculations.
    ax + by (Bezout's theorem)  is equal to  gcd(a, b) or in other terms: Find the inverse of a mod b.

    //Now we check if either a or b is zero.


    First we need to assign the user input to proper variables. */
    /*int original_a = a;
    int original_b = b;
    int x = 1;
    int y = -1; //We begin at minus 1, since we are only using and accepting positive integer input. Thus all solutions will involve a negative factor.

    /*Now we must express our initial x and y values as they are connected to the a and b values.
    /*For this we need to determine whether a subtraction or addition is proper.

    Example: GCD(102, 53) = GCD(53, rem(102, 53) = GCD(53, 49) -> Expressed with Bezout's theorem as: 49 = 1 * 102 - 1 * 53*/

    //We establish a while loop, that runs until we have iterated through the EEA algorithm until the remainder between the last iterations value of a (prior_a) and b (prior_b) is 0.
    /*int i1stWhileLoopIteration = 0;

    System.out.println("a1 is : " + original_a);
    System.out.println("b1 is : " + original_b);



    while (((original_a * x) + (original_b * y)) != (original_a % original_b) && i1stWhileLoopIteration < 20)
    {
      System.out.println("a2 is : " + original_a);
      System.out.println("b2 is : " + original_a);
      //This is done through a bruteforce method. Continuously adding/subtraction to/from either x or y until and expression that is equal to the remainder between original_a and original_b is identified.
      //We check if the first part of bezout's identity (a*x) is larger than twice the size of the second part (b*x). If yes, we decrease the size of y by 1. If no, we increase the size of x by 1.

      if ((original_a * x) + (original_b * y) >= (original_a * x))
      {
        y--;
      }
      else
      {
        x++;
      }

      System.out.println("Loop iteration #" + i1stWhileLoopIteration + "\nAttempting to calculate: " + original_a + " % " + original_b + " (" + original_a % original_b + ")" + " = " + original_a + "(a) * " + x + "(x) + " + original_b + "(b) * " + y + "(y) + (" + ((original_a * x) + (original_b * y)) + ")\n--------");
      i1stWhileLoopIteration++;
    }

    System.out.println("here");



    int counter = 0;
    while(original_a * x + original_b * y != 1 && counter < 4)
    {

      System.out.println("Nested execution #" + counter + "\nAttempting to calculate: " + original_a + " % " + original_b + " (" + original_a % original_b + ")" + " = " + original_a + "(a) * " + x + "(x) + " + original_b + "(b) * " + y + "(y) + (" + ((original_a * x) + (original_b * y)) + ")\n--------");



      x = (nextIteration[1] * x) + nextIteration[1];
      System.out.println("\nx is : " + x);

      y = ((nextIteration[0]) + (nextIteration[1] * y) + nextIteration[1]);
      System.out.println("y is : " + y);
      //4  =  1 ∗ 53 − 1 ∗ (1 ∗ 102 − 1 ∗ 53)
      //4  =  1 ∗ 53 − (102 − 53)
      //4  =  1 ∗ 53 − 1 ∗ 49

      // (rem(34,13)) 8 = 1 * 34 - 2 * 13

      // (rem(13,8)) 5 = 1*13 - 1 * 8
      // (rem(13,8)) 5 = 1*13 - 1 * (1 * 34 - 2 * 13)
      /*counter++;

    }

    return new int[] {x, y};*/
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
