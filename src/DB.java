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

    public static void deleteType (int id) throws SQLException {//удаление типа кошки по ID
        stmt.executeUpdate("DELETE FROM types WHERE id=" + id);
    }

    public static void deleteType (String type) throws SQLException {//удаление типа кошки по названию
        stmt.executeUpdate("DELETE FROM types WHERE type='" + type + "'");
    }

    public static void updateType (int id, String newType ) {//обновление типа кошки по ID
        try {
            stmt.executeUpdate("UPDATE types SET type='"+newType+"' WHERE id=" + id);
        }
        catch (SQLException s) {
            System.out.println("Запись с ключом '"+id+"' уже существует");
        }
    }

    public static void updateType (String type, String newType ) {//обновление типа кошки по названию
        try{
            stmt.executeUpdate("UPDATE types SET type='"+newType+"' WHERE type='" + type + "'");
        }
        catch (SQLException s) {
            System.out.println("Запись '"+newType+"' уже существует");
        }
    }
}