package soundwave.views;

import soundwave.views.View;

import java.util.Scanner;

public class Music extends View {
    public Music() {}

    @Override
    public void console() {
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("+---------------------------------+");
            System.out.println("|              Menu               |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1 | Tambah Musik                |");
            System.out.println("+---+-----------------------------+");
            System.out.println("| 2 | Edit Musik                  |");
            System.out.println("+---+-----------------------------+");
            System.out.println("| 3 | Hapus Musik                 |");
            System.out.println("+---+-----------------------------+");
            System.out.println("| 4 | Daftar Musik                |");
            System.out.println("+---+-----------------------------+");
            System.out.println("| 0 | Kembali                     |");
            System.out.println("+---------------------------------+");

            System.out.print("Silahkan masukan pilihan anda: [1 - 4]");
            String menu = input.nextLine();

            if (menu.equalsIgnoreCase("0")) {
                System.out.print("Apa anda yakin ingin kembali (yes/no)?: [yes]");
                String out = input.nextLine();

                if (out.equalsIgnoreCase("no")) {
                    break;
                }
            }
        } while (true);
    }
}
