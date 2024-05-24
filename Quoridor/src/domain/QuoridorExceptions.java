package domain;

import java.io.Serializable;

public class QuoridorExceptions extends Exception implements Serializable {


    public QuoridorExceptions(String message){
        super(message);
    }


}
