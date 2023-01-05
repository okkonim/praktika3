package objects.gameItems;

public class EquipmentCell<T extends Equipment> {
    private T equipment;

    public EquipmentCell(T equipment) {
        this.equipment = equipment;
    }

    public T getEquipment() {
        return equipment;
    }

    public void setEquipment(T equipment) {
        this.equipment = equipment;
    }


}
