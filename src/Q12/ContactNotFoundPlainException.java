package Q12;

public class ContactNotFoundPlainException extends Exception {
    @Override
    public String getMessage() {
        return "Contact inconnu";
    }
}
