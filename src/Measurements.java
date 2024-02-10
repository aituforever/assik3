import java.sql.*;
import java.sql.Date;
import java.util.*;
public class Measurements extends TableCreate{
    public void BMI(Connection conn){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter userID to calculate BMI: ");
        int userID = scanner.nextInt();
        try{
            String query = "SELECT height, weight FROM users WHERE userId = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet bm = statement.executeQuery();
            if(bm.next()){
                double height = bm.getDouble("height");
                double weight = bm.getDouble("weight");
                double bmi = weight/Math.pow(height/100, 2);
                if(bmi<18.5){
                    System.out.println("BMI = "+bmi+", Underweight");
                }
                else if(bmi<24.9){
                    System.out.println("BMI = "+bmi+", Normal weight");
                }
                else if(bmi<29.9){
                    System.out.println("BMI = "+bmi+", Overweight");
                }
                else{
                    System.out.println("BMI = "+bmi+", Obesity detected");
                }
            }
        }catch(Exception e){
            System.out.println("The BMI can not be calculated: "+e);
        }
    }

    @Override
    public void createTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query = "create table " +table_name+"(MeasurementID SERIAL, UserID INTEGER, type varchar(200), Date date, value double precision, unit varchar(50), primary key(MeasurementID), foreign key (UserID) references users(userID))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Measurements sdelan");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void createRow(Connection conn){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter UserID:");
        int userID = scanner.nextInt();

        System.out.println("Enter measurement type:");
        String type = scanner.next();

        System.out.println("Enter date (yyyy-mm-dd):");
        Date date = Date.valueOf(scanner.next());

        System.out.println("Enter value:");
        double value = scanner.nextDouble();

        System.out.println("Enter unit:");
        String unit = scanner.next();

        PreparedStatement statement = null;
        try{
            String query = "insert into Measurements(UserID, type, Date, value, unit) values(?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setString(2, type);
            statement.setDate(3, date);
            statement.setDouble(4, value);
            statement.setString(5, unit);
            statement.executeUpdate();
            System.out.println("Measurement created");
        }catch(Exception e){
            System.out.println("not created: activity "+e);
        }
    }
    @Override
    public void deleteRow(Connection conn) {
        PreparedStatement statement = null;
        String query = "DELETE FROM measurements"+ " WHERE id = ?";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter MeasurementID that will be deleted: ");
        int MeasurementID = scanner.nextInt();
        try {
            statement.setInt(1, MeasurementID);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Override
    public void updateRow(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter MeasurementID to modify: ");
        int MeasurementID = scanner.nextInt();
        System.out.println("Enter column to modify");
        String column_name = scanner.next();
        System.out.println("Enter new value");
        String new_value = scanner.next();
        String query = "UPDATE goals"+ " SET "+ column_name +" = ? WHERE measurementid = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, new_value);
            statement.setInt(2, MeasurementID);
            statement.executeUpdate();
            System.out.println("Value has been changed");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
