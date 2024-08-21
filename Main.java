import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        HashMap<String, String> phoneBook = new HashMap<>();
        while (true) {
            showMenu();
            switch (s.nextLine()) {
                case "1":
                    addContact(s, phoneBook);
                    break;
                case "2":
                    findContactByName(s, phoneBook);
                    break;
                case "3":
                    displayPhoneBook(phoneBook);
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private static void showMenu() {
        System.out.println("===Main menu===");
        System.out.println("1 - Add contact");
        System.out.println("2 - Look up phone number");
        System.out.println("3 - Display phone book");
        System.out.println("4 - Exit");
    }

    private static void addContact(Scanner s, HashMap<String, String> phoneBook) {
        System.out.println("Enter phone number in format +55-555-55555555");
        String phoneNumber = s.nextLine();
        if (!validatePhoneNumber(phoneNumber)) {
            System.err.println("Wrong format");
            return;
        }
        System.out.println("Enter name:");
        String name = s.nextLine();
        if (phoneBook.putIfAbsent(phoneNumber, name) != null) {
            System.err.println("This phone number was already added");
        }
    }

    private static void findContactByName(Scanner s, HashMap<String, String> phoneBook) {
        if (phoneBook.isEmpty()) {
            System.err.println("Phonebook is empty");
            return;
        }
        System.out.println("Enter name");
        String name = s.nextLine();
        for (var contact : phoneBook.entrySet()) {
            if (contact.getValue().equals(name)) {
                System.out.println("Found contact:");
                System.out.println(name + " " + contact.getKey());
                return;
            }
        }
        System.err.println("You don't have contact with name " + name);
    }

    private static void displayPhoneBook(HashMap<String, String> phoneBook) {
        if (phoneBook.isEmpty()) {
            System.err.println("Phonebook is empty");
            return;
        }
        System.out.println("All contacts:");
        for (var contact : phoneBook.entrySet()) {
            System.out.println("\t" + contact.getKey() + " " + contact.getValue());
        }
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+\\d{1,2}-\\d{3}-\\d{8}");
    }
}