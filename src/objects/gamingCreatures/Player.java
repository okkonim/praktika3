package objects.gamingCreatures;

import interfaces.Attacker;
import interfaces.Damageable;
import interfaces.Damager;
import objects.gameItems.*;

import java.util.List;
import java.util.Random;

public class Player extends Creature implements Attacker, Damageable {
    private final Inventory<Item> playerInventory = new Inventory<>();
    private final Weapon fists = new Weapon("Кулаки", 1, 1);
    private final Armor shirt = new Armor("Рубашка", 1, 1);
    private EquipmentCell<Weapon> playerWeapon = new EquipmentCell<>(fists);
    private EquipmentCell<Armor> playerArmor = new EquipmentCell<>(shirt);
    private int experience;
    private int curLvl;

    public Player(String name) {
        this.name = name;
        Random ran = new Random();
        this.experience = ran.nextInt(0, 100);
        this.curLvl = ran.nextInt(10);
        this.setHealth(ran.nextInt(30, 50));
        this.setIdentificator(0);
        System.out.println("Игрок создан! Текущий уровень: " + this.curLvl);
        System.out.println("Начальная экипировка игрока -  " + playerArmor.getEquipment().getName() + " и "+ playerWeapon.getEquipment().getName());
        position.setToZero();
    }

    @Override
    public int getHit(Damager damager) {
        int damagerDamage = damager.getDamage();
        this.health = this.health - damagerDamage;
        this.setHealth(this.health);
        return this.getHealth();
    }

    public void LvlCheck() {
        this.getExperienceFromMob();
        if (experience > 90) {
            experience = 0;
            this.lvlUp();
            System.out.println("Игрок повысил уровень! Текущий уровень: " + this.curLvl);
        }
    }

    public void getExperienceFromMob() {
        experience += 30;
    }

    public void lvlUp() {
        curLvl++;
    }

    @Override
    public void attackTo(Damageable target) {
        target.setHealth(target.getHit(this.playerWeapon.getEquipment()));
    }

    public boolean matchWeaponByLvl(int reqLvl) {
        return this.curLvl > reqLvl;
    }

    public void takeTheBestWeapon() {
        Inventory<Weapon> weaponInventory = new Inventory<>();
        int BestWeaponId = 0;
        for (int i = 0; i < playerInventory.inventory.size(); i++) {
            if ((playerInventory.inventory.get(i) instanceof Weapon) && this.matchWeaponByLvl(((Weapon) playerInventory.inventory.get(i)).getReqLvl())) {
                weaponInventory.inventory.add((Weapon) playerInventory.inventory.get(i));
            }
        }
        for (int i = 1; i < weaponInventory.inventory.size(); i++) {
            if (weaponInventory.inventory.size() <= 1) {
                playerWeapon = new EquipmentCell<>(weaponInventory.inventory.get(0));
            }
            if (weaponInventory.inventory.size() > 1) {
                if ((weaponInventory.inventory.get(i).getDamage() > weaponInventory.inventory.get(i - 1).getDamage())) {
                    BestWeaponId = weaponInventory.inventory.get(i).getIdentificator();
                }
            }
        }
        for (int i = 0; i < playerInventory.inventory.size(); i++) {
            if (playerInventory.inventory.get(i).getIdentificator() == BestWeaponId)
                playerWeapon = new EquipmentCell<>((Weapon) playerInventory.inventory.get(i));
        }
        System.out.println("Текущее оружие игрока -  " + playerWeapon.getEquipment().getName());
    }

    public boolean matchArmorByLvl(int reqLvl) {
        return (this.curLvl > reqLvl);
    }

    public void takeTheBestArmor() {
        Inventory<Armor> armorInventory = new Inventory<>();
        int BestArmorId = 0;
        for (int i = 0; i < playerInventory.inventory.size(); i++) {
            if ((playerInventory.inventory.get(i) instanceof Armor) && this.matchArmorByLvl(((Armor) playerInventory.inventory.get(i)).getReqLvl())) {
                armorInventory.inventory.add((Armor) playerInventory.inventory.get(i));
            }
        }
        for (int i = 1; i < armorInventory.inventory.size(); i++) {
            if (armorInventory.inventory.size() <= 1) {
                playerArmor = new EquipmentCell<>(armorInventory.inventory.get(0));
            }
            if (armorInventory.inventory.size() > 1) {
                if ((armorInventory.inventory.get(i).getProtection() > armorInventory.inventory.get(i - 1).getProtection())) {
                    BestArmorId = armorInventory.inventory.get(i).getIdentificator();
                }
            }
        }
        for (int i = 0; i < playerInventory.inventory.size(); i++) {
            if (playerInventory.inventory.get(i).getIdentificator() == BestArmorId)
                playerArmor = new EquipmentCell<>((Armor) playerInventory.inventory.get(i));
        }
        System.out.println("Текущая броня игрока -  " + playerArmor.getEquipment().getName());
    }

    public void takeLoot(List<Item> loot) {
        this.playerInventory.inventory.addAll(loot);
    }

    public void setPlayerArmor(Armor armor) {
        this.playerArmor.setEquipment(armor);
    }
    public void setPlayerWeapon(Weapon weapon) {
        this.playerWeapon.setEquipment(weapon);
    }


}