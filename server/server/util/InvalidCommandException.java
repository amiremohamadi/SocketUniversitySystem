package util;

// in case of passing invalid opcode
public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}

