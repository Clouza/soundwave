package soundwave.views;

import java.util.Scanner;

public class Menu extends View {
    public Menu() {}

    @Override
    public void console() {
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("+---------------------------------+");
            System.out.println("|              Menu               |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1 | Data Musik                  |");
            System.out.println("+---+-----------------------------+");
            System.out.println("| 2 | Data Artis                  |");
            System.out.println("+---+-----------------------------+");
            System.out.println("| 3 | Data Genre                  |");
            System.out.println("+---+-----------------------------+");
            System.out.println("| 4 | Data Playlist               |");
            System.out.println("+---+-----------------------------+");
            System.out.println("| 0 | Logout                      |");
            System.out.println("+---------------------------------+");

            System.out.print("Silahkan masukan pilihan anda: [1 - 4]");
            String menu = input.nextLine();

            if (menu.equalsIgnoreCase("0")) {
                System.out.print("Apa anda yakin ingin keluar?: [yes]");
                String out = input.nextLine();

                if (out.equalsIgnoreCase("no")) {
                    break;
                }
            }

            this.menus(menu);
        } while (true);
    }

    private void menus(String menu) {
        switch (menu) {
            case "1":
                break;
            default:
                break;
        }
    }
}
