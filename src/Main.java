import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB.connectOrCreateDB("jdbc:sqlite:My_cats.db");
        DB.createTable();
        DB.insertType("Абиссинская кошка");
        DB.insertType("Австралийский мист");
        DB.insertType("Американская жесткошерстная");
    }
}