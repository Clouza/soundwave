package soundwave.views;

import soundwave.repository.Migration;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.Objects;
import java.util.Scanner;

public class Login {
    private boolean isRoot = false;

    public Login() {}

    public void console() {
        Scanner input = new Scanner(System.in);
        UserInterface ui = new UserInterface();
        boolean save = false;

        do {
            ui.banner("Login");

            System.out.print("Username: ");
            String username = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            ClearConsole.clear();
            save = this.login(username, password);
        } while (!save);

        ClearConsole.clear();
    }

    public boolean login(String username, String password) {
        try {
            UserInterface ui = new UserInterface();
            if (Migration.getUsers() == null || Migration.getUsers().get(username) == null) {
                ui.banner("User tidak ditemukan. Silahkan daftar terlebih dahulu");
                return false;
            }

            String existingPassword = Migration.getUsers().get(username).getPassword();
            if (!Objects.equals(existingPassword, password)) {
                ui.banner("Username atau Password salah");
                return false;
            }

            if (username.equals("admin")) {
                this.isRoot = true;
            }
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }

        Migration.setUserActive(username);
        return true;
    }

    public boolean getIsRoot() {
        return this.isRoot;
    }
}
