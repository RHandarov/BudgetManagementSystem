public class ConsoleManager {
    private static ConsoleManager INSTANCE = null;

    public static ConsoleManager getInstance() {
        if (ConsoleManager.INSTANCE == null) {
            ConsoleManager.INSTANCE = new ConsoleManager();
        }

        return ConsoleManager.INSTANCE;
    }

    private ConsoleManager() {

    }

    public void run() {
        System.out.println("Hello world!");
    }
}
