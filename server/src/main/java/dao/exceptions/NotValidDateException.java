package dao.exceptions;

public class NotValidDateException extends RuntimeException{
    public NotValidDateException(String message) {
        super(message);
    }
}
