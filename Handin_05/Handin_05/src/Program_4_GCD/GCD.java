package Program_4_GCD;

public class GCD
{
  // Euclidean algorithm is the most simple to implement

  public int findGCD1(int a, int b)
  {

    // Implement your method here that uses for loop
    return 42;
  }

  public int findGCD2(int a, int b)
  {
    // Implement your method here that uses a while loop
    return 42;
  }

  public int findGCD3(int a, int b)
  {
    // Implement your method here that uses for loop
    return findGCD3(b % a, a);
  }
}
