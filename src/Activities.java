import java.sql.*;
import java.sql.Date;
import java.util.*;
public class Activities extends TableCreate{
    @Override
    public void createTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query = "create table " +table_name+"(ActivityID SERIAL, UserID INTEGER,type varchar(200), Date date, duration time, distance double precision, calories_burned int, notes varchar(200), primary key(ActivityID), foreign key (UserID) references users(userID))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Activity sdelan");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void createRow(Connection conn){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter UserID:");
        int userID = scanner.nextInt();

        System.out.println("Enter activity type:");
        String type = scanner.next();

        System.out.println("Enter date (yyyy-mm-dd):");
        Date date = Date.valueOf(scanner.next());

        System.out.println("Enter duration (HH:MM:SS):");
        Time duration = Time.valueOf(scanner.next());

        System.out.println("Enter distance:");
        int distance = scanner.nextInt();

        System.out.println("Enter calories burned:");
        int caloriesBurned = scanner.nextInt();

        System.out.println("Enter notes:");
        String notes = scanner.next();

        PreparedStatement statement = null;
        try{
            String query = "insert into Activities(UserID, type, Date, duration, distance, calories_burned, notes) values(?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setString(2, type);
            statement.setDate(3, date);
            statement.setTime(4, duration);
            statement.setInt(5, distance);
            statement.setInt(6, caloriesBurned);
            statement.setString(7, notes);
            statement.executeUpdate();
            System.out.println("Activity created");
        }catch(Exception e){
            System.out.println("not created: activity "+e);
        }
    }

    @Override
    public void deleteRow(Connection conn) {
        PreparedStatement statement = null;
        String query = "DELETE FROM activities " + " WHERE id = ?";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ActivityID that will be deleted: ");
        int ActivityID = scanner.nextInt();
        try {
            statement.setInt(1, ActivityID);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Override
    public void updateRow(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ActivityID to modify: ");
        int activityid = scanner.nextInt();
        System.out.println("Enter column to modify");
        String column_name = scanner.next();
        System.out.println("Enter new value");
        String new_value = scanner.next();
        String query = "UPDATE activities"+ " SET "+ column_name +" = ? WHERE activityid = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, new_value);
            statement.setInt(2, activityid);
            statement.executeUpdate();
            System.out.println("Value has been changed");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    @Override
    public void readRow(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ActivityID: ");
        int ActivityID = scanner.nextInt();
        System.out.println("Enter required information to display: ");
        String column_name = scanner.next();
        String query = "SELECT "+ column_name +" FROM users WHERE activityid = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, ActivityID);
            ResultSet ans = statement.executeQuery();
            if(ans.next()){
                System.out.println(column_name+": "+ans.getString(column_name));
            }
            else{
                System.out.println("No activity was found");
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
    }
}
