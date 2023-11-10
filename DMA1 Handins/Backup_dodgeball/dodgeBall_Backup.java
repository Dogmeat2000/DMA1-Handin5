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

  public int iRootIndexPosition; //Used to contain the index position of the root node. I use a balanced binary tree to sort the above ArrayList.

  //Kristian Dashnaw
  public void addPlayer(int x)
  {
    // Implement your code here to add a player to the line
    int xPos, lowX, midX, highX;

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

    //Check if this is the first player being added. If so, initialize the playerList array, with a fixed capacity of 5,000,000:
    if (this.playerList == null)
    {
      //Create
      this.player = new ArrayList<>();
      this.player.add(0, xPos);
      this.player.add(1, -1); //left_child_index. -1 means no child.
      this.player.add(2, -1); //right_child_index. -1 means no child.
      this.player.add(3, -1); //parent_index. -1 means no parent.
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
      /**SLET DENNE LINIE???? We utilize an "inorder" search method, which basically involves the following evaluations 1: traverse left child. 2: visit node. 3: traverse right child.*/
      int currentNodeIndex = this.iRootIndexPosition;
      boolean playerEvaluationCompleted = false;
      boolean addedLeftChild = false;
      boolean addedRightChild = false;
      boolean FinalIteration = false;

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
            addedLeftChild = true;

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
            addedRightChild = true;

            //Update the parent nodes attributes so that the added child is properly referenced:
            this.playerList.get(currentNodeIndex).set(2, rightChildLeafIndex);
          }
        }
      }
      //Having added the player to our ArrayList in a sorted manner, we now must update all parents/grandparents/etc. all the way up to the root in order to determine tree balance, updating the balance attributes.
      //As well as where needed, rotating the tree and updating the root value.

      /*if(addedLeftChild || addedRightChild)
      {*/
        /*do
        {*/
          /**WORK ON THIS PART MORE!*/

          int currentParentBalance = this.playerList.get(currentNodeIndex).get(4);
          if (addedLeftChild)
          {
            //If left child was added to the node, we subtract 1 from the balance, all the way up to the root node:
            this.playerList.get(currentNodeIndex)
                .set(4, currentParentBalance - 1);
            addedLeftChild=false;
          }
          else if (addedRightChild)
          {
            //If right child was added to the node, we add 1 to the balance.
            this.playerList.get(currentNodeIndex)
                .set(4, currentParentBalance + 1);
            addedRightChild=false;
          }

          //Recalculate the balance values from the current node iteratively up to the root:
      int nextNodeIndex = this.playerList.get(currentNodeIndex).get(3); //Get the index position of the next parent.
      int leftChildBalanceValue;
      int rightChildBalanceValue;
      int leftChildDepth = 0;
      int rightChildDepth = 0;
      int overAllDepth = 0;

      System.out.println("next node index is: " + nextNodeIndex);

      while(nextNodeIndex != -1)
      {
        //leftChildBalanceValue = 0; //Assume if child does not exist, the balance for a non-existent child is zero.
        //rightChildBalanceValue = 0; //Assume if child does not exist, the balance for a non-existent child is zero.

        //Check if left child exists.
        if(this.playerList.get(nextNodeIndex).get(1) != -1)
        {
          //leftChildBalanceValue = this.playerList.get(nextNodeIndex).get(1); //Get the balance value from the next parents left child.
          leftChildDepth++;
        }

        //Check if right child exists.
        if(this.playerList.get(nextNodeIndex).get(2) != -1)
        {
          //rightChildBalanceValue = this.playerList.get(nextNodeIndex).get(2); //Get the balance value from the next parents right child.
          rightChildDepth++;
        }

        //Update this nodes balance value:
        //int newBalanceValue = rightChildBalanceValue - leftChildBalanceValue;
        int newBalanceValue = rightChildDepth - leftChildDepth;
        System.out.println("new balance: " + rightChildDepth + " - " + leftChildDepth + " = " + newBalanceValue);
        this.playerList.get(nextNodeIndex).set(4, newBalanceValue);

        //Define the next node to evaluate:
        nextNodeIndex = this.playerList.get(nextNodeIndex).get(3);
        overAllDepth++;

      }





      /*int updatingParentNodes_BalanceIndex = this.playerList.get(currentNodeIndex).get(3);
      if(updatingParentNodes_BalanceIndex == -1)
      {
        updatingParentNodes_BalanceIndex = this.iRootIndexPosition;
      }

      do
      {
        int leftChild_BalanceValue;
        System.out.println(this.playerList.get(updatingParentNodes_BalanceIndex));
        if(this.playerList.get(updatingParentNodes_BalanceIndex).get(1) != -1)
        {
          leftChild_BalanceValue = this.playerList.get(this.playerList.get(updatingParentNodes_BalanceIndex).get(1)).get(4);
        }
        else
        {
          leftChild_BalanceValue = 0;
        }

        int rightChild_BalanceValue;
        if(this.playerList.get(updatingParentNodes_BalanceIndex).get(2) != -1)
        {
          rightChild_BalanceValue = this.playerList.get(this.playerList.get(updatingParentNodes_BalanceIndex).get(2)).get(4);
        }
        else
        {
          rightChild_BalanceValue = 0;
        }

        int newBalanceValue = rightChild_BalanceValue - leftChild_BalanceValue;
        this.playerList.get(updatingParentNodes_BalanceIndex).set(4, newBalanceValue);

        updatingParentNodes_BalanceIndex = this.playerList.get(this.playerList.get(currentNodeIndex).get(3)).get(3);
      }
      while(updatingParentNodes_BalanceIndex != this.iRootIndexPosition);*/






          /*currentParentBalance = this.playerList.get(currentNodeIndex).get(4);

          //We now check if the tree is unbalanced. It will be unbalanced if the current node has a balance that is not either -1, 0 or 1.
          if (currentParentBalance < -1 || currentParentBalance > 1)
          {
            //Current tree is unbalanced. We need to perform a rotation.

            //Case: Left-left heavy tree (Parent balance is -2, and left child IS NOT -1 or +1
            //Solution: Rotate right around root.
            if(currentParentBalance == -2 && this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).get(4) != -1 && this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).get(4) != 1)
            {
              System.out.println("Tree is unbalanced. It is left-left heavy.");

              //New root will be the current root nodes' left child:
              int newRootIndex = this.playerList.get(this.iRootIndexPosition).get(1);

              //New right child of the new root node will be the old root node.
              int newRootRightChildIndex = this.iRootIndexPosition;

              //New left child of the old root node will be the old left childs' right node.
              int newRightChildLeftLeafIndex = this.playerList.get(this.playerList.get(this.iRootIndexPosition).get(1)).get(2);

              //Perform the right rotation:

              //  Set the new root nodes' attributes:
              this.playerList.get(newRootIndex).set(2, newRootRightChildIndex); //Sets new right child, to the old root.
              this.playerList.get(newRootIndex).set(3,-1); //Sets new parent to -1 (no parent).
              this.playerList.get(newRootIndex).set(4,0); //Sets new balance to 0 (balanced).

              //  Set the new attributes for the old root node:
              this.playerList.get(iRootIndexPosition).set(1, newRightChildLeftLeafIndex); //Sets new left child, to the old root's right child.
              this.playerList.get(iRootIndexPosition).set(3,newRootIndex); //Sets new parent to the new root.
              this.playerList.get(iRootIndexPosition).set(4,0); //Sets new balance to 0 (balanced).

              //  Set the new attributes for the old root node's left child's right leaf node:
              this.playerList.get(this.playerList.get(newRootIndex).get(2)).set(3,this.iRootIndexPosition); //Sets new parent to the old root node's left child's right leaf node.

              //Update the global attribute pointing to the now current root node.
              this.iRootIndexPosition = newRootIndex;
            }









            //Case: Left-right heavy tree

            //Case: Critically unbalanced left-left heavy tree (parent balance is -2, left-child balance is -1)
            //Solution: Rotate right around parent.

            //Case: Critically unbalanced left-right heavy tree (Parent balance is -2, left-child balance is +1)
            //Solution: 1. Rotate left around child, then 2. rotate right around parent.
            if(currentParentBalance == -2 && this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).get(4) == 1)
            {

              System.out.println("Tree is unbalanced. It is critical left-right heavy.");
              //Perform the initial left rotation around the child

              //New right sub-child for the parents old left child will be:
              int oldLeftChild_NewRightChildIndex = this.playerList.get(this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).get(2)).get(1);

              //New left child for the parent will be:
              int oldLeftChild_OldRightChildIndex = this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).get(2);

              //Parents old left childs' right sub-child's new left sub-sub-child index is:
              int oldLeftChildsRightChild_NewLeftChild = this.playerList.get(currentNodeIndex).get(1);

              //  Set the new attributes for the parents old left child:
              this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).set(2, oldLeftChild_NewRightChildIndex); //sets this nodes' new right child.
              this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).set(3,oldLeftChild_OldRightChildIndex); //Sets the new parent to the node.
              this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).set(4,0); //Sets new balance to 0 (balanced).

              //  Set the new attributes for the old left-childs' right sub-child:
              this.playerList.get(oldLeftChild_OldRightChildIndex).set(1,oldLeftChildsRightChild_NewLeftChild); //sets this nodes' new left child.
              this.playerList.get(oldLeftChild_OldRightChildIndex).set(3,currentNodeIndex); //Sets the new parent to the node.
              this.playerList.get(oldLeftChild_OldRightChildIndex).set(4,-2); //Sets new balance to 0 (balanced).

              //  Set the parent nodes' changed attributes:
              this.playerList.get(currentNodeIndex).set(1, oldLeftChild_OldRightChildIndex); //Sets new left child.



              //Perform the final right rotation:

              //New parent will be the current parent nodes' left child:
              int newParentIndex = this.playerList.get(currentNodeIndex).get(1);

              //New right child of the new root node will be the old root node.
              int newParentRightChildIndex = currentNodeIndex;

              //New left child of the old parent node will be the old left childs' right node.
              int newRightChildLeftLeafIndex = this.playerList.get(this.playerList.get(currentNodeIndex).get(1)).get(2);


              //  Set the new parent nodes' attributes:
              this.playerList.get(newParentIndex).set(2, newParentRightChildIndex); //Sets new right child, to the old root.
              this.playerList.get(newParentIndex).set(3,-1); //Sets new parent to -1 (no parent).
              this.playerList.get(newParentIndex).set(4,0); //Sets new balance to 0 (balanced).

              //  Set the new attributes for the old parent node:
              this.playerList.get(currentNodeIndex).set(1, newRightChildLeftLeafIndex); //Sets new left child, to the old parent's right child.
              this.playerList.get(currentNodeIndex).set(3,newParentIndex); //Sets new parent to the old parent.
              this.playerList.get(currentNodeIndex).set(4,0); //Sets new balance to 0 (balanced).

              //  Set the new attributes for the old parent node's left child's right leaf node:
              this.playerList.get(this.playerList.get(newParentIndex).get(2)).set(3,currentNodeIndex); //Sets new parent to the old parent node's left child's right leaf node.

              //Update the global attribute pointing to the now current root node.
              if(this.playerList.get(newParentIndex).get(3) == -1)
              {
                this.iRootIndexPosition = newParentIndex;
              }

            }


            //Case: Critically unbalanced right-right heavy tree (Parent balance is +2, right child balance is +1)
            //Solution: Rotate left around parent.

            //Case: Critically unbalanced right-left heavy tree (Parent balance is +2, right child balance is -1)
            //Solution: 1. Rotate right around child, then 2. rotate left around parent.

            if(currentNodeIndex == this.iRootIndexPosition)
            {
              FinalIteration=true;
            }

            if(this.playerList.get(currentNodeIndex).get(3) != -1)
            {
              currentNodeIndex = this.playerList.get(currentNodeIndex).get(3);
            }

          }
          else
          {
            //Tree is not unbalanced, so far. We continue updating parent node balances until reaching the root node.

            if(currentNodeIndex == this.iRootIndexPosition)
            {
              FinalIteration=true;
            }

            if(currentNodeIndex != this.iRootIndexPosition)
            {
              currentNodeIndex = this.playerList.get(currentNodeIndex).get(3);
            }
          }
        }
        while (currentNodeIndex != this.iRootIndexPosition || !FinalIteration);*/
      //}


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