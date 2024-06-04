package soundwave.util;

public class Animation {
    boolean stop = false;

    public Animation() {}

    public void stop() {
        this.stop = true;
    }

    public void loading() throws InterruptedException {
        String pattern = "\\ | / -";

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            pattern = "\\ /";
        } else {
            // pattern = "⣾ ⣽ ⣻ ⢿ ⡿ ⣟ ⣯ ⣷";
            pattern = "🌑 🌒 🌓 🌔 🌕 🌖 🌗 🌘 🌑";
        }

        String[] frames = pattern.split(" ");
        do {
            for (String frame : frames) {
                System.out.print(frame);
                Thread.sleep(200);

                System.out.print("\b");
            }
        } while (!stop);
    }
}
