import control.MainController;
import view.InteractionPanelHandler;
import view.ViewWindow;

public class Main {
    public static void main(String[] args) {
        new InteractionPanelHandler(new MainController());
    }
}
