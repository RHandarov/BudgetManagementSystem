import exceptions.FilePermissionException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleManager {
    private static ConsoleManager INSTANCE = null;

    public static ConsoleManager getInstance() {
        if (ConsoleManager.INSTANCE == null) {
            ConsoleManager.INSTANCE = new ConsoleManager();
        }

        return ConsoleManager.INSTANCE;
    }

    private final Scanner scanner = new Scanner(System.in);

    private ConsoleManager() {

    }

    private void authorize() throws FileNotFoundException, FilePermissionException {
        PasswordManager manager = PasswordManager.getInstance();

        while (true) {
            System.out.print("Please enter password: ");
            String password = this.scanner.next();

            if (manager.isPasswordCorrect(password)) {
                System.out.println("Password is correct! Welcome!");
                break;
            } else {
                System.out.println("Password is wrong! Please try again!");
            }
        }
    }

    public void run() throws FileNotFoundException, FilePermissionException {
        this.authorize();
    }
}
