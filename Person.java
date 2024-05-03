public class Person {
    //creat the attributes
    private String name;
    private String surname;
    private  String email;

    //create the constructor
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void print_person_info(){
        System.out.println("Name    :" + name);
        System.out.println("Surname :" + surname);
        System.out.println("email   :" + email);
        System.out.println("-------------------");
    }
}
