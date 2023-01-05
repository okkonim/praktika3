package main;

import interfaces.Attacker;
import interfaces.Damageable;
import map.Cell;
import objects.gamingCreatures.Mob;
import objects.gamingCreatures.Player;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    public int counterWin;

    public GameLogic(List<Mob> fight) {
        this.counterWin = fight.size();
    }

    public static void fightOf(Attacker attacker, Damageable target) {
        attacker.attackTo(target);
        if (target.getHealth() > 0)
            System.out.println("Здоровье " + target.getName() + " после атаки: " + target.getHealth());
        else {
            System.out.println("\nЗдоровье " + target.getName() + " ниже нуля. " + target.getName() + " погиб.");
        }
    }

    public void fightBetween(Player player, Mob mob) {
        if (player.getHealth() > 0) {
            System.out.println("Схватка между " + player.getName() + " и " + mob.getName() + "\n");
            System.out.println("Здоровье игрока на данный момент: " + player.getHealth());
            System.out.println("Здоровье " + mob.getName() + " на данный момент: " + mob.getHealth());
            while (mob.getHealth() > 0) {
                GameLogic.fightOf(player, mob);
                if (mob.getHealth() <= 0) {
                    player.takeLoot(mob.dropLoot());
                    counterWin--;
                    player.LvlCheck();
                    player.takeTheBestWeapon();
                    player.takeTheBestArmor();
                }
                GameLogic.fightOf(mob, player);
                if (player.getHealth() <= 0) {
                    System.out.println("Игрок умер. Игра окончена.");
                }
            }

        } else {
            System.out.println("Игрок умер. Игра окончена.");

        }
    }

    public void movement(Player player, Cell[][] cells, List<Mob> fight) {
        List<Mob> toRemove = new ArrayList<>();

        for (Mob mob1 : fight) {
            if (mob1.getHealth() > 0) {
                cells[mob1.position.getX()][mob1.position.getY()].removeObjectFromCell(mob1);
                mob1.go();
                cells[mob1.position.getX()][mob1.position.getY()].addObjectOnCell(mob1);
            } else {
                toRemove.add(mob1);
                cells[player.position.getX()][player.position.getY()].removeObjectFromCell(toRemove.get(0));
            }
        }

        cells[player.position.getX()][player.position.getY()].removeObjectFromCell(player);
        player.go();
        cells[player.position.getX()][player.position.getY()].addObjectOnCell(player);


        fight.removeAll(toRemove);

    }

}

