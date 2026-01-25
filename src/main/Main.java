import java.io.*;
import java.time.LocalDateTime;

public class Main {

    private static final String LOG_FILE = "/app/data/app.log";

    public static void main(String[] args) {
        File dataDir = new File("/app/data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        // 1. Write to the log file
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("Application started at: "
                    + LocalDateTime.now() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Read and print the log file
        System.out.println("---- Log file contents ----");
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------");
    }
}