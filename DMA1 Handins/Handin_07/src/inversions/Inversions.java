package inversions;

import java.util.*;

public class Inversions
{

  public int countInversions(ArrayList<Integer> input)
  {
    /*Implement your code here to return the number of inversions in input!

    The following code is developed and implemented by: Kristian Dashnaw

    Assumptions and constraints given in the assignment paper:
    1: Each element of the given ArrayList is an integer between 1 and 65536.
    2: Each element occurs exactly once in the list. There are no duplicates.
    3: The number of inversions fits in a java int without overflowing.

    Based on the above constraints, we now begin implementing some code.
    To reduce the time complexity of this code I will NOT perform any input validation.

    For this task an inversion is defined as: If i < j and A[i] > A[j], then the pair (i, j) is called an inversion of A
    For  instance,  the  list: {1, 4, 2, 3}
    contains two inversions, namely (2, 3) and (2, 4), since A[2] > A[3] and A[2] > A[4].

    What this basically means, is that we must implement an algorithm that, for every element in a sequence of numbers that is larger than a later element in the same sequence, we count an inversion.

    In order to solve this assignment, I have utilized the principles from the way a binary search tree functions.

    The principle is simple. We run through the given ArrayList from index = 0 to index = N. For each step, we insert the given ArrayList element into an unbalanced binary tree
    and at each step during this insertion, we evaluate how many inversions are present for the inserted element.

    View the attached .pdf document next to the source code files (./inversions/The principle behind solution to Inversion task.pdf) for a more detailed walkthrough.
     */

    //Declare and initialize our variables and attributes:
    int counterInversions = 0;

    ArrayList<ArrayList<Integer>> sequenceList = new ArrayList<>(); //This arrayList will be used to contain received Integer value from the number sequence.
    ArrayList<Integer> sequenceNumber = new ArrayList<>(4); //This arrayList will be used to contain these values for each sequenceNumber={Integer value, left_child_index, right_child_index, number of child elements}.
    int currentIndexPos = 0; //This is used to keep track of the current index in the received sequence.
    int currentNodeIndex = 0; //This is used to keep track of which node in the tree we are currently evaluating the sequence element against.

    //First we add the root node to our tree, which we define as the first element in the sequence:
    sequenceNumber.add(0, input.get(currentIndexPos));
    sequenceNumber.add(1, -1); //left_child_index. -1 means no child.
    sequenceNumber.add(2, -1); //right_child_index. -1 means no child
    sequenceNumber.add(3, 0); //number of child elements.
    sequenceList.add(sequenceNumber);
    currentIndexPos++;

    /*
    While inserting we catch every time a value is inserted to the left of a node - if at any point an element is inserted as a left child it must mean that it has experienced an inversion.
    At this point it is important to add the amount of ALL the elements in the opposing right child, since all these will be larger than the current element - and as such are also inversions.

    We use the principle behind a binary tree to find where to insert our sequenceNumber.
    Principle is:
    (1) Compare element with current node.
    (2) If smaller, traverse left.
    (3) If larger travers right.
    */

    //Create a loop, that runs until all sequence numbers have been evaluated.
    while (currentIndexPos < input.size())
    {
      //Now we evaluate whether the given element should be added to the left, or to the right of the current node.

      //Evaluate current node against the current index value. If current index value is larger than the current node, then there exists an inversion:
      if (input.get(currentIndexPos) < sequenceList.get(currentNodeIndex).get(0))
      {
        //Element belongs to the left of the current nodes value. We just compared against a larger element already present in the sequence. Thus, we have an inversion here.
        counterInversions++;

        //Remember to, if there exists any right children, to add their total number of elements in the right subtree to the amount of inversions identified.
        if (sequenceList.get(currentNodeIndex).get(2) != -1)
        {
          counterInversions = counterInversions + (sequenceList.get(sequenceList.get(currentNodeIndex).get(2)).get(3) +1); //This is counting all the right child nodes of the parent.
        }

        //Now we check if there already is a left-child:
        if (sequenceList.get(currentNodeIndex).get(1) != -1)
        {
          //Current node already has a left child. We now must evaluate this left child as the new current node.
          //But first we update the exiting left child's number of children, since our current element will become a child (or grandchild, etc.) under this node.
          sequenceList.get(currentNodeIndex).set(3, (sequenceList.get(currentNodeIndex).get(3) + 1));

          //Point to the next child's index position to evaluate against:
          currentNodeIndex = sequenceList.get(currentNodeIndex).get(1);
        }
        else
        {
          //Current node has no left child. We add the given element as the new left child:

          //Create a new leaf
          sequenceNumber = new ArrayList<>(4);
          sequenceNumber.add(0, input.get(currentIndexPos));
          sequenceNumber.add(1, -1); //left_child_index. -1 means no child.
          sequenceNumber.add(2, -1); //right_child_index. -1 means no child.
          sequenceNumber.add(3, 0);  //number of child elements.

          //Adding the created leaf to the sequenceList.
          sequenceList.add(sequenceNumber);

          //Update the parent nodes attributes so that the added child is properly referenced:
          sequenceList.get(currentNodeIndex).set(1, sequenceList.size()-1);
          sequenceList.get(currentNodeIndex).set(3, (sequenceList.get(currentNodeIndex).get(3) + 1)); //Adding +1 to number of child elements, for the parent.
          currentNodeIndex = 0;
          currentIndexPos++;
        }
      }
      else
      {
        //Element belongs to the right of the current nodes value. No inversion detected here.

        //Now we check if there already is a right-child:
        if (sequenceList.get(currentNodeIndex).get(2) != -1)
        {
          //Current node already has a right child. We now must evaluate this right child as the new current node.
          sequenceList.get(currentNodeIndex).set(3, (sequenceList.get(currentNodeIndex).get(3) + 1));

          //Point to the next child's index position to evaluate against:
          currentNodeIndex = sequenceList.get(currentNodeIndex).get(2);
        }
        else
        {
          //Current node has no right child. We add the given element as the new left child:

          //Create a new leaf
          sequenceNumber = new ArrayList<>(4);
          sequenceNumber.add(0, input.get(currentIndexPos));
          sequenceNumber.add(1, -1); //left_child_index. -1 means no child.
          sequenceNumber.add(2, -1); //right_child_index. -1 means no child.
          sequenceNumber.add(3, 0);  //number of child elements.

          //Adding the created leaf to the sequenceList.
          sequenceList.add(sequenceNumber);

          //Update the parent nodes attributes so that the added child is properly referenced:
          sequenceList.get(currentNodeIndex).set(2, sequenceList.size()-1);
          sequenceList.get(currentNodeIndex).set(3, (sequenceList.get(currentNodeIndex).get(3) + 1)); //Adding +1 to number of child elements, for the parent.
          currentNodeIndex = 0;
          currentIndexPos++;
        }
      }
    }
    return counterInversions;
  }
}

