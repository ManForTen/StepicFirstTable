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
}