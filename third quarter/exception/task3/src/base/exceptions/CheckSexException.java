package base.exceptions;

public class CheckSexException extends RuntimeException{
    public CheckSexException() {
        super("Sex's value is empty!!!");
    }
}
