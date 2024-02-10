import java.sql.*;
import java.util.*;
import java.sql.Date;

public class Users extends TableCreate{
    public Connection connecttodb(String aituforever, String user, String pass){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+aituforever,user,pass);
            if(conn!=null){
                System.out.println("lol, working");
            }
            else{
                System.out.println("nah, working is not");
            }
        }
        catch (SQLException e) {
            System.out.println("Oshibka poimana");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver is not driving");
        }
        return conn;
    }
    @Override
    public void createTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query = "create table " +table_name+"(userID SERIAL, username varchar(200), password varchar(200), fname varchar(200), sname varchar(200), dateofbirth date, height integer, weight integer, gender varchar(100), primary key(userID))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Users sdelan");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void createRow(Connection conn){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Enter first name:");
        String fname = scanner.nextLine();

        System.out.println("Enter last name:");
        String sname = scanner.nextLine();

        System.out.println("Enter date of birth (yyyy-mm-dd):");
        Date dateofbirth = Date.valueOf(scanner.nextLine());

        System.out.println("Enter height:");
        int height = scanner.nextInt();

        System.out.println("Enter weight:");
        int weight = scanner.nextInt();

        System.out.println("Enter gender:");
        String gender = scanner.next();

        PreparedStatement statement = null;
        try{
            String query = "INSERT INTO users" + " (username, password, fname, sname, dateofbirth, height, weight, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";            statement = conn.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2, password);
            statement.setString(3, fname);
            statement.setString(4, sname);
            statement.setDate(5, dateofbirth);
            statement.setInt(6, height);
            statement.setInt(7, weight);
            statement.setString(8, gender);
            statement.executeUpdate();
            System.out.println("User created");
        }catch(Exception e){
            System.out.println("not created: "+e);
        }
    }
    @Override
    public void deleteRow(Connection conn) {
        PreparedStatement statement = null;
        String query = "DELETE FROM users" + " WHERE id = ?";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserID that will be deleted: ");
        int UserID = scanner.nextInt();
        try {
            statement.setInt(1, UserID);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Override
    public void updateRow(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserID to modify: ");
        int UserID = scanner.nextInt();
        System.out.println("Enter column to modify");
        String column_name = scanner.next();
        System.out.println("Enter new value");
        String new_value = scanner.next();
        String query = "UPDATE users"+ " SET "+ column_name +" = ? WHERE userid = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, new_value);
            statement.setInt(2, UserID);
            statement.executeUpdate();
            System.out.println("Value has been changed");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

