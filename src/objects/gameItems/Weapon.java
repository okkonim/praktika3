package objects.gameItems;

import interfaces.Damager;

import java.util.Random;


public class Weapon extends Equipment implements Damager {
    String[] nazvaniew = {
            "Облегченный меч", "Тяжелый меч", "Лук",
            "Копье", "Рапира", "Хлыст",
            "Сюрикены", "Саи", "Молот",
            "Нож"};
    private final int damage;

    public Weapon() {
        super();
        Random ran = new Random();
        int numberOfName = (int) (Math.round(Math.random() * 10) % 10);
        this.setName(nazvaniew[numberOfName]);
        int attack = ran.nextInt(2, 5);
        int reqLvl = ran.nextInt(10);
        this.damage = attack;
        this.reqLvl = reqLvl;
        this.setIdentificator();
    }

    public Weapon(String name, int req_lvl, int damage) {
        super(name, req_lvl);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }


}
