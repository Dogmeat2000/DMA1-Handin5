package median;

import java.io.*;
import java.util.*;

public class Median {
    //First we Create our Arraylist which will hold the data
    private ArrayList<Integer>Input = new ArrayList<>();


    // Add your private fields here


    public void add(int x) {
            //To add a number in the arraylist so that we can save the data
            Input.add(x);
        // Implement your method to add x to the data structure
    }

    public int median() {

        // Implement your method to return the median of the numbers added so far


        //Sorting the list from lowest to highest number
        Collections.sort(Input);
        //Returning the number in the middle which is our median, if the list size is even, then we have 2 median size/2 and size/2 + 1, and if it is odd its only size/2,
        //therefore we can always use size/2 to get the median
        return Input.get(Input.size()/2);
    }
}
