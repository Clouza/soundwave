package soundwave.views;

import soundwave.model.User;
import soundwave.repository.Migration;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Registration extends View {
    private Scanner input;

    public Registration() {}
    public Registration(Scanner input) {
        this.input = input;
    }

    @Override
    public void console() {
        System.out.println("+---------------------------+");
        System.out.println("|       Registration        |");
        System.out.println("+---------------------------+");

        Scanner input = new Scanner(System.in);
        boolean save = false;

        do {
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
        } while (!save);

        System.out.println("Register berhasil. Selamat!");
        input.nextLine();
        ClearConsole.clear();
    }

    public boolean register(String name, String email, String username, String password, String passwordConfirmation) {
        try {
            System.out.println();
            if (!Objects.equals(password, passwordConfirmation)) {
                System.out.println("Password tidak sama.");
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
