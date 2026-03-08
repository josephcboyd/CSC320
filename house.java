import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.File;
import java.io.FileNotFoundException;


public class house { // primary house class

    public Integer square_feet;
    public String address;
    public String city;
    public String state;
    public Integer zip_code;
    public String model_name;
    public String sale_status;

    public house(int square_feet, String address, String city, String state, int zip_code, String model_name, String sale_status) {
            this.square_feet = square_feet;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zip_code = zip_code;
            this.model_name = model_name;
            this.sale_status = sale_status;
    }
}

class inventory implements Serializable{
    
    public List<house> homeInventory = new ArrayList<>();  //arraylist created to store each house for viewing, editing, etc
    public static final String FILENAME = "houseList.txt"; //setting filename here to make it easily changeable or even user enterable is needed

    public void save() { //save method to save the inventory list when a house is added, updated, or deleted. currently saving to C:\Users\'currentuser'
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) { //writes house information to file based on above filename
            for (house h : homeInventory) {
                writer.println(h.square_feet + "," + h.address + "," + h.city + "," + 
                            h.state + "," + h.zip_code + "," + h.model_name + "," + h.sale_status); //writes it very plainly to file for ease of access/viewing in a text editor
            }
            System.out.println("\nInventory saved successfully!");
        } catch (IOException e) {
            System.out.println("\nError saving inventory: " + e.getMessage());
        }
    }

    public void load() {
        homeInventory = new ArrayList<>();
        File file = new File(FILENAME);
        if (!file.exists()) {
            return;
        }
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) { //parses through the saved string, with each line designating a separate house. once it runs out of new lines, i.e. no more homes, it ends
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                house h = new house(
                    Integer.parseInt(parts[0]),  // square_feet
                    parts[1],                    // address
                    parts[2],                    // city
                    parts[3],                    // state
                    Integer.parseInt(parts[4]),  // zip_code
                    parts[5],                    // model_name
                    parts[6]                     // sale_status
                );
                homeInventory.add(h);
            }
            System.out.println("\nInventory loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("\nError loading inventory: " + e.getMessage());
        }
    }

    public void AddHome() { //method for adding a home. runs through each available attribute to save for the house
        Scanner scnr = new Scanner(System.in);
        
    try {
        System.out.println("\nEnter state name");
        String state = scnr.nextLine();

        System.out.println("Enter city name");
        String city = scnr.nextLine();

        System.out.println("Enter zip code");
        int zip_code = Integer.parseInt(scnr.nextLine());

        System.out.println("Enter home address");
        String address = scnr.nextLine();

        System.out.println("Enter house size in square feet");
        int square_feet = Integer.parseInt(scnr.nextLine());


        System.out.println("Enter home model name");
        String model_name = scnr.nextLine();


        String sale_status = "";
        boolean needsInput = true;

        while (needsInput) { //uses a while and switch to ensure that sale statues are a consistent format. thought about adding a filter to the view statement, and this would potentially allow for filtering out based on status a lot easier if the status convnetion is all the same
            System.out.println("Select sale status:");
            System.out.println("1. Available");
            System.out.println("2. Under Contract");
            System.out.println("3. Sold");
            System.out.print("Enter choice (1-3): ");
            
            int statusChoice = Integer.parseInt(scnr.nextLine());
            
            switch (statusChoice) {
                case 1:
                    sale_status = "AVAIL";
                    needsInput = false;
                    break;
                case 2:
                    sale_status = "CONTRACTED";
                    needsInput = false;
                    break;
                case 3:
                    sale_status = "SOLD";
                    needsInput = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
        
        house newHouse = new house(square_feet, address, city, state, zip_code, model_name, sale_status); //pulls all of the above info into a new house and then adds it to the arraylist
        homeInventory.add(newHouse);    

            System.out.println("Home added sucessfully!");
            save();

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers for zip and square feet."); //checks for non-int entries for zip and square feet
            System.out.println("Home was not added. Please try again.");
        } catch (Exception e) {
            System.out.println("Something happened. error - " + e.getMessage());
            System.out.println("Home was not added. Please try again.");
        }
    }

    public void ViewHomes() { //method for printing out the list of homes, both for viewing and for knowing which home to enter for the update, delete, and print methods
    int counter = 1;
    for (house h : homeInventory) { //loops through each house in the arraylist and prints info
        System.out.println("\nHouse #" + counter);
        System.out.println("Square Feet: " + h.square_feet);        
        System.out.println("Address: " + h.address + " " + h.city + ", " + h.state + " " + h.zip_code);        
        System.out.println("Model Name: " + h.model_name);        
        System.out.println("Status: " + h.sale_status);
        counter++;

    }
    System.out.println();
}
    public void UpdateHome() { //update home method
    Scanner scnr = new Scanner(System.in);
    boolean keepUpdating = true;
    ViewHomes();
    System.out.println("Which house would you like to update?"); //prompts user to enter
    int selection = Integer.parseInt(scnr.nextLine()) - 1; //minus 1, arraylist starts at 0
    house selected = homeInventory.get(selection);

    while (keepUpdating) {
        System.out.println("\n--- Selected Home Information ---"); //prints out the info of the selected house
        System.out.println("Current Info:");
        System.out.println("Square Feet: " + selected.square_feet);        
        System.out.println("Address: " + selected.address + " " + selected.city + ", " + selected.state + " " + selected.zip_code);        
        System.out.println("Model Name: " + selected.model_name);        
        System.out.println("Status: " + selected.sale_status);
        
        System.out.println("\nWhat would you like to update?"); //displays switch options for upcoming switch case. returns here once done with each action to allow for the updating of specific attributes without requiring each one to be 
        System.out.println("1. State");
        System.out.println("2. City");
        System.out.println("3. Zip Code");
        System.out.println("4. Address");
        System.out.println("5. Square Feet");
        System.out.println("6. Model Name");
        System.out.println("7. Sale Status");
        System.out.println("8. Done updating");
        System.out.print("Enter choice (1-8): ");
        System.out.println();

        int choice = scnr.nextInt();
        scnr.nextLine(); // clears buffer, ran into an issue where it would occasionally skip ability for user to enter text. Mostly a carryover from AddHome() where it was outright skipping state each time
        
        switch (choice) {
            case 1:
                System.out.print("Enter new state name: ");
                selected.state = scnr.nextLine();
                System.out.println("State updated!");
                break;
            case 2:
                System.out.print("Enter new city name: ");
                selected.city = scnr.nextLine();
                System.out.println("City updated!");
                break;
            case 3:
                System.out.print("Enter new zip code: ");
                selected.zip_code = scnr.nextInt();
                scnr.nextLine();
                System.out.println("Zip code updated!");
                break;
            case 4:
                System.out.print("Enter new address: ");
                selected.address = scnr.nextLine();
                System.out.println("Address updated!");
                break;
            case 5:
                System.out.print("Enter new square feet: ");
                selected.square_feet = scnr.nextInt();
                scnr.nextLine();
                System.out.println("Square feet updated!");
                break;
            case 6:
                System.out.print("Enter new model name: ");
                selected.model_name = scnr.nextLine();
                System.out.println("Model name updated!");
                break;
            case 7:
                boolean validInput = false;
                while (!validInput) {
                    System.out.println("\nSelect new sale status:");
                    System.out.println("1. Available");
                    System.out.println("2. Under Contract");
                    System.out.println("3. Sold");
                    System.out.print("Enter choice (1-3): ");
                    
                    int statusChoice = scnr.nextInt();
                    scnr.nextLine();
                    
                    switch (statusChoice) {
                        case 1:
                            selected.sale_status = "AVAIL";
                            validInput = true;
                            System.out.println("\nStatus updated to Available!");
                            break;
                        case 2:
                            selected.sale_status = "CONTRACTED";
                            validInput = true;
                            System.out.println("\nStatus updated to Under Contract!");
                            break;
                        case 3:
                            selected.sale_status = "SOLD";
                            validInput = true;
                            System.out.println("\nStatus updated to Sold!");
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please try again.");
                    }
                }
                break;
            case 8:
                keepUpdating = false;
                System.out.println("\nUpdate complete!");
                break;
            default:
                System.out.println("\nInvalid choice. Please try again.");
        }
    }
    save();
}
    public void RemoveHome() {
        ViewHomes();
        Scanner scnr = new Scanner(System.in);
        try {
            System.out.println("Which house would you like to remove?"); //same as update home, prompts them to enter.
        int selection = Integer.parseInt(scnr.nextLine()) - 1;
        house selected = homeInventory.get(selection);

        System.out.println("\n--- Selected Home Information ---"); //prints out the info of the selected house so they can verify it's the one they'd like to delete
        System.out.println("Current Info:");
        System.out.println("Square Feet: " + selected.square_feet);        
        System.out.println("Address: " + selected.address + " " + selected.city + ", " + selected.state + " " + selected.zip_code);        
        System.out.println("Model Name: " + selected.model_name);        
        System.out.println("Status: " + selected.sale_status);
        System.out.println("Are you sure you want to delete this home?");
        System.out.println("Please type 'Delete Home' to confirm:");

        if (scnr.nextLine().equals("Delete Home")) { //prompts them to enter a string to verify that they'd actually want to delete this house from the list.
            homeInventory.remove(selected);
            System.out.println("House deleted Successfully");
            ViewHomes();
            save();
        }
            else {
                System.out.println("Not confirmed, please try again");
            }
        }
        catch (Exception e) {
            System.out.println("Something happened. error - " + e.getMessage());
            System.out.println("Home was not renmoved. Please try again.");
        }
        
    }

    public void PrintHome() {
        Scanner scnr = new Scanner(System.in); //effectively the same as the save method, but instead just saves the information for a single house, vs. the bulk of the inventory.
        ViewHomes();
            System.out.println("Which house would you like to print?");
        int selection = Integer.parseInt(scnr.nextLine()) - 1;
        house h = homeInventory.get(selection);
       
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("singleHome.txt"))) { //saving just the one house info to a text file, same dir as the bulk inventory list
            writer.println(h.square_feet + "," + h.address + "," + h.city + "," + 
                        h.state + "," + h.zip_code + "," + h.model_name + "," + h.sale_status);
            System.out.println("House saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public static void main(String[] args) { //main for the root menu. prompts user on what they'd like to do. returns to list after each action is completed
        inventory instance = new inventory();
        instance.load();

        Scanner scnr = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            System.out.println("\nWhat would you like to do:");
            System.out.println("1. Add new home");
            System.out.println("2. View homes");
            System.out.println("3. Update home");
            System.out.println("4. Remove home");
            System.out.println("5. Print home");
            System.out.print("Enter choice (1-4): ");
            
            int statusChoice = scnr.nextInt();
            scnr.nextLine();
            
            switch (statusChoice) {
                case 1:
                    instance.AddHome();
                    break;
                case 2:
                    instance.ViewHomes();
                    break;
                case 3:
                    instance.UpdateHome();
                    break;
                case 4:
                    instance.RemoveHome();
                    break;
                case 5:
                    instance.PrintHome();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
    }
    }
}

