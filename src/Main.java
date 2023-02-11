public class Main {
    public static void main(String[] args) {
        Animal duck = new Animal("Утка", "Утя", 3, 5.8, true, true, true);
        duck.display();

        Bird b = new Bird();
        b.display();
        b.setArea("На югах");
        b.setWinterFly(false);
        System.out.println(b.getArea());
        System.out.println(b.isWinterFly());
        b.chirick_chirick();

        Fish f = new Fish();
        f.display();
        f.setSquama("Крупная");
        f.setUpStreamSwim(true);
        System.out.println(f.getSquama());
        System.out.println(f.isUpStreamSwim());
        f.bul_bul();

        Insect i = new Insect();
        i.display();
        i.setWingCount(4);
        i.setLikeJesus(true);
        System.out.println(i.getWingCount());
        System.out.println(i.isLikeJesus());
        i.ggggg();
    }
}

class Animal {
    private String type, name;
    private int age;
    private double weight;
    private boolean isSwim, isWalk, isFly;

    public int getAge() {
        return age;
    }

    public boolean isSwim() {
        return isSwim;
    }

    public void setSwim(boolean swim) {
        isSwim = swim;
    }

    public boolean isWalk() {
        return isWalk;
    }

    public void setWalk(boolean walk) {
        isWalk = walk;
    }

    public boolean isFly() {
        return isFly;
    }

    public void setFly(boolean fly) {
        isFly = fly;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Animal(String type, String name, int age, double weight, boolean isSwim, boolean isWalk, boolean isFly) {
        this.type = type;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.isSwim = isSwim;
        this.isWalk = isWalk;
        this.isFly = isFly;
    }

    public void display() {
        System.out.println("Тип:" + type +
                ", Имя:" + name +
                ", Возраст:" + age +
                ", Вес:" + Math.round(weight * 100) / 100.0 +
                ", Умеет летать:" + (isFly ? "Да" : "Нет") +
                ", Умеет ходить:" + (isWalk ? "Да" : "Нет") +
                ", Умеет плавать:" + (isSwim ? "Да." : "Нет."));

    }

    public void holiday() {
        weight += 0.1;
    }

    public void holiday(double m) {
        weight += m;
    }

    public void holiday(double m, int n) {
        weight -= (m * n);
    }
}

class Bird extends Animal {
    {
        setType("Птица");
        setName("Bob");
        setFly(true);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isWinterFly() {
        return winterFly;
    }

    public void setWinterFly(boolean winterFly) {
        this.winterFly = winterFly;
    }

    private String area;
    private boolean winterFly;

    static void chirick_chirick() {
        System.out.println("Chirik-Chirik");
    }

    @Override
    public void display() {
        System.out.println("Тип:" + this.getClass().getName() +
                ", Имя:" + getName() +
                ", Зона обитания: " + getArea() +
                ", Возраст:" + getAge() +
                ", Вес:" + Math.round(getWeight() * 100) / 100.0 +
                ", Умеет летать:" + (isFly() ? "Да" : "Нет") +
                ", Умеет ходить:" + (isWalk() ? "Да" : "Нет") +
                ", Умеет плавать:" + (isSwim() ? "Да." : "Нет.") +
                ", Улетает зимовать на югах:" + (isSwim() ? "Да." : "Нет."));
    }
}

class Fish extends Animal {
    {
        setType("Рыба");
        setName("Сельдь");
        setSwim(true);
    }

    private String squama;
    private boolean upStreamSwim;

    public String getSquama() {
        return squama;
    }

    public void setSquama(String squama) {
        this.squama = squama;
    }

    public boolean isUpStreamSwim() {
        return upStreamSwim;
    }

    public void setUpStreamSwim(boolean upStreamSwim) {
        this.upStreamSwim = upStreamSwim;
    }

    public void bul_bul() {
        System.out.println("Bul_bul");
    }

    @Override
    public void display() {
        System.out.println("Тип:" + this.getClass().getName() +
                ", Имя:" + getName() +
                ", Тип чешуи: " + getSquama() +
                ", Возраст:" + getAge() +
                ", Вес:" + Math.round(getWeight() * 100) / 100.0 +
                ", Умеет летать:" + (isFly() ? "Да" : "Нет") +
                ", Умеет ходить:" + (isWalk() ? "Да" : "Нет") +
                ", Умеет плавать:" + (isSwim() ? "Да." : "Нет.") +
                ", Умеет ли плавать против течения:" + (isUpStreamSwim() ? "Да." : "Нет."));
    }
}

class Insect extends Animal {
    {
        setType("Насекомое");
        setName("Жук Жукыч");
        setWalk(true);
    }

    private int wingCount;
    private boolean likeJesus;

    public int getWingCount() {
        return wingCount;
    }

    public void setWingCount(int wingCount) {
        this.wingCount = wingCount;
    }

    public boolean isLikeJesus() {
        return likeJesus;
    }

    public void setLikeJesus(boolean likeJesus) {
        this.likeJesus = likeJesus;
    }

    public void ggggg() {
        System.out.println("Ggggg");
    }

    @Override
    public void display() {
        System.out.println("Тип:" + this.getClass().getName() +
                ", Имя:" + getName() +
                ", Количество крыльев: " + getWingCount() +
                ", Возраст:" + getAge() +
                ", Вес:" + Math.round(getWeight() * 100) / 100.0 +
                ", Умеет летать:" + (isFly() ? "Да" : "Нет") +
                ", Умеет ходить:" + (isWalk() ? "Да" : "Нет") +
                ", Умеет плавать:" + (isSwim() ? "Да." : "Нет.") +
                ", Умеет ли ходить по воде:" + (isLikeJesus() ? "Да." : "Нет."));
    }
}