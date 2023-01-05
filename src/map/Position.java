package map;

import java.util.Random;

public class Position {
    private int x;
    private int y;

    public Position() {
        Random random = new Random();
        setX(random.nextInt(5));
        setY(random.nextInt(5));
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setToZero() {
        this.setX(3);
        this.setY(3);
    }
    public void goNorth() {
        setY(getY() + 1);
        if (this.getY() > 4) {
            this.goSouth();
            this.goSouth();
        }
    }
    public void goSouth() {
        setY(getY() - 1);
        if (this.getY() < 0) {
            this.goNorth();
            this.goNorth();
        }
    }
    public void goEast() {
        setX(getX() + 1);
        if (this.getX() > 4) {
            goWest();
            goWest();
        }
    }
    public void goWest() {
        setX(getX() - 1);
        if (this.getX() < 0) {
            goEast();
            goEast();
        }
    }
}