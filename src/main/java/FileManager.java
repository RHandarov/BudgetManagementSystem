import exceptions.FilePermissionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
    private static FileManager INSTANCE = null;

    public static FileManager getInstance() {
        if (FileManager.INSTANCE == null) {
            FileManager.INSTANCE = new FileManager();
        }

        return FileManager.INSTANCE;
    }

    private FileManager() {

    }

    public String getPassword() throws FileNotFoundException, FilePermissionException {
        File passwordFile = new File("src/main/java/password.txt");

        if (!passwordFile.exists()) {
            throw new FileNotFoundException("File password.txt does not exists! Please create it and try again!");
        }

        if (!passwordFile.canRead()) {
            throw new FilePermissionException("File password.txt cannot be read!");
        }

        Scanner scanner = new Scanner(passwordFile);

        if (!scanner.hasNext()) {
            scanner.close();
            throw new IllegalStateException("File password.txt should not be empty!");
        }

        String password = scanner.next();
        scanner.close();
        return password;
    }
}
