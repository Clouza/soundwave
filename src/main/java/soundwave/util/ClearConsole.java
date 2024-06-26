package soundwave.util;

import java.io.IOException;

/**
 * ClearConsole is a function to clear a console.... indeed.
 * <p>
 * Docs and Example for ProcessBuilder
 * @see <a href="https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/lang/ProcessBuilder.html">ProcessBuilder</a>
 * @see <a href="https://stackoverflow.com/questions/15464111/run-cmd-commands-through-java">Example</a>
 */
public class ClearConsole {
    public static void clear() {
//        try {
//            if (System.getProperty("os.name").toLowerCase().contains("win")) {
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
//            } else {
//                new ProcessBuilder("clear").inheritIO().start();
//            }
//        } catch (IOException exception) {
//            Logger.log(exception);
//            exception.printStackTrace();
//        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
