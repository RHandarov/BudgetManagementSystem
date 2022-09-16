public class Startup {
    public static void main(String[] args) {
        try {
            ConsoleManager.getInstance().run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
