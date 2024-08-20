package ezechukwu.ui;

import ezechukwu.controllers.HotelController;
import ezechukwu.model.Customer;
import ezechukwu.model.IRoom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private MainMenu(){}

    private static void createReservation(Scanner scanner, DateFormat dateFormat){
        System.out.println("Enter your Checkin Date (dd/mm/yyyy):");
        Date checkin = null;
        try {
            checkin = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enter your Checkout Date(dd/mm/yyyy):");
        Date checkout = null;
        try {
            checkout = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Collection<IRoom> rooms = HotelController.getInstance().findARoom(checkin,checkout);
        System.out.println("There are "+rooms.size()+" available rooms");
        if(rooms.size() > 0){
            boolean bookRoomKeepRunning = true;
            while(bookRoomKeepRunning){
                for(IRoom room : rooms){
                    System.out.println("Room Number:"+ room.getRoomNumber()+" Room Price "+ room.getRoomPrice()+" Room Type:"+ room.getRoomType()
                    );
                }

                System.out.println("Would you like to book a room? y/n");
                String yesNo = scanner.nextLine();
                switch (yesNo){
                    case "y":
                        System.out.println("Select room number");
                        String roomNumber = scanner.nextLine();
                        IRoom registeredRoom = HotelController.getInstance().getRoom(roomNumber);
                        System.out.println(registeredRoom + " "+ roomNumber);
                        System.out.println("Do you have an account? y/n");
                        String accountYesNo = scanner.nextLine();
                        switch (accountYesNo){
                            case "y":
                                System.out.println("Enter your email");
                                String accountEmail = scanner.nextLine();
                                Customer registeredCustomer = HotelController.getInstance().getCustomer(accountEmail);
                                System.out.println(HotelController.getInstance().bookARoom(registeredCustomer,registeredRoom,checkin, checkout));
                                break;
                                case "n":
                                Customer newCustomer = MainMenu.createCustomer(scanner);
                                System.out.println(HotelController.getInstance().bookARoom(newCustomer,registeredRoom,checkin, checkout));
                                break;
                            default:
                                System.out.println("Invalid input. \n Enter y or n");
                                break;
                        }
                        bookRoomKeepRunning = false;
                        break;
                    case "n":
                        bookRoomKeepRunning = false;
                        break;
                    default:
                        System.out.println("Invalid input. \n Enter y or n");
                        break;

                }
            }
        }
    }
    private static Customer createCustomer(Scanner scanner){
        System.out.println("Enter your firstname");
        String firstName = scanner.nextLine();
        System.out.println("Enter your lastName");
        String lastName = scanner.nextLine();
        System.out.println("Enter your email");
        String customerEmail = scanner.nextLine();
        HotelController.getInstance().createACustomer(customerEmail,firstName,lastName);
        return HotelController.getInstance().getCustomer(customerEmail);
    }
    public static void launchMainMenu(){
      boolean keepRunning = true;
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      try(Scanner scanner = new Scanner(System.in)) {
          while(keepRunning){
              try {
                  System.out.println("Main Menu");
                  System.out.println("-------------------------------------------");
                  System.out.println("1. Find and reserve a room");
                  System.out.println("2. See my reservations");
                  System.out.println("3. Create an account");
                  System.out.println("4. Admin");
                  System.out.println("5. Exit");
                  System.out.println("-------------------------------------------");
                  int selection = Integer.parseInt(scanner.nextLine());
                  switch(selection){
                      case 1:
                          MainMenu.createReservation(scanner, dateFormat);
                          break;
                      case 2:
                          System.out.println("Enter your email");
                          String email = scanner.nextLine();
                          System.out.println("List of customer reservations: "+ HotelController.getInstance().getCustomersReservations(email));
                          break;
                      case 3:
                          System.out.println("Customer account created: " + MainMenu.createCustomer(scanner));
                          break;
                      case 4:
                          keepRunning = false;
                          AdminMenu.launchAdminMenu();
                      case 5:
                          keepRunning = false;
                          break;
                      default:
                          System.out.println("Invalid input. \n Enter numbers between 1 - 5");
                          break;
                  }
              } catch (Exception ex) {
                  System.out.println(ex.getLocalizedMessage());
              }
          }

      }
  }
}
