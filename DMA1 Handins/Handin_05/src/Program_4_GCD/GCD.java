package Program_4_GCD;

public class GCD
{
  // Euclidean algorithm is the most simple to implement

  public int findGCD1(int a, int b)
  {
    // checks if one of the input numbers are negative, if true, then throwing a exception.
    if (a < 0 || b < 0) /** [Time complexity = O(3), constant time] */
    {
      throw new RuntimeException("Not working!");
    }

    //declaring and initializing a variable, and assigning the initial value to be 1.
    int gcd = 1; /** [Time complexity = O(1), constant time] */
    {
        /*/ The loop control variable is set to 1. The loop will continue illiterate as long as
        i <= a and i <= b are true.
         */

      for (int i = 1; i <= a && i<= b; i++) /** [Time complexity = O(5) + O(a) + O(b), linear time. Since the factor determining the amount of iterations/loops is both a and b.] */
      {

          /*/ we're using the modulo operator, and checking if the remainder of
          a and b are equal to 0.... If both statement true, then the variable gcd
          is set to i, because common divisor found and now we're updating the gcd.
           */
        if (a % i == 0 && b % i == 0) /** [Time complexity = O(5), constant time] */
        {
          gcd = i; /** [Time complexity = O(1), constant time] */
        }
      }
    }
    // returning the gcd
    return gcd; /** [Time complexity = O(1), constant time] */

    /** Time Complexity Analysis:
     * We observe the lines of code. The principle is to identify the "worst-case" performance as Big-Oh. As such if we have performance worse than constant time, we simply ignore the constant factor.
     * From line 10 we have T(n) = O(3) constant time.
     * From line 16 we have T(n) = O(1) constant time.
     * From line 22 we have T(n) = O(5) + O(a) + O(b), linear time.
     * From line 29 we have T(n) = O(5) constant time.
     * From line 31 we have T(n) = O(1) constant time.
     * From line 36 we have T(n) = O(1) constant time.

     * Since we have a part of the code that is repeated a linear number of times, we can simply ignore the constant time factor, and provide our Big-Oh expression as:
     * T(n) = O(a) + O(b).

     * This can be further reduced to the following final Big-Oh expression, which simply expressed the linear growth:
     * T(n) = O(n)
     * */
  }


  public int findGCD2(int a, int b)
  {
    // continuing looping as long as a not equal to b
    while(a != b) /** [Time complexity = O(a) + O(b), linear time. Since the factor determining the amount of iterations/loops is both a and b.] */
    {
      // if a is greater than b, then a can be divided by b, with a remainder.
      if (a > b) /** [Time complexity = O(1), constant time, since this check is always performed] */
      {
        // if a is greater than b, then (a = a-b), here we're finding the remainder.
        a -= b; /** [Time complexity = O(a), linear time, since in this subsection it is 'a' that is the determining time factor.] */

        // executes following code, because b is greater or equal to a.
      }
      else
      {
        // finding the remainder, by (b = b-a) to find the remainder.
        b -= a; /** [Time complexity = O(b), linear time, since in this subsection it is 'b' that is the determining time factor.] */
      }
    }
    return a; /** [Time complexity = O(1), constant time - since a value is always returned] */
    /* The code above reduce a og b until they are equal. When they are equal, then the gcd is found.
    We return the value a, because it's the gcd of the input arguments, a and b. */

    /** Time Complexity Analysis:
     * We observe the lines of code. The principle is to identify the "worst-case" performance as Big-Oh. As such if we have performance worse than constant time, we simply ignore the constant factor.
     * From line 59 we have T(n) = O(a) + O(b), linear time.
     * From line 62 we have T(n) = O(1), constant time.
     * From line 65 we have T(n) = O(a), linear time.
     * From line 72 we have T(n) = O(b), linear time.
     * From line 75 we have T(n) = O(1) constant time.

     * We can express the above as:
     * T(n) = O(a) + O(b) + O(1) + O(a) + O(b) + O(1)

     * Since we have a part of the code that is repeated a linear number of times, we can simply ignore the constant time factors, and provide our Big-Oh expression as:
     * T(n) = O(a) + O(b) + O(a) + O(b)

     * We rewrite this as:
     * T(n) = 2 * O(a) + 2 * O(b)

     * Again we ignore the constants:
     * T(n) = O(a) + O(b)

     * This can be further reduced to the following final Big-Oh expression, which simply expresses a linear growth:
     * T(n) = O(n)
     * */

  }




  public int findGCD3(int a, int b)
  {

    if (b == 0) // if b is equal to 0, means that a is our gcd.
      /** [Time complexity = O(1), constant time] */
    {
      return a; /** [Time complexity = O(1), constant time] */
    }

    return findGCD3(b,a % b); // Making a recursive call to find the gcd,using Euclidean algorithm
    /** [For every recursive call here, we have that [a,b] calculates into [b, a % b] and results in one of these two possibilities:
     * Case 1. 'b1' becomes a value larger than, or equal, to 'a0 / 2' -> The division by 2 is because this is the smallest value of division that will be applied, since division with '1' would result in an endless loop of modulus divisions. The next recursive iteration will call a new value where b1 = a0 / 2, or where the largest value of b1 is half the size of 'a0'.
     * Case 1. Thus for this case we get T(n) = O(b/2)

     * Case 2. 'b1' becomes a value smaller than 'a0 / 2' -> this would result in the next iteration to calculate 'a1 = b0', since prior iterations 'b' values in the euclidean algorithm turn into the next iterations 'a' values.
     * Case 2. Thus for this case we get T(n) = O(a/2)

     * From the above 2 cases we can begin to establish a Big-Oh notation for this program. First we throw away all constant time, since we have identified the above non-constant time factors. We can express Big-Oh as:
     * O(n) = O(a/2) + O(b/2)

     * Observing the above Big-Oh expression, we can also see that the worst-case decrease (division by 2, which results in the most recursive iterations), is similar to a logarithmic function - as opposed to an exponential one.
     * We can thus also express the Big-Oh notation as:
     * T(n) = O(log(a)) + O(log(b)).

     * Using basic math principles [log(x * y) = log(x) + log(y).] we can reduce this to a single expression, such that:
     * Final Big-Oh Notation -> T(n) = O(log(a * b))
     * ] */
  }



}
