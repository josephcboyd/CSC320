import java.util.Scanner;


public class MyClass {
  public static void main(String args[]) {
    double enteredIncome;
    Scanner scnr = new Scanner(System.in);
    double taxRate;
    
    enteredIncome = scnr.nextDouble();
    
    if(enteredIncome < 500) {
        taxRate = 0.10;
    } 
    else{
        if(enteredIncome >= 1500) {
            if(enteredIncome >= 2500){
                taxRate = 0.30;
            } 
            else {
                taxRate = 0.20;
            }
        } 
        else {
            taxRate = 0.15;
        }
    }
   
    System.out.println("Your income was: $" + enteredIncome + ". And the tax rate is: " + 
    (taxRate * 100) + "%. Which means you owe: $" + (enteredIncome * taxRate) + " in taxes.");

  }
}