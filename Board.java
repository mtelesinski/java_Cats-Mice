package pl.edu.pg;

import static pl.edu.pg.CatsAndMiceGame.BOARD_SIZE;
import static pl.edu.pg.CatsAndMiceGame.B_S_SQR;

public class Board {
    private BoardField[] boardFields = new BoardField[B_S_SQR]; // the Board contains a list of BoardFields

    public Board() {
        for (int i = 0; i < B_S_SQR; i++) {
            BoardField aBoardField = new BoardField(); // create a BoardField
            this.boardFields[i] = aBoardField; // add it to the list
        }
    }

    public BoardField[] getBoardFields() {
        return boardFields;
    }

    public void displayBoard() {
        int k = 0;
        System.out.println(); // empty line
        // display a symbol of each field in BOARD_SIZE rows and BOARD_SIZE columns (separated by a space)
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(boardFields[k].getSymbol()+" ");
                k++;
            }
            System.out.println();
        }
    }
}
