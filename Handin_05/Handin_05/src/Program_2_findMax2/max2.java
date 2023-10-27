package Program_2_findMax2;

public class max2
{
  public int findMax2(int[] input)
  {
    // TODO implement method

    //Creating an int which will represent our highest number, starting with the first number
    int highestnumber = input[0];

    //Inside the loop we compare the int which represent the higest number with the current number in the loop, and set the number to the hightest number int, if it is higher
    for (int i = 0; i<input.length;i++)
    {
      if (highestnumber < input[i])
      {
        highestnumber = input[i];
      }
    }
    //At the end, after the loop, we now have our highest number in the variable "highestnumber"
    return highestnumber;
  }
}
