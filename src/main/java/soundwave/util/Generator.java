package soundwave.util;

public class Generator {
    public static void textBanner(String message) {
        int messageLength = message.length();
        int border = 4; // left & right
        int rows = 3;
        int selectedRowToMessage = 2;
        int total = (messageLength + border) * rows;

        int tmp = 0;
        int row = 0;
        for (int i = 0; i < total; i++) {
            boolean alwaysTheFirstOrLast = tmp == 0 || tmp == (messageLength + border) - 1;
            tmp++;

            if (!alwaysTheFirstOrLast && row < rows - 1) {
                System.out.print("-");
            }

            if (alwaysTheFirstOrLast && row < rows - 1) {
                System.out.print("+");
            }

            if (tmp == (messageLength + border) && row < rows - 1) {
                System.out.print("\n");
                tmp = 0;
                row++;

                if (row == selectedRowToMessage - 1) {
                    System.out.printf("| %s |\n", message);
                }
            }

            if (row == rows) {
                break;
            }
        }
    };
}
