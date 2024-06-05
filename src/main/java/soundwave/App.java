package soundwave;

import soundwave.util.ClearConsole;
import soundwave.util.Dotenv;
import soundwave.util.Logger;
import soundwave.views.Dashboard;
import soundwave.views.Login;

import java.io.IOException;

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
 * - Authentication                             [DONE]
 * - Interactive Console & Animation
 * - Built-in dotenv                            [DONE]
 * - Exception handler & logger                 [DONE]
 * - Automation testing
 * - Scanner DI
 *
 * Features:
 * - Playlist (Music name, Artist, Genre)
 * - User Profile
 *
 * Started from June 3, 2024
 */
public class App {
    public static void main( String[] args ) {
        Dashboard dashboard = new Dashboard("Welcome to Soundwave");

        dashboard.console();
    }
}
