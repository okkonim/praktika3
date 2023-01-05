package objects.gamingCreatures;

import map.Position;

import java.util.Random;

public class Creature {

    protected String name;
    protected int identificator;
    int idCounter = 1;

    public Creature() {
    }

    public Creature(String name) {
        this.name = name;
        this.setIdentificator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdentificator() {
        this.identificator = idCounter;
        idCounter++;
    }

    public int getIdentificator() {
        return identificator;
    }

    public void setIdentificator(int id) {
        this.identificator = id;
    }

    protected int health;

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public Position position = new Position();
    int oldX;
    int oldY;

    public void go() {
        oldX = this.position.getX();
        oldY = this.position.getY();
        Random ran = new Random();
        int way = ran.nextInt(4);
        switch (way) {
            case 0 -> {
                this.position.goNorth();
            }
            case 1 -> {
                this.position.goSouth();
            }
            case 2 -> {
                this.position.goEast();
            }
            case 3 -> {
                this.position.goWest();
            }
        }
        System.out.println(this.getName() + " передвигается с клетки (" + oldX + ":" + oldY + ")  на клетку (" + this.position.getX() + ":" + this.position.getY() + ") ");
    }
}
