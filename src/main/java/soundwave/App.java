package soundwave;

import soundwave.repository.Seeder;
import soundwave.views.Dashboard;

/**
 * Soundwave Project
 *
 * TODO :
 * - Develop Interface & Program Flow           [DONE]
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

        Dashboard dashboard = new Dashboard();
        dashboard.console();
    }
}
