package tetris.game;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {
    private final Pane gamePane;
    private final Pane nextMinoPane;
    private Shape currentMino;
    private Shape nextMino;
    private Timer minoFall;
//    private TimerTask fallingTask;

    public GameManager(Pane gamePane, Pane nextMinoPane) {
        this.gamePane = gamePane;
        this.nextMinoPane = nextMinoPane;
        nextMino = getNewMino();
        this.nextMinoPane.getChildren().addAll(nextMino.a, nextMino.b, nextMino.c, nextMino.d);

        start();
    }

    public void start() {
//        Timer minoFall = new Timer();
        currentMino = getNewMino();
        currentMino.initPos();
        gamePane.getChildren().addAll(currentMino.a, currentMino.b, currentMino.c, currentMino.d);
        minoFall = new Timer();
        minoFall.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    boolean canMoveDown = true;
                    for(int i=0; i<gamePane.getChildren().size(); i++) {
                        ImageView currentPart = (ImageView)gamePane.getChildren().get(i);
                        if(!currentMino.contains(currentPart) &&
                                ((currentMino.a.getX() == currentPart.getX() &&
                                    currentMino.a.getY() + currentMino.SIZE == currentPart.getY()) ||
                                (currentMino.b.getX() == currentPart.getX() &&
                                    currentMino.b.getY() + currentMino.SIZE == currentPart.getY()) ||
                                (currentMino.c.getX() == currentPart.getX() &&
                                    currentMino.c.getY() + currentMino.SIZE == currentPart.getY()) ||
                                (currentMino.d.getX() == currentPart.getX() &&
                                    currentMino.d.getY() + currentMino.SIZE == currentPart.getY()))) {
                            canMoveDown = false;
                            break;
                        }
                    }

                    if(canMoveDown && currentMino.a.getY() + currentMino.SIZE < gamePane.getHeight() - 1 &&
                            currentMino.b.getY() + currentMino.SIZE < gamePane.getHeight() - 1 &&
                            currentMino.c.getY() + currentMino.SIZE < gamePane.getHeight() - 1 &&
                            currentMino.d.getY() + currentMino.SIZE < gamePane.getHeight() - 1) {
                        currentMino.moveDown();
                    } else {
                        currentMino = nextMino;
                        currentMino.initPos();
                        gamePane.getChildren().addAll(currentMino.a, currentMino.b, currentMino.c, currentMino.d);
                        nextMinoPane.getChildren().clear();
                        nextMino = getNewMino();
                        nextMinoPane.getChildren().addAll(nextMino.a, nextMino.b, nextMino.c, nextMino.d);
                    }
                });
            }
        }, 200, 200);
    }

    public void stop() {
        if(minoFall != null) {
            minoFall.cancel();
        }
    }

    public void moveLeft() {
        boolean canMoveLeft = true;
        for(int i=0; i<gamePane.getChildren().size(); i++) {
            ImageView currentPart = (ImageView)gamePane.getChildren().get(i);
            if(!currentMino.contains(currentPart) &&
                    ((currentMino.a.getY() == currentPart.getY() &&
                        currentMino.a.getX() - currentMino.SIZE == currentPart.getX()) ||
                    (currentMino.b.getY() == currentPart.getY() &&
                        currentMino.b.getX() - currentMino.SIZE == currentPart.getX()) ||
                    (currentMino.c.getY() == currentPart.getY() &&
                        currentMino.c.getX() - currentMino.SIZE == currentPart.getX()) ||
                    (currentMino.d.getY() == currentPart.getY() &&
                        currentMino.d.getX() - currentMino.SIZE == currentPart.getX()))) {
                canMoveLeft = false;
                break;
            }
        }
        if(canMoveLeft && currentMino.a.getX() > 0 &&
                currentMino.b.getX() > 0 &&
                currentMino.c.getX() > 0 &&
                currentMino.d.getX() > 0) {
            currentMino.moveLeft();
        }
    }

    public void moveRight() {
        boolean canMoveRight = true;
        for(int i=0; i<gamePane.getChildren().size(); i++) {
            ImageView currentPart = (ImageView)gamePane.getChildren().get(i);
            if(!currentMino.contains(currentPart) &&
                    ((currentMino.a.getY() == currentPart.getY() &&
                        currentMino.a.getX() + currentMino.SIZE == currentPart.getX()) ||
                    (currentMino.b.getY() == currentPart.getY() &&
                            currentMino.b.getX() + currentMino.SIZE == currentPart.getX()) ||
                    (currentMino.c.getY() == currentPart.getY() &&
                            currentMino.c.getX() + currentMino.SIZE == currentPart.getX()) ||
                    (currentMino.d.getY() == currentPart.getY() &&
                            currentMino.d.getX() + currentMino.SIZE == currentPart.getX()))) {
                canMoveRight = false;
                break;
            }
        }
        if(canMoveRight && currentMino.a.getX() + currentMino.SIZE < gamePane.getWidth() - 1 &&
                currentMino.b.getX() + currentMino.SIZE < gamePane.getWidth() - 1 &&
                currentMino.c.getX() + currentMino.SIZE < gamePane.getWidth() - 1 &&
                currentMino.d.getX() + currentMino.SIZE < gamePane.getWidth() - 1) {
            currentMino.moveRight();
        }
    }

    public void rotate() {
        currentMino.rotate();
    }

    private Shape getNewMino() {
//        return new Shape(0);
        return new Shape((int)(Math.random() * 7));
//        int random = (int)(Math.random() * 7);
//        switch(random) {
//            case 0:
//                return new Shape(Shape.ShapeType.I);
//            case 1:
//                return new Shape(Shape.ShapeType.J);
//            case 2:
//                return new Shape(Shape.ShapeType.L);
//            case 3:
//                return new Shape(Shape.ShapeType.O);
//            case 4:
//                return new Shape(Shape.ShapeType.S);
//            case 5:
//                return new Shape(Shape.ShapeType.T);
//            case 6:
//                return new Shape(Shape.ShapeType.Z);
//            default:
//                System.out.println("Error! (GameManager:35)");
//                return null;
//        }
    }
}
