package tetris.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shape {
    private static final String[] MINO_NAMES = {"I", "J", "L", "O", "S", "T", "Z"};

    protected ImageView a;
    protected ImageView b;
    protected ImageView c;
    protected ImageView d;

    private int rotationState = 0;

//    public final ShapeType TYPE;
    public final int MINO_INDEX;
    public final int SIZE = 36;

//    public enum ShapeType {
//        I, J, L, O, S, T, Z
//    }

    /**
     * @param minoIndex 0 - I; 1 - J; 2 - L; 3 - O; 4 - S; 5 - T; 6 - Z
     */
    public Shape(int minoIndex) {
        this.MINO_INDEX = minoIndex;
//        this.TYPE = type;
//        Image minoImage = new Image("assets/minos/mino_" + type + ".png");
        Image minoImage = new Image("assets/minos/mino_" + MINO_NAMES[minoIndex] + ".png");

        a = new ImageView(minoImage);
        a.setFitWidth(SIZE);
        a.setFitHeight(SIZE);
        b = new ImageView(minoImage);
        b.setFitWidth(SIZE);
        b.setFitHeight(SIZE);
        c = new ImageView(minoImage);
        c.setFitWidth(SIZE);
        c.setFitHeight(SIZE);
        d = new ImageView(minoImage);
        d.setFitWidth(SIZE);
        d.setFitHeight(SIZE);

        switch(MINO_INDEX) {
            case 0:
                a.setX(SIZE);
                b.setX(2 * SIZE);
                c.setX(3 * SIZE);
                d.setX(4 * SIZE);
                break;
            case 1:
                a.setX(SIZE);
                b.setX(SIZE);
                b.setY(SIZE);
                c.setX(2 * SIZE);
                c.setY(SIZE);
                d.setX(3 * SIZE);
                d.setY(SIZE);
                break;
            case 2:
                a.setX(SIZE);
                a.setY(SIZE);
                b.setX(2 * SIZE);
                b.setY(SIZE);
                c.setX(3 * SIZE);
                c.setY(SIZE);
                d.setX(3 * SIZE);
                break;
            case 3:
                a.setX(SIZE);
                b.setX(2 * SIZE);
                c.setX(SIZE);
                c.setY(SIZE);
                d.setX(2 * SIZE);
                d.setY(SIZE);
                break;
            case 4:
                a.setX(SIZE);
                a.setY(SIZE);
                b.setX(2 * SIZE);
                b.setY(SIZE);
                c.setX(2 * SIZE);
                d.setX(3 * SIZE);
                break;
            case 5:
                a.setX(2 * SIZE);
                b.setX(SIZE);
                b.setY(SIZE);
                c.setX(2 * SIZE);
                c.setY(SIZE);
                d.setX(3 * SIZE);
                d.setY(SIZE);
                break;
            case 6:
                a.setX(SIZE);
                b.setX(2 * SIZE);
                c.setX(2 * SIZE);
                c.setY(SIZE);
                d.setX(3 * SIZE);
                d.setY(SIZE);
                break;
        }
    }

    public void initPos() {
        switch(MINO_INDEX) {
            case 0:
                a.setX(3 * SIZE);
                b.setX(4 * SIZE);
                c.setX(5 * SIZE);
                d.setX(6 * SIZE);
                break;
            case 1:
                a.setX(3 * SIZE);
                b.setX(3 * SIZE);
                b.setY(SIZE);
                c.setX(4 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                d.setY(SIZE);
                break;
            case 2:
                a.setX(3 * SIZE);
                a.setY(SIZE);
                b.setX(4 * SIZE);
                b.setY(SIZE);
                c.setX(5 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                break;
            case 3:
                a.setX(4 * SIZE);
                b.setX(5 * SIZE);
                c.setX(4 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                d.setY(SIZE);
                break;
            case 4:
                a.setX(3 * SIZE);
                a.setY(SIZE);
                b.setX(4 * SIZE);
                b.setY(SIZE);
                c.setX(4 * SIZE);
                d.setX(5 * SIZE);
                break;
            case 5:
                a.setX(4 * SIZE);
                b.setX(3 * SIZE);
                b.setY(SIZE);
                c.setX(4 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                d.setY(SIZE);
                break;
            case 6:
                a.setX(3 * SIZE);
                b.setX(4 * SIZE);
                c.setX(4 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                d.setY(SIZE);
                break;
        }
    }

    public void moveDown() {
        a.setY(a.getY() + SIZE);
        b.setY(b.getY() + SIZE);
        c.setY(c.getY() + SIZE);
        d.setY(d.getY() + SIZE);
    }

    public void moveLeft() {
        a.setX(a.getX() - SIZE);
        b.setX(b.getX() - SIZE);
        c.setX(c.getX() - SIZE);
        d.setX(d.getX() - SIZE);
    }

    public void moveRight() {
        a.setX(a.getX() + SIZE);
        b.setX(b.getX() + SIZE);
        c.setX(c.getX() + SIZE);
        d.setX(d.getX() + SIZE);
    }

    public void rotate() {
        switch(MINO_INDEX) {
            case 0:
                if(rotationState == 0) {
                    a.setX(a.getX() + 2 * SIZE);
                    a.setY(a.getY() - SIZE);
                    b.setX(b.getX() + SIZE);
                    c.setY(c.getY() + SIZE);
                    d.setX(d.getX() - SIZE);
                    d.setY(d.getY() + 2 * SIZE);
                } else if(rotationState == 1) {
                    a.setX(a.getX() + SIZE);
                    a.setY(a.getY() + 2 * SIZE);
                    b.setY(b.getY() + SIZE);
                    c.setX(c.getX() - SIZE);
                    d.setX(d.getX() - 2 * SIZE);
                    d.setY(d.getY() - SIZE);
                } else if(rotationState == 2) {
                    a.setX(a.getX() - 2 * SIZE);
                    a.setY(a.getY() + SIZE);
                    b.setX(b.getX() - SIZE);
                    c.setY(c.getY() - SIZE);
                    d.setX(d.getX() + SIZE);
                    d.setY(d.getY() - 2 * SIZE);
                } else {
                    a.setX(a.getX() - SIZE);
                    a.setY(a.getY() - 2 * SIZE);
                    b.setY(b.getY() - SIZE);
                    c.setX(c.getX() + SIZE);
                    d.setX(d.getX() + 2 * SIZE);
                    d.setY(d.getY() + SIZE);
                }
                break;
            case 1:

        }

        if(rotationState < 3) {
            rotationState++;
        } else {
            rotationState = 0;
        }
    }

    public boolean contains(ImageView part) {
        return (a == part) || (b == part) || (c == part) || (d == part);
    }
}
