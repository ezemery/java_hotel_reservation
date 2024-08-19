package ezechukwu.ui;

import java.util.Scanner;

public class AdminMenu {
    private AdminMenu(){}
    public static void launchAdminMenu(){
      boolean adminKeepRunning = true;
      try(Scanner scanner = new Scanner(System.in)) {
          while (adminKeepRunning) {
              try {
                  System.out.println("Admin Menu");
                  System.out.println("-------------------------------------------");
                  System.out.println("1. See all customers");
                  System.out.println("2. See all rooms");
                  System.out.println("3. See all reservations");
                  System.out.println("4. Add a room");
                  System.out.println("5. Back to main menu");
                  System.out.println("-------------------------------------------");
                  int selection = Integer.parseInt(scanner.nextLine());
                  switch (selection) {
                      case 1:
                          System.out.println(1);
                          break;
                      case 2:
                          System.out.println(2);
                          break;
                      case 3:
                          System.out.println(3);
                          break;
                      case 4:
                          System.out.println(4);
                      case 5:
                          adminKeepRunning = false;
                          MainMenu.launchMainMenu();
                          break;
                      default:
                          System.out.println("Invalid input. \n Enter numbers between 1 - 5");
                  }

              } catch (Exception ex) {
                  System.out.println("Invalid input. \n Enter numbers between 1 - 5");
              }
          }
      }
  }
}
