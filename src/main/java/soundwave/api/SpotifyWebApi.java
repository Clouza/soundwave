package soundwave.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import soundwave.model.Music;
import soundwave.model.Playlist;
import soundwave.repository.Migration;
import soundwave.util.Dotenv;
import soundwave.util.Logger;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class SpotifyWebApi {
    private String bearer;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String username = "";

    public SpotifyWebApi(String authorizationCode) {
        try {
            Dotenv app = new Dotenv();

            String code = authorizationCode;
            String clientId = app.ENV("CLIENT_ID");
            String clientSecret = app.ENV("CLIENT_SECRET");
            String redirectUri = app.ENV("REDIRECT_URI");

            String credentials = clientId + ":" + clientSecret;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://accounts.spotify.com/api/token"))
                    .header("Authorization", "Basic " + encodedCredentials)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&code=" + code + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            this.bearer = this.extractAccessToken(response.body());
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    public String getBearer() {
        return this.bearer;
    }

    public String getPlaylist(String accessToken) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.spotify.com/v1/me/playlists"))
                    .header("Authorization", "Bearer " + accessToken)
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }

        return "{}";
    }

    public String getMusic(String accessToken, String playlistId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks"))
                    .header("Authorization", "Bearer " + accessToken)
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }

        return "";
    }

    public void getSongs(JsonNode rootNode) {
        rootNode.fields().forEachRemaining(node -> {
            if (node.getKey().equalsIgnoreCase("items")) {
                for (int i = 0; i < node.getValue().size(); i++) {
                    node.getValue().get(i).fields().forEachRemaining(music -> {

                        if (music.getKey().equalsIgnoreCase("track")) {
                            music.getValue().fields().forEachRemaining(song -> {
                                // spotify music id
                                if (song.getKey().equalsIgnoreCase("id")) {
                                    System.out.println("ID: " + song.getValue().asText());
                                }

                                // name
                                if (song.getKey().equalsIgnoreCase("name")) {
                                    System.out.println("Name: " + song.getValue().asText());
                                }

                                // duration
                                if (song.getKey().equalsIgnoreCase("duration_ms")) {
                                    System.out.println("Duration (minute) : " + Integer.parseInt(String.valueOf(song.getValue())) / 60000);
                                }

                                // spotify link
                                if (song.getKey().equalsIgnoreCase("external_urls")) {
                                    song.getValue().fields().forEachRemaining(url -> {
                                        System.out.println("Link: " + url.getValue().asText());
                                    });
                                }

                                // spotify app
                                if (song.getKey().equalsIgnoreCase("uri")) {
                                    System.out.println("Play on Spotify: " + song.getValue().asText());
                                }
                            });
                        }

                        // release date
                        if (music.getKey().equalsIgnoreCase("added_at")) {
                            System.out.println("Release Date: " + music.getValue().asText());
                        }

                        // username
                        if (music.getKey().equalsIgnoreCase("added_by")) {
                            music.getValue().fields().forEachRemaining(user -> {
                                if (user.getKey().equalsIgnoreCase("id")) {
                                    System.out.println("Username: " + user.getValue().asText());
                                }
                            });
                        }
                        System.out.println();
                    });
                }
            }
        });
    }

    public void setSongsToMigrationTable(JsonNode rootNode) {
        Playlist playlist = new Playlist();
        ArrayList<Music> songs = new ArrayList<>();

        rootNode.fields().forEachRemaining(node -> {
            if (node.getKey().equalsIgnoreCase("items")) {
                for (int i = 0; i < node.getValue().size(); i++) {
                    Music music = new Music();

                    node.getValue().get(i).fields().forEachRemaining(itemsValue -> {

                        if (itemsValue.getKey().equalsIgnoreCase("track")) {
                            itemsValue.getValue().fields().forEachRemaining(trackValue -> {
                                // spotify music id
                                if (trackValue.getKey().equalsIgnoreCase("id")) {
                                    music.setId(trackValue.getValue().asText());
                                }

                                // name
                                if (trackValue.getKey().equalsIgnoreCase("name")) {
                                    music.setName(trackValue.getValue().asText());
                                }

                                // duration
                                if (trackValue.getKey().equalsIgnoreCase("duration_ms")) {
                                    music.setDuration(String.valueOf(Integer.parseInt(String.valueOf(trackValue.getValue())) / 60000));
                                }

                                // spotify link
                                if (trackValue.getKey().equalsIgnoreCase("external_urls")) {
                                    trackValue.getValue().fields().forEachRemaining(url -> {
                                        music.setExternalLink(url.getValue().asText());
                                    });
                                }

                                // spotify app
                                if (trackValue.getKey().equalsIgnoreCase("uri")) {
                                    music.setSpotifyLink(trackValue.getValue().asText());
                                }
                            });
                        }

                        // release date
                        if (itemsValue.getKey().equalsIgnoreCase("added_at")) {
                            music.setReleaseDate(itemsValue.getValue().asText());
                        }

                        // username
                        if (itemsValue.getKey().equalsIgnoreCase("added_by")) {
                            itemsValue.getValue().fields().forEachRemaining(user -> {
                                if (user.getKey().equalsIgnoreCase("id")) {
                                    this.username = user.getValue().asText();
                                }
                            });
                        }
                    });

                    songs.add(music);
                }
            }
        });
        playlist.setSongs(songs);

        ArrayList<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);

        HashMap<String, ArrayList<Playlist>> spotifyPlaylist = Migration.getPlaylists() == null
                ? new HashMap<>()
                : Migration.getPlaylists();
        spotifyPlaylist.put(this.username, playlists);

        // update
        Migration.setPlaylists(spotifyPlaylist);
        Migration.setUserActive(this.username);
    }

    private String extractAccessToken(String jsonResponse) {
        String token = null;
        String[] parts = jsonResponse.split(",");
        for (String part : parts) {
            if (part.contains("\"access_token\"")) {
                token = part.split(":")[1].replace("\"", "").trim();
                break;
            }
        }

        return token;
    }

    public JsonNode parseJson(String json) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            return rootNode;
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }

        return null;
    }
}
