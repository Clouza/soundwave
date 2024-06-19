package soundwave.views;

import java.io.IOException;

public abstract class View {
    private String message;

    public View(){}
    public View(String message) {
        this.message = message;
    }
    public abstract void console();
    public abstract void read();
    public abstract void create();
    public abstract void update();
    public abstract void delete();
}
