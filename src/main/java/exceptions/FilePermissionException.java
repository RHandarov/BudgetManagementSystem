package exceptions;

public class FilePermissionException extends Exception {
    public FilePermissionException() {
    }

    public FilePermissionException(String message) {
        super(message);
    }
}
