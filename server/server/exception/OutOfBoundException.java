package exception;

public class OutOfBoundException extends RuntimeException {
    public OutOfBoundException() {
        super("check the index. it's out of bound!");
    }
}

