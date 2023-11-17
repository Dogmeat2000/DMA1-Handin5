package closestBall;
import java.util.*;
/*/ The overall time complexity for the following code is O(m+n).
When sorting each arraylist it takes O(m*log(m)) and O(n*log(n)), we can re-write this to
O(m + n).
 */
public class closestBall
{
  public int computeClosest(ArrayList<Integer> players,
      ArrayList<Integer> balls)
  {

    // sorting to get the list in ascending order.
    Collections.sort(players);
    Collections.sort(balls);

    //Setting the variable to the biggest Integer value.
    int smallestDifference = Integer.MAX_VALUE;

    // Iterate through the sorted lists.
    int i = 0, j = 0;
    while (i < players.size() && j < balls.size())
    {
      // Getting the difference the current element at position i and j in the sorted list.
      int difference = Math.abs(players.get(i) - balls.get(j));

      // Updates the smallestDifference if a smaller difference is found
      if (difference < smallestDifference)
      {
        smallestDifference = difference;
      }

      if (players.get(i) < balls.get(j))
      {
        i++;
      }
      else
      {
        j++;
      }
    }

    return smallestDifference;
  }
}

