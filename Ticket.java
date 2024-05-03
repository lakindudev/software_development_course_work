import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    //create the attributes
    private String row;
    private int seat;
    private int price;
    private Person person;

    public Ticket(String row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
     //add method for print the information of a ticket
    public void print_ticket(){

        System.out.println("-------------------");
        System.out.println("Row     :" +row);
        System.out.println("Seat    :" +seat);
        System.out.println("Price   :" +price);
        person.print_person_info();
    }

    //file writing part
    public void save(){
        try {

            FileWriter fileWriter = new FileWriter(row + seat +" "+".txt");
            fileWriter.write(row + seat +"Ticket information" + "\n");
            fileWriter.write("Row: "+row +"\n");
            fileWriter.write("Seat: "+seat + "\n");
            fileWriter.write("Price: "+price + "\n");
            fileWriter.write("Name: "+person.getName() +"\n");
            fileWriter.write("Surname: "+person.getSurname() + "\n");
            fileWriter.write("Email: "+person.getEmail() + "\n");

            fileWriter.close();
            System.out.println("successfully ticket wrote to the file");
        }catch (IOException ex)
        {
            System.out.println("error saving ticket information.");
        }
    }

}