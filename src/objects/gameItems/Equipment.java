package objects.gameItems;

public class Equipment extends Item {
    int reqLvl;

    public Equipment() {
    }

    public Equipment(String name, int req_lvl) {
        super(name);
        this.reqLvl = req_lvl;
    }

    public int getReqLvl() {
        return reqLvl;
    }
}

