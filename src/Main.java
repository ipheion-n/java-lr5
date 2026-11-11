import controller.ShapeController;
import view.ShapeView;

public class Main {
    public static void main(String[] args) {
        util.ConsoleUtil.setup();
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        controller.run();
    }
}
