import java.sql.*;
import java.util.Random;

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

    public static void getType () throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT type FROM types");
        while(rs.next())
            System.out.println(rs.getString("type"));
    }

    public static void createTableCats() throws SQLException{
        stmt.executeUpdate("CREATE TABLE if not exists 'cats' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' VARCHAR(20) NOT NULL, 'id_type' INTEGER REFERENCES 'types' ('id') ON DELETE RESTRICT NOT NULL, 'age' INTEGER NOT NULL, 'weight' DOUBLE, FOREIGN KEY ('id_type')  REFERENCES 'types' ('id'))");
        System.out.println("Таблица создана!");
    }
    public static ResultSet getType (String where) throws SQLException {//получаем типы по условию
        ResultSet rs = stmt.executeQuery("SELECT id, type FROM types WHERE " + where);
        //while(rs.next())//пока печать отключаем
//            System.out.println(rs.getString("type"));
        return rs;
    }

    public static void insertCat (String name, String type, int age, Double weight) throws SQLException {//добавление кошки
        ResultSet rs = getType("type='"+type+"'");//ищем тип
        int id;
        if (rs.isBeforeFirst())//если тип есть то берем его id
            id=rs.getInt("id");
        else {//если нет, то создаем и берем
            insertType(type);
            id = getType("type='"+type+"'").getInt("id");
        }
        stmt.executeUpdate("INSERT INTO 'cats' ('name','id_type','age','weight') VALUES ('" + name + "',"+id+","+age+","+weight+")");
    }

    public static void addMoreCats (int n) throws SQLException {
        int minType=stmt.executeQuery("SELECT MIN(id) as id FROM types").getInt("id");
        int maxType=stmt.executeQuery("SELECT MAX(id) as id FROM types").getInt("id");
        int idType, age;
        String name, type;
        double weight;
        String[] names = {"Гарфилд","Том","Гудвин","Рокки","Ленивец","Пушок","Спорти","Бегемот","Пират","Гудини","Зорро","Саймон","Альбус","Базилио","Леопольд","Нарцисс","Атос","Каспер","Валлито","Оксфорд","Бисквит","Соня","Клеопатра","Цунами","Забияка","Матильда","Кнопка","Масяня","Царапка","Серсея","Ворсинка","Амели","Наоми","Маркиза","Изольда","Вальс","Несквик","Златан","Баскет","Изюм","Цукат","Мокко","Месси","Кокос","Адидас","Бейлиз","Тайгер","Зефир","Мохи","Валенсия","Баунти","Свити","Текила","Ириска","Карамель","Виски","Кукуруза","Гренка","Фасолька","Льдинка","Китана","Офелия","Дайкири","Брусника","Аватар","Космос","Призрак","Изумруд","Плинтус","Яндекс","Крисмас","Метеор","Оптимус","Смайлик","Цельсий","Краска","Дейзи","Пенка","Веста","Астра","Эйприл","Среда","Бусинка","Гайка","Елка","Золушка","Мята","Радость","Сиам","Оскар","Феликс","Гарри","Байрон","Чарли","Симба","Тао","Абу","Ватсон","Енисей","Измир","Кайзер","Васаби","Байкал","Багира","Айрис","Диана","Мими","Сакура","Индия","Тиффани","Скарлетт","Пикси","Лиззи","Алиса","Лило","Ямайка","Пэрис","Мальта","Аляска"};

        for (int i = 0; i < n; i++) {
            type="";
            while (type.equals("")) {
                idType = (int) ((Math.random() * (maxType - minType)) + minType);
                ResultSet rs = stmt.executeQuery("SELECT type FROM types WHERE id=" + idType);
                if (rs.isBeforeFirst())
                    type=rs.getString("type");
            }
            name = names[(int) (Math.random() * (names.length - 1))];
            age =  (int) (Math.random() * (30));
            weight = Math.round((0.01 + (30.0 - 0.01) * new Random().nextDouble())*100.0)/100.0;
            insertCat(name,type,age,weight);
        }
    }
    public static void deleteById (String table, int id) throws SQLException {//удаление из таблицы по ID
        stmt.executeUpdate("DELETE FROM "+table+" WHERE id=" + id);
    }

    public static void deleteByWhere (String table, String where) throws SQLException {//удаление из таблицы по where
        stmt.executeUpdate("DELETE FROM "+table+" WHERE "+where);
    }

    public static void updateByWhereWithSet (String table, String set, String where) {
        try{
            stmt.executeUpdate("UPDATE "+table+" SET "+set+" WHERE "+where);
        }
        catch (SQLException s) {
            System.out.println("Запись уже существует");
        }
    }

    public static void updateByIdWithSet (String table, String set, int id) {
        try{
            stmt.executeUpdate("UPDATE "+table+" SET "+set+" WHERE id="+id);
        }
        catch (SQLException s) {
            System.out.println("Запись уже существует!");
        }
    }

    public static String getCat (int id) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT cats.name, types.type as type, cats.age, cats.weight FROM cats JOIN types ON cats.id_type=types.id WHERE cats.id=" + id);
        return rs.isBeforeFirst()?rs.getString("name")+", "+rs.getString("type")+", "+rs.getInt("age")+", "+rs.getDouble("weight"):"Котика с ключом "+id+" не существует";
    }

    public static ResultSet getCat (String where) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT cats.name, types.type as type, cats.age, cats.weight FROM cats JOIN types ON cats.id_type=types.id WHERE " + where);
        while(rs.next())
            System.out.println(rs.getString("name")+", "+rs.getString("type")+", "+rs.getInt("age")+", "+rs.getDouble("weight"));
        return rs;
    }

    public static void getCat () throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT cats.name, types.type as type, cats.age, cats.weight FROM cats JOIN types ON cats.id_type=types.id");
        while(rs.next())
            System.out.println(rs.getString("name")+", "+rs.getString("type")+", "+rs.getInt("age")+", "+rs.getDouble("weight"));
    }
}