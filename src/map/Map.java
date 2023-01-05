package map;

public class Map {
    public Cell[][] cells = new Cell[5][5];

    public Map() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
}
