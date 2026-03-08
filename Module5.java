import java.util.Scanner;
import java.util.Arrays;

public class Module5 {
  public static void main(String[] args) {

    String[] dayOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}; //array for each day of the week
    Double[] dailyTemp = {84.2, 83.4, 85.0, 82.4, 83.4, 84.5, 81.7}; //array for the corresponding temp
    Scanner scnr = new Scanner(System.in); 
    
    String dayToLookUp = " "; //inializing empty string as placeholder

    int i = 0; //declaring variables
    double totalTemp = 0.0;
    double tempAvg;

    dayToLookUp = scnr.nextLine().toLowerCase(); //sanitizing input to lowercase
    dayToLookUp = dayToLookUp.substring(0, 1).toUpperCase() + dayToLookUp.substring(1); //matching case of the days in the array 

    int counter = 0;
    
    if (dayToLookUp.equals("Week")) { //if they enter week
    for (double temp : dailyTemp) { //loops through the array, and for each array entry it prints out the day and temp.
      System.out.println("For " + dayOfWeek[counter] + " the temperature is " + temp+ " degrees.");
      totalTemp = totalTemp + temp; // while looping it begins to add temperaturs to a temp total
      counter++;
    }
    tempAvg = totalTemp/(dayOfWeek.length); // getting the average
    tempAvg = Math.round(tempAvg * 100) / 100; //quick but imprecise rounding
    System.out.println("The average temperature for the week is " + tempAvg + " degrees.");
    } else {
        int enteredDay = Arrays.asList(dayOfWeek).indexOf(dayToLookUp);
        if (enteredDay >= 0) {
          System.out.println("For " + dayOfWeek[enteredDay] + " the temperature is " + dailyTemp[enteredDay] + " degrees.");
        } else {
            System.out.print("Not a valid day or Temperature not found."); //if they enter anything other than a day of the week or incorrect spelling, returns this
        }
  }
}
}