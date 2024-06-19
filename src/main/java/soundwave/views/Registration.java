package soundwave.views;

import soundwave.model.User;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.Objects;
import java.util.Scanner;

public class Registration {
    private Scanner input;

    public Registration() {}
    public Registration(Scanner input) {
        this.input = input;
    }

    public void console() {
        Scanner input = new Scanner(System.in);
        UserInterface ui = new UserInterface();
        boolean save = false;

        do {
            ui.banner("Registration");

            System.out.print("Nama: ");
            String name = input.nextLine();

            System.out.print("Email: ");
            String email = input.nextLine();

            System.out.print("Username: ");
            String username = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            System.out.print("Password Confirmation: ");
            String passwordConfirmation = input.nextLine();

            save = this.register(name, email, username, password, passwordConfirmation);
            if (save) {
                ClearConsole.clear();
                ui.banner("Register berhasil. Selamat!");
                input.nextLine();
            }
        } while (!save);
    }

    public boolean register(String name, String email, String username, String password, String passwordConfirmation) {
        try {
            UserInterface ui = new UserInterface();
            if (!Objects.equals(password, passwordConfirmation)) {
                ui.banner("Password tidak sama");
                return false;
            }

            User user = new User();
            user.setName(name);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            return user.save();
        } catch (Exception exception) {
            Logger.log(exception);
        }

        return false;
    }
}
