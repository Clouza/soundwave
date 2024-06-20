package soundwave.repository;

import soundwave.util.Dotenv;
import soundwave.util.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public SQLConnection() {
        Connection conn = null;
        try {
            String url = new Dotenv().ENV("DATABASE_NAME");
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException exception) {
                Logger.log(exception);
                exception.printStackTrace();
            }
        }
    }
}
