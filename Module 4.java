import java.util.Scanner;

public class Mod4Class {
  public static void main(String args[]) {
    double enteredValues[] = new double[5]; //array to hold 5 entered values
    double total = 0;
    double maximum;
    double minimum;
    double tempMin = 0;
    double tempMax = 0;

    Scanner scnr = new Scanner(System.in);
    
    System.out.print("Enter 5 values: ");
    int i = enteredValues.length;
    while (i > 0) { //loops through array backwards and sets each of the 5 values as array elements
        enteredValues[i - 1] = scnr.nextDouble(); //stores each entered value in array
        System.out.println(enteredValues[i - 1]); //reads back each entered value
        total += enteredValues[i - 1]; //adds each element to total
        minimum = enteredValues[i - 1];
        maximum = enteredValues[i - 1];
        if (i == enteredValues.length) {
          tempMin = minimum; //sets initial min and max
          tempMax = maximum;
        } else { //checks for new max and min
            if (tempMax < maximum) { 
              tempMax = maximum;
            }
            if (tempMin > minimum){
              tempMin = minimum;
            }
            if (i == 1){ //final print on last run of loop
              System.out.println("The total is:" + total);
              System.out.println("The average is:" + (total/enteredValues.length));
              System.out.println("The maximum is:" + tempMax);
              System.out.println("The minimum is:" + tempMin);
              System.out.println("20% interest on " + total + " is: " + (total * 0.2));
            }
            
      }
      i -= 1;
    }
  }
}