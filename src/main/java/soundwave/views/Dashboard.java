package soundwave.views;

import soundwave.util.ClearConsole;
import soundwave.util.Dotenv;
import soundwave.util.Logger;

import java.io.IOException;
import java.util.Scanner;

public class Dashboard extends View {
    private String halaman;
    private ClearConsole console;

    public Dashboard() {}
    public Dashboard(String message) {
        super(message);
    }

    public void console() {
        try {
            while(true) {
                System.out.println("+-----------------------------------+");
                System.out.println("|       Welcome to Soundwave        |");
                System.out.println("+-----------------------------------+");

                Scanner input = new Scanner(System.in);
                System.out.println("1. Registration");
                System.out.println("2. Login");

                System.out.print("Pilih salah satu halaman: ");
                this.halaman = input.nextLine();
                ClearConsole.clear();

                if (this.halaman.equalsIgnoreCase("registration") || this.halaman.equalsIgnoreCase("register") || this.halaman.equalsIgnoreCase("1") ) {
                    new Registration().console();
                }

                Login login = new Login();
                login.console();


            }
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }
}
