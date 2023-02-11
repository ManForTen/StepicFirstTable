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
        DB.insertCat("Васька","Бенгальская кошка",5,15.1);
        DB.insertCat("Моська","Чаузи",1,3.0);
        DB.insertCat("Буська","Сибирская уличная",3,10.88);
        DB.addMoreCats(10);
        DB.deleteById("cats", 10036);
        DB.deleteByWhere("cats","name='Золушка'");
        DB.updateByWhereWithSet("cats","name='Лиловая'","name='Лило'");
        DB.updateByIdWithSet("cats","name='Пиратище'",10040);
        System.out.println(DB.getCat(5));
        DB.getCat( "name LIKE '%а'");
        DB.getCat();
    }

}