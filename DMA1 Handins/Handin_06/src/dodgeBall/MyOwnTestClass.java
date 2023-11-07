package dodgeBall;

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
      i = i*2;
    }

  }
}
