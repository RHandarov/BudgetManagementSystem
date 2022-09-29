import exceptions.FilePermissionException;
import log.Log;

import java.io.FileNotFoundException;
import java.text.ParseException;
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

    private void printOperationsInfo() {
        System.out.println("1. Add new entity");
        System.out.println("2. Exit");
    }

    private int getOperationType() {
        System.out.print("Please enter operation number from list below: ");
        return scanner.nextInt();
    }

    private void printGoodbyeGreeting() {
        System.out.println("Thank you for using our app!");
    }

    private void printInvalidOperationTypeMessage(int operationType) {
        System.out.println("ERROR: Operation with number " + operationType + " does not exists! Please try again!");
    }

    private void printException(Exception exception) {
        System.out.print("ERROR: ");
        System.out.print(exception.getMessage());
        System.out.println(" Please try again!");
    }

    public void run() throws FileNotFoundException, FilePermissionException {
        this.authorize();

        while (true) {
            this.printOperationsInfo();
            int operationType = this.getOperationType();

            if (operationType == 1) {
                this.scanner.nextLine();
                System.out.print("Enter name of the entity: ");
                String name = this.scanner.nextLine();

                System.out.print("Enter entity's date (dd-mm-yyyy): ");
                String date = this.scanner.next();

                System.out.print("Enter entity's type (P or E): ");
                char type = this.scanner.next().charAt(0);

                System.out.print("Enter entity's amount of money: ");
                double moneyAmount = this.scanner.nextDouble();

                try {
                    try {
                        Log entity = new Log(name, date, type, moneyAmount);
                        FileManager.getInstance().saveLog(entity);
                        System.out.println("The entity has been successfully saved!");
                    } catch (Exception exception) {
                        this.printException(exception);
                    }
                } catch (Exception exception) {
                    this.printException(exception);
                }
            } else if (operationType == 2) {
                this.printGoodbyeGreeting();
                break;
            } else {
                this.printInvalidOperationTypeMessage(operationType);
            }
        }
    }
}
