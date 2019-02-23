package pl.edu.pg;

import java.util.concurrent.ThreadLocalRandom;

import static pl.edu.pg.CatsAndMiceGame.B_S_SQR;

public class Cat extends Animal {

    public Cat() {
        this.fieldNumber = ThreadLocalRandom.current().nextInt(0, B_S_SQR); // assign a random field
    }

    @Override
    public void move() {
        int disloc = changeRow();
        while (disloc == 0) {
            disloc += changeColumn();
        }
        this.fieldNumber += disloc;
    }
}
