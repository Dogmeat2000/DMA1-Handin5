package findClosest;

import java.util.*;

public class closest
{
  public int findClosest(ArrayList<Integer> input)
  {
    //Collections.sort() sorts the Arraylist, from the lowest to highest number.
    Collections.sort(input); //Time complexity: O(1) = constant time
    //We create a new variable which will store the lowest difference we can find. to define a value at start we can use Integer.MAX_VALUE, which is the highest number an integer can store.
    int mindiff = Integer.MAX_VALUE; // Time complexity: O(1) = constant time

    //We create a fori loop which will start from index 0 in the arraylist, and stop before we reach the last index in the arraylist.
    for (int i = 0; i < input.size() - 1; i++) // Time complexity: O(n) = linear time
    {
      //if the difference between the input with index i and the next index shorter than the current difference, then it will update to the new lowest difference we find.
      if (input.get(i + 1) - input.get(i) < mindiff)
      {
        mindiff = input.get(i + 1) - input.get(i); // Time complexity: O(1) = constant time
      }
    }

    // TODO implement method
    //After the fori loop, we have now found the lowest difference
    return mindiff;


    /**
     * To identify the worst case we can see in each code that:
     * line 10: Time Complexity = O(1)
     * line 12: Time Complexity = O(1)
     * line 15: Time Complexity = O(n)
     * line 20: Time Complexity = O(1)
     * We can see that our worst case Big Oh will be: O(n)
     */
  }
}
