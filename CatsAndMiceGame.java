package pl.edu.pg;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit; // needed for the delay between the consecutive turns of the simulations

public class CatsAndMiceGame {
    // CONSTANTS:
    public static final int BOARD_SIZE = 9; // 2 < BOARD_SIZE < 100 (AN ODD NUMBER!)
    public static final int B_S_SQR = BOARD_SIZE * BOARD_SIZE; // used several times in the code so I preferred
                    // to calculate it here once and use as a constant instead of using the pow() function each time
    public static final int MAX_MICE = 4; // 1 < MAX_MICE < 100
    public static final int MAX_CATS = 4; // 1 < MAX_CATS < 100
    public static final int DRAW_BOARD_PERIOD = 3; // the board is displayed ever DRAW_BOARD_PERIOD turns
    public static final int DELAY = 1000; // the delay between the rounds in milliseconds

    // Counters:
    int mouseCounter = 0;
    int counter = 0; // round counter

    public void start() throws InterruptedException {
        System.out.println("################################");
        System.out.println("### A TALE OF CATS AND MICE ####");
        System.out.println("########### For Lucy ###########");
        System.out.println("################################");
        TimeUnit.MILLISECONDS.sleep(DELAY); // throws InterruptedException

        // Initialize the board:
        Board theBoard = new Board();
        int[] edge = new int[4 * (BOARD_SIZE - 1)]; // A list of fields on the edge of the board
        int[] inner = new int[B_S_SQR - 4 * BOARD_SIZE + 4]; // A list of fields in the inner part of the board
        int e = 0;
        int in = 0;
        for (int i = 0; i < B_S_SQR; i++) {
            // conditions to check whether a BoardField is on the edge of a Board:
            if (((i % BOARD_SIZE) == 0)
                    || ((i % BOARD_SIZE) == (BOARD_SIZE - 1))
                    || ((i > 0) && (i < (BOARD_SIZE - 1)))
                    || ((i > (B_S_SQR - BOARD_SIZE)) && (i < (B_S_SQR - 1)))) {
                edge[e] = i;
                e++;
            } else {
                inner[in] = i;
                in++;
            }
        }

        // Initialize the cats:
        ArrayList<Cat> cats = new ArrayList<>(); // create a list of cats
        for (int i = 0; i < MAX_CATS; i++) {
            Cat aCat = new Cat(); // create a new cat
            int rnd = ThreadLocalRandom.current().nextInt(0, B_S_SQR - 4 * BOARD_SIZE + 4);
            aCat.setFieldNumber(inner[rnd]); // assign a random field from the inner part of the board to the cat
            cats.add(i, aCat); // add the cat to the cats list
            theBoard.getBoardFields()[aCat.fieldNumber].setCat(true); // 'put' the cat on a field
        }

        // Initialize the mice:
        ArrayList<Mouse> mice = new ArrayList<>(); // create a list of mice
        for (int i = 0; i < MAX_MICE; i++) {
            Mouse aMouse = new Mouse(i); // create a new mouse
            int rnd = ThreadLocalRandom.current().nextInt(0, 4 * (BOARD_SIZE - 1));
            aMouse.setFieldNumber(edge[rnd]); // assign a random field from the edge of the board to the mouse
            mouseCounter++; // increase the mouseCounter
            mice.add(i, aMouse); // add the mouse to the mice list
            theBoard.getBoardFields()[aMouse.fieldNumber].setMouse(true); // 'put' the mouse on a field
        }

        // Put cheese on the middle field of the board ((B_S_SQR - 1) / 2):
        theBoard.getBoardFields()[(B_S_SQR - 1) / 2].setCheese(true);

        // Display the initial situation on the board:
        for (int i = 0; i < B_S_SQR; i++) {
            theBoard.getBoardFields()[i].setSymbol();
        }
        theBoard.displayBoard();
        System.out.println();
        TimeUnit.MILLISECONDS.sleep(DELAY); // throws InterruptedException

        // The simulation:
        // conditions to keep the simulation running:
        while (mouseCounter > 0 && theBoard.getBoardFields()[(B_S_SQR - 1) / 2].getCheese()) {
            System.out.println("Round "+(counter+1)+" - "+mouseCounter+" mice left.");
            System.out.println();

            // Mice' move:
            for (int i = 0; i < mouseCounter; i++) {
                Mouse aMouse = mice.get(i); // get a mouse
                System.out.print("Mouse " + (aMouse.getId() + 1) + ": (" + (aMouse.getFieldNumber() / BOARD_SIZE + 1)
                        + ", " + (aMouse.getFieldNumber() % BOARD_SIZE + 1) + ") -> ("); // display its position
                theBoard.getBoardFields()[aMouse.fieldNumber].setMouse(false); // 'take' the mouse from the field
                aMouse.move(); // move the mouse
                theBoard.getBoardFields()[aMouse.fieldNumber].setMouse(true); // 'put' the mouse on the field
                System.out.println((aMouse.getFieldNumber() / BOARD_SIZE + 1) + ", "
                        + (aMouse.getFieldNumber() % BOARD_SIZE + 1) + ")"); // display its new position
            }

            // Cats' move:
            for (int i = 0; i < MAX_CATS; i++) {
                Cat aCat = cats.get(i); // get a cat
                System.out.print("Cat " + (i + 1) + ": (" + (aCat.getFieldNumber() / BOARD_SIZE + 1) + ", "
                        + (aCat.getFieldNumber() % BOARD_SIZE + 1) + ") -> ("); // display its position
                // row number: fieldNumber / BOARD_SIZE + 1
                // column number: fieldNumber % BOARD_SIZE +1
                theBoard.getBoardFields()[aCat.fieldNumber].setCat(false); // 'take' the cat from the field
                aCat.move(); // move the cat
                theBoard.getBoardFields()[aCat.fieldNumber].setCat(true); // 'put' the cat on the field
                System.out.println((aCat.getFieldNumber() / BOARD_SIZE + 1) + ", "
                        + (aCat.getFieldNumber() % BOARD_SIZE + 1) + ")"); // display its new position
            }

            // Eat a mice:
            // check all the cats and mice for the same field number:
            for (int i = 0; i < MAX_CATS; i++){
                for (int j = 0; j < mouseCounter; j++) {
                    if (cats.get(i).getFieldNumber() == mice.get(j).getFieldNumber()) {
                        theBoard.getBoardFields()[mice.get(j).getFieldNumber()].setMouse(false); // 'take the mouse
                                                                                                    // from the field
                        mice.remove(j); // remove the mouse from the list
                        mouseCounter--; // decrease the mouseCounter
                        System.out.println("Cat "+(i+1)+" caught Mouse "+(j+1)); // display the information
                    }
                }
            }

            // Eat the cheese:
            // check if any mouse is on the middle field:
            for (int i = 0; i < mouseCounter; i++) {
                if (mice.get(i).getFieldNumber() == ((B_S_SQR - 1) / 2)) {
                    theBoard.getBoardFields()[(B_S_SQR - 1) / 2].setCheese(false); // 'remove' the cheese
                    System.out.println("Mouse "+(mice.get(i).getId()+1)+" reached the cheese!"); // display the info
                }
            }

            // Set the fields' symbols:
            for (int i = 0; i < B_S_SQR; i++) {
                theBoard.getBoardFields()[i].setSymbol();
            }
            // Incrementing the counter:
            counter++;

            // Display the board:
            if (counter % DRAW_BOARD_PERIOD == 0) { // every DRAW_BOARD_PERIOD rounds
                theBoard.displayBoard();
            }

            // Displaying the result:
            if (mouseCounter == 0 && theBoard.getBoardFields()[(B_S_SQR - 1) / 2].getCheese() == false) {
                System.out.println("\n ### IT'S A DRAW! ###");
            } else if (mouseCounter == 0) {
                System.out.println("\n ### CATS WON! ###");
            } else if (theBoard.getBoardFields()[(B_S_SQR - 1) / 2].getCheese() == false) {
                System.out.println("\n ### MICE WON! ###");
            }

            // The delay between consecutive turns of the simulation (in milliseconds):
            TimeUnit.MILLISECONDS.sleep(DELAY); // throws InterruptedException
            System.out.println();
        }
    }
}
