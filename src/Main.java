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
        goals.updateRow(conn);
    }

}
