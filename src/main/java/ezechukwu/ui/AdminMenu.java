package ezechukwu.ui;

import ezechukwu.controllers.AdminController;
import ezechukwu.controllers.HotelController;
import ezechukwu.model.*;
import ezechukwu.service.CustomerService;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AdminMenu {
    private AdminMenu(){}
    public static void launchAdminMenu(){
      boolean adminKeepRunning = true;
      Random random = new Random();
      try(Scanner scanner = new Scanner(System.in)) {
          while (adminKeepRunning) {
              try {
                  System.out.println("Admin Menu");
                  System.out.println("-------------------------------------------");
                  System.out.println("1. See all customers");
                  System.out.println("2. See all rooms");
                  System.out.println("3. See all reservations");
                  System.out.println("4. Add a room");
                  System.out.println("5. Add test data");
                  System.out.println("6. Back to main menu");
                  System.out.println("-------------------------------------------");
                  int selection = Integer.parseInt(scanner.nextLine());
                  switch (selection) {
                      case 1:
                          System.out.println("List of Customers: "+ AdminController.getInstance().getAllCustomers());
                          break;
                      case 2:
                          System.out.println("List of Rooms: "+ AdminController.getInstance().getAllRooms());
                          break;
                      case 3:
                          System.out.println("List of Reservations: "+ AdminController.getInstance().displayAllReservations());
                          break;
                      case 4:
                          System.out.println("Enter room price");
                          Double price = Double.parseDouble(scanner.nextLine());
                          System.out.println("Select room type:");
                          System.out.println("1. DOUBLE");
                          System.out.println("2. SINGLE");
                          int roomType = Integer.parseInt(scanner.nextLine());
                          switch (roomType){
                              case 1:
                                  AdminController.getInstance().addARoom(new Room(String.valueOf(random.nextInt(100) + 1), price, RoomType.DOUBLE));
                                  break;
                              case 2:
                                  AdminController.getInstance().addARoom(new Room(String.valueOf(random.nextInt(100) + 1), price, RoomType.SINGLE));
                                  break;
                              default:
                                  System.out.println("Invalid input. \n Enter numbers between 1 - 2");
                                  break;
                          }
                          break;
                      case 5:
                          List<Customer> customers =  List.of(new Customer("Eze", "Emery", "eze@gmail.com"), new Customer("Michael", "Powers", "michael.p@gmail.com"));
                          List<IRoom> rooms = List.of(new Room(String.valueOf(random.nextInt(100) + 1), 120.0, RoomType.DOUBLE), new Room(String.valueOf(random.nextInt(100) + 1), 150.0, RoomType.DOUBLE), new Room(String.valueOf(random.nextInt(100) + 1), 100.0, RoomType.DOUBLE), new Room(String.valueOf(random.nextInt(100) + 1), 60.0, RoomType.SINGLE), new FreeRoom(String.valueOf(random.nextInt(100) + 1), RoomType.SINGLE));
                          AdminController.getInstance().addRooms(rooms);
                          for(Customer customer: customers){
                              CustomerService.getInstance().addCustomer(customer.getEmail(), customer.getFirstName(),customer.getLastName());
                          }
                          break;
                      case 6:
                          adminKeepRunning = false;
                          MainMenu.launchMainMenu();
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
