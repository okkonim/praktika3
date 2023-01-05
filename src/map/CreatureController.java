package map;

import main.GameLogic;
import objects.gamingCreatures.Mob;
import objects.gamingCreatures.Player;

import java.util.List;

public class CreatureController extends Thread {
     Mob copyMob;
     Player copyPlayer;

    public CreatureController(Player player, Mob mob) {
        copyMob = mob;
        copyPlayer = player;
    }

    public void run(GameLogic gameLogic, List<Mob> fight) {
        if (copyMob.getHealth() > 0) {
            System.out.println("КЛЕТКА (" + copyPlayer.position.getX() + "; " + copyPlayer.position.getY() + ")");
            gameLogic.fightBetween(copyPlayer, copyMob);
        }
    }
}
