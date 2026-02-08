import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    // Values match docker-compose.yml
    private static final String DB_URL =
            "jdbc:postgresql://postgres-db:5432/mydb";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {

        try {
            // 1. Connect to database
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            // 2. Create table
            statement.execute("""
                CREATE TABLE IF NOT EXISTS messages (
                    id SERIAL PRIMARY KEY,
                    content TEXT
                )
            """);

            // 3. Insert data
            statement.executeUpdate("""
                INSERT INTO messages (content)
                VALUES ('Hello from Java container!')
            """);

            // 4. Read data
            ResultSet rs = statement.executeQuery(
                    "SELECT id, content FROM messages");

            System.out.println("---- Messages in DB ----");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ": " + rs.getString("content"));
            }
            System.out.println("------------------------");

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}