import java.util.ArrayList;

public class dodgeBall
{
  // Add any private fields you might need here
  private int xPos;
  private ArrayList<Integer> playerList;

  //Kristian Dashnaw
  public void addPlayer(int x)
  {
    // Implement your code here to add a player to the line

    //Validate input, ensuring that worked attributes are within the input constraints of [1 <= x <= 5,000,000]
    if (this.xPos < 1)
    {
      this.xPos = 1;
    }
    else if (this.xPos > 5000000)
    {
      this.xPos = 5000000;
    }
    else
    {
      this.xPos = x;
    }

    //Check if this is the first player being added. If so, initialize the playerList arrayList:
    if(this.playerList == null)
    {
      playerList = new ArrayList<>(5000000);
    }



  }

  public int throwBall(int x)
  {
    int distance = 42;
    // Implement your code here to update the line of players and return the distance
    return distance;
  }
}
