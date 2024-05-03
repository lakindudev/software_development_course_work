import java.util.InputMismatchException;
import java.util.Scanner;

public class w2051869_PlaneManagement {

  //make the all seats
  private static int[][] seats = 
    {
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},       //row 1(A)-14 seats
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},            //row 2(B)-12 seats
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},            //row 3(C)-12 seats
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},       //row 4(D)-14 seats
    };
  //create array for store the tickets
  public static Ticket[][] ticket = new Ticket[4][14];

  public static void main(String[] args)
  {
    System.out.println("Welcome to the Plane Management application");

    //
    for(int i=0; i<seats.length; i++)
    {
      for(int j=0; j < seats[i].length ; j++){
        seats[i][j] = 0;
      }
    }
    // calling the menu method
      menu();
  }

  //show the seating plan 
  private static void seatingplan(int[][] seats) {
    System.out.println("seating plan: ");
      for(int i=0; i<seats.length; i++) {
        for(int j=0; j<seats[i].length; j++) {
          if(seats[i][j] == 0) {
            System.out.print("o ");     //show available seats
          } else {
            System.out.print("1 ");    //show sold seats
          }
        }
        System.out.println();    //move to next line
      }
  }

    //making menu
  private static void menu()
  {
    Scanner scanner = new Scanner(System.in);
    int option = -1;

    while (option != 0)
    {
      System.out.println("***********************************************");
      System.out.println("*                MENU OPTIONS                 *");
      System.out.println("***********************************************");
      System.out.println();
      System.out.println("    1) Buy a seat                               ");
      System.out.println("    2) Cancel a seat                           ");
      System.out.println("    3) Find first available seat                ");
      System.out.println("    4) Show seating plan                       ");
      System.out.println("    5) Print tickets information and total sales");
      System.out.println("    6) Search ticket                           ");
      System.out.println("    0) Quit                                     ");
      System.out.println("***********************************************");
      System.out.println();

      System.out.println("please select an option: ");     //ask user to give him choice

      try {
        option = scanner.nextInt();
      }
      catch (InputMismatchException e){
        System.out.println("invalid data type.please try again.");         //if user input wrong data type it can catch by try catch block
        scanner.next();   //consume the invalid input
        continue;
      }
      switch (option)
      {
        case 1:
          buy_seat();        //calling but_seat method
          break;

        case 2:
          cancel_seat();     //calling cancel_seat method
          break;

        case 3:
          find_first_available();       //calling find_first_available method
          break;

         case 4:
           show_seating_plan();           //calling show_seating method
           break;

         case 5:
           print_tickets_info();          //calling print_ticket_info method
           break;

         case 6:
           search_ticket();              //calling search_ticket method
            break;
         case 0:                    // quite the program
           System.out.println("good bye!");
            break;
        default:                               //if enter a wrong input
          System.out.println("invalid value");
          continue;
      }
    }
  }

  private static void buy_seat(){                          //creat buy_seat method{
    Scanner scanner = new Scanner(System.in);            //make the Scanner object

    boolean validinput = false;
    while(!validinput) {
      //ask user enter row letter
      System.out.println("please enter the row letter: ");
      String seat_row = scanner.nextLine().toUpperCase();

      //identify the seat_row as a integer
      int row_num = -1;
      switch (seat_row) {
        case "A":
          row_num = 0;              //A - 0
          break;

        case "B":
          row_num = 1;              //B - 1
          break;

        case "C":
          row_num = 2;             //C - 2
          break;

        case "D":
          row_num = 3;              //D - 4
          break;

        default:
          System.out.println("invalid row letter");    //identify the user give a wrong input
          continue;
      }
      //references[https://www.w3schools.com/java/java_try_catch.asp]
      int seat_number;
        while (true) {
          System.out.println("please enter the seat number: ");          //ask user to enter seat number
          try {
            seat_number = scanner.nextInt();
          }catch (InputMismatchException e){
            System.out.println("invalid data type.please enter again");  //check user give a right data type
            scanner.next();
            continue;
          }
          //check the seat number
          if (seat_number < 1 || seat_number > 14) {
            System.out.println("invalid seat number.");       //check the seat number is between 1 and 14
            continue;
          }
          if ((seat_row.equals("B") || seat_row.equals("C")) && seat_number > 12) {     //B and c rows has only 12 seats
            System.out.println("invalid seat number.");
            continue;
          }
          if (seats[row_num][seat_number - 1] == 1) {                                             // check the seat is available
            System.out.println("Seat" + " " +seat_row + seat_number + "has been sold.try again");
            return;     // return keyword use for get out of the loop
          } else {
            break;                //there is no problem then break use for stop the loop
          }
        }
        seats[row_num][seat_number - 1] = 1;
        System.out.println("seat"+" "+ seat_row + seat_number + " purchased successfully.");  //sell the seat to the user

         // ask the user to enter his information
        System.out.println("enter your name: ");
        String name = scanner.next();                    //user name

        System.out.println("enter your surname: ");
        String surname = scanner.next();                //user surname

        String email;
        while(true) {
          System.out.println("enter your email: ");
           email = scanner.next();                       //user email
          if(email.contains("@") || email.contains(".com")){
            break;
          }
          //reference[https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#contains-java.lang.CharSequence-]
          else{
            System.out.println("invalid email");
          }
        }

        Person person = new Person(name, surname, email);        //create a new Person class object called person

        //calculate the price
        int price = ticket_price_calculator(row_num, seat_number);

        //add details to ticket array
         ticket[row_num][seat_number -1] = new Ticket(seat_row,seat_number,price,person);
         ticket[row_num][seat_number -1].print_ticket();

         ticket[row_num][seat_number -1].save();    // save the ticket in the file
         validinput =true;
    }
  }

  //create cancel_seat method
  private static void cancel_seat() {
    Scanner scanner = new Scanner(System.in);
    boolean validinput = false;

    while(!validinput) {
      System.out.println("please enter the row letter: ");      //ask user enter row letter to cancel
      String seat_row = scanner.nextLine().toUpperCase();     //if user give a lower letter it conver to upper case letter

      int row_num = -1;
      switch (seat_row) {                   //identify the seat_row as a integer
        case "A":
          row_num = 0;
          break;

        case "B":
          row_num = 1;
          break;

        case "C":
          row_num = 2;
          break;

        case "D":
          row_num = 3;
          break;

        default:
          System.out.println("invalid row letter");
          continue;
      }
      int seat_number;
      while(true) {
                                     //ask user to enter seat number to cancel
        System.out.println("please enter the seat number: ");
        seat_number = scanner.nextInt();

        //check the seat number
        if (seat_number < 1 || seat_number > 14) {
          System.out.println("invalid seat number");
          continue;
        }
        if ((seat_row.equals("B") || seat_row.equals("C")) && seat_number > 12) {
          System.out.println("invalid seat number");
          continue;
        } else {
          break;
        }
      }
        if (seats[row_num][seat_number - 1] == 0) {
          System.out.println("this seat is already available.try again");
          return;        //return key word use for get out of the cancel_seat method
        }

      if (seats[row_num][seat_number - 1] == 1) {
        seats[row_num][seat_number - 1] = 0;
        System.out.println("successfully cancel the seat");     //user succefully cancel the seat

        ticket[row_num][seat_number -1] =null;                // cancel seat in the ticket array
      }
      validinput =true;
    }
  }

  //excute the find first available seat
   static void find_first_available() {
    for (int i=0; i < seats.length; i++ ){
      for (int j=0; j < seats[i].length; j++){
        if (seats[i][j] == 0 ){
          System.out.println("the first available is: "+ (char)('A' + i) + (j + 1)  );
          return;
        }
      }
    }
  }

  static void show_seating_plan(){          //create method called show_seating_plan
    for (int i=0; i<seats.length; i++){
      for (int j=0; j<seats[i].length; j++){
        if (seats[i][j] ==0){
          System.out.print("O");             //if seat is the available then it show by 'O'
        } else{
          System.out.print("X");             //if seat is booked then it show by '1'
        }
      }
      System.out.println();          // go to next line
    }
  }

  //creat a method for calculate the ticket
  static int ticket_price_calculator(int row_num , int seat_num ){
      int ticket_price = 0;

    if(seat_num<6){
      ticket_price = 200;                   //first five columns seats price is €200
    }
    else if (seat_num>6 & seat_num<=9){
      ticket_price = 150;                   //seat number 6 to 9 seats price is €150
    }
    else{
      ticket_price =180;                   //seat number 9 to 12 or 14 seats price is €180
    }
    //System.out.println(ticket_price);
    return ticket_price;
  }
   static void  print_tickets_info(){                //create method called print_tickets_info

    double total_amount = 0;                      //assign total_amount is 0 in the beginning
    for(int i = 0; i < ticket.length; i++) {
      for (int j = 0; j<ticket[i].length; j++) {
        if (ticket[i][j] != null) {                  //remove from array
          ticket[i][j].print_ticket();
          total_amount += ticket[i][j].getPrice();
        }
      }
    }
    System.out.println("Total amount :$" + total_amount);    //print the total amount
  }

  //creat a method called search_ticket
  static  void search_ticket()
  {
    Scanner scanner = new Scanner(System.in);
    boolean validinput = false;

    while(!validinput)
    {
      //ask user enter row letter
      System.out.println("please enter the row letter: ");
      String seat_row = scanner.nextLine().toUpperCase();
      
      //identify the seat_row as a integer
      int row_num = -1;

      switch (seat_row) {
        case "A":
          row_num = 0;
          break;

        case "B":
          row_num = 1;
          break;

        case "C":
          row_num = 2;
          break;

        case "D":
          row_num = 3;
          break;

        default:
          System.out.println("invalid row letter");
          continue;
      }
      int seat_number;
      while (true) {
        //ask user to enter seat number
        System.out.println("please enter the seat number: ");
        try {
          seat_number = scanner.nextInt();
        } catch (InputMismatchException e) {
          System.out.println("invalid data type.please ente again");
          scanner.next();
          continue;
        }

        //check the seat number
        if (seat_number < 1 || seat_number > 14) {
          System.out.println("invalid seat number");
          continue;
        }
        if ((seat_row.equals("B") || seat_row.equals("C")) && seat_number > 12) {
          System.out.println("invalid seat number");
          continue;
        }
        if(seats[row_num][seat_number -1] == 0){
          System.out.println("This seat is available");
        }
        if (seats[row_num][seat_number - 1] == 1) {
          ticket[row_num][seat_number -1].print_ticket();
          break;
        } else {
          break;
        }
      }
      validinput = true;
    }
  }
}



 