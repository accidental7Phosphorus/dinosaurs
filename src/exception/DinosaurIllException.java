package exception;

public class DinosaurIllException extends Exception{
    public DinosaurIllException(String message) {
        super(message);
    }

    public DinosaurIllException(String message, Throwable cause) {
        super(message, cause);
    }
}
