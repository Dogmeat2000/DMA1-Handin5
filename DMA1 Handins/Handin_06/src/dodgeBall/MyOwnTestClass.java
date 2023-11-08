package dodgeBall;

import java.util.ArrayList;

public class MyOwnTestClass
{
  public static void main(String[] args)
  {
    System.out.println("Add a player:");
    dodgeBall b = new dodgeBall();
    b.addPlayer(10);
    System.out.println("Player was added to x=10");
    System.out.println("Array currently Contains: " + b.playerList);

    System.out.println("\nNow we cycle through a loop adding some more values");
    for (int i = 0; i < 500; i++)
    {
      System.out.println("\nAdding x=" + i);
      b.addPlayer(i);
      System.out.println("Array currently Contains: " + b.playerList);
      i = i * 2;
    }

    ArrayList<ArrayList<Integer>> treePlayerList1 = new ArrayList<>();
    ArrayList<ArrayList<Integer>> treePlayerList2 = new ArrayList<>();
    ArrayList<Integer> treePlayerEmptyPos = new ArrayList<>();
    treePlayerEmptyPos.add(-1); //x value
    treePlayerEmptyPos.add(-1); //left child index
    treePlayerEmptyPos.add(-1); //right child index
    treePlayerEmptyPos.add(-1); //parent index
    treePlayerEmptyPos.add(-1); //balance value

    System.out.println(
        "\n\nPrint the first 5 depths/heights of the balanced binary tree, so validate that proper balancing has occurred:\n");
    int[] depth_zero = {-1}; //1 possible entries
    int[] depth_one = {-1, -1}; //2 possible entries
    int[] depth_two = {-1, -1, -1, -1}; //4 possible entries
    int[] depth_three = {-1, -1, -1, -1, -1, -1, -1, -1}; //8 possible entries
    int[] depth_four = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1}; //16 possible entries
    int[] depth_five = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    //Print depth level 0
    treePlayerList1.add(b.playerList.get(b.iRootIndexPosition));
    depth_zero[0] = b.playerList.get(b.iRootIndexPosition).get(0);
    System.out.println("d0:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t[" + depth_zero[0] + "]");

    //System.out.println(treePlayerList1 + "\n");


    //Print depth level 1
    System.out.print("\nd1:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
    int indexArrayCounter = 0;
    for (int i = 0; i < treePlayerList1.size(); i++)
    {
      System.out.print("[");
      //print left child of previous node
      if (treePlayerList1.get(i).get(1) != -1)
      {
        //Only attempt to read value, if it exists. otherwise we simply enter -1 to indicate position is empty.
        depth_one[indexArrayCounter] = b.playerList.get(
            treePlayerList1.get(i).get(1)).get(0);
        System.out.print(depth_one[indexArrayCounter] + " | ");
        treePlayerList2.add(b.playerList.get(treePlayerList1.get(i).get(1)));
        indexArrayCounter++;
      }
      else
      {
        depth_one[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  " + " | ");

        treePlayerEmptyPos.clear();
        treePlayerEmptyPos.add(-1);
        treePlayerEmptyPos.add(-1);
        treePlayerEmptyPos.add(-1);
        treePlayerEmptyPos.add(b.iRootIndexPosition); //Because at depth 1, the parent is the root.
        treePlayerEmptyPos.add(-1);

        treePlayerList2.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      //print right child of previous node
      if (treePlayerList1.get(i).get(2) != -1)
      {
        depth_one[indexArrayCounter] = b.playerList.get(
            treePlayerList1.get(i).get(2)).get(0);
        System.out.print(depth_one[indexArrayCounter]);
        treePlayerList2.add(b.playerList.get(treePlayerList1.get(i).get(2)));
        indexArrayCounter++;
      }
      else
      {
        depth_one[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  ");

        treePlayerEmptyPos.clear();
        treePlayerEmptyPos.add(-1);
        treePlayerEmptyPos.add(-1);
        treePlayerEmptyPos.add(-1);
        treePlayerEmptyPos.add(b.iRootIndexPosition); //Because at depth 1, the parent is the root.
        treePlayerEmptyPos.add(-1);

        treePlayerList2.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      if (i == treePlayerList1.size() - 1)
      {
        System.out.println("]");
      }
      else
      {
        if (i % 2 != 0)
        {
          System.out.print("]\t\t\t\t");
        }
        else
        {
          System.out.print("]\t\t");
        }
      }
    }
    //Clear previous treePlayerList, so we can fill it with the next depth levels values:
    treePlayerList1.clear();
    //System.out.println(treePlayerList2 + "\n");


    treePlayerEmptyPos.clear();
    treePlayerEmptyPos.add(-1);
    treePlayerEmptyPos.add(-1);
    treePlayerEmptyPos.add(-1);
    treePlayerEmptyPos.add(-1);
    treePlayerEmptyPos.add(-1);




    //Print depth level 2
    System.out.print("\nd2:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
    indexArrayCounter = 0;
    for (int i = 0; i < treePlayerList2.size(); i++)
    {
      System.out.print("[");
      //print left child of previous node
      if (treePlayerList2.get(i).get(1) != -1)
      {
        //Only attempt to read value, if it exists. otherwise we simply enter -1 to indicate position is empty.
        depth_two[indexArrayCounter] = b.playerList.get(
            treePlayerList2.get(i).get(1)).get(0);
        System.out.print(depth_two[indexArrayCounter] + " | ");
        treePlayerList1.add(b.playerList.get(treePlayerList2.get(i).get(1)));
        indexArrayCounter++;
      }
      else
      {
        depth_two[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  " + " | ");

        treePlayerList1.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      //print right child of previous node
      if (treePlayerList2.get(i).get(2) != -1)
      {
        depth_two[indexArrayCounter] = b.playerList.get(
            treePlayerList2.get(i).get(2)).get(0);
        System.out.print(depth_two[indexArrayCounter]);
        treePlayerList1.add(b.playerList.get(treePlayerList2.get(i).get(2)));
        indexArrayCounter++;
      }
      else
      {
        depth_two[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  ");

        treePlayerList1.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      if (i == treePlayerList2.size() - 1)
      {
        System.out.println("]");
      }
      else
      {
        if (i % 2 != 0)
        {
          System.out.print("]\t\t\t\t");
        }
        else
        {
          System.out.print("]\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        }
      }
    }
    //Clear previous treePlayerList, so we can fill it with the next depth levels values:
    treePlayerList2.clear();
    //System.out.println(treePlayerList1 + "\n");

    //Print depth level 3
    System.out.print("\nd3:\t\t\t\t\t\t\t\t\t\t\t");
    indexArrayCounter = 0;
    for (int i = 0; i < treePlayerList1.size(); i++)
    {
      System.out.print("[");
      //print left child of previous node
      if (treePlayerList1.get(i).get(1) != -1)
      {
        //Only attempt to read value, if it exists. otherwise we simply enter -1 to indicate position is empty.
        depth_three[indexArrayCounter] = b.playerList.get(
            treePlayerList1.get(i).get(1)).get(0);
        System.out.print(depth_three[indexArrayCounter] + " | ");
        treePlayerList2.add(b.playerList.get(treePlayerList1.get(i).get(1)));
        indexArrayCounter++;
      }
      else
      {
        depth_three[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  " + " | ");

        treePlayerList2.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      //print right child of previous node
      if (treePlayerList1.get(i).get(2) != -1)
      {
        depth_three[indexArrayCounter] = b.playerList.get(
            treePlayerList1.get(i).get(2)).get(0);
        System.out.print(depth_three[indexArrayCounter]);
        treePlayerList2.add(b.playerList.get(treePlayerList1.get(i).get(2)));
        indexArrayCounter++;
      }
      else
      {
        depth_three[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  ");

        treePlayerList2.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      if (i == treePlayerList1.size() - 1)
      {
        System.out.println("]");
      }
      else
      {
        if (i % 2 != 0)
        {
          System.out.print("]\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        }
        else
        {
          System.out.print("]\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        }
      }
    }
    //Clear previous treePlayerList, so we can fill it with the next depth levels values:
    treePlayerList1.clear();

    //Print depth level 4
    System.out.print("\nd4:\t\t\t\t");
    indexArrayCounter = 0;
    for (int i = 0; i < treePlayerList2.size(); i++)
    {
      System.out.print("[");
      //print left child of previous node
      if (treePlayerList2.get(i).get(1) != -1)
      {
        //Only attempt to read value, if it exists. otherwise we simply enter -1 to indicate position is empty.
        depth_four[indexArrayCounter] = b.playerList.get(
            treePlayerList2.get(i).get(1)).get(0);
        System.out.print(depth_four[indexArrayCounter] + " | ");
        treePlayerList1.add(b.playerList.get(treePlayerList2.get(i).get(1)));
        indexArrayCounter++;
      }
      else
      {
        depth_four[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  " + " | ");

        treePlayerList1.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      //print right child of previous node
      if (treePlayerList2.get(i).get(2) != -1)
      {
        depth_four[indexArrayCounter] = b.playerList.get(
            treePlayerList2.get(i).get(2)).get(0);
        System.out.print(depth_four[indexArrayCounter]);
        treePlayerList1.add(b.playerList.get(treePlayerList2.get(i).get(2)));
        indexArrayCounter++;
      }
      else
      {
        depth_four[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  ");

        treePlayerList1.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      if (i == treePlayerList2.size() - 1)
      {
        System.out.println("]");
      }
      else
      {
        if (i % 2 != 0)
        {
          System.out.print("]\t\t\t\t\t\t\t\t\t\t");
        }
        else
        {
          System.out.print("]\t\t\t\t\t\t\t\t\t\t");
        }
      }
    }
    //Clear previous treePlayerList, so we can fill it with the next depth levels values:
    treePlayerList2.clear();
    //System.out.println(treePlayerList1 + "\n");


    //Print depth level 5
    System.out.print("\nd5:\t");
    indexArrayCounter = 0;
    for (int i = 0; i < treePlayerList1.size(); i++)
    {
      System.out.print("[");
      //print left child of previous node
      if (treePlayerList1.get(i).get(1) != -1)
      {
        //Only attempt to read value, if it exists. otherwise we simply enter -1 to indicate position is empty.
        depth_five[indexArrayCounter] = b.playerList.get(
            treePlayerList1.get(i).get(1)).get(0);
        System.out.print(depth_five[indexArrayCounter] + " | ");
        treePlayerList2.add(b.playerList.get(treePlayerList1.get(i).get(1)));
        indexArrayCounter++;
      }
      else
      {
        depth_five[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  " + " | ");

        treePlayerList2.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      //print right child of previous node
      if (treePlayerList1.get(i).get(2) != -1)
      {
        depth_five[indexArrayCounter] = b.playerList.get(
            treePlayerList1.get(i).get(2)).get(0);
        System.out.print(depth_five[indexArrayCounter]);
        treePlayerList2.add(b.playerList.get(treePlayerList1.get(i).get(2)));
        indexArrayCounter++;
      }
      else
      {
        depth_five[indexArrayCounter] = -1; // no node/leaf
        System.out.print("  ");

        treePlayerList2.add(treePlayerEmptyPos);
        indexArrayCounter++;
      }

      if (i == treePlayerList1.size() - 1)
      {
        System.out.println("]");
      }
      else
      {
        if (i % 2 != 0)
        {
          System.out.print("]\t\t\t\t");
        }
        else
        {
          System.out.print("]\t\t");
        }
      }
    }
    //Clear previous treePlayerList, so we can fill it with the next depth levels values:
    treePlayerList1.clear();


  }
}
