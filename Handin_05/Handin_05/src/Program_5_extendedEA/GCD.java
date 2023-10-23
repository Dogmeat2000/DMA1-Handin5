package Program_5_extendedEA;
import java.util.Scanner;
import java.io.*;
import java.util.*;

// This is a dependent class whose methods are used by
// extendedEA

public class GCD
{

  public static int findGCD(int a, int b)
  {
    // Copy your favorite GCD method from Program 1 here
    //THE BELOW MODEL IS A PLACEHOLDER MODEL FOUND ON THE INTERNET!
    // if b=0, a is the GCD
    if (b == 0)
      return a;

      // call the gcd() method recursively by
      // replacing a with b and b with
      // modulus(a,b) as long as b != 0
    else
      return findGCD(b, a % b);
    //THE ABOVE MODEL IS A PLACEHOLDER MODEL FOUND ON THE INTERNET!
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
