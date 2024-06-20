package soundwave.views;

import com.fasterxml.jackson.databind.JsonNode;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import soundwave.api.SpotifyWebApi;
import soundwave.util.ClearConsole;
import soundwave.util.Dotenv;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

public class Dashboard {
    private String halaman;
    private ClearConsole console;

    public Dashboard() {}

    public void console() {
        try {
            UserInterface ui = new UserInterface();
            Scanner input = new Scanner(System.in);

            while(true) {
                ClearConsole.clear();
                ui.banner("Welcome to Soundwave");
                System.out.println("1. Registration");
                System.out.println("2. Login");
                System.out.println("3. Spotify");

                System.out.print("Pilih salah satu halaman: ");
                this.halaman = input.nextLine();
                ClearConsole.clear();

                if (this.halaman.equalsIgnoreCase("registration") || this.halaman.equalsIgnoreCase("register") || this.halaman.equalsIgnoreCase("1") ) {
                    new Registration().console();
                }

                if (this.halaman.equalsIgnoreCase("login") || this.halaman.equalsIgnoreCase("2") ) {
                    Login login = new Login();
                    login.console();

                    // admin mode
                    if (login.getIsRoot()) {
                        new Admin();
                    }

                    // user mode
                    if (!login.getIsRoot()) {
                        new UserDashboard();
                    }
                }

                if (this.halaman.equalsIgnoreCase("spotify") || this.halaman.equalsIgnoreCase("3") ) {
                    Dotenv app = new Dotenv();
                    String clientId = app.ENV("CLIENT_ID");
                    String redirectUri = app.ENV("REDIRECT_URI");
                    String scopes = "user-read-private user-read-email";

                    String url = "https://accounts.spotify.com/authorize?response_type=code"
                            + "&client_id=" + clientId
                            + "&scope=" + URLEncoder.encode(scopes)
                            + "&redirect_uri=" + URLEncoder.encode(redirectUri);

                    // login
                    System.out.println("Login Spotify: " + url);

                    input = new Scanner(System.in);
                    System.out.print("Authorization Code: ");
                    SpotifyWebApi spotify = new SpotifyWebApi(input.nextLine());

                    String bearer = spotify.getBearer();
                    JsonNode node = spotify.parseJson(spotify.getMusic(bearer, "4JOYnCnPtYxcfpmq5SEbwI"));

                    spotify.setSongsToMigrationTable(node);
                    new UserDashboard();
                }
            }

//            ui.banner("Terimakasih telah menggunakan program ini.");
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }

    }
}
