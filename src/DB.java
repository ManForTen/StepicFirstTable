import java.sql.*;

public class DB {
    public static Connection conn;
    public static Statement stmt;

    public static void connectOrCreateDB(String path) throws SQLException, ClassNotFoundException {//создание или подключение БД
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(path);//создаем соединение
        stmt = conn.createStatement();//получаем доступ к БД для выполнения SQL запросов
    }

    public static void createTable() throws SQLException{//создание таблицы
        stmt.execute("CREATE TABLE if not exists 'types' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'type' VARCHAR(100) NOT NULL);");
    }
}