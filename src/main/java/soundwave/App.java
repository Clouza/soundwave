package soundwave;

import soundwave.repository.Migration;
import soundwave.repository.Seeder;
import soundwave.views.Dashboard;

/**
 * Soundwave Project
 *
 * TODO :
 * - Develop Interface & Program Flow           [DONE]
 * - Spotify API Integration                    [DONE]
 * - Database Integration: SQLite               [DONE]
 * - Faker Identity Generator                   [DONE]
 * - Port Tunneling (Remote Access)
 * - Simple User IAM                            [DONE]
 * - Authentication                             [DONE]
 * - Interactive Console
 * - Built-in dotenv                            [DONE]
 * - Exception handler & logger                 [DONE]
 * - Automation testing                         [DONE]
 *
 * Features:
 * - Playlist (Music name, Artist, Genre)
 * - User Profile
 *
 * Detail Menu:
 * - Music
 *      - Tampil Musik
 *      - Tambah Musik
 *      - Edit Musik
 *      - Hapus Musik
 *
 * - Artist
 *      - Tampil Artis
 *      - Tambah Artis
 *      - Edit Artis
 *      - Hapus Artis
 *
 * - Genre
 *      - Tampil Genre
 *      - Tambah Genre
 *      - Edit Genre
 *      - Hapus Genre
 *
 * - Playlist
 *      - Tampil Playlist
 *      - Tambah Playlist
 *      - Edit Playlist
 *      - Hapus Playlist
 *
 * Started from June 3, 2024
 */
public class App {
    public static void main( String[] args ) {
        Seeder seed = new Seeder();
        seed.genreSeed();
        seed.artistSeed();
        seed.musicSeed();
        seed.userSeed();

        // initialize table
        new Migration();

        Dashboard dashboard = new Dashboard();
        dashboard.console();
    }
}
