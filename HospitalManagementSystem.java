import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hospital hospital = new Hospital();
        FindDoctor findDoctor = new FindDoctor();

        System.out.println("\n `MedAccess` \n");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (AdminLogin.authenticate(username, password)) {
            System.out.println("Login successful! Welcome, Admin.");

            while(true){
                System.out.println(" \n~~~~ MedAccess ~~~~ \n");
                System.out.println("1. Add Doctor ");
                System.out.println("2. Add Patient ");
                System.out.println("3. Schedule Appointment ");
                System.out.println("4. List Doctors ");
                System.out.println("5. Find Doctor by Name ");
                System.out.println("6. Find Doctor by Specialization ");
                System.out.println("7. List Patients ");
                System.out.println("8. View Patient Details ");
                System.out.println("9. List Appointments ");
                System.out.println("10. Exit ");
                System.out.print("Select a number: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.println("\nEnter Doctor Name: ");
                        String doctorName = sc.nextLine();
                        System.out.println("Enter Age: ");
                        int  doctorAge = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter Gender: ");
                        String doctorGender = sc.nextLine();
                        System.out.println("Enter Specialization: ");
                        String specialization = sc.nextLine();
                        Doctor doctor = new Doctor(doctorName, doctorAge, doctorGender, specialization);
                        hospital.addDoctor(doctor);
                        System.out.println("Doctor Added Successfully!");

                        break;

                    case 2:
                        System.out.println("\nEnter Patient Name: ");
                        String patientName = sc.nextLine();
                        System.out.println("Enter Age: ");
                        int  patientAge = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter Gender: ");
                        String patientGender = sc.nextLine();
                        System.out.println("Enter Illness: ");
                        String illness = sc.nextLine();
                        Patient patient = new Patient(patientName, patientAge, patientGender, illness);
                        hospital.addPatient(patient);
                        System.out.println("Patient Added Successfully!");

                        break;

                    case 3:
                        System.out.println("Enter Doctor's Name: ");
                        String doctorForAppointment = sc.nextLine();
                        Doctor foundDoctor = findDoctor.findByName(hospital.getDoctors(), doctorForAppointment);
                        if (foundDoctor == null) {
                            System.out.println("Doctor not found!");
                            break;
                        }
                        System.out.println("Enter Patient's Name: ");
                        String patientForAppointment = sc.nextLine();
                        Patient foundPatient = null;
                        for (Patient p : hospital.getPatients()) {
                            if (p.getName().equalsIgnoreCase(patientForAppointment)) {
                                foundPatient = p;
                                break;
                            }
                        }
                        if (foundPatient == null) {
                            System.out.println("Patient not found!");
                            break;
                        }
                        System.out.println("Enter Appointment Date (year-month-day): ");
                        String date = sc.nextLine();
                        Appointment appointment = new Appointment(foundDoctor, foundPatient, date);
                        hospital.addAppointment(appointment);
                        System.out.println("Appointment scheduled successfully!");
                        break;

                    case 4:
                        System.out.println("\nDoctors List: \n");
                        hospital.doctorlist();
                        break;

                    case 5:
                        System.out.println("Enter Doctor Name: ");
                        String name = sc.nextLine();
                        Doctor doctorByName = findDoctor.findByName(hospital.getDoctors(), name);
                        if (doctorByName == null) {
                            System.out.println("Doctor not found!");
                        } else {
                            doctorByName.DoctorInformation();
                        }
                        break;

                    case 6:
                        System.out.println("Enter Specialization: ");
                        String specializationToFind = sc.nextLine();
                        List<Doctor> doctorsBySpecialization = findDoctor.findBySpecialization(hospital.getDoctors(), specializationToFind);
                        if (doctorsBySpecialization.isEmpty()) {
                            System.out.println("No doctor with specialization: " + specializationToFind);
                        } else {
                            for(Doctor doctors : doctorsBySpecialization) {
                                System.out.println("Doctor's Name: " + doctors.getName()) ;
                            }
                        }
                        break;

                    case 7:
                        System.out.println("\nPatient's List: \n");
                        hospital.patientlist();
                        break;

                    case 8:
                        System.out.println("Enter Patient Name: ");
                        String namePatient = sc.nextLine();
                        Patient patientByName = FindPatient.findByNamePatient(hospital.getPatients(), namePatient);
                        if (patientByName == null) {
                            System.out.println("Patient not found!");
                        } else {
                            patientByName.PatientInformation();
                        }
                        break;

                    case 9:
                        System.out.println("\nAppointments List: \n");
                        hospital.Appointmentlist();
                        break;

                    case 10:
                        System.out.println("\nExiting HOSPITAL MANAGEMENT SYSTEM. bye!");
                        sc.close();
                        break;

                    default:
                        System.out.println("Invalid choice! try again.");

                }

            }
        } else {
            System.out.println("Invalid username or password. Access denied.");
        }
    }
}
abstract class Person {
    private String Name;
    private int Age;
    private String Gender;

    public Person (String Name, int Age, String Gender) {
        this.Name =Name;
        this.Age = Age;
        this.Gender = Gender;
    }

    public String getName(){
        return Name;
    }
    public int getAge(){
        return Age;
    }
    public String getGender(){
        return Gender;
    }

}

class Doctor extends Person {
    final String Specialization;

    public Doctor (String Name, int Age, String Gender, String Specialization) {
        super (Name, Age, Gender);
        this.Specialization = Specialization;
    }

    public String getSpecialization(){
        return Specialization;
    }

    public void DoctorInformation(){
        System.out.println("Doctor's Name: " + getName());
        System.out.println("Doctor's Age: " + getAge());
        System.out.println("Doctor's Gender: " + getGender());
        System.out.println("Specialization: " + getSpecialization());
    }
    public String toString() {
        return getName();
    }

}

class Patient extends Person {
   final String Illness;

   public Patient (String Name, int Age, String Gender, String Illness){
       super (Name, Age, Gender);
       this.Illness = Illness;
   }

   public String getIllness(){
       return Illness;
   }

   public void PatientInformation(){
       System.out.println("Patient's Name: " + getName());
       System.out.println("Patient's Age: " + getAge());
       System.out.println("Patient's Gender: " + getGender());
       System.out.println("Illness: " + getIllness());
   }
    public String toString() {
        return getName();
    }
}

class Appointment {
    private Doctor doctor;
    private Patient patient;
    private String date;

    public Appointment(Doctor doctor, Patient patient, String date){
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    public String toString() {
        return "Date: " + date + "\nDoctor's Name: " + doctor.getName() + "\nDoctor's Specialization: " + doctor.getSpecialization() + "\nPatient's Name: " + patient.getName() + "\nPatient's Illness: " + patient.getIllness() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    }

}

class FindDoctor {

    public Doctor findByName (List<Doctor> doctors, String name){
        for (Doctor doctor: doctors){
            if (doctor.getName().equalsIgnoreCase(name)){
                return doctor;
            }
        }
        return null;
    }

    public List<Doctor> findBySpecialization(List<Doctor> doctors, String specialization){
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor: doctors){
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)){
                result.add(doctor);
            }
        }
       return result;
    }
}

class FindPatient {

    public static Patient findByNamePatient (List<Patient> patients, String name){
        for (Patient patient: patients){
            if (patient.getName().equalsIgnoreCase(name)){
                return patient;
            }
        }
        return null;
    }
}

class Hospital {

    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void doctorlist() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors added yet.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
    }

    public void patientlist() {
        if (patients.isEmpty()) {
            System.out.println("No patient added yet.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    public void Appointmentlist() {
        if (appointments.isEmpty()) {
            System.out.println("No Appointments added yet.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }
}

class AdminLogin {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    static boolean authenticate(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }
}


