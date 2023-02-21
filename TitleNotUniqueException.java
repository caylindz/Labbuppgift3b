package model;

public class TitleNotUniqueException extends RuntimeException{

    public TitleNotUniqueException(){
    }

    public TitleNotUniqueException(String msg){
        throw new RuntimeException(msg);
    }

}
