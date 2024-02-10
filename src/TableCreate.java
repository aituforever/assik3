import java.sql.Connection;

public abstract class TableCreate {
    public abstract void createTable(Connection conn, String tableName);
    public abstract void createRow(Connection conn);
    public abstract void deleteRow(Connection conn);
    public abstract void updateRow(Connection conn);
}
