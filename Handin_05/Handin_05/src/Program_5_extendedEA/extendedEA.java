package Program_5_extendedEA;

import java.util.Arrays;
import java.util.Scanner;

public class extendedEA
{
  public static int[] EAA(int a, int b) {
    // Return x and y in form a*x + b*y = gcd(a,b)
    // x is the inverse of a
    // There is only an inverse if gcd(a,b) == 1

    // Implement your method here

    //Start by checking if GCD is equal to 1. If not, we simply return 0. However, I think returning null would be better, if the proper error/exception handling had been set up.
    if((GCD.findGCD(a,b)) != 1)
    {
      return new int[]{0,0};
    }

    //We now know that there must be an inverse. So we begin the algorithm calculations.
    //ax + by  =  gcd(a, b) or in other terms. Find the inverse of a mod b.
    //For this I expect the GCD method to be looping within itself until we have finished working through the entire extended euclidean algorithm.
    //Lets initialize a few variables to easier keep track of what is what:
    int iOriginal_a = a;
    int iOriginal_b = b;
    int iFirst_x = 0;
    int iFirst_y = 0;

    //Run GCD Algorithm to find the initial values of x and y.
    //GCD(a,b) = gcd(a, rem(b,a))
    //Example: GCD(102, 53) = GCD(53, rem(102, 53) = GCD(53, 49) -> Expressed with Bezout's theorem as: 49 = 1 * 102 - 1 * 53
    if(a - a%b >= 0)
    {
      iFirst_y = a % b; //102 % 53 = rem(102, 53)
    }

    iFirst_y = a % b; //102 % 53 = rem(102, 53)
    iFirst_x = a;
    System.out.println("first x = " + iFirst_x + " | first y = " + iFirst_y);




      return new int[]{42,42};
  }



  // Do not change methods below;

  private static int mod(int a, int b) {
    return ((a%b)+b) % b;
  }

  public static int moduloInverse(int a, int b) {
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
    System.out.println(Arrays.toString(EAA(a,b)));
    System.out.println(GCD.findGCD(a,b) + " = " + a + "(" + EAA(a,b)[0] + ")"
        + ((b < 0) ? " - " : " + ")
        + Math.abs(b) + "(" + EAA(a,b)[1] + ")");
    System.out.println((GCD.findGCD(a,b) != 1 ? "No inverse exists" : "The inverse of "
        + a + " mod " + b + " is " + EAA(a,b)[0]));

  }
}
