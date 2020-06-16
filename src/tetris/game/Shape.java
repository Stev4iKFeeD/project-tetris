package tetris.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shape {
    private static final String[] MINOS = {"I", "J", "L", "O", "S", "T", "Z"};

    protected ImageView a;
    protected ImageView b;
    protected ImageView c;
    protected ImageView d;

    protected int rotationState = 0;

    public final int MINO_INDEX;
    public final int SIZE = 36;


    /**
     * @param minoIndex 0 - I; 1 - J; 2 - L; 3 - O; 4 - S; 5 - T; 6 - Z
     */
    public Shape(int minoIndex) {
        this.MINO_INDEX = minoIndex;
        Image minoImage = new Image("assets/minos/mino_" + MINOS[minoIndex] + ".png");

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

        resetPos();
    }

    public void resetPos() {
        switch(MINOS[MINO_INDEX]) {
            case "I":
                a.setX(0);
                a.setY(0);
                b.setX(SIZE);
                b.setY(0);
                c.setX(2 * SIZE);
                c.setY(0);
                d.setX(3 * SIZE);
                d.setY(0);
                break;
            case "J":
                a.setX(0);
                a.setY(0);
                b.setX(0);
                b.setY(SIZE);
                c.setX(SIZE);
                c.setY(SIZE);
                d.setX(2 * SIZE);
                d.setY(SIZE);
                break;
            case "L":
                a.setX(0);
                a.setY(SIZE);
                b.setX(SIZE);
                b.setY(SIZE);
                c.setX(2 * SIZE);
                c.setY(SIZE);
                d.setX(2 * SIZE);
                d.setY(0);
                break;
            case "O":
                a.setX(0);
                a.setY(0);
                b.setX(SIZE);
                b.setY(0);
                c.setX(0);
                c.setY(SIZE);
                d.setX(SIZE);
                d.setY(SIZE);
                break;
            case "S":
                a.setX(0);
                a.setY(SIZE);
                b.setX(SIZE);
                b.setY(SIZE);
                c.setX(SIZE);
                c.setY(0);
                d.setX(2 * SIZE);
                d.setY(0);
                break;
            case "T":
                a.setX(SIZE);
                a.setY(0);
                b.setX(0);
                b.setY(SIZE);
                c.setX(SIZE);
                c.setY(SIZE);
                d.setX(2 * SIZE);
                d.setY(SIZE);
                break;
            case "Z":
                a.setX(0);
                a.setY(0);
                b.setX(SIZE);
                b.setY(0);
                c.setX(SIZE);
                c.setY(SIZE);
                d.setX(2 * SIZE);
                d.setY(SIZE);
                break;
        }
    }

    public void initPos() {
        resetPos();

        if (MINOS[MINO_INDEX].equals("O")) {
            moveX(4 * SIZE);
        } else {
            moveX(3 * SIZE);
        }
    }

    public void moveX(int offset){
        a.setX(a.getX() + offset);
        b.setX(b.getX() + offset);
        c.setX(c.getX() + offset);
        d.setX(d.getX() + offset);
    }

    public void moveY(int offset){
        a.setY(a.getY() + offset);
        b.setY(b.getY() + offset);
        c.setY(c.getY() + offset);
        d.setY(d.getY() + offset);
    }

    public void rotate() {
        switch(MINOS[MINO_INDEX]) {
            case "I":
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
            case "J":
                if(rotationState == 0) {
                    a.setX(a.getX() + 2 * SIZE);
                    b.setX(b.getX() + SIZE);
                    b.setY(b.getY() - SIZE);
                    d.setX(d.getX() - SIZE);
                    d.setY(d.getY() + SIZE);
                } else if(rotationState == 1) {
                    a.setY(a.getY() + 2 * SIZE);
                    b.setX(b.getX() + SIZE);
                    b.setY(b.getY() + SIZE);
                    d.setX(d.getX() - SIZE);
                    d.setY(d.getY() - SIZE);
                } else if(rotationState == 2) {
                    a.setX(a.getX() - 2 * SIZE);
                    b.setX(b.getX() - SIZE);
                    b.setY(b.getY() + SIZE);
                    d.setX(d.getX() + SIZE);
                    d.setY(d.getY() - SIZE);
                } else {
                    a.setY(a.getY() - 2 * SIZE);
                    b.setX(b.getX() - SIZE);
                    b.setY(b.getY() - SIZE);
                    d.setX(d.getX() + SIZE);
                    d.setY(d.getY() + SIZE);
                }
                break;
            case "L":
                if(rotationState == 0) {
                    a.setX(a.getX() + SIZE);
                    a.setY(a.getY() - SIZE);
                    c.setX(c.getX() - SIZE);
                    c.setY(c.getY() + SIZE);
                    d.setY(d.getY() + 2 * SIZE);
                } else if(rotationState == 1) {
                    a.setX(a.getX() + SIZE);
                    a.setY(a.getY() + SIZE);
                    c.setX(c.getX() - SIZE);
                    c.setY(c.getY() - SIZE);
                    d.setX(d.getX() - 2 * SIZE);
                } else if(rotationState == 2) {
                    a.setX(a.getX() - SIZE);
                    a.setY(a.getY() + SIZE);
                    c.setX(c.getX() + SIZE);
                    c.setY(c.getY() - SIZE);
                    d.setY(d.getY() - 2 * SIZE);
                } else {
                    a.setX(a.getX() - SIZE);
                    a.setY(a.getY() - SIZE);
                    c.setX(c.getX() + SIZE);
                    c.setY(c.getY() + SIZE);
                    d.setX(d.getX() + 2 * SIZE);
                }
                break;
            case "O":
                /* O does not rotate */
                break;
            case "S":
                if(rotationState == 0) {
                    a.setX(a.getX() + SIZE);
                    a.setY(a.getY() - SIZE);
                    c.setX(c.getX() + SIZE);
                    c.setY(c.getY() + SIZE);
                    d.setY(d.getY() + 2 * SIZE);
                } else if(rotationState == 1) {
                    a.setX(a.getX() + SIZE);
                    a.setY(a.getY() + SIZE);
                    c.setX(c.getX() - SIZE);
                    c.setY(c.getY() + SIZE);
                    d.setX(d.getX() - 2 * SIZE);
                } else if(rotationState == 2) {
                    a.setX(a.getX() - SIZE);
                    a.setY(a.getY() + SIZE);
                    c.setX(c.getX() - SIZE);
                    c.setY(c.getY() - SIZE);
                    d.setY(d.getY() - 2 * SIZE);
                } else {
                    a.setX(a.getX() - SIZE);
                    a.setY(a.getY() - SIZE);
                    c.setX(c.getX() + SIZE);
                    c.setY(c.getY() - SIZE);
                    d.setX(d.getX() + 2 * SIZE);
                }
                break;
            case "T":
                if(rotationState == 0) {
                    a.setX(a.getX() + SIZE);
                    a.setY(a.getY() + SIZE);
                    b.setX(b.getX() + SIZE);
                    b.setY(b.getY() - SIZE);
                    d.setX(d.getX() - SIZE);
                    d.setY(d.getY() + SIZE);
                } else if(rotationState == 1) {
                    a.setX(a.getX() - SIZE);
                    a.setY(a.getY() + SIZE);
                    b.setX(b.getX() + SIZE);
                    b.setY(b.getY() + SIZE);
                    d.setX(d.getX() - SIZE);
                    d.setY(d.getY() - SIZE);
                } else if(rotationState == 2) {
                    a.setX(a.getX() - SIZE);
                    a.setY(a.getY() - SIZE);
                    b.setX(b.getX() - SIZE);
                    b.setY(b.getY() + SIZE);
                    d.setX(d.getX() + SIZE);
                    d.setY(d.getY() - SIZE);
                } else {
                    a.setX(a.getX() + SIZE);
                    a.setY(a.getY() - SIZE);
                    b.setX(b.getX() - SIZE);
                    b.setY(b.getY() - SIZE);
                    d.setX(d.getX() + SIZE);
                    d.setY(d.getY() + SIZE);
                }
                break;
            case "Z":
                if(rotationState == 0) {
                    a.setX(a.getX() + 2 * SIZE);
                    b.setX(b.getX() + SIZE);
                    b.setY(b.getY() + SIZE);
                    d.setX(d.getX() - SIZE);
                    d.setY(d.getY() + SIZE);
                } else if(rotationState == 1) {
                    a.setY(a.getY() + 2 * SIZE);
                    b.setX(b.getX() - SIZE);
                    b.setY(b.getY() + SIZE);
                    d.setX(d.getX() - SIZE);
                    d.setY(d.getY() - SIZE);
                } else if(rotationState == 2) {
                    a.setX(a.getX() - 2 * SIZE);
                    b.setX(b.getX() - SIZE);
                    b.setY(b.getY() - SIZE);
                    d.setX(d.getX() + SIZE);
                    d.setY(d.getY() - SIZE);
                } else {
                    a.setY(a.getY() - 2 * SIZE);
                    b.setX(b.getX() + SIZE);
                    b.setY(b.getY() - SIZE);
                    d.setX(d.getX() + SIZE);
                    d.setY(d.getY() + SIZE);
                }
                break;
        }

        if(rotationState < 3) {
            rotationState++;
        } else {
            rotationState = 0;
        }
    }

    public Shape copy() {
        Shape result = new Shape(MINO_INDEX);
        result.a.setX(a.getX());
        result.a.setY(a.getY());
        result.b.setX(b.getX());
        result.b.setY(b.getY());
        result.c.setX(c.getX());
        result.c.setY(c.getY());
        result.d.setX(d.getX());
        result.d.setY(d.getY());
        result.rotationState = rotationState;
        return result;
    }
}
