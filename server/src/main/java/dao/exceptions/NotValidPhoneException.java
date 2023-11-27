package dao.exceptions;

public class NotValidPhoneException extends RuntimeException{
    public NotValidPhoneException(String message) {
        super(message);
    }
}
