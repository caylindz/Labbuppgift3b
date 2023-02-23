package model;

public enum Prio {
    High(0), Medium(1), Low(2) ;

    public int getValue(){
        return value;
    }

    private final int value;

    private Prio(int value){
        this.value  = value;
    }
}
