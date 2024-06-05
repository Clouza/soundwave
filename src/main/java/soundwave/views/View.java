package soundwave.views;

import java.io.IOException;

public abstract class View {
    private String message;

    public View(){}
    public View(String message) {
        this.message = message;
    }
    public abstract void console();
}
