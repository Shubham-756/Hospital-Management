package Project;

import java.util.ArrayList;


public class Appointment {
	
	int appId = 3001;
    private int docID;
    private int patID;
    private String date;
    private String time;
//    static Appointment[] appointments = new Appointment[30];
static ArrayList<Appointment> appointments = new ArrayList<>();
    static int countAppointments = 0;
    public Appointment(int docId, int patId, String date, String time){   //constructor booking appointments
        this.docID = docId;
        this.patID = patId;
        this.date = date;
        this.time = time;
        appointments.add(this);
        appId+=countAppointments;
        countAppointments++;

    }

    public static void showAppointments(){//function to show appointments booked till now
        //this will show all the appointments of all the doctors
        int size = appointments.size();
        if(size==0){
            System.out.println("No Appointments for today");
            return;
        }
        System.out.printf("%20s %20s %20s %20s %20s \n","Appointment ID","Doctor's name","patient's name", "Date" ,"Time");
        for(int i=0;i<countAppointments;i++){
            System.out.printf("%20s %20s %20s %20s %20s\n \n",appointments.get(i).appId,appointments.get(i).docID,appointments.get(i).patID,appointments.get(i).date,appointments.get(i).time);
        }
    }
    public static void showDoctorApp(int docId) {//show appointments of specified doctor
        if(countAppointments==0){
            System.out.println("No Appointments");
        }
        Boolean flag = false;
        System.out.printf("%20s %20s %20s %20s","Doctor's name","patient's name", "Date" ,"Time");
        for(int i=0;i<countAppointments;i++){
            if(appointments.get(i).docID==docId){
                System.out.printf("%20s %20s %20s %20s %20s\n \n",appointments.get(i).appId,appointments.get(i).docID,appointments.get(i).patID,appointments.get(i).date,appointments.get(i).time);
                flag=true;
            }
        }
        if(!flag)
            System.out.println("No Appointments for this Id");
    }

    public static void showPatientApp(int patId){// show appointments of specified patient
        boolean found = false;
        int ind=-1;
        for(int i=0;i<countAppointments;i++){
            if(appointments.get(i).patID==patId){
                ind = i;
                found=true;
            }
        }
        if(found){
            System.out.printf("%20s %20s %20s %20s %20s \n", "Appointment ID","Doctor's Id","patient's Id", "Date" ,"Time");
            System.out.printf("%20s %20s %20s %20s %20s\n \n",appointments.get(ind).appId,appointments.get(ind).docID,appointments.get(ind).patID,appointments.get(ind).date,appointments.get(ind).time);

        }	
        else{
            System.out.println("No such appointments scheduled for this patient");
        }
    }

    public static void DenyApp(int AppId){//To cancel an appointment

        int ind=0;
        boolean found = false;
        for(int i=0;i<countAppointments;i++){
            if(appointments.get(i).appId == AppId){
                ind =i;
                found = true;
            }
        }
        if(found){
            appointments.remove(ind);
            countAppointments--;
        }
        else{
            System.out.println("Appointment not found");
        }
    }
    public static void rescheApp(int id,String t, String d){//To Reschedule any appointment
        int ind=0;
        boolean found = false;
        for(int i=0;i<countAppointments;i++){
            if(appointments.get(i).appId==id){
                ind = i;
                found = true;
                appointments.get(i).time = t;
                appointments.get(i).date = d;
                break;
            }
        }
        if(found) {
            System.out.println("Re-Schedule done!");
            System.out.printf("%20s %20s %20s %20s \n", "Doctor's Id", "patient's Id", "Date", "Time");
            System.out.printf("%20s %20s %20s %20s \n\n", appointments.get(ind).docID, appointments.get(ind).patID, appointments.get(ind).date, appointments.get(ind).time);
        }
        else{
            System.out.println("No records to ReSchedule for the given input");
        }
    }

}
