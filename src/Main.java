import java.sql.*;
import java.sql.Date;
import java.util.*;
public class Main {
    public static void main(String[] args){
        Users users = new Users();
        Activities activities = new Activities();
        Measurements measurements = new Measurements();
        Goals goals = new Goals();
        Connection conn = users.connecttodb("aituforever", "postgres", "123456789");
        //users.createTable(conn, "Users");
        //activities.createTable(conn, "Activities");
        //measurements.createTable(conn, "Measurements");
        //goals.createTable(conn, "Goals");
        //users.createRow(conn, "users");
        //activities.createRow(conn, "activities");
        //measurements.createRow(conn, "measurements");
        //goals.createRow(conn, "goals");
        //measurements.BMI(conn);
        //goals.updateRow(conn);
        System.out.println("Log in/sign in?(1/2)");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextInt()==1){
            System.out.println("Enter username: ");
            String username = scanner.next();
            System.out.println("Enter password: ");
            String pass = scanner.next();
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try{
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, pass);
                ResultSet ans = statement.executeQuery();
                if(ans.next()){
                    System.out.println("Welcome, "+ ans.getString("fname"));
                    System.out.println("Enter a command: ");// create/read/update/delete or specific(bmi)
                    String command = scanner.next();
                    System.out.flush();
                    System.out.println("Enter the destination: ");
                    String where = scanner.next();//activity/measurement/goal
                    System.out.flush();
                        switch(command){
                            case "create":
                                if(Objects.equals(where, "activity")){
                                    activities.createRow(conn);
                                }
                                else if(Objects.equals(where, "measurement")){
                                    measurements.createRow(conn);
                                }
                                else if(Objects.equals(where, "goal")){
                                    goals.createRow(conn);
                                }
                                else if (Objects.equals(where, "user")) {
                                    users.createRow(conn);
                                }
                                else {
                                    System.out.println("No table detected");
                                }
                                break;
                            case "read":
                                if(Objects.equals(where, "activity")){
                                    activities.readRow(conn);
                                }
                                else if (Objects.equals(where, "measurement")) {
                                    measurements.readRow(conn);
                                }
                                else if (Objects.equals(where, "goal")) {
                                    goals.readRow(conn);
                                }
                                else if (Objects.equals(where, "user")) {
                                    users.readRow(conn);
                                }
                                else{
                                    System.out.println("No table detected");
                                }
                                break;
                            case "update":
                                if(Objects.equals(where, "activity")){
                                    activities.updateRow(conn);
                                }
                                else if(Objects.equals(where, "measurement")){
                                    measurements.updateRow(conn);
                                }
                                else if(Objects.equals(where, "goal")){
                                    goals.updateRow(conn);
                                }
                                else if (Objects.equals(where, "user")) {
                                    users.updateRow(conn);
                                }
                                else {
                                    System.out.println("No table detected");
                                }
                                break;
                            case "delete":
                                if(Objects.equals(where, "activity")){
                                    activities.deleteRow(conn);
                                }
                                else if(Objects.equals(where, "measurement")){
                                    measurements.deleteRow(conn);
                                }
                                else if(Objects.equals(where, "goal")){
                                    goals.deleteRow(conn);
                                }
                                else if (Objects.equals(where, "user")) {
                                    users.deleteRow(conn);
                                }
                                else {
                                    System.out.println("No table detected");
                                }
                                break;
                            case "calculate BMI:":
                                if(Objects.equals(where, "measurements")){
                                    measurements.BMI(conn);
                                }
                                else{
                                    System.out.println("Wrong table");
                                }
                                break;
                        }
                }
                else{
                    System.out.println("No user found");
                }
            }catch(Exception e){
                System.out.println("Error: "+e);
            }
        } else if (scanner.nextInt()==2) {
            users.createRow(conn);
        }
    }

}
