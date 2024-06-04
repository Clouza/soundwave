package soundwave;

import com.github.javafaker.Bool;
import soundwave.util.Dotenv;
import soundwave.util.Logger;

/**
 * Soundwave Project
 *
 * TODO (develop, max. June 10, 2024):
 * - Develop Interface & Program Flow
 * - Spotify API Integration
 * - Database Integration: SQLite
 * - Faker Identity Generator                   [DONE]
 * - Audio Stream
 * - Port Tunneling (Remote Access)
 * - User Identity & Access Management (IAM)
 * - Interactive Console & Animation
 * - Built-in dotenv                            [DONE]
 * - Exception handler & logger                 [DONE]
 * - Automation testing
 *
 * Features:
 * - Playlist (Music name, Artist, Genre)
 * - User Profile
 *
 * Started from June 3, 2024
 */
public class App {
    public static void main( String[] args ) {
        try {
            Dotenv app = new Dotenv();
            boolean isInteractive = Boolean.parseBoolean(app.ENV("INTERACTIVE_MODE"));

            if (!isInteractive) {
                basic();
            }

            if (isInteractive) {
                interactive();
            }
        } catch (Exception exception) {
            new Logger().log(exception);
            exception.printStackTrace();
        }
    }

    private static void basic() {

    }

    private static void interactive() {

    }
}
