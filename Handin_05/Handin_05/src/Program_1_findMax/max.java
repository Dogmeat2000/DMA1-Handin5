package Program_1_findMax;

public class max
{
  public int findMax(int input1, int input2)
  {

    // Creating a variable called int max, where we store the max value, among the two input numbers.

    int max;

    // Following comparison, checks if one argument is bigger than the other argument, and stores it in the integer variable called max, if true.

    if (input1 > input2)
    {

      max = input1;

    }

    // This comparison is executed if the previous statement is false. It does the same as the comparison above, but for the other argument, checking if it's bigger, and stores it in max.

    else
    {

      max = input2;

    }

    // finally we return the integer variable, max.
    return max;
  }
}
