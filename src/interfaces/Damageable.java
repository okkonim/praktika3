package interfaces;

public interface Damageable {
    int getHit(Damager damager);

    void setHealth(int health);

    int getHealth();

    String getName();

}
