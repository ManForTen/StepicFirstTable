import java.sql.*;

public class DB {
    public static Connection conn;
    public static Statement stmt;

    public static void connectOrCreateDB (String path) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(path);
        stmt = conn.createStatement();
    }

    public static void createTable() throws SQLException{ // Создание таблицы
        stmt.executeUpdate("CREATE TABLE if not exists 'types' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'type' VARCHAR(100) UNIQUE NOT NULL)");
    }

    public static void insertType (String type) // Добавление типа кошки
    {
        try {
            stmt.executeUpdate("INSERT INTO 'types' ('type') VALUES ('" + type + "')");
        }
        catch (SQLException s) {
            System.out.println("Запись '"+type+"' уже существует");
        }
    }

    public static void deleteType (int id) throws SQLException {
        stmt.executeUpdate("DELETE FROM types WHERE id=" + id);
    }

    public static void deleteType (String type) throws SQLException {
        stmt.executeUpdate("DELETE FROM types WHERE type='" + type + "'");
    }

    public static void updateType (int id, String newType ) {
        try {
            stmt.executeUpdate("UPDATE types SET type='"+newType+"' WHERE id=" + id);
        }
        catch (SQLException s) {
            System.out.println("Запись с ключом '"+id+"' уже существует");
        }
    }

    public static void updateType (String type, String newType ) {
        try{
            stmt.executeUpdate("UPDATE types SET type='"+newType+"' WHERE type='" + type + "'");
        }
        catch (SQLException s) {
            System.out.println("Запись '"+newType+"' уже существует");
        }
    }

    public static String getType (int id) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT type FROM types WHERE id=" + id);
        return rs.isBeforeFirst()?rs.getString("type"):"Типа с ключом "+id+" не существует";
    }

    public static void getType (String where) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT type FROM types WHERE " + where);
        while(rs.next())
            System.out.println(rs.getString("type"));
    }

    public static void getType () throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT type FROM types");
        while(rs.next())
            System.out.println(rs.getString("type"));
    }

    public static void createTableCats() throws SQLException{
        stmt.executeUpdate("CREATE TABLE if not exists 'cats' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' VARCHAR(20) NOT NULL, 'id_type' INTEGER REFERENCES 'types' ('id') ON DELETE RESTRICT NOT NULL, 'age' INTEGER NOT NULL, 'weight' DOUBLE, FOREIGN KEY ('id_type')  REFERENCES 'types' ('id'))");
        System.out.println("Таблица создана!");
    }
}