package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Formatter;
import javax.print.Doc;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.math.BigInteger;


public class Doctors extends Person{
	
	int Did=2000;
	static ArrayList<Doctors> doctors = new ArrayList<>();
    Scanner sc= new Scanner(System.in);
    String splz;
    int fee;
    static int count = 0;

    void createDoctor(){// creating doctor object
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "shubham");
            System.out.println("Doctor's Details: ");
            super.createPerson();
            System.out.print("Enter Speciallization: ");
            this.splz = sc.nextLine();

            System.out.print("Enter Consultation fees: ");
            this.fee = sc.nextInt();

            doctors.add(this);
            count++;
            this.Did+=count;
            PreparedStatement stm = con.prepareStatement("INSERT INTO Doctors VALUES(?,?,?,?,?,?)");
            stm.setInt(1,this.Did);
            stm.setString(2,this.name);
            stm.setLong(3,this.phn);
            stm.setString(4,this.gender);
            stm.setString(5,this.splz);
            stm.setInt(6,this.fee);
            stm.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    void getDetail(){
        System.out.println("Doctor's ID: "+Did);
        super.getDetail();
        System.out.println("Spciallized in"+splz);
        System.out.println("Consultation fee: "+fee);
    }
}
