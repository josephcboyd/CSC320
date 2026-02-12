import java.util.Scanner;
import java.util.ArrayList; 


public class Module5 {
  public static void main(String[] args) {

    ArrayList<String[]> days = new ArrayList<String[]>(7); //sets new Arraylist of arrays with a cap of 7

    Scanner scnr = new Scanner(System.in); 

    String[] monday = {"Monday", "84.2"}; //initializing each day as an entry in an array
    String[] tuesday = {"Tuesday", "83.4"};
    String[] wednesday = {"Wednesday", "85"};
    String[] thursday = {"Thursday","82.4"};
    String[] friday = {"Friday","83.4"};
    String[] saturday = {"Saturday","84.5"};
    String[] sunday = {"Sunday", "81.7"};
    
    String dayToLookUp = " ";

    days.add(monday);  //initially adding each day to the main arraylist
    days.add(tuesday);
    days.add(wednesday);
    days.add(thursday);
    days.add(friday);
    days.add(saturday);
    days.add(sunday);

    int i = days.size() - 1; //declaring variables
    double totalTemp = 0.0;
    double tempAvg;

    dayToLookUp = scnr.nextLine().toLowerCase(); //sanitizing input to lowercase
    dayToLookUp = dayToLookUp.substring(0, 1).toUpperCase() + dayToLookUp.substring(1); //matching case of the days in the array list

    if (dayToLookUp.equals("Week")) { //if they enter week
      for (String[] day : days) { //loops through the arraylist, and for each array entry it prints out the day and temp.
        System.out.println("For " + day[0] + " the temperature is " + day[1]+ " degrees.");
        totalTemp = totalTemp + Double.parseDouble(day[1]); // while looping it begins to add temperaturs to a temp total
      }
      tempAvg = totalTemp/(days.size()); // getting the average
      tempAvg = Math.round(tempAvg * 100) / 100; //quick but imprecise rounding
      System.out.println("The average temperature for the week is " + tempAvg + " degrees.");
    }
      else{
        while (i >= 0) { // loops starting at the length/size of the arraylist and works backwards
          if (dayToLookUp.equals(days.get(i)[0])) { // checks the entered text against the arraylist 
            System.out.print("The day of the week is " + days.get(i)[0] + " and the temperature is " + days.get(i)[1] + " degrees."); //when a match is found it prints the day and the temp
            i = -1; // sets i to negative to stop loop
          }
          else {
            if (i == 0 ) {
              System.out.print("Not a valid day or Temperature not found."); //if they enter anything other than a day of the week or incorrect spelling, returns this
            }    
            i -= 1; // keeps loop progressing through the array list. final run is when i = 0, so the last run stops the loop
          }
        }
      }
  }
}