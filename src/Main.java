import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB.connectOrCreateDB("jdbc:sqlite:My_cats.db");
        DB.createTable();
        DB.createTableCats();
        DB.insertType("Абиссинская кошка");
        DB.insertType("Австралийский мист");
        DB.insertType("Американская жесткошерстная");
        DB.deleteType(1);
        DB.deleteType("Австралийский мист");
        DB.updateType(190,"Обычная домашняя");
        DB.updateType("Американская короткошерстная","Обычная домашняя");
        System.out.println(DB.getType(10));
        DB.getType( "type LIKE '%а'");
        DB.getType();
    }

}