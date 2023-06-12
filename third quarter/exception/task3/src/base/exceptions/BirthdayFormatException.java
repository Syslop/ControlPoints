package base.exceptions;

public class BirthdayFormatException extends RuntimeException{
    public BirthdayFormatException() {
        super("Incorrect birthday's format!!!");
    }
}
