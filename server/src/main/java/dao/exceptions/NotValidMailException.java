package dao.exceptions;

public class NotValidMailException extends RuntimeException{
    public NotValidMailException(String message) {
        super(message);
    }
}
