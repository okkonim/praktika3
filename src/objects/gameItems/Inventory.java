package objects.gameItems;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T extends Item> {
    public List<T> inventory = new ArrayList<>();
}