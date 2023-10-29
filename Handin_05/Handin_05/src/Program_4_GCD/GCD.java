package Program_4_GCD;

public class GCD
{
  // Euclidean algorithm is the most simple to implement

  public int findGCD1(int a, int b)
  {
    // checks if one of the input numbers are negative, if true, then throwing a exception.
    if (a < 0 || b < 0)
    {
      throw new RuntimeException("Not working!");
    }

    //declaring and initializing a variable, and assigning the initial value to be 1.
    int gcd = 1;
    {
        /*/ The loop control variable is set to 1. The loop will continue illiterate as long as
        i <= a and i <= b are true.
         */

      for (int i = 1; i <= a && i<= b; i++)
      {

          /*/ we're using the modulo operator, and checking if the remainder of
          a and b are equal to 0.... If both statement true, then the variable gcd
          is set to i, because common divisor found and now we're updating the gcd.
           */
        if (a % i == 0 && b % i == 0)
        {
          gcd = i;
        }
      }
    }
    // returning the gcd
    return gcd;
  }


  public int findGCD2(int a, int b)
  {
    // continuing looping as long as a not equal to b
    while(a != b)
    {

      if (a > b) // if a is greater than b, then a can be divided by b, with a remainder.
      {
        a -= b; // if a is greater than b, then (a = a-b), here we're finding the remainder.

        // executes following code, because b is greater or equal to a.

      }
      else
      {
        b -= a; // finding the remainder, by (b = b-a) to find the remainder.

      }

    }

    return a; /*/ The code above reduce a og b until they are equal. When they are equal, then the gcd is found.
                    We return the value a, because it's the gcd of the input arguments, a and b.
                    */
  }

  public int findGCD3(int a, int b)
  {

    if (b == 0) // if b is equal to 0, means that a is our gcd.
    {
      return a;
    }

    return findGCD3(b,a % b); // Making a recursive call to find the gcd,using Euclidean algorithm
  }
}
