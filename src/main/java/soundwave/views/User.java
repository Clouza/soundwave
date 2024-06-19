package soundwave.views;

import soundwave.repository.Migration;
import soundwave.util.ClearConsole;
import soundwave.util.Logger;
import soundwave.util.UserInterface;

import java.util.Scanner;

public class User extends View {
    public User() {}

    @Override
    public void console() {
        try {
            Scanner input = new Scanner(System.in);
            UserInterface ui = new UserInterface();

            do {
                ui.banner("MENU USER");
                ui.user();
                System.out.print("Silahkan masukan pilihan Anda: [1 - 4]: ");
                String menu = input.nextLine();

                switch (menu) {
                    case "1":
                        read();
                        break;
                    case "2":
                        create();
                        break;
                    case "3":
                        update();
                        break;
                    case "4":
                        delete();
                        break;

                    default:
                        break;
                }

                if (menu.equalsIgnoreCase("0")) {
                    System.out.print("Apa anda yakin ingin kembali (yes/no) [default: yes]: ");
                    String out = input.nextLine();

                    if (!out.equalsIgnoreCase("no")) {
                        break;
                    }
                }
            } while (true);
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    @Override
    public void read() {
        try {
            ClearConsole.clear();
            UserInterface ui = new UserInterface();
            Scanner input = new Scanner(System.in);

            if (Migration.getUsers() == null) {
                ClearConsole.clear();
                ui.banner("Data User kosong");
            } else {
                for (soundwave.model.User user: Migration.getUsers().values()) {
                    ui.readUser(user);
                }
            }

            input.nextLine();
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    @Override
    public void create() {
        try {
            Scanner input = new Scanner(System.in);
            soundwave.model.User user = new soundwave.model.User();

            UserInterface ui = new UserInterface();
            ui.banner("TAMBAH USER");

            System.out.print("Username  : ");
            user.setUsername(input.nextLine());

            System.out.print("Email     : ");
            user.setEmail(input.nextLine());

            System.out.print("Nama      : ");
            user.setName(input.nextLine());

            System.out.print("Password  : ");
            user.setPassword(input.nextLine());

            boolean isClean = user.save();
            if (isClean) {
                ClearConsole.clear();
                ui.banner("User berhasil ditambah!");
            }
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    @Override
    public void update() {
        this.read();

        try {
            Scanner input = new Scanner(System.in);

            System.out.print("Username: ");
            soundwave.model.User user = Migration.getUsers().get(input.nextLine());

            UserInterface ui = new UserInterface();
            ui.banner("EDIT USER");

            System.out.print("Username  : ");
            user.setUsername(input.nextLine());

            System.out.print("Email     : ");
            user.setEmail(input.nextLine());

            System.out.print("Nama      : ");
            user.setName(input.nextLine());

            System.out.print("Password  : ");
            user.setPassword(input.nextLine());

            boolean isClean = user.save();
            if (isClean) {
                ClearConsole.clear();
                ui.banner("User berhasil diperbarui!");
            }

        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }

    @Override
    public void delete() {
        this.read();

        try {
            Scanner input = new Scanner(System.in);
            UserInterface ui = new UserInterface();
            ui.banner("HAPUS USER");

            System.out.print("Username: ");
            Migration.getUsers().remove(input.nextLine());

            ClearConsole.clear();
            ui.banner("User berhasil dihapus!");
        } catch (Exception exception) {
            Logger.log(exception);
            exception.printStackTrace();
        }
    }
}
