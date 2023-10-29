package Program_6_squareMultiply;

public class squareMultiply
{
  public int modExpoCalculator(int base, int exponent, int modulo)
  {

    // Implement method here

    // Creating an int which is our result, starting with 1. the result will remain as the same if the exponent is 0.
    // a number with the power of 0 equals 1. 1 modulo a number will be 1. This equation only works if the numbers are positive numbers.
    int result = 1; /** [Time complexity = O(1), constant time] */

    // repeating until we reached our exponent
    // instead of using Math.pow(a,b), we use an for loop, where the base will multiply, until it repeated the exponent times.
    // example: a^6 <=> a*a*a*a*a*a
    for (int i = 0; i < exponent; i++) /** [Time complexity = O(n), linear time. This since we let n be 'exponent', which defines the amount of iterations for this loop.] */
    {
      // every time our base multiplies with an exponent, we take mod modulo
      // a base with the power of exponent modulo a number, is the same as taking the modulo every time you multiply the base with itself
      /* example:
       * a^3 % "mod"
       * result = a % "mod"
       * result = result * a % "mod"
       * result = result * a % "mod"
       */
      result = (result * base) % modulo; /** [Time complexity = O(3), constant time] */
    }
    // At the end we have our result
    return result; /** [Time complexity = O(1), constant time] */

    /** Time Complexity Analysis:
     * We observe the lines of code. The principle is to identify the "worst-case" performance as Big-Oh. As such if we have performance worse than constant time, we simply ignore the constant factor.
     * From line 12 we have T(n) = O(1) constant time.
     * From line 17 we have T(n) = O(n) linear time.
     * From line 27 we have T(n) = O(3) constant time.
     * From line 30 we have T(n) = O(1) constant time.

     * Since we have a part of the code that is repeated a linear number of times, we can simply ignore the constant time factor, and provide our final Big-Oh expression as:
     * T(n) = O(n).
     * */

  }
}
