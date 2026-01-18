public class Person {
private String first;
private String last;
private String address;
private String city;
private String zip;
    
    public Person(String first, String last, String address, String city, String zip) {
        this.first = first;
        this.last = last;
        this.address = address;
        this.city = city;
        this.zip = zip;
    }
    
    public void printInfo() { System.out.println(first); System.out.println(last); System.out.println(address); System.out.println(city); System.out.println(zip); } 

    public static void main(String[] args) { Person person = new Person("John", "Doe", "123 Main St", "Phoenix", "85001"); person.printInfo(); }
}
