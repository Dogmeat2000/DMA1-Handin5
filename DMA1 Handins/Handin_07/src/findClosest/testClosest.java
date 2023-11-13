package findClosest;

import java.util.ArrayList;

public class testClosest
{
  public static void testAll() {
    clearTerminal();
    testSinglePair();
    testSinglePairInv();
    test1();
    test2();
    test3();
    testQuadraticTime();
  }

  public static void testSinglePair() {
    int[] input = {3, 5};
    int correctAnswer = 2;

    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i : input) list.add(i);

    int output = new closest().findClosest(list);

    if (output != correctAnswer)
      outputFail("testSinglePair",
          "Expected output " + correctAnswer +
              " but got " + output);
    else
      outputPass("testSinglePair");
  }

  public static void testSinglePairInv() {
    int[] input = {7, 4};
    int correctAnswer = 3;

    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i : input) list.add(i);

    int output = new closest().findClosest(list);

    if (output != correctAnswer)
      outputFail("testSinglePairInv",
          "Expected output " + correctAnswer +
              " but got " + output);
    else
      outputPass("testSinglePairInv");
  }

  public static void test1() {
    int[] input = {95, 66, 82, 63, 78, 37, 100, 96, 98, 17, 13, 7, 28, 74, 73, 77, 41, 25, 53, 93};
    int correctAnswer = 96 - 95;

    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i : input) list.add(i);

    int output = new closest().findClosest(list);

    if (output != correctAnswer)
      outputFail("test1",
          "Expected output " + correctAnswer +
              " but got " + output);
    else
      outputPass("test1");
  }

  public static void test2() {
    int[] input = {275, 938, 8, 77, 649, 803, 500, 823, 519, 711, 422, 227, 235, 655, 373};
    int correctAnswer = 655 - 649;

    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i : input) list.add(i);

    int output = new closest().findClosest(list);

    if (output != correctAnswer)
      outputFail("test2",
          "Expected output " + correctAnswer +
              " but got " + output);
    else
      outputPass("test2");
  }
  public static void test3() {
    int[] input = { 190, -6, -38, 269, 832, 7399, 637, 237, 217, 416, 978, 1444, 4177, 197, 1118, 367, 55, -55, 74, 12, 95 };
    int correctAnswer = 7;

    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i : input) list.add(i);

    int output = new closest().findClosest(list);

    if (output != correctAnswer)
      outputFail("test3",
          "Expected output " + correctAnswer +
              " but got " + output);
    else
      outputPass("test3");
  }


  public static void testQuadraticTime() {
    int n1 = 1000;
    int n2 = 10000;
    long t11 = timeTest(n1);
    long t12 = timeTest(n1);
    long t13 = timeTest(n1);
    long t14 = timeTest(n1);
    long t15 = timeTest(n1);
    long t1 = Math.min(Math.min(Math.min(Math.min(t11, t12), t13), t14), t15);
    System.out.println("");
    System.out.println("Testing time complexity.");
    long t21 = timeTest(n2);
    long t22 = timeTest(n2);
    long t23 = timeTest(n2);
    long t2 = Math.min(Math.min(t21, t22), t23);

    double slowdown = ((double) t2) / t1;
    double linearSlowdown = ((double) n2) / n1;
    if (slowdown > Math.pow(linearSlowdown, 1.2))
      System.out.println("That's not log-linear time!");
    else
      System.out.println("Yes! Log-linear time! Assuming all tests are ok, you get 2 points in total.");
  }

  private static long timeTest(int n) {
    long t1 = System.nanoTime();
    ArrayList<Integer> list = new ArrayList<Integer>(n);
    for (int i = 0; i < n; ++i)
      list.add((int) (i * 982451653L % 413158511L));
    new closest().findClosest(list);
    long t2 = System.nanoTime();
    // For debugging, output the individual times:
    // System.out.println(n + " " + (t2 - t1));
    return t2 - t1;
  }

  private static void clearTerminal() {
    System.out.print('\u000C');
  }

  private static void outputPass(String testName) {
    System.out.println("[Pass " + testName + "]");
  }

  private static void outputFail(String testName, String message) {
    System.out.println("[FAIL " + testName + "] " + message);
  }

  public static void main(String[] args) {
    testAll();
    /*Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    for (int t = 0; t < testcases; ++t) {
      int n = sc.nextInt();
      ArrayList<Integer> list = new ArrayList<Integer>();
      for (int i = 0; i < n; ++i) list.add(sc.nextInt());
      System.out.println(new Closest().computeClosest(list));
    } */
  }
}
