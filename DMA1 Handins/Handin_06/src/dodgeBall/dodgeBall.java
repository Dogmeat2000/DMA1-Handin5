package dodgeBall;

import java.util.TreeSet;

public class dodgeBall
{
  // Add any private fields you might need here
  private TreeSet<Integer> balancedBinarySearchTree;

  //Kristian Dashnaw
  //Jeg droppede min anden manuelle implementation af binary search trees. Jeg kunne ikke holde antallet af operationer under 500,000 i værste fald (med fem millioner spillere på linien),
  //i forbindelse med den løbende rotation af de givne "nodes" når de kom ud af balance.
  public void addPlayer(int x)
  {
    // Implement your code here to add a player to the line
    int xPos;

    //Validate input, ensuring that worked attributes are within the input constraints of [1 <= x <= 5,000,000]
    if (x < 1)
    {
      System.out.println("Given x-value (x=" + x
          + ") is outside the legal scope. Changing x to x=1. (min. legal value)");
      xPos = 1;
    }
    else if (x > 5000000)
    {
      System.out.println("Given x-value (x=" + x
          + ") is outside the legal scope. Changing x to x=5000000. (max. legal value)");
      xPos = 5000000;
    }
    else
    {
      xPos = x;
    }

    //Check if this is the first player being added. If so, initialize the Balanced Binary Search Tree (type TreeSet):
    if (this.balancedBinarySearchTree == null)
    {
      //Create tree
      this.balancedBinarySearchTree = new TreeSet<>(); /** [O(1)], Constant time*/

      //Since the tree has just been created, we know that it is impossible for other players to currently be on the field. So we simply add the new player to the field, without further evaluations:
      this.balancedBinarySearchTree.add(
          xPos); /** [O(log N)], logarithmic time*/
    }
    //If we failed the above test, it means that there are players in the list.
    else
    {
      //We first check if there already exists a player on the given x-coordinate:
      if(this.balancedBinarySearchTree.contains(xPos))
      {
        //The given x-coordinate is already occupied. we DO NOT add the player in this case.
        System.out.println(
            "Player was not added: X-position already occupied by another player.");
      }
      else
      {
        this.balancedBinarySearchTree.add(
            xPos); /** [O(log N)], logarithmic time*/
      }
    }
  }

  public int throwBall(int x)
  {
    int distance = 0;
    int xPosPredecessor = -1; //Placeholder value.
    int xPosSuccessor = -1; //Placeholder value.

    //First we check if the position was occupied with a player. If so, we kill the player:
    if(this.balancedBinarySearchTree.contains(x))
    {
      //Player at position. Kill the player and return zero.
      this.balancedBinarySearchTree.remove(x);
    }

    //If no player was at position, we find the closest player and calculate the return distance as well as moving the closest player to the ball location
    else
    {
      //First we check if there exists a predecessor (value smaller than x) in the balanced tree:
      if(this.balancedBinarySearchTree.floor(x) == null)
      {
        //There is no smaller value in the tree. Thus, there can be no predecessor.
      }
      else
      {
        //There is a predecessor. Lets save it.
        try
        {
          xPosPredecessor = this.balancedBinarySearchTree.floor(x);
        }
        catch(NullPointerException error)
        {
          System.out.println("Erro, read: " + error);
        }

      }

      //Then we check if there exists a successor (value larger than x) in the balanced tree:
      if(this.balancedBinarySearchTree.ceiling(x) == null)
      {
        //There is no larger value in the tree. Thus, there can be no predecessor.
      }
      else
      {
        //There is a successor. Lets save it.
        try
        {
          xPosSuccessor = this.balancedBinarySearchTree.ceiling(x);
        }
        catch(NullPointerException error)
        {
          System.out.println("Erro, read: " + error);
        }
      }

      //We now determine which player to move to the balls location:
      if(xPosPredecessor != -1 && xPosSuccessor != -1 && (x-xPosPredecessor) <= (xPosSuccessor-x))
      {
        //There exists players to both the left and right of the given x-coordinate. And the player to the left (smaller x-value) is closest or same distance from the ball.
        distance = x-xPosPredecessor;

        //Move the predecessor to the ball location:
        this.balancedBinarySearchTree.remove(xPosPredecessor);
        this.balancedBinarySearchTree.add(x);
      }
      else if(xPosPredecessor != -1 && xPosSuccessor != -1 && (x-xPosPredecessor) > (xPosSuccessor-x))
      {
        //There exists players to both the left and right of the given x-coordinate. And the player to the right (larger x-value) is closest or same distance from the ball.
        distance = xPosSuccessor-x;

        //Move the Successor to the ball location:
        this.balancedBinarySearchTree.remove(xPosSuccessor);
        this.balancedBinarySearchTree.add(x);
      }
      else if (xPosPredecessor != -1 && xPosSuccessor == -1)
      {
        //There doesn't exist a successor, only a predecessor:
        distance = x-xPosPredecessor;

        //Move the predecessor to the ball location:
        this.balancedBinarySearchTree.remove(xPosPredecessor);
        this.balancedBinarySearchTree.add(x);
      }
      else if (xPosPredecessor == -1 && xPosSuccessor != -1)
      {
        //There doesn't exist a predecessor, only a successor:
        distance = xPosSuccessor-x;

        //Move the successor to the ball location:
        this.balancedBinarySearchTree.remove(xPosSuccessor);
        this.balancedBinarySearchTree.add(x);
      }
      else
      {
        System.out.println("ERROR: Unknown error occurred.");
      }
    }

    // Implement your code here to update the line of players and return the distance
    return distance;
  }
}