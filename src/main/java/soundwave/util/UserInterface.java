package soundwave.util;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import soundwave.model.*;

import java.util.ArrayList;

public class UserInterface {
    private final int width = 90;

    public void banner(String message) {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow(message);
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    public void admin() {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("1", "Manajemen Musik");
        table.addRule();
        table.addRow("2", "Manajemen Artis");
        table.addRule();
        table.addRow("3", "Manajemen Genre");
        table.addRule();
        table.addRow("4", "Manajemen Playlist");
        table.addRule();
        table.addRow("5", "Manajemen Pengguna");
        table.addRule();
        table.addRow("0", "Keluar");
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    public void userDashboard() {
        AsciiTable table = new AsciiTable();


        table.addRule();
        table.addRow("1", "Manajemen Playlist");
        table.addRule();
        table.addRow("0", "Keluar");
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    /**
     * Music CRUD console
     */
    public void music() {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("1", "Tampil Musik");
        table.addRule();
        table.addRow("2", "Tambah Musik");
        table.addRule();
        table.addRow("3", "Edit Musik");
        table.addRule();
        table.addRow("4", "Hapus Musik");
        table.addRule();
        table.addRow("0", "Kembali");
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    public void readMusic(Music music) {
        AsciiTable table = new AsciiTable();
        String artistName = music.getArtist() != null ? music.getArtist().getName() : "-";
        String genreName = music.getGenre() != null ? music.getGenre().getName() : "-";

        table.addRule();
        table.addRow("ID", music.getId());
        table.addRule();
        table.addRow("Nama Musik", music.getName());
        table.addRule();
        table.addRow("Durasi", music.getDuration());
        table.addRule();
        table.addRow("Tahun Rilis", music.getReleaseDate());
        table.addRule();
        table.addRow("By", artistName);
        table.addRule();
        table.addRow("Genre", genreName);
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    /**
     * Artist CRUD console
     */
    public void artist() {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("1", "Tampil Artis");
        table.addRule();
        table.addRow("2", "Tambah Artis");
        table.addRule();
        table.addRow("3", "Edit Artis");
        table.addRule();
        table.addRow("4", "Hapus Artis");
        table.addRule();
        table.addRow("0", "Kembali");
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    public void readArtis(Artist artist) {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("ID", artist.getId());
        table.addRule();
        table.addRow("Nama Artis", artist.getName());
        table.addRule();
        table.addRow("Bio", artist.getBio());
        table.addRule();
        table.addRow("Negara", artist.getCountry());
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    /**
     * Genre CRUD console
     */
    public void genre() {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("1", "Tampil Genre");
        table.addRule();
        table.addRow("2", "Tambah Genre");
        table.addRule();
        table.addRow("3", "Edit Genre");
        table.addRule();
        table.addRow("4", "Hapus Genre");
        table.addRule();
        table.addRow("0", "Kembali");
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    public void readGenres(Genre genre) {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("ID", genre.getId());
        table.addRule();
        table.addRow("Nama Genre", genre.getName());
        table.addRule();
        table.addRow("Deskripsi", genre.getDescription());
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    /**
     * Playlist CRUD console
     */
    public void playlist() {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("1", "Tampil Playlist");
        table.addRule();
        table.addRow("2", "Tambah Playlist");
        table.addRule();
        table.addRow("3", "Edit Playlist");
        table.addRule();
        table.addRow("4", "Hapus Playlist");
        table.addRule();
        table.addRow("0", "Kembali");
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    public void readPlaylist(ArrayList<Playlist> playlists) {
        for (int i = 0; i < playlists.size(); i++) {
            this.banner("Playlist ke-" + (i + 1));

            for(Music music: playlists.get(i).getSongs()) {
                AsciiTable table = new AsciiTable();
                String id = music == null
                        ? "-"
                        : music.getId();

                String name = music == null
                        ? "-"
                        : music.getName();

                table.addRule();
                table.addRow("ID Musik", id);
                table.addRule();
                table.addRow("Nama Musik", name);
                table.addRule();
                table.setTextAlignment(TextAlignment.CENTER);
                System.out.println(table.render(width));
            }
        }
    }

    /**
     * User CRUD console
     */
    public void user() {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("1", "Tampil User");
        table.addRule();
        table.addRow("2", "Tambah User");
        table.addRule();
        table.addRow("3", "Edit User");
        table.addRule();
        table.addRow("4", "Hapus User");
        table.addRule();
        table.addRow("0", "Kembali");
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }

    public void readUser(User user) {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("ID", user.getId());
        table.addRule();
        table.addRow("Email", user.getEmail());
        table.addRule();
        table.addRow("Nama", user.getName());
        table.addRule();
        table.addRow("Username", user.getUsername());
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        System.out.println(table.render(width));
    }
}
