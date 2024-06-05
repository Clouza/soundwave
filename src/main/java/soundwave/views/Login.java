package soundwave.views;

import soundwave.repository.Migration;
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
        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();
    }

    public boolean login(String username, String password) {
        try {
            if (Migration.getUsers() == null || Migration.getUsers().get(username) == null) {
                System.out.println("User tidak ditemukan. Silahkan daftar terlebih dahulu.");
                return false;
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
