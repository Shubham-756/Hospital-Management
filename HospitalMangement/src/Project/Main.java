package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	static void showDocMenu(){//menu driven options for Doctor's portal
        System.out.println("1. New Doctor joining Registeration");
        System.out.println("2: Scheduled Appointments for the day");
        System.out.println("3: Get Doctor's List");
        System.out.println("4: Deny an Appointment");
        System.out.println("5: Reschedule Appointment");
        System.out.println("6: Patients List");
        System.out.println("7: Exit");
        System.out.println("Press 0 to switch to Patient's Portal");
    }

    static void showPatMenu(){//menu driven options for Patient's portal
        System.out.println("1. New Patient's admitting Registeration");
        System.out.println("2: Book Your Appointment");
        System.out.println("3: Your Scheduled Appointments");
        System.out.println("4: Check Doctor's Details");
        System.out.println("5: Doctors Available for you");
        System.out.println("6: Exit");
        System.out.println("Press 0 to switch to Doctor's portal");
    }
    static void showPatList(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "shubham");
            System.out.printf("%20s %20s %20s %20s %20s \n","Patient's Id","patient's name", "Phone number" ,"Gender", "Insurance");
            String selQuery = "select * from patients";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(selQuery);
            while(rs.next()){
                String col1 = Integer.toString(rs.getInt(1));
                String col2 = rs.getString(2);
                String col3 = Long.toString(rs.getLong(3));
                String col4 = rs.getString(4);
                String col5 = Boolean.toString(rs.getBoolean(5));
                System.out.printf("%20s %20s %20s %20s %20s \n",col1,col2, col3 ,col4, col5);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    static void showDocList(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "shubham");
            System.out.printf("%20s %20s %20s %20s %20s %20s \n", "Doctor's ID","Doctor's name", "Phone number", "Gender", "Speciallization", "Consultation fees");
            String selQuery = "select * from Doctors";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(selQuery);
            while(rs.next()){
                String col1 = Integer.toString(rs.getInt(1));
                String col2 = rs.getString(2);
                String col3 = Long.toString(rs.getLong(3));
                String col4 = rs.getString(4);
                String col5 = rs.getString(5);
                String col6 = Integer.toString(rs.getInt(6));
                System.out.printf("%20s %20s %20s %20s %20s %20s\n",col1,col2, col3 ,col4, col5,col6);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    static void docOperations() {//function contains The Menu Driven operations for doctor's portal
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Select: ");
            showDocMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case (1)://New Doctor joining Registeration
                    Doctors doc = new Doctors();
                    doc.createDoctor();
                    showDocList();
                    break;
                case (2)://Scheduled Appointments for the day
                    Appointment.showAppointments();
                    break;
                case (3)://Get Doctor's List
                    ArrayList<Doctors> arr1 = Doctors.doctors;
                    if(Doctors.count==0){
                        System.out.println("no records");
                        break;
                    }
                    showDocList();
                    break;
                case (4)://Deny an Appointment
                    if(Appointment.countAppointments==0){
                        System.out.println("No appointments");
                        break;
                    }
                    System.out.println("These are the scheduled appointments");
                    Appointment.showAppointments();
                    System.out.print("Enter the Appointment ID: ");
                    int id = sc.nextInt();
                    Appointment.DenyApp(id);
                    break;

                case (5)://Reschedule Appointment
                    if(Appointment.countAppointments==0){
                        System.out.println("No Appointments to re-Schedule");
                        break;
                    }
                    System.out.println("available appointments");
                    Appointment.showAppointments();
                    System.out.println("Enter App id: ");
                    id = sc.nextInt();
                    System.out.println("Enter new date: ");
                    String date = sc.next();
                    System.out.println("Enter new time");
                    String time = sc.next();
                    Appointment.rescheApp(id,time,date);
                    break;
                case(6):
                    showPatList();
                    break;
                case(7)://Exit
                    try{
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "shubham");
                        Statement stm = con.createStatement();
                        String selQuery = "delete from patients";
                        String selQuery1 = "delete from doctors";
                        stm.executeUpdate(selQuery);
                        stm.executeUpdate(selQuery1);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    System.exit(0);
                default://switch to Patient's Portal
                    patOperations();
            }
        }
    }
    static void patOperations(){//function contains The menu driven operations for patient's portal
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Select: ");
            showPatMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case (1)://New Patient's admitting Registeration
                    Patients pat = new Patients();
                    pat.createPatient();
                    System.out.println("Updated list of Patients");
                    showPatList();
                    break;
                case (2)://Book Your Appointment
//                    Doctor doc = new Doctor();
//                    doc.createDoctor();
//                    Patient pat1 = new Patient();
//                    pat1.createPatient();
                    if(Doctors.count==0){
                        System.out.println("No Doctors available for appointment");
                        break;
                    }
                    System.out.println("Doctors available: ");
                    showDocList();
                    System.out.println("Enter Patient ID: ");
                    int id = sc.nextInt();
                    System.out.println("Enter Doctor's ID for Appointment: ");
                    int id1 = sc.nextInt();
                    String date, time;
                    System.out.print("Enter date: ");
                    date = sc.next();
                    System.out.print("Enter time: ");
                    time = sc.next();
                    Appointment ap = new Appointment(id1,id,date,time);
                    Appointment.showAppointments();
                    break;
                case (3)://Your Scheduled Appointments
                    System.out.print("enter patient's Id: ");
                    int Id = sc.nextInt();
                    Appointment.showPatientApp(Id);
                    break;
                case (4)://Check Doctor's Details
                    if(Doctors.count==0){
                        System.out.println("No Doctor's available right now");
                        break;
                    }
                    System.out.println("Showing doctors list");
                    ArrayList<Doctors> arr = Doctors.doctors;
//                    System.out.printf("%20s %20s \n", "Doctor's id", "Doctor's name");
//                    for (int i = 0; i < Doctor.count; i++)
//                        System.out.printf("%20s %20s \n", arr.get(i).Did, arr.get(i).name);
                    showDocList();
                    System.out.println("which doctor's details you want enter id: ");
                    id = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < Doctors.count; i++){
                        if(arr.get(i).Did == id){
                            found = true;
                            arr.get(i).getDetail();
                        }
                    }
                    if(!found){
                        System.out.println("No records");
                    }
                    break;
                case (5)://Doctors Available for you
                    if(Doctors.count==0){
                        System.out.println("No doctors available right now");
                        break;
                    }
                   showDocList();
                    break;

                case(6)://Exit
                    try{
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "shubham");
                        Statement stm = con.createStatement();
                        String selQuery = "delete from patients";
                        String selQuery1 = "delete from doctors";
                        stm.execute(selQuery);
                        stm.execute(selQuery1);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    System.exit(0);
                default://switch to Doctor's portal
                    docOperations();
            }
        }
    }
	
	public static void main(String[] args) {
		try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "shubham");
            Statement stm = con.createStatement();
            String selQuery = "delete from patients";
            String selQuery1 = "delete from doctors";
            stm.execute(selQuery);
            stm.execute(selQuery1);
        }catch(Exception e){
            System.out.println(e);
        }//this is to clear old data that causes abnormal result in new run.
        try{
        Scanner sc = new Scanner(System.in);
        System.out.println("********* Welcome to the Portal *********");
        System.out.println("Select the designation");
        System.out.println("1: Patient \n2: Doctor");
        int person = sc.nextInt();
        sc.nextLine();
            switch (person) {
                case (1):
                    System.out.println("Patient's Portal");
                    patOperations();
                    break;
                case (2):
                    System.out.println("Doctor's Portal");
                    docOperations();
                    break;
            }
        }catch(Exception e){
            System.out.println("Some Error Occured exiting...");
            System.out.println(e);
        }
	}

}
