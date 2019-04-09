package assignment;

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
    }
}

class NamePrinter {
    /**
     * Prints the names Scott Jacobsen, Jorge Gonzalez, Michael Loney, and Humphrey Akuba
     * separated by semicolons
     * 
     * @author Team 08
     * @version 1.0
     * 
     */
    public void printNames() {
        String separator = ";";

        String[] names = {
                "Scott Jacobsen",
                "Jorge Gonzalez",
                "Michael Loney",
                "Humphrey Akuba",
                "Name 5"};

        System.out.println(String.join(separator, names));
    }
}
