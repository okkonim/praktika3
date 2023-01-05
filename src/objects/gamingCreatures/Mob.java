package objects.gamingCreatures;

import interfaces.Attacker;
import interfaces.Damageable;
import interfaces.Damager;
import interfaces.Lootable;
import map.CreatureController;
import objects.gameItems.Armor;
import objects.gameItems.EquipmentCell;
import objects.gameItems.Item;
import objects.gameItems.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mob extends Creature implements Lootable, Attacker, Damageable {
    private final Weapon fists = new Weapon("Кулаки", 1, 1);
    private final EquipmentCell<Weapon> mobWeapon = new EquipmentCell<>(fists);
    String[] nazvaniee = {
            "Орк", "Эльф", "Вампир",
            "Слизень", "Дикая собака", "Дикий кот",
            "Гоблин", "Бандит", "Фея",
            "Кабан"};
    CreatureController creatureController;
    private int damagePerSecond;

    public Mob() {
        super();
        Random random = new Random();
        long a = random.nextInt(10);
        this.setName(nazvaniee[(int) a]);
        this.health = random.nextInt(10, 15);
        int defence = random.nextInt(10);
        int damage = random.nextInt(10);
        int attackPerSecond = random.nextInt(5);
        this.damagePerSecond = (damage + this.mobWeapon.getEquipment().getDamage() )* attackPerSecond;
        this.setIdentificator();

    }

    @Override
    public void attackTo(Damageable target) {
        target.setHealth(target.getHit(this.mobWeapon.getEquipment()));
    }

    @Override
    public int getHit(Damager damager) {
        int damagerDamage = damager.getDamage();
        this.health = this.health - damagerDamage;
        this.setHealth(this.health);
        return this.getHealth();
    }

    public int getDamagePerSecond() {
        return (this.damagePerSecond);
    }

    public void setDamagePerSecond(int damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    public void setMobWeapon(Weapon weapon) {
        this.mobWeapon.setEquipment(weapon);
    }

    public List<Item> dropLoot() {
        List<Item> mobLoot = new ArrayList<>();
        int numberOfItems = (int) (Math.round(Math.random() % 4));
        for (int b = 0; b < numberOfItems; b++) {
            int type = (int) (Math.round(Math.random() % 2));
            switch (type) {
                case 0: {
                    Armor lootArmor = new Armor();
                    mobLoot.add(lootArmor);
                }
                case 1: {
                    Weapon lootWeapon = new Weapon();
                    mobLoot.add(lootWeapon);
                }
            }
        }
        if (mobLoot.isEmpty()) {
            System.out.println("Из противника " + this.name + " ничего не выпало.");
        } else {
            System.out.println("Из противника " + this.name + " выпали предметы:");
            for (Item item : mobLoot) {
                System.out.println(item.getName());
            }
        }
        return mobLoot;
    }

    public void addCreatureController(Player player) {
        creatureController = new CreatureController(player, this);
    }

    public CreatureController getCreatureController() {
        return creatureController;
    }
}