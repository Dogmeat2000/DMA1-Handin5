package Program_6_squareMultiply;

public class squareMultiply
{
  public int modExpoCalculator(int base, int exponent, int modulo)
  {

    // Implement method here

    // Creating an int which is our result, starting with "base" mod "modulo".
    int result = base % modulo;

    // repeating until we reached our exponent
    for (int i = 1; i < exponent; i++)
    {
      // every time our base multiplies with an exponent, we take mod modulo
      result = (result * base) % modulo;
    }
    // At the end we have our result
    return result;
  }
}
