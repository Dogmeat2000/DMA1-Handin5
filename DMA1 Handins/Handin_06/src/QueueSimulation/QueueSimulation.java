package QueueSimulation;

import java.util.*;

public class QueueSimulation
{
  public int simulate(ArrayList<Integer> input)
  {

    // Need a queue, therefore we create one, using a linkedlist.

    /*/ The arraylist iterates through each element. This iteration results in a
    time complexity proportional to the number of elements in the list. O(N)
     */
    Queue<Integer> queue = new LinkedList<>(input);

    // loop continues, as long as there 2 elements in the queue.

    /*/ The time complexity of of the loop, is O(N), because the loop checks each element
    once.
     */
    while (queue.size() >= 2)
    {
      //the first integer gets removed from the front of the queue.
      //Has a time complexity of 0(1), because the first element gets removed.
      int firstLine = queue.poll();
      // the second integer gets removed from the front of the queue.
      //Has a time complexity of 0(1), because the first element gets removed.
      int secondLine = queue.poll();
      // adding the second integer back to end of the queue.
      /*/Has a time complexity of 0(1), because we insert element in the
      end of the array, this structure allows constant time insertion.
       */
      queue.add(secondLine);
    }

    // We've still got a constant time O(1).
    if (!queue.isEmpty())
    {
      return queue.poll();
    }
    else
    {
     throw new IllegalStateException(" Theres no integers left ");
    }
  }
}
