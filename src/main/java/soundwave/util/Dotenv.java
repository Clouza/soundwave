package soundwave.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Dotenv allows reading values for variables stored in .env file.
 * <p>
 * Please remember that .env is stored in the project root for easier reading.
 * <p>
 * It's not mandatory, you can change it if you want :D
 */
public class Dotenv {
    private String env = ".env";

    public Dotenv() {}
    public Dotenv(String envFileName) {
        this.env = envFileName;
    }

    /**
     * Read value in .env file
     *
     * @param key
     * @return value of key
     * @throws IOException
     */
    public final String ENV(String key) {
        Properties properties = new Properties();

        try (FileInputStream fs = new FileInputStream(this.env)) {
            properties.load(fs);
            return properties.getProperty(key);
        } catch (IOException exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }

        return key;
    }
}
