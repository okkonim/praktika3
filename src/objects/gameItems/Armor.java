package objects.gameItems;

import java.util.Random;

public class Armor extends Equipment {
    int protection;
    String[] nazvaniea = {
            "Щит", "Шлем", "Кольчуга",
            "Нагрудник", "Мантия", "Поножи",
            "Тяжелые доспехи", "Облегченные доспехи", "Гарвеевская броня",
            "Чешуйчатая броня"};

    public Armor() {
        super();
        Random ran = new Random();
        this.setName(nazvaniea[(int) (Math.round(Math.random() * 10) % 10)]);

        int z = ran.nextInt(10);
        int b = ran.nextInt(10);
        this.setParameters(z, b);
        this.setIdentificator();
    }
    public Armor (String name, int reqLvl, int protection){
        this.setName(name);
        setParameters(protection,reqLvl);
    }

    public void setParameters(int protection, int req_lvl) {
        this.protection = protection;
        this.reqLvl = req_lvl;
    }

    public int getProtection() {
        return protection;
    }
}
