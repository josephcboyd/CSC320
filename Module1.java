public class Module1 { //declaring attributes for class
private String firstName;
private String lastName;
private String streetAddress;
private String city;
private String zipCode;
    
    public Person(String firstName, String lastName, String streetAddress, String city, String zipCode) { //construct for person class
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
    }
    
    public void printInfo() { System.out.println(firstName); System.out.println(lastName); System.out.println(streetAddress); System.out.println(city); System.out.println(zipCode); } //printing out info

    public static void main(String[] args) { Person person = new Person("John", "Doe", "123 Main St", "Phoenix", "85001"); person.printInfo(); } //creating a new person
}
