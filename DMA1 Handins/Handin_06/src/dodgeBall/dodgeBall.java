package dodgeBall;

import java.util.TreeSet;

public class dodgeBall
{
  // Add any private fields you might need here
  private TreeSet<Integer> balancedBinarySearchTree;

  //Kristian Dashnaw
  //Jeg droppede min anden manuelle implementation af binary search trees. Jeg kunne ikke holde antallet af operationer under 500,000 i værste fald (med fem millioner spillere på linien) i forbindelse med den løbende rotation af de givne "nodes" når de kom ud af balance.
  public void addPlayer(int x)
  {
    // Implement your code here to add a player to the line

    //In order to effectively implement this algorithm, I have decided to use the Balanced Binary Search Trees available through the Java TreeSet library.
    //This is documented here: https://github.com/Mortal/csaudk-submitj/tree/master/javalib#readme
    //Specifically I use these methods from the TreeSet library:
    //1. Create new tree                  : TreeSet<Integer> tree = new TreeSet<>();  [Time Complexity is given as: O(1)]
    //2. Add a new node/leaf              : tree.add(x);                              [Time Complexity is given as: O(log N)]
    //3. Remove a node/leaf               : tree.remove(x);                           [Time Complexity is given as: O(log N)]
    //4. Check if tree contains node      : if (tree.contains(x)) { ... };            [Time Complexity is given as: O(log N)]
    //5. Get predecessor of the node/leaf : Integer p = tree.floor(x);                [Time Complexity is given as: O(log N)]
    //6. Set predecessor of the node/leaf : Integer s = tree.ceiling(x);              [Time Complexity is given as: O(log N)]
    //These time complexities are given here: https://github.com/Mortal/csaudk-submitj/blob/master/javalib/TreeSet.md

    int xPos;

    //Validate input, ensuring that worked attributes are within the input constraints of [1 <= x <= 5,000,000]
    if (x < 1) /** Case A1: [Time complexity = O(1), from "<" operator] */
    {
      System.out.println("Given x-value (x=" + x
          + ") is outside the legal scope. Changing x to x=1. (min. legal value)"); /** Case A1:  [Time complexity = O(2), from 2 "+" operators] */
      xPos = 1; /** Case A1:  [Time complexity = O(1), from 1 "=" operator] */
    }
    else if (x > 5000000) /** Case A2: [Time complexity = O(1), from ">" operator] */
    {
      System.out.println("Given x-value (x=" + x
          + ") is outside the legal scope. Changing x to x=5000000. (max. legal value)"); /** Case A2:  [Time complexity = O(2), from 2 "+" operators] */
      xPos = 5000000; /** Case A2:  [Time complexity = O(1), from 1 "=" operator] */
    }
    else
    {
      xPos = x; /** Case A3: [Time complexity = O(1), from "=" operator] */
    }

    //Check if this is the first player being added. If so, initialize the Balanced Binary Search Tree (type TreeSet):
    if (this.balancedBinarySearchTree == null) /** Case B1: [Time complexity = O(1), from "==" operator] */
    {
      //Create tree
      this.balancedBinarySearchTree = new TreeSet<>(); /** Case B1: [Time complexity = O(1), from "=" operator] */

      //Since the tree has just been created, we know that it is impossible for other players to currently be on the field. So we simply add the new player to the field, without further evaluations:
      this.balancedBinarySearchTree.add(
          xPos); /** Case B1: [Time complexity = O(log N), from "tree.add(x)" method call] */
    }
    //If we failed the above test, it means that there are players in the list.
    else
    {
      //We first check if there already exists a player on the given x-coordinate:
      if (this.balancedBinarySearchTree.contains(xPos)) /** Case B2A: [Time complexity = O(log N), from "tree.contains(x)" method call] */
      {
        //The given x-coordinate is already occupied. we DO NOT add the player in this case.
        System.out.println(
            "Player was not added: X-position already occupied by another player.");
      }
      else
      {
        this.balancedBinarySearchTree.add(
            xPos); /** Case B2B: [Time complexity = O(log N), from "tree.add(x)" method call] */
      }
    }
  }

  /** Time complexity Analysis of the .addPlayer() method:
   * As documented in the above code, we can extract these time complexities from each line of the code:
   * Line 30: Case A1: T(N) = O(1)
   * Line 33: Case A1: T(N) = O(2)
   * Line 34: Case A1: T(N) = O(1)
   * Line 36: Case A2: T(N) = O(1)
   * Line 39: Case A2: T(N) = O(2)
   * Line 40: Case A2: T(N) = O(1)
   * Line 44: Case A3: T(N) = O(1)
   * Line 48: Case B1: T(N) = O(1)
   * Line 51: Case B1: T(N) = O(1)
   * Line 55: Case B1: T(N) = O(log N)
   * Line 61: Case B2A: T(N) = O(log N)
   * Line 70: Case B2B: T(N) = O(log N)

   * We can compact these further, such that:
   * Line 30+33+34  : Case A1: T(N)   = O(1) + O(2) + O(1) = O(4)
   * Line 36+39+40  : Case A2: T(N)   = O(1) + O(2) + O(1) = O(4)
   * Line 44        : Case A3: T(N)   = O(1)
   * Line 48+51+55  : Case B1: T(N)   = O(1) + O(1) + O(log N) = O(2) + O(log N)
   * Line 61        : Case B2A: T(N)  = O(log N)
   * Line 70        : Case B2B: T(N)  = O(log N)

   * In order to determine the time complexity during the worst-case scenario, we evaluate each section of the algorithm.
   * Since the given if-statements cannot be both true and false at the same time, we know that only one of Case A1-A3 is executed pr. method call.
   * Similarly, we know that only one of Case B1-B2B is executed pr. method call.
   * We observe that both case A1 and A2 have similar time complexities with a constant time of O(4), where Case A3 is faster with a constant time of O(1).
   * Thus, we choose to use O(4) below, in evaluating this methods overall T(N).

   * For the second part of the method, we can also see that Case B1 is the most complex, with a combination of constant time and logarithmic time: O(2) + O(Log N).
   * Thus, we choose to use Case B1 below, in evaluating this methods overall T(N).

   * From the above, we can express the T(N) of addPlayer() in a single expression as:
   * addPlayer(), T(N) = O(4) + O(2) + O(Log N).
   * addPlayer(), T(N) = O(6) + O(Log N).

   * Since we must evaluate the worst-case performance, we can ignore constants.
   * This leaves us with the time complexity for this method being in logarithmic time.
   * addPlayer(), T(N) = O(Log N).
   * */


  public int throwBall(int x)
  {
    // Implement your code here to update the line of players and return the distance

    //In order to effectively implement this algorithm, I have decided to use the Balanced Binary Search Trees available through the Java TreeSet library.
    //This is documented here: https://github.com/Mortal/csaudk-submitj/tree/master/javalib#readme
    //Specifically I use these methods from the TreeSet library:
    //1. Create new tree                  : TreeSet<Integer> tree = new TreeSet<>();  [Time Complexity is given as: O(1)]
    //2. Add a new node/leaf              : tree.add(x);                              [Time Complexity is given as: O(log N)]
    //3. Remove a node/leaf               : tree.remove(x);                           [Time Complexity is given as: O(log N)]
    //4. Check if tree contains node      : if (tree.contains(x)) { ... };            [Time Complexity is given as: O(log N)]
    //5. Get predecessor of the node/leaf : Integer p = tree.floor(x);                [Time Complexity is given as: O(log N)]
    //6. Set predecessor of the node/leaf : Integer s = tree.ceiling(x);              [Time Complexity is given as: O(log N)]
    //These time complexities are given here: https://github.com/Mortal/csaudk-submitj/blob/master/javalib/TreeSet.md

    int xPos, distance;
    //Placeholder values inserted below:
    int xPosPredecessor = -1; /** Base Case: [Time complexity = O(1), from 1 use of "=" operator] */
    int xPosSuccessor = -1; /** Base Case: [Time complexity = O(1), from 1 use of "=" operator] */

    //Validate input, ensuring that worked attributes are within the input constraints of [1 <= x <= 5,000,000]
    //(This is duplicate code from the "addPlayer" method. Ideally I would want to place this input validation in a method of its own.)

    if (x < 1) /** Case A1: [Time complexity = O(1), from "<" operator] */
    {
      System.out.println("Given x-value (x=" + x
          + ") is outside the legal scope. Changing x to x=1. (min. legal value)"); /** Case A1:  [Time complexity = O(2), from 2 "+" operators] */
      xPos = 1; /** Case A1:  [Time complexity = O(1), from 1 "=" operator] */
    }
    else if (x > 5000000) /** Case A2: [Time complexity = O(1), from ">" operator] */
    {
      System.out.println("Given x-value (x=" + x
          + ") is outside the legal scope. Changing x to x=5000000. (max. legal value)"); /** Case A2:  [Time complexity = O(2), from 2 "+" operators] */
      xPos = 5000000; /** Case A2:  [Time complexity = O(1), from 1 "=" operator] */
    }
    else
    {
      xPos = x; /** Case A3: [Time complexity = O(1), from "=" operator] */
    }


    //First we check if the position was occupied with a player. If so, we kill the player:
    if (this.balancedBinarySearchTree.contains(xPos)) /** Case B1: [Time complexity = O(log N), from "tree.contains(x)" method] */
    {
      //Player at position. Kill the player and return zero.
      this.balancedBinarySearchTree.remove(xPos); /** Case B1: [Time complexity = O(log N), from "tree.remove(x)" method] */
      distance = 0; /** Case B1: [Time complexity = O(1), from "=" operator] */
    }
    //If no player was at position, we find the closest player and calculate the return distance as well as moving the closest player to the ball location
    else /** Case B2: */
    {
      //First we check if there exists a predecessor (value smaller than x) in the balanced tree:
      if (this.balancedBinarySearchTree.floor(xPos) == null) /** Case B2A1: [Time complexity = O(log N) + O(1), from "tree.floor(x)" method and "==" operator] */
      {
        //There is no smaller value in the tree. Thus, there can be no predecessor.
      }
      else /** Case B2A2: */
      {
        //There is a predecessor. Lets save it.
        try
        {
          xPosPredecessor = this.balancedBinarySearchTree.floor(xPos); /** Case B2A2: [Time complexity = O(1) + O(log N), from "=" operator and "tree.floor(x)" method] */
        }
        catch (NullPointerException error)
        {
          System.out.println("Error, read: " + error);  /** Case B2A2: [Time complexity = O(1), from "+" operator] */
        }
      }

      //Then we check if there exists a successor (value larger than x) in the balanced tree:
      if (this.balancedBinarySearchTree.ceiling(xPos) == null) /** Case B2B1: [Time complexity = O(log N) + O(1), from "tree.ceiling(x)" method and "==" operator] */
      {
        //There is no larger value in the tree. Thus, there can be no predecessor.
      }
      else /** Case B2B2: */
      {
        //There is a successor. Lets save it.
        try
        {
          xPosSuccessor = this.balancedBinarySearchTree.ceiling(xPos); /** Case B2B2: [Time complexity = O(1) + O(log N), from "=" operator and "tree.ceiling(x)" method] */
        }
        catch (NullPointerException error)
        {
          System.out.println("Error, read: " + error); /** Case B2B2: [Time complexity = O(1), from "+" operator] */
        }
      }

      //We now determine which player to move to the balls' location:
      if (xPosPredecessor != -1 && xPosSuccessor != -1
          && (xPos - xPosPredecessor) <= (xPosSuccessor - xPos)) /** Case B2C1: [Time complexity = O(7), from 2 uses of "!=" operators, 2 uses of "&&" operators, 2 uses of "-" operators and 1 use of "<=" operator] */
      {
        //There exists players to both the left and right of the given x-coordinate. And the player to the left (smaller x-value) is closest or same distance from the ball.
        distance = xPos - xPosPredecessor; /** Case B2C1: [Time complexity = O(2), from 1 use of "=" operators, 1 uses of "-" operator] */

        //Move the predecessor to the ball location:
        this.balancedBinarySearchTree.remove(xPosPredecessor); /** Case B2C1: [Time complexity = O(log N), from "tree.remove(x)" method call */
        this.balancedBinarySearchTree.add(xPos); /** Case B2C1: [Time complexity = O(log N), from "tree.add(x)" method call */
      }
      else if (xPosPredecessor != -1 && xPosSuccessor != -1) /** Case B2C2: [Time complexity = O(3), from 2 uses of "!=" operators and 1 use of "&&" operator] */
      {
        //There exists players to both the left and right of the given x-coordinate. And the player to the right (larger x-value) is closest or same distance from the ball.
        distance = xPosSuccessor - xPos; /** Case B2C2: [Time complexity = O(2), from 1 use of "=" operator and 1 use of "-" operator] */

        //Move the Successor to the ball location:
        this.balancedBinarySearchTree.remove(xPosSuccessor); /** Case B2C2: [Time complexity = O(log N), from "tree.remove(x)" method call */
        this.balancedBinarySearchTree.add(xPos); /** Case B2C2: [Time complexity = O(log N), from "tree.add(x)" method call */
      }
      else if (xPosPredecessor != -1) /** Case B2C3: [Time complexity = O(1), from 1 use of "!=" operators] */
      {
        //A successor does not exist, only a predecessor:
        distance = xPos - xPosPredecessor; /** Case B2C3: [Time complexity = O(2), from 1 use of "=" operator and 1 use of "-" operator] */

        //Move the predecessor to the ball location:
        this.balancedBinarySearchTree.remove(xPosPredecessor); /** Case B2C3: [Time complexity = O(log N), from "tree.remove(x)" method call */
        this.balancedBinarySearchTree.add(xPos); /** Case B2C3: [Time complexity = O(log N), from "tree.add(x)" method call */
      }
      else if (xPosSuccessor != -1) /** Case B2C4: [Time complexity = O(1), from 1 use of "!=" operators] */
      {
        //There doesn't exist a predecessor, only a successor:
        distance = xPosSuccessor - xPos; /** Case B2C4: [Time complexity = O(2), from 1 use of "=" operator and 1 use of "-" operator] */

        //Move the successor to the ball location:
        this.balancedBinarySearchTree.remove(xPosSuccessor); /** Case B2C4: [Time complexity = O(log N), from "tree.remove(x)" method call */
        this.balancedBinarySearchTree.add(xPos); /** Case B2C4: [Time complexity = O(log N), from "tree.add(x)" method call */
      }
      else /** Case B2C5: */
      {
        System.out.println("ERROR: Unknown error occurred.");
        //To provide ability to catch negative return values as error-ridden.
        distance = -1; /** Case B2C5: [Time complexity = O(1), from 1 use of "=" operator] */
      }
    }
    return distance; /** Base Case: [Time complexity = O(1), from 1 use of "return" operator] */
  }

  /** Time complexity Analysis of the .throwBall() method:
   * As documented in the above code, we can extract these time complexities from each line of the code:
   * Line 134: Base Case: T(N) = O(1)
   * Line 135: Base Case: T(N) = O(1)
   * Line 140: Case A1: T(N) = O(1)
   * Line 143: Case A1: T(N) = O(2)
   * Line 144: Case A1: T(N) = O(1)
   * Line 146: Case A2: T(N) = O(1)
   * Line 149: Case A2: T(N) = O(2)
   * Line 150: Case A2: T(N) = O(1)
   * Line 154: Case A3: T(N) = O(1)
   * Line 159: Case B1: T(N) = O(log N)
   * Line 162: Case B1: T(N) = O(log N)
   * Line 163: Case B1: T(N) = O(1)
   * Line 169: Case B2A1: T(N) = O(log N) + O(1)
   * Line 178: Case B2A2: T(N) = O(1) + O(log N)
   * Line 182: Case B2A2: T(N) = O(1)
   * Line 187: Case B2B1: T(N) = O(log N) + O(1)
   * Line 196: Case B2B2: T(N) = O(1) + O(log N)
   * Line 200: Case B2B2: T(N) = O(1)
   * Line 206: Case B2C1: T(N) = O(7)
   * Line 209: Case B2C1: T(N) = O(2)
   * Line 212: Case B2C1: T(N) = O(log N)
   * Line 213: Case B2C1: T(N) = O(log N)
   * Line 215: Case B2C2: T(N) = O(3)
   * Line 218: Case B2C2: T(N) = O(2)
   * Line 221: Case B2C2: T(N) = O(log N)
   * Line 222: Case B2C2: T(N) = O(log N)
   * Line 224: Case B2C3: T(N) = O(1)
   * Line 227: Case B2C3: T(N) = O(2)
   * Line 230: Case B2C3: T(N) = O(log N)
   * Line 231: Case B2C3: T(N) = O(log N)
   * Line 233: Case B2C4: T(N) = O(1)
   * Line 236: Case B2C4: T(N) = O(2)
   * Line 239: Case B2C4: T(N) = O(log N)
   * Line 240: Case B2C4: T(N) = O(log N)
   * Line 246: Case B2C5: T(N) = O(1)
   * Line 249: Base Case: [Time complexity = O(1)

   * We can compact these further, such that:
   * Line 134+135+249     : Base Case : T(N) = O(1) + O(1) + O(1) = O(3)
   * Line 140+143+144     : Case A1   : T(N) = O(1) + O(2) + O(1) = O(4)
   * Line 146+149+150     : Case A2   : T(N) = O(1) + O(2) + O(1) = O(4)
   * Line 154             : Case A3   : T(N) = O(1)
   * Line 159+162+163     : Case B1   : T(N) = O(Log N) + O(Log N) + O(1) = 2*O(Log N) + O(1)
   * Line 169             : Case B2A1 : T(N) = O(Log N) + O(1)
   * Line 178+182         : Case B2A2 : T(N) = O(1) + O(Log N) + O(1) = O(2) + O(Log N)
   * Line 187             : Case B2B1 : T(N) = O(Log N) + O(1)
   * Line 196+200         : Case B2B2 : T(N) = O(1) + O(Log N) + O(1) = O(2) + O(Log N)
   * Line 206+209+212+213 : Case B2C1 : T(N) = O(7) + O(2) + O(Log N) + O(Log N) = O(9) + 2*O(Log N)
   * Line 215+218+221+222 : Case B2C2 : T(N) = O(3) + O(2) + O(Log N) + O(Log N) = O(5) + 2*O(Log N)
   * Line 224+227+230+231 : Case B2C3 : T(N) = O(1) + O(2) + O(Log N) + O(Log N) = O(3) + 2*O(Log N)
   * Line 233+236+237+240 : Case B2C4 : T(N) = O(1) + O(2) + O(Log N) + O(Log N) = O(3) + 2*O(Log N)
   * Line 246             : Case B2C5 : T(N) = O(1)

   * In order to determine the time complexity during the worst-case scenario, we evaluate each section of the method.
   * Since the given if-statements cannot be both true and false at the same time, we know that only of the the following cases is executed:
   * Base Case + (either A1, A2, A3) + (either B1 or (B2 and (either B2A1 or B2A2) and (either B2B1 or B2B2) and (either B2C1 or B2C2 or B2C3 or B2C4 or B2C5)

   * From the above, we now evaluate each term of the combined time complexity expression, isolating each term with the worst time complexity (slowest execution, or most cpu clock cycles/operations)
   * Base case is always executed. Remains T(N) = O(3)
   * A1 and A2 are similar, and larger than A3. Thus second term is T(N) = O(4).
   * In order to determine if B1 og B2 is most complex, we first evaluate the sub-terms in B2:
   * B2A2 is larger than B2A1. Thus B2's first sub-term is T(N) = O(2) + O(Log N)
   * B2B2 is larger than B2B1. Thus B2's second sub-term is T(N) = O(2) + O(Log N)
   * B2C1 is larger than the rest of B2C*. Thus B2's third sub-term is: T(N) = O(9) + 2*O(Log N)
   * From this we get that B2's T(N) is expressed as: O(2) + O(Log N) + O(2) + O(Log N) + O(9) + 2*O(Log N) = O(13) + 4*O(Log N)
   * Since B2 is larger than B1, we choose B2.

   * We can now express our methods' time complexity as a combination of these terms:
   * Base Case + (either A1, A2) + (B2 and B2A2 and B2B2 and B2C1)

   * Substituting the terms with each of their T(N) values we get:
   * throwBall(), T(N) = O(3) + O(4) + O(13) + 4*O(Log N)
   * throwBall(), T(N) = O(20) + 4*O(Log N)

   * Since we must evaluate the worst-case performance, we can ignore constants.
   * This leaves us with the time complexity for this method being in logarithmic time.
   * throwBall(), T(N) = O(Log N).
   * */
}

/** TIME COMPLEXITY SUMMATION:
 * We have now determined the time complexity of both methods to be:
 * addPlayer(), T(N) = O(6) + O(Log N). (Theta Time)
 * addPlayer(), T(N) = O(Log N). (Omega Time)

 * throwBall(), T(N) = O(20) + 4*O(Log N) (Theta Time)
 * throwBall(), T(N) = O(Log N). (Omega Time)

 * Finally, we can now evaluate the assignments limitation on number of operations, which was 500,000 operations.
 * We do this by inserting N=5,000,000 into our Theta Time (which is the more exact of the two), for each method.

 * Number of operations for addPlayer(), T(5,000,000):
 * T(5,000,000) = 6 + Log(5,000,000) = 28.25 operations (rounded).

 *  Number of operations for throwBall(), T(5,000,000):
 *  T(5,000,000) = 20 + 4*Log(5,000,000) = 109.01 operations (rounded).

 *  CONCLUSION:
 *  Both implemented methods are very fast, significantly faster than the limitation of five million operations pr. execution.
 * */