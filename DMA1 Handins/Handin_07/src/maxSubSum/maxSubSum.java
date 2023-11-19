package maxSubSum;

import java.util.*;

public class maxSubSum
{
  public int findMaxSubSum(ArrayList<Integer> input)
  {
    // TODO implement method

    // Check if the input list is empty
    if (input == null || input.isEmpty())
    {
      return 0;
    }

    //Initialize variables to keep track of current sum and maximum sum
    int currentSum = 0;
    int maxSum = 0;

    // Iterate through each element in the array
    for (int num : input)
    {
      // update the current sum by adding the current element. If the current sum becomes negative, reset it to 0
      currentSum = Math.max(0, currentSum + num);

      // Update the maximum sum if the current sum is greater
      maxSum = Math.max(maxSum, currentSum);
    }

    // The final result is the maximum sum found
    return maxSum;
  }
}
