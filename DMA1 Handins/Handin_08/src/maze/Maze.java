package maze;

import java.util.*;

public class Maze
{
  public int shortestPath(char[][] maze)
  {
    int height = maze.length;
    int width = maze[0].length;

    // Implement your path-finding algorithm here!

    //In order to implement this method, we base our below code on the principles outlined in the DMA1 Lecture 11 PowerPoint slides.
    //Our basic approach will involve implementing a breadth first search algorithm into a graph representation of the maze.

    //Check if received input is within input constraints. If not, we simply terminate method now.
    if (height > 1000 || width < 3)
    {
      return Integer.MAX_VALUE;
    }

    //Declare additional attributes:
    Deque<Integer[]> q = new ArrayDeque<>(); //Used as a queue during the breath first search.
    int numberOfTotalSteps = 0;

    //We conduct a breath first search pattern starting from [1][1].
    //Now we begin tracking each position of the maze in a matrix, in order to keep track of identified/visited vertex's, as well as parent vertex's.
    int[][][] mazeData = new int[height][width][3]; //Used to contain a coordinate/index based "graph" representation of the possible pathways in the maze.

    //We start by adding our source vertex / root node to the queue, and marking it as identified.

    Integer[] vertex = {1, 1, 1, -1, -1}; /* vertex [height index, width index, unidentified or identified or visited?, parent node height index, parent node width index]*/
    mazeData[1][1][0] = 1; //0 = unidentified, 1 = identified, 2=visited
    mazeData[1][1][1] = -1; //No parent vertex y-coordinate
    mazeData[1][1][2] = -1; //No parent vertex x-coordinate
    q.add(vertex);

    //Let us declare some attributes to check if we find an exit:
    boolean exitVertexFound = false;
    Integer[] vertexExit = {-1, -1, -1, -1, -1};

    while (q.peekFirst() != null && !exitVertexFound)
    {
      //Runs until the queue is empty - meaning we have reached the last element in the maze OR until we reach the exit node.
      //Visit first element in the queue, and remove it from the queue.
      Integer[] u = q.removeFirst();
      mazeData[u[0]][u[1]][0] = 2; //mark this vertex as being visited.

      //First check if this is an exit node. If it is, we don't need to perform any more evaluations:
      if (u[0] == height-2 && u[1] == width-2)
      {
        //This is an exit node. We also know that it must also be connected to the root node, since it has been added to the queue. We declare this as our shortest exit vertex:
        vertexExit = u;
        exitVertexFound = true;
      }
      else
      {
        //Add all adjacent elements around the current vertex to the queue, if they haven't already been either identified or visited.

        //Check the element above:
        Integer[] vertexAbove = {u[0] - 1, u[1], 1, u[0], u[1]};
        if (mazeData[u[0] - 1][u[1]][0] == 0 && maze[u[0] - 1][u[1]] == '.')
        {
          //The above element has not been identified nor visited AND is an empty path-position. Add it to the queue.
          q.add(vertexAbove);

          //Update its metadata, so that we know it has been identified.
          mazeData[u[0] - 1][u[1]][0] = 1; //1 = identified, 2=visited
          mazeData[u[0] - 1][u[1]][1] = u[0]; //set u as the parent vertex (y-coordinate)
          mazeData[u[0] - 1][u[1]][2] = u[1]; //set u as the parent vertex (x-coordinate)
        }

        //Check the element to the left:
        Integer[] vertexLeft = {u[0], u[1] - 1, 1, u[0], u[1]};
        if (mazeData[u[0]][u[1] - 1][0] == 0 && maze[u[0]][u[1] - 1] == '.')
        {
          //The left element has not been identified nor visited AND is an empty path-position. Add it to the queue.
          q.add(vertexLeft);

          //Update its metadata, so that we know it has been identified.
          mazeData[u[0]][u[1] - 1][0] = 1; //1 = identified, 2=visited
          mazeData[u[0]][u[1] - 1][1] = u[0]; //set u as the parent vertex (y-coordinate)
          mazeData[u[0]][u[1] - 1][2] = u[1]; //set u as the parent vertex (x-coordinate)
        }

        //Check the element below:
        Integer[] vertexBelow = {u[0] + 1, u[1], 1, u[0], u[1]};
        if (mazeData[u[0] + 1][u[1]][0] == 0 && maze[u[0] + 1][u[1]] == '.')
        {
          //The left element has not been identified nor visited AND is an empty path-position. Add it to the queue.
          q.add(vertexBelow);

          //Update its metadata, so that we know it has been identified.
          mazeData[u[0] + 1][u[1]][0] = 1; //1 = identified, 2=visited
          mazeData[u[0] + 1][u[1]][1] = u[0]; //set u as the parent vertex (y-coordinate)
          mazeData[u[0] + 1][u[1]][2] = u[1]; //set u as the parent vertex (x-coordinate)
        }

        //Check the element to the right:
        Integer[] vertexRight = {u[0], u[1] + 1, 1, u[0], u[1]};
        if (mazeData[u[0]][u[1] + 1][0] == 0 && maze[u[0]][u[1] + 1] == '.')
        {
          //The left element has not been identified nor visited AND is an empty path-position. Add it to the queue.
          q.add(vertexRight);

          //Update its parent metadata, so that we know it has been identified.
          mazeData[u[0]][u[1] + 1][0] = 1; //1 = identified, 2=visited
          mazeData[u[0]][u[1] + 1][1] = u[0]; //set u as the parent vertex (y-coordinate)
          mazeData[u[0]][u[1] + 1][2] = u[1]; //set u as the parent vertex (x-coordinate)
        }
      }
    }

    if(exitVertexFound)
    {
      //let's backtrack to the root vertex through our identified parents and count the number of steps it takes. This will be the path length from root vertex to exit vertex.
      Integer[] currentVertex = vertexExit;

      while(mazeData[currentVertex[0]][currentVertex[1]][1] != 1 || mazeData[currentVertex[0]][currentVertex[1]][2] != 1)
      {
        int nextXCoordinate = mazeData[currentVertex[0]][currentVertex[1]][2];
        int nextYCoordinate = mazeData[currentVertex[0]][currentVertex[1]][1];

        currentVertex[0] = nextYCoordinate; //y-coordinate of current vertex's parent.
        currentVertex[1] = nextXCoordinate; //x-coordinate of current vertex's parent.
        numberOfTotalSteps++;
      }
      numberOfTotalSteps++;
    }
    else
    {
      //There is no path from [1][1] to [H-2][W-2], Return max_integer.
      return Integer.MAX_VALUE;
    }
    return numberOfTotalSteps;
  }
}