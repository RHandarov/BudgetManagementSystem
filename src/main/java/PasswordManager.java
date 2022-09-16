import exceptions.FilePermissionException;

import java.io.FileNotFoundException;

public class PasswordManager {
    private static PasswordManager INSTANCE = null;

    public static PasswordManager getInstance() throws FileNotFoundException, FilePermissionException {
        if (PasswordManager.INSTANCE == null) {
            PasswordManager.INSTANCE = new PasswordManager();
        }

        return PasswordManager.INSTANCE;
    }

    private final String correctPassword;

    private PasswordManager() throws FileNotFoundException, FilePermissionException {
        this.correctPassword = FileManager.getInstance().getPassword();
    }

    public boolean isPasswordCorrect(String password) {
        return password.equals(this.correctPassword);
    }
}
