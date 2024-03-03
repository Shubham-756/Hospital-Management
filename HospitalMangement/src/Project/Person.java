package Project;

import java.util.Scanner;

public class Person {
	
	Scanner sc= new Scanner(System.in);
    String name, gender;
    long phn;
    void createPerson(){//Create person object
        System.out.println("****************Fill Your details****************");
        System.out.print("Enter name: ");
        this.name = sc.nextLine();

        System.out.print("Enter phone number: ");
        this.phn = sc.nextLong();

        System.out.print("Enter Gender(F/M/O): ");
        this.gender = sc.next();

    }
    void getDetail() {//function to show all details of person

        System.out.println("Name = " + name);
        System.out.println("Gender = " + gender);
        System.out.println("Phone number = " + phn);
    }

}
