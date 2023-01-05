package main;

import map.Map;
import objects.gameItems.Armor;
import objects.gameItems.Weapon;
import objects.gamingCreatures.Mob;
import objects.gamingCreatures.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoop {


    public static void main(String[] args) {


        Map map = new Map();

        Random ran = new Random();
        int r;
        r = ran.nextInt(1, 7);

        Player player = new Player("Игрок");
        player.setPlayerArmor(new Armor());
        player.setPlayerWeapon(new Weapon());
        map.cells[player.position.getX()][player.position.getY()].addObjectOnCell(player);

        Mob[] enemies = new Mob[7];
        List<Mob> fight = new ArrayList<>();

        for (int count = 0; count < r; count++) {
            enemies[count] = new Mob();
        }

        for (int i = 0; i < r; i++) {
            enemies[i].setMobWeapon(new Weapon());
            enemies[i].addCreatureController(player);
            Random random = new Random();
            int x = random.nextInt(5);
            int y = random.nextInt(5);
            map.cells[x][y].addObjectOnCell(enemies[i]);
            fight.add(enemies[i]);
        }
        GameLogic gameLogic = new GameLogic(fight);
        System.out.println("Противников на карте - " + fight.size());

        while (player.getHealth() > 0 && gameLogic.counterWin != 0) {
            gameLogic.movement(player, map.cells, fight);
            if (map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().size() <= 2 && map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().size() > 1) {
                for (int i = 0; i < map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().size(); i++) {
                    if (map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().get(i) instanceof Mob) {
                        Mob mob = (Mob) map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().get(i);
                        mob.getCreatureController().run(gameLogic, fight);
                    }
                }
            }
            if (map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().size() > 2) {
                Mob mob;
                int pointer = -1;
                for (int i = 0; i < map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().size(); i++) {
                    int maximum = 0;
                    if (map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().get(i) instanceof Mob) {
                        mob = (Mob) map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().get(i);
                        if (maximum < mob.getDamagePerSecond()) {
                            maximum = mob.getDamagePerSecond();
                            pointer = i;
                        }
                    }
                }
                for (int i = 0; i < map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().size(); i++) {
                    if (map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().get(i) instanceof Mob) {
                        mob = (Mob) map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().get(i);
                        mob.setDamagePerSecond(mob.getDamagePerSecond() + 1);
                    }
                }
                if (pointer != -1) {
                    if (map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().get(pointer) instanceof Mob) {
                        mob = (Mob) map.cells[player.position.getX()][player.position.getY()].getObjectsOnCell().get(pointer);
                        mob.getCreatureController().run(gameLogic, fight);
                    }
                }
            }
            if (gameLogic.counterWin == 0 && player.getHealth() > 0) {
                System.out.println("Поздравляем! Игрок победил.");
            }
        }
    }
}