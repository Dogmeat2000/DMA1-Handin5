package Program_6_squareMultiply;

public class squareMultiply
{
  public int modExpoCalculator(int base, int exponent, int modulo)
  {

    // Implement method here

    // Creating an int which is our result, starting with 1. the result will remain as the same if the exponent is 0.
    // a number with the power of 0 equals 1. 1 modulo a number will be 1. This equation only works if the numbers are positive numbers.
    int result = 1;

    // repeating until we reached our exponent
    // instead of using Math.pow(a,b), we use an for loop, where the base will multiply, until it repeated the exponent times.
    // example: a^6 <=> a*a*a*a*a*a
    for (int i = 0; i < exponent; i++)
    {
      // every time our base multiplies with an exponent, we take mod modulo
      // a base with the power of exponent modulo a number, is the same as taking the modulo every time you multiply the base with itself
      /* example:
       * a^3 % "mod"
       * result = a % "mod"
       * result = result * a % "mod"
       * result = result * a % "mod"
       */
      result = (result * base) % modulo;
    }
    // At the end we have our result
    return result;
  }
}
