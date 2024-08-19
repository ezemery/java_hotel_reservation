package ezechukwu.ui;

import java.util.Scanner;

public class MainMenu {
    private MainMenu(){}
    public static void launchMainMenu(){
      boolean keepRunning = true;
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
                          System.out.println(1);
                          break;
                      case 2:
                          System.out.println(2);
                          break;
                      case 3:
                          System.out.println(3);
                          break;
                      case 4:
                          keepRunning = false;
                          AdminMenu.launchAdminMenu();
                      case 5:
                          keepRunning = false;
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
