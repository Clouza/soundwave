package soundwave.views;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.io.IOException;
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
                }
            }

//            ui.banner("Terimakasih telah menggunakan program ini.");
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }

    }
}
