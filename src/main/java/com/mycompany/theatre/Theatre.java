/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.theatre;

import java.io.*;
import java.util.*;


public class Theatre {
    // Assigns a fixed value to the variables, number of seats isn't going to change.
    //Reference:
    //https://westminster.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=0689b2bb-d76a-4450-9a17-af9a00956ad6
    
    static int[][] seats = new int[3][];
    static final int row1Seats = 12;
    static final int row2Seats = 16;
    static final int row3Seats = 20;
    
    
    //Creating an ArrayList for tickets as the number of tickets is always changing
    //Reference: https://westminster.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=d890d1ca-ef15-4b1d-99db-afa100947e08
    private static ArrayList<Ticket> tickets = new ArrayList<>();
    
    public static void main(String[] args) {
        // Display welcome message
        System.out.println("Welcome to the New Theatre");
        
        
        seats[0] = new int[row1Seats];
        seats[1] = new int[row2Seats];
        seats[2] = new int[row3Seats];
        
        // Sets all seats to 0 (free)
        for (int i = 0; i < row1Seats; i++) {
            seats[0][i] = 0;
        }
        for (int i = 0; i < row2Seats; i++) {
            seats[1][i] = 0;
        }
        for (int i = 0; i < row3Seats; i++) {
            seats[2][i] = 0;
        }
        
        //Menu
        Scanner input = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("------------------------");
            System.out.println("Please choose an option:");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("0) Quit");
            System.out.println("------------------------");
            System.out.print("Enter option: ");
            option = input.nextInt();
            
            //Takes user input and will execute based on input calling the function and prevents using many if else statements
            //Reference:
            //https://westminster.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=0689b2bb-d76a-4450-9a17-af9a00956ad6
            
            switch (option) {
                case 0:
                    return;
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    print_seating_area();
                    break;
                case 3:
                    cancel_ticket();
                    break;
                case 4:
                    show_available();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }

    public static void buy_ticket() {
        Scanner input = new Scanner(System.in);
        int row = -1;
        
        //While loop until set condtion is met
        while (row < 0 || row > 2) {
            System.out.print("Row: ");
            if (input.hasNextInt()) {
                row = input.nextInt() - 1;
                if (row < 0 || row > 2) {
                    System.out.println("Invalid row, please enter 1-3.");
                    System.out.println("");
                }
            } else {
                //Returns back to start
                input.next();
            }
           
        } 
        
        int seat = -1;
        
        //While loop until set condtion is met
        while (seat < 0 || seat >= seats[row].length){
            System.out.print("Seat: ");
            if (input.hasNextInt()) {
                seat = input.nextInt() - 1;
                if (seat < 0 || seat >= seats[row].length) {
                    System.out.println("Invalid seat, please enter (1-" + seats[row].length + ")");
                    System.out.println("");
                }
            } else {
                input.next();
            }
        }
        
        //Asks input from the user
        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Surname: ");
        String surname = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Price: ");
        double price = input.nextDouble();

        input.nextLine();

        //Class, instance and creating a new instance of the class
        Person person = new Person(name, surname, email);
        Ticket ticket = new Ticket(row, seat, price, person);

        tickets.add(ticket);
        
        seats[row][seat] = 1;
        System.out.println("");
        System.out.println("Ticket bought successfully.");
        
        //References:
        //https://westminster.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=0689b2bb-d76a-4450-9a17-af9a00956ad6
        //https://westminster.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=d890d1ca-ef15-4b1d-99db-afa100947e08
        
        
    }

    public static void print_seating_area() {
        System.out.println("");
        System.out.println("***********");
        System.out.println("*  STAGE  *");
        System.out.println("***********");
        System.out.println("");
        //Nested for loop
        for (int row = 0; row < seats.length; row++) {
            for (int seat = 0; seat < seats[row].length; seat++) {
                if (seats[row][seat] == 1) {
                    System.out.print("X");
                }else{
                    System.out.print("O");
                }
            }
            System.out.println();
        }
        //Refernces:
        //https://westminster.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=d890d1ca-ef15-4b1d-99db-afa100947e08
        
    }

    public static void cancel_ticket() {
        Scanner input = new Scanner(System.in);
        int row = -1;
        
        //While loop until correct row
        while (row < 0 || row > 2) {
            System.out.print("Row: ");
            if (input.hasNextInt()) {
                row = input.nextInt() - 1;
                if (row < 0 || row > 2) {
                    System.out.println("Invalid row, please enter 1-3.");
                    System.out.println("");
                }
            } else {
                input.next();
            }
        } 
        
        int seat = -1;
        
        //While loop until correct seat
        while (seat < 0 || seat >= seats[row].length){
            System.out.print("Seat: ");
            if (input.hasNextInt()) {
                seat = input.nextInt() - 1;
                if (seat < 0 || seat >= seats[row].length) {
                    System.out.println("Invalid seat, please enter (1-" + seats[row].length + ")");
                    System.out.println("");
                }
            } else {
                input.next();
            }
        }
        
        if (seats[row][seat] == 0) {
            System.out.println("Error. The seat has not been sold");
        return;
        }
        seats[row][seat] = 0;
        System.out.println("");
        System.out.println("Ticket cancelled successfully.");
}

    public static void show_available() {
        //Prints out in an array
        for (int row = 0; row < seats.length; row++) {
            System.out.print("Seats available in row " + (row + 1) + ": ");
            for (int seat = 0; seat < seats[row].length; seat++) {
                System.out.print(seats[row][seat] == 0 ? (seat + 1) + " " : "");
            }
            System.out.println();
        }
}
    
    //Reference:
    //https://westminster.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=573ab356-2ca4-479c-ac34-afaf0093bf9e
    //https://stackoverflow.com/questions/28570967/store-java-arrays-to-file-for-read-and-write
    //https://java2blog.com/write-array-to-file-java/
    
    public static void save() {
        try {
            //FileOutputStream writes the data to the text file
            FileOutputStream file = new FileOutputStream("seats.txt");
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(seats);
            object.close();
            System.out.println("Success: Saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving seats to file: " + e.getMessage());
        }
}

    public static void load() {
        try {
            FileInputStream file = new FileInputStream("seats.txt");
            ObjectInputStream object = new ObjectInputStream(file);
            seats = (int[][]) object.readObject();
            object.close();
        System.out.println("Success: loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading seats from file: " + e.getMessage());
        }
}

        
    }

