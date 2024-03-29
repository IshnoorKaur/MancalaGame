package mancala;

public class NoSuchPlayerException extends Exception {
    private static final long serialVersionUID = 1L;

    public NoSuchPlayerException() {
        super("No such player found.");
    }

    public NoSuchPlayerException(final String message) {
        super(message);
    }
}
