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


public class Patients extends Person {

	static ArrayList<Patients> patients = new ArrayList<>();
    Scanner sc= new Scanner(System.in);
    int Pid=1000;
    Boolean ins;
    static int count = 0;
    void createPatient(){//Create patient object
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "shubham");
            System.out.println("Patient Details: ");
            super.createPerson();
            System.out.print("Do you have an Insurance (Y=true/N=false): ");
            this.ins = sc.nextBoolean();
            patients.add(this);
            count++;
            this.Pid+=count;
            PreparedStatement stm = con.prepareStatement("INSERT INTO Patients VALUES(?,?,?,?,?)");
            stm.setInt(1,this.Pid);
            stm.setString(2,this.name);
            stm.setLong(3,this.phn);
            stm.setString(4,this.gender);
            stm.setBoolean(5,this.ins);
            stm.executeUpdate();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    void getDetail(){
        System.out.println("Patient's ID: "+Pid);
        super.getDetail();
        System.out.println("Have Insurance = "+ins);
    }
}
