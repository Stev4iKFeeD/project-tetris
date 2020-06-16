package tetris.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shape {
    private static final String[] MINOS = {"I", "J", "L", "O", "S", "T", "Z"};

    protected ImageView a;
    protected ImageView b;
    protected ImageView c;
    protected ImageView d;

    private int rotationState = 0;

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

        switch(MINOS[MINO_INDEX]) {
            case "I":
                a.setX(SIZE);
                b.setX(2 * SIZE);
                c.setX(3 * SIZE);
                d.setX(4 * SIZE);
                break;
            case "J":
                a.setX(SIZE);
                b.setX(SIZE);
                b.setY(SIZE);
                c.setX(2 * SIZE);
                c.setY(SIZE);
                d.setX(3 * SIZE);
                d.setY(SIZE);
                break;
            case "L":
                a.setX(SIZE);
                a.setY(SIZE);
                b.setX(2 * SIZE);
                b.setY(SIZE);
                c.setX(3 * SIZE);
                c.setY(SIZE);
                d.setX(3 * SIZE);
                break;
            case "O":
                a.setX(SIZE);
                b.setX(2 * SIZE);
                c.setX(SIZE);
                c.setY(SIZE);
                d.setX(2 * SIZE);
                d.setY(SIZE);
                break;
            case "S":
                a.setX(SIZE);
                a.setY(SIZE);
                b.setX(2 * SIZE);
                b.setY(SIZE);
                c.setX(2 * SIZE);
                d.setX(3 * SIZE);
                break;
            case "T":
                a.setX(2 * SIZE);
                b.setX(SIZE);
                b.setY(SIZE);
                c.setX(2 * SIZE);
                c.setY(SIZE);
                d.setX(3 * SIZE);
                d.setY(SIZE);
                break;
            case "Z":
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
        switch(MINOS[MINO_INDEX]) {
            case "I":
                a.setX(3 * SIZE);
                b.setX(4 * SIZE);
                c.setX(5 * SIZE);
                d.setX(6 * SIZE);
                break;
            case "J":
                a.setX(3 * SIZE);
                b.setX(3 * SIZE);
                b.setY(SIZE);
                c.setX(4 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                d.setY(SIZE);
                break;
            case "L":
                a.setX(3 * SIZE);
                a.setY(SIZE);
                b.setX(4 * SIZE);
                b.setY(SIZE);
                c.setX(5 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                break;
            case "O":
                a.setX(4 * SIZE);
                b.setX(5 * SIZE);
                c.setX(4 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                d.setY(SIZE);
                break;
            case "S":
                a.setX(3 * SIZE);
                a.setY(SIZE);
                b.setX(4 * SIZE);
                b.setY(SIZE);
                c.setX(4 * SIZE);
                d.setX(5 * SIZE);
                break;
            case "T":
                a.setX(4 * SIZE);
                b.setX(3 * SIZE);
                b.setY(SIZE);
                c.setX(4 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                d.setY(SIZE);
                break;
            case "Z":
                a.setX(3 * SIZE);
                b.setX(4 * SIZE);
                c.setX(4 * SIZE);
                c.setY(SIZE);
                d.setX(5 * SIZE);
                d.setY(SIZE);
                break;
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
