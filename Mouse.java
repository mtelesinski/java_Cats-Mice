package pl.edu.pg;

public class Mouse extends Animal {
    // Mouse number (independent of the position on the mouse list - in case a mouse in front of it on the list gets eaten)
    private int id;

    public Mouse(int mouseId) {
        this.id = mouseId;
    }

    public int getId() { return id; }

    @Override
    public void move() {
        int disloc = 0;
        while (disloc == 0) {
            disloc += changeRow();
            disloc += changeColumn();
        }
        fieldNumber += disloc;
    }
}
