package map;

import objects.gamingCreatures.Creature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {
    List<Creature> objectsOnCell;

    Cell() {
        objectsOnCell = new ArrayList<>();
    }


    public List getObjectsOnCell() {
        return objectsOnCell;
    }

    public void addObjectOnCell(Creature gameObject) {
            objectsOnCell.add(gameObject);

    }


    public void removeDublicants() {
        Set<Creature> set = new HashSet<>(objectsOnCell);
        objectsOnCell.clear();
        objectsOnCell.addAll(set);
    }

    public void removeObjectFromCell(Creature object) {
        objectsOnCell.remove(object);
    }
}
