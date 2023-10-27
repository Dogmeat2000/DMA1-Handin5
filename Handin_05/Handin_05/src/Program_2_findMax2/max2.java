package Program_2_findMax2;

public class max2
{
  public int findMax2(int[] input)
  {
    // TODO implement method
    int highestnumber = 0;
    for (int i = 0; i<input.length;i++)
    {
      if (highestnumber < input[i])
      {
        highestnumber = input[i];
      }
    }
    return highestnumber;
  }
}
