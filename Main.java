import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {


        HashMap<String, ArrayList<String>> contactBook = new HashMap<>();
        menu(contactBook);

    }
    public static void displayContacts(HashMap<String, ArrayList<String>> contacts){
        System.out.println("\nContacts:");
        for(Map.Entry<String, ArrayList<String>> entry: contacts.entrySet()){
            display(entry.getKey(), contacts);
            System.out.println();
        }
    }
    public static void display(String name, HashMap<String, ArrayList<String>> contacts){
        if (!contacts.containsKey(name)) {
            System.out.println("Sorry, contact '" + name + "' not found");
        } else{
            System.out.println("\t" + name + ":");
            for(String el: contacts.get(name)){
                System.out.print("\t\t" + el);
            }
        }
    }

    public static void displayContactInfo(HashMap<String, ArrayList<String>> contacts, Scanner scan){
        System.out.println("Enter name:");
        String name = scan.nextLine();
        display(name, contacts);
    }
    public static void addNumber(HashMap<String, ArrayList<String>> contacts, Scanner scan){
        System.out.println("\tAdd number to a contact   -> '1'");
        System.out.println("\tCreate a new contact      -> '2'");
        int input = Integer.valueOf(scan.nextLine());
        if(input == 2){
            addContact(contacts, scan);
        }else if(input == 1) {
            ArrayList<String> numbers = findNumbers(contacts, scan);
            if(numbers != null){
                System.out.println("Enter number:");
                String number = scan.nextLine();
                numbers.add(number);
                System.out.println("Number was added");
            }
        }else{
            System.err.println("Invalid input.");
        }
    }

    public static ArrayList<String> findNumbers(HashMap<String, ArrayList<String>> contacts, Scanner scan){
        System.out.println("Enter name:");
        String name = scan.nextLine();
        if (!contacts.containsKey(name)) {
            System.out.println("Sorry, contact '" + name + "' not found");
            return null;
        } else {
            return contacts.get(name);
        }
    }


    public static void addContact(HashMap<String, ArrayList<String>> contacts, Scanner scan){
        System.out.println("Enter name:");
        String name = scan.nextLine();
        ArrayList<String> numbers = new ArrayList<>();
        if(contacts.putIfAbsent(name, numbers) == null){
            System.out.println("Enter number:");
            String number = scan.nextLine();
            numbers.add(number);
            System.out.println("\nContact '" + name + "' was added");
        }else{
            System.err.println("\nContact '" + name + "' is already exist!");
        }
    }

    public static void menu(HashMap<String, ArrayList<String>> contacts) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            displayMenu();
            switch (Integer.valueOf(scan.nextLine())) {
                case 1:
                    addContact(contacts, scan);
                    break;
                case 2:
                    addNumber(contacts, scan);
                    break;
                case 3:
                    displayContactInfo(contacts, scan);
                    break;
                case 4:
                    displayContacts(contacts);
                    break;
                case 5:
                    scan.close();
                    System.exit(0);
                default:
                    System.err.println("\n Invalid input");
                    break;
            }

        }
    }
    public static void displayMenu(){

            System.out.println("\nMenu: ");
            System.out.println("* Add a contact:            -> '1'");
            System.out.println("* Add a number:             -> '2'");
            System.out.println("* Display contact info:     -> '3'");
            System.out.println("* Display list of contacts: -> '4'");
            System.out.println("* Exit:                     -> '5'");
    }

}
