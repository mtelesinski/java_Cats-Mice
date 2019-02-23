package pl.edu.pg;

public class BoardField {
    private boolean cat = false;
    private boolean mouse = false;
    private boolean cheese = false;
    private char symbol = '.'; // a symbol to display, not used yet

    public BoardField(){}

    public void setCat(boolean cat){
        this.cat = cat;
    }

    public void setMouse(boolean mouse){
        this.mouse = mouse;
    }

    public void setCheese(boolean cheese){
        this.cheese = cheese;
    }

    public void setSymbol(){
        if (cat == true){symbol = 'K';}
        else if (mouse == true){symbol = 'M';}
        else if (cheese == true){symbol = 'S';}
        else {symbol = '.';}
    }

    public boolean getCheese() { return cheese; }

    public char getSymbol() { return symbol; }
}
