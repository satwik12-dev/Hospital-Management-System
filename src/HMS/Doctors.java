package HMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
    private Connection connection;

    public Doctors(Connection connection){
        this.connection=connection;
    }



    public void view_doctor(){
        String Query ="SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Doctors:");
            System.out.println("+------------+--------------------+-------------------------+");
            System.out.println("|  Doctor_Id | Name               | Specialisation          |");
            System.out.println("+------------+--------------------+----------+-------------+");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String specialisation = rs.getString("specialisation");
                System.out.printf( "|%-12s|%-20s|%-25s|\n",id,name,specialisation);
                System.out.println("+------------+--------------------+----------+-------------+");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean doctorbyid(int id) {
        String query = "SELECT * FROM doctors WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
