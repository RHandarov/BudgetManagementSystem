import exceptions.FilePermissionException;
import log.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileManager {
    private static final String HOME_DIR_PATH = "src/main/java/";
    private static final char LOG_DELIMITER = ';';

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
        File passwordFile = new File(FileManager.HOME_DIR_PATH + "password.txt");

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

    private String getLogFileName(Log log) {
        String logDate = log.getDateAsString();
        return "log-" + logDate.substring(logDate.indexOf('-') + 1) + ".txt";
    }

    public void saveLog(Log log) throws IOException {
        File logFile = new File(FileManager.HOME_DIR_PATH + "logs/" + this.getLogFileName(log));

        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        FileWriter writer = new FileWriter(logFile, true);

        writer.write(log.getName());
        writer.write(FileManager.LOG_DELIMITER);
        writer.write(log.getDay());
        writer.write(FileManager.LOG_DELIMITER);
        writer.write(log.getType().toString());
        writer.write(FileManager.LOG_DELIMITER);
        writer.write(log.getMoneyAmount().toString());
        writer.write('\n');

        writer.close();
    }
}
