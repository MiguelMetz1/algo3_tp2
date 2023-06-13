package edu.fiuba.algo3.Exceptions;

public class WrongPlayerName extends RuntimeException{
    public WrongPlayerName(String message){
        super(message);
    }
}
