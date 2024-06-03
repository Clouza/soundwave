package soundwave.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Dotenv {
    private String env = ".env";

    public Dotenv() {}
    public Dotenv(String envFileName) {
        this.env = envFileName;
    }

    public final String APP_ENV(String key) {
        Properties properties = new Properties();
        try (FileInputStream fs = new FileInputStream(this.env)) {
            properties.load(fs);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return key;
    }
}
