package soundwave.views;

import soundwave.repository.Migration;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;

import java.util.Objects;
import java.util.Scanner;

public class Login extends View {
    public Login() {}
    public Login(String message) {
        super(message);
    }

    @Override
    public void console() {
        Scanner input = new Scanner(System.in);
        boolean save = false;

        do {
            System.out.println("+--------------------+");
            System.out.println("|       Login        |");
            System.out.println("+--------------------+");

            System.out.print("Username: ");
            String username = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            save = this.login(username, password);

            input.nextLine();
            ClearConsole.clear();
        } while (!save);

        input.nextLine();
        ClearConsole.clear();
    }

    public boolean login(String username, String password) {
        try {
            if (Migration.getUsers() == null || Migration.getUsers().get(username) == null) {
                System.out.println("User tidak ditemukan. Silahkan daftar terlebih dahulu.");
                return true;
            }

            String existingPassword = Migration.getUsers().get(username).getPassword();
            if (!Objects.equals(existingPassword, password)) {
                System.out.println("Username atau Password salah.");
                return false;
            }
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }

        return true;
    }
}
