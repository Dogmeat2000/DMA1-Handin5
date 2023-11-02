package Program_3_decToBinary;

import java.util.Scanner;

public class dec2Bin
{
  public static String convertDec2Bin(int n)
  {
    // We initialise an output as a string
    String binaryNum = "";

    // We throw an exception if there is an illegal input
    if (n < 0)
      throw new IllegalArgumentException();

    // TODO implement method

    //Start of TODO
    // Special case: If the input is 0, the binary representation is "0"
    if (n == 0) {
      binaryNum = "0";
    } else {
      // Convert decimal to binary
      while (n > 0) {
        // Get the remainder when dividing by 2 (this gives the rightmost bit)
        int remainder = n % 2;

        // Build the binary representation by adding the remainder at the beginning
        binaryNum = remainder + binaryNum;

        // Update n by dividing it by 2 (shift to the next bit)
        n = n / 2;
        // End of TODO
      }
    }



    return binaryNum;
  }

  public static void main(String[] args)
  {
    while (true)
    {
      System.out.println("Type input:");
      Scanner in = new Scanner(System.in);
      int read = in.nextInt();
      System.out.println(convertDec2Bin(read));
      System.out.println();
    }
  }
}
