package dodgeBall;

import java.util.ArrayList;

public class dodgeBall
{
  // Add any private fields you might need here

  /** REMEMBER TO CHANGE PUBLIC ATTRIBUTES TO PRIVATE AFTER TESTING! */
  public ArrayList<ArrayList<Integer>> playerList; //This arrayList will be used to contain added x coordinates of all players on the field at any time.
  public ArrayList<Integer> player; //This arrayList will be used to contain these values for each player={x position, left_child_index, right_child_index, parent_index, node balance value}.
  private int iNumOfOperations;
  private int iNumOfPlayersInArray; //Contains the number of players in the array (on the line) at any point in time.

  private int iRootIndexPosition; //Used to contain the index position of the root node. I use a balanced binary tree to sort the above ArrayList.

  //Kristian Dashnaw
  public void addPlayer(int x)
  {
    // Implement your code here to add a player to the line
    int xPos, lowX, midX, highX;

    //Validate input, ensuring that worked attributes are within the input constraints of [1 <= x <= 5,000,000]
    if (x < 1)
    {
      xPos = 1;
    }
    else if (x > 5000000)
    {
      xPos = 5000000;
    }
    else
    {
      xPos = x;
    }

    //Check if this is the first player being added. If so, initialize the playerList array, with a fixed capacity of 5,000,000:
    if (this.playerList == null)
    {
      //Create
      this.player = new ArrayList<>();
      this.player.add(0,xPos);
      this.player.add(1,-1); //left_child_index. -1 means no child.
      this.player.add(2,-1); //right_child_index. -1 means no child.
      this.player.add(3,-1); //parent_index. -1 means no parent.
      this.player.add(4,
          0); //node balance value. Set to zero as first root has no children.

      this.playerList = new ArrayList<>();
      //Since the playerList has just been created, we know that it is impossible for other players to currently be on the field. So we simply add the new player to the field:
      this.playerList.add(this.player);
      this.iRootIndexPosition = 0;
    }
    else
    {
      //There are already players on the field, so we now must analyze the current playerList to see where to insert the new player in a sorted manner.

      //We use the principle behind a binary tree to find where to insert our player.
      //We utilize an "inorder" search method, which basically involves the following evaluations 1: traverse left child. 2: visit node. 3: traverse right child.
      int currentNodeIndex = this.iRootIndexPosition;
      boolean playerEvaluationCompleted = false;

      while (!playerEvaluationCompleted)
      {
        //First we evaluate whether the given x-value is equal to the current nodes value. If yes, we do NOT add the player.

        if (this.playerList.get(currentNodeIndex).get(0) == xPos)
        {
          //The given x-value is equal to the current nodes value. we DO NOT add the player in this case.
          System.out.println(
              "Player was not added: X-position already occupied by another player.");
          playerEvaluationCompleted = true;
        }
        //Now we evaluate whether the given x-value should be added to the left, or to the right of the current node.
        //First we check if the given x-value should be added to the left of the current nodes x-value:
        else if (xPos < this.playerList.get(currentNodeIndex).get(0))
        {
          //xPos belongs to the left of the current nodes value.

          //Now we check if there already is a left-child:
          if (this.playerList.get(currentNodeIndex).get(1) != -1)
          {
            //Current node has a left child. We now must evaluate this left child as the new current node.
            currentNodeIndex = this.playerList.get(currentNodeIndex).get(1);
          }
          else
          {
            //Current node has no left child. We add the given x-value as the new left child:

            //Create a new leaf
            this.player = new ArrayList<>();
            this.player.add(xPos);
            this.player.add(-1); //left_child_index. -1 means no child.
            this.player.add(-1); //right_child_index. -1 means no child.
            this.player.add(currentNodeIndex); //parent_index.
            this.player.add(
                0); //node balance value. Set to zero as leafs root have no children.

            //Adding the created leaf to the playerList. We use "playerList.size()" to avoid calling the built-in Java search methods that are implemented within .contains(), in order to reduce CPU iterations/clock-cycles.
            int leftChildLeafIndex = this.playerList.size();
            this.playerList.add(leftChildLeafIndex, this.player);
            playerEvaluationCompleted = true;

            //Update the parent nodes attributes so that the added child is properly referenced:
            this.playerList.get(currentNodeIndex).set(1, leftChildLeafIndex);
          }
        }
        else
        {
          //xPos belongs to the right of the current nodes value. We already checked earlier if the given x-value is equal to the current nodes x-value. So at this point we know that the given x-value must be larger.

          //Now we check if there already is a right-child:
          if (this.playerList.get(currentNodeIndex).get(2) != -1)
          {
            //Current node has a right child. We now must evaluate this right child as the new current node.
            currentNodeIndex = this.playerList.get(currentNodeIndex).get(2);
          }
          else
          {
            //Current node has no right child. We add the given x-value as the new right child:

            //Create a new leaf
            this.player = new ArrayList<>();
            this.player.add(xPos);
            this.player.add(-1); //left_child_index. -1 means no child.
            this.player.add(-1); //right_child_index. -1 means no child.
            this.player.add(currentNodeIndex); //parent_index.
            this.player.add(
                0); //node balance value. Set to zero as leafs root have no children.

            //Adding the created leaf to the playerList. We use "playerList.size()" to avoid calling the built-in Java search methods that are implemented within .contains(), in order to reduce CPU iterations/clock-cycles.
            int rightChildLeafIndex = this.playerList.size();
            this.playerList.add(rightChildLeafIndex, this.player);
            playerEvaluationCompleted = true;

            //Update the parent nodes attributes so that the added child is properly referenced:
            this.playerList.get(currentNodeIndex).set(2, rightChildLeafIndex);
          }
        }

      }

      //Having added the player to our ArrayList in a sorted manner, we now must update all parents/grandparents/etc. all the way up to the root in order to determine tree balance, updating the balance attributes.
      //As well as where needed, rotating the tree and updating the root value.
    }



  }

  public int throwBall(int x)
  {
    int distance = 0;
    int xPosClosestPlayer = -1; //Placeholder value. Negative values are not allowed in an array.
    int xPosSecondClosestPlayer = -1; //Placeholder value. Negative values are not allowed in an array.

    //First we check if the position was occupied with a player. If so, we kill the player:
    /*if(this.playerList[x] == 1)
    {
      //Player at position. Kill the player.
      this.playerList[x] = 0;
      iNumOfPlayersInArray -= 1;
    }

    //Now we find the closest two players to the x position.
    for (int i = 0; i < this.playerList.length; i++)
    {
      if(this.playerList[i] - x > 0)
      {

      }
    }*/

    // Implement your code here to update the line of players and return the distance
    return distance;
  }
}