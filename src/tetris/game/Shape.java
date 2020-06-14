package tetris.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shape {
    private final Image minoImage;
    protected ImageView a;
    protected ImageView b;
    protected ImageView c;
    protected ImageView d;

    private ShapeType type;
    private int rotationState = 0;

    public final int SIZE = 35;

    public enum ShapeType {
        I, J, L, O, S, T, Z
    }

    public Shape(ShapeType type) {
        this.type = type;
        minoImage = new Image("assets/minos/mino_" + type + ".png");

        a = new ImageView(minoImage);
        b = new ImageView(minoImage);
        c = new ImageView(minoImage);
        d = new ImageView(minoImage);
    }

    public void changeRotationState() {
        if(rotationState < 3) {
            rotationState++;
        } else {
            rotationState = 0;
        }
    }
}
