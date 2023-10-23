package Program_5_extendedEA;
import java.util.Scanner;

// This is a dependent class whose methods are used by
// extendedEA

public class GCD
{

  public static int findGCD(int a, int b)
  {
    // Copy your favorite GCD method from Program 1 here
    return 42;
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
