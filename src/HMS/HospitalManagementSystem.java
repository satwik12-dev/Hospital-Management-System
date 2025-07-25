package HMS;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private  static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "812666";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Pateint pateint = new Pateint(connection, scanner);
            Doctors doctors = new Doctors(connection);

            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctor");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter your choice:");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        pateint.add_patient();
                        System.out.println();
                        break;
                    case 2:
                        pateint.view_patient();
                        System.out.println();
                        break;
                    case 3:
                        doctors.view_doctor();
                        System.out.println();
                        break;
                    case 4:
                        bookAppointment(pateint,doctors,connection,scanner);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("Thank you for using Hospital Management System");
                        return;
                    default:
                        System.out.println("Enter valid choice!!");
                        break;
                }

            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Pateint pateint, Doctors doctors,Connection connection, Scanner scanner){
        System.out.println("Enter Patient id:");
        int patient_id = scanner.nextInt();
        System.out.println("Enter Doctor id:");
        int doctorId = scanner.nextInt();
        System.out.println("Enter the Appointment Date(YYYY-MM-DD): ");
        String appointmentdate = scanner.next();
        if(pateint.patientbyid(patient_id) && doctors.doctorbyid(doctorId)){
            if(checkDoctorAvailability(doctorId,appointmentdate,connection)){
                String appointmentQuery = "INSERT INTO appointments(patient_id,doctor_id,appointment_date) VALUES(?,?,?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patient_id);
                    preparedStatement.setInt(2,doctorId);
                    preparedStatement.setString(3, appointmentdate);
                    int rowsaffected = preparedStatement.executeUpdate();
                    if (rowsaffected > 0) {
                        System.out.println("Appointment Booked..");
                    }else{
                        System.out.println("Failed to book appointment..");
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Doctor not avilable on the date..");
            }
        }else{
            System.out.println("Either docotor or patient doesn't exist...");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentdate,Connection connection){
        String Query = "SELECT COUNT(*) FROM appointments where doctor_id = ? AND appointment_date=?";
        try{
            PreparedStatement pstmt = connection.prepareStatement(Query);
            pstmt.setInt(1, doctorId);
            pstmt.setString(2,appointmentdate);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int count= rs.getInt(1);
                if(count==0){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }

}
