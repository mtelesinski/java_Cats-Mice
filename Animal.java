package pl.edu.pg;

import java.util.concurrent.ThreadLocalRandom;

import static pl.edu.pg.CatsAndMiceGame.BOARD_SIZE;
import static pl.edu.pg.CatsAndMiceGame.B_S_SQR;

public abstract class Animal {
    // an animal's position:
    protected int fieldNumber;
    public int vertDisloc; // a variable needed for the changeRow function

    public void setFieldNumber(int aFieldNumber) {
        this.fieldNumber = aFieldNumber;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public abstract void move();

    public int changeRow() { // vertical movement
        if ((fieldNumber > BOARD_SIZE)
                && (fieldNumber < (B_S_SQR - BOARD_SIZE))) {
            vertDisloc = ThreadLocalRandom.current().nextInt(-1, 2);
        } else if (fieldNumber < BOARD_SIZE) {
            vertDisloc = ThreadLocalRandom.current().nextInt(0, 2);
        } else if (fieldNumber >= (B_S_SQR - BOARD_SIZE)) {
            vertDisloc = ThreadLocalRandom.current().nextInt(-1, 1);
        }
        return vertDisloc * BOARD_SIZE;
    }

    public int changeColumn() { // horizontal movement
        if ((fieldNumber % BOARD_SIZE != 0) && (fieldNumber % BOARD_SIZE != (BOARD_SIZE-1))) {
            return ThreadLocalRandom.current().nextInt(-1, 2);
        } else if (fieldNumber % BOARD_SIZE == 0) {
            return ThreadLocalRandom.current().nextInt(0, 2);
        } else {
            return ThreadLocalRandom.current().nextInt(-1, 1);
        }
    }

}