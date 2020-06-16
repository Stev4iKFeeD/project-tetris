package tetris.game;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {

    private final int OFFSET = 36;
    private final Pane gamePane;
    private final Pane nextMinoPane;
    private Shape currentMino;
    private Shape nextMino;
    private Timer minoFall;
    private TimerTask fallingTask;

    private final boolean[][] field = new boolean[10][20];

    private int currentLevel = 2;


    public GameManager(Pane gamePane, Pane nextMinoPane) {
        this.gamePane = gamePane;
        this.nextMinoPane = nextMinoPane;
        nextMino = getNewMino();
        nextMino.initNextPos();
        this.nextMinoPane.getChildren().addAll(nextMino.a, nextMino.b, nextMino.c, nextMino.d);
        start();
    }

    public void start() {
        currentMino = getNewMino();
        currentMino.initPos();
        gamePane.getChildren().addAll(currentMino.a, currentMino.b, currentMino.c, currentMino.d);
        fallingTask = getFallingTask();
        minoFall = new Timer();
        minoFall.schedule(fallingTask, 420 - currentLevel * 10, 420 - currentLevel * 10);
    }

    public void stop() {
        if (minoFall != null) {
            minoFall.cancel();
        }
    }

    private TimerTask getFallingTask(){
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (!moveDown()) {
                        field[(int) currentMino.a.getX() / currentMino.SIZE][(int) currentMino.a.getY() / currentMino.SIZE] = true;
                        field[(int) currentMino.b.getX() / currentMino.SIZE][(int) currentMino.b.getY() / currentMino.SIZE] = true;
                        field[(int) currentMino.c.getX() / currentMino.SIZE][(int) currentMino.c.getY() / currentMino.SIZE] = true;
                        field[(int) currentMino.d.getX() / currentMino.SIZE][(int) currentMino.d.getY() / currentMino.SIZE] = true;
                        currentMino = nextMino;
                        currentMino.initPos();
                        gamePane.getChildren().addAll(currentMino.a, currentMino.b, currentMino.c, currentMino.d);
                        nextMinoPane.getChildren().clear();
                        nextMino = getNewMino();
                        nextMino.initNextPos();
                        nextMinoPane.getChildren().addAll(nextMino.a, nextMino.b, nextMino.c, nextMino.d);
                    }
                });
            }
        };
    }

    public void dropDown(){
        while (moveDown());
        minoFall.cancel();
        minoFall = new Timer();
        fallingTask = getFallingTask();
        minoFall.schedule(fallingTask, 0, 420 - currentLevel * 10);
    }

    public boolean moveDown() {
        if (currentMino.a.getY() + OFFSET >= gamePane.getHeight() - 1 || field[(int) currentMino.a.getX() / currentMino.SIZE][(int) (currentMino.a.getY() + OFFSET) / currentMino.SIZE]) {
            return false;
        }
        if (currentMino.b.getY() + OFFSET >= gamePane.getHeight() - 1 || field[(int) currentMino.b.getX() / currentMino.SIZE][(int) (currentMino.b.getY() + OFFSET) / currentMino.SIZE]) {
            return false;
        }
        if (currentMino.c.getY() + OFFSET >= gamePane.getHeight() - 1 || field[(int) currentMino.c.getX() / currentMino.SIZE][(int) (currentMino.c.getY() + OFFSET) / currentMino.SIZE]) {
            return false;
        }
        if (currentMino.d.getY() + OFFSET >= gamePane.getHeight() - 1 || field[(int) currentMino.d.getX() / currentMino.SIZE][(int) (currentMino.d.getY() + OFFSET) / currentMino.SIZE]) {
            return false;
        }

        currentMino.moveY(OFFSET);
        return true;
    }

    public void moveLeft() {
        if (currentMino.a.getX() - OFFSET < 0 || field[(int) (currentMino.a.getX() - OFFSET) / currentMino.SIZE][(int) (currentMino.a.getY()) / currentMino.SIZE]) {
            return;
        }
        if (currentMino.b.getX() - OFFSET < 0 || field[(int) (currentMino.b.getX() - OFFSET) / currentMino.SIZE][(int) (currentMino.b.getY()) / currentMino.SIZE]) {
            return;
        }
        if (currentMino.c.getX() - OFFSET < 0 || field[(int) (currentMino.c.getX() - OFFSET) / currentMino.SIZE][(int) (currentMino.c.getY()) / currentMino.SIZE]) {
            return;
        }
        if (currentMino.d.getX() - OFFSET < 0 || field[(int) (currentMino.d.getX() - OFFSET) / currentMino.SIZE][(int) (currentMino.d.getY()) / currentMino.SIZE]) {
            return;
        }

        currentMino.moveX(-OFFSET);
    }

    public void moveRight() {
        if (currentMino.a.getX() + OFFSET >= gamePane.getWidth() - 1 || field[(int) (currentMino.a.getX() + OFFSET) / currentMino.SIZE][(int) (currentMino.a.getY()) / currentMino.SIZE]) {
            return;
        }
        if (currentMino.b.getX() + OFFSET >= gamePane.getWidth() - 1 || field[(int) (currentMino.b.getX() + OFFSET) / currentMino.SIZE][(int) (currentMino.b.getY()) / currentMino.SIZE]) {
            return;
        }
        if (currentMino.c.getX() + OFFSET >= gamePane.getWidth() - 1 || field[(int) (currentMino.c.getX() + OFFSET) / currentMino.SIZE][(int) (currentMino.c.getY()) / currentMino.SIZE]) {
            return;
        }
        if (currentMino.d.getX() + OFFSET >= gamePane.getWidth() - 1 || field[(int) (currentMino.d.getX() + OFFSET) / currentMino.SIZE][(int) (currentMino.d.getY()) / currentMino.SIZE]) {
            return;
        }

        currentMino.moveX(OFFSET);
    }

    public void rotate() {
        Shape rotateCheckMino = currentMino.copy();
        rotateCheckMino.rotate();

        // Left edge
        while (rotateCheckMino.a.getX() < 0 ||
                rotateCheckMino.b.getX() < 0 ||
                rotateCheckMino.c.getX() < 0 ||
                rotateCheckMino.d.getX() < 0) {
            rotateCheckMino.moveX(OFFSET);
        }

        // Right edge
        while (rotateCheckMino.a.getX() >= gamePane.getWidth() - 1 ||
                rotateCheckMino.b.getX() >= gamePane.getWidth() - 1 ||
                rotateCheckMino.c.getX() >= gamePane.getWidth() - 1 ||
                rotateCheckMino.d.getX() >= gamePane.getWidth() - 1) {
            rotateCheckMino.moveX(-OFFSET);
        }

        // Bottom edge
        while (rotateCheckMino.a.getY() >= gamePane.getHeight() - 1 ||
                rotateCheckMino.b.getY() >= gamePane.getHeight() - 1 ||
                rotateCheckMino.c.getY() >= gamePane.getHeight() - 1 ||
                rotateCheckMino.d.getY() >= gamePane.getHeight() - 1) {
            rotateCheckMino.moveY(-OFFSET);
        }

        if(field[(int) (rotateCheckMino.a.getX()) / rotateCheckMino.SIZE][(int) (rotateCheckMino.a.getY()) / rotateCheckMino.SIZE]) {
            return;
        }
        if(field[(int) (rotateCheckMino.b.getX()) / rotateCheckMino.SIZE][(int) (rotateCheckMino.b.getY()) / rotateCheckMino.SIZE]) {
            return;
        }
        if(field[(int) (rotateCheckMino.c.getX()) / rotateCheckMino.SIZE][(int) (rotateCheckMino.c.getY()) / rotateCheckMino.SIZE]) {
            return;
        }
        if(field[(int) (rotateCheckMino.d.getX()) / rotateCheckMino.SIZE][(int) (rotateCheckMino.d.getY()) / rotateCheckMino.SIZE]) {
            return;
        }

        currentMino.a.setX(rotateCheckMino.a.getX());
        currentMino.a.setY(rotateCheckMino.a.getY());
        currentMino.b.setX(rotateCheckMino.b.getX());
        currentMino.b.setY(rotateCheckMino.b.getY());
        currentMino.c.setX(rotateCheckMino.c.getX());
        currentMino.c.setY(rotateCheckMino.c.getY());
        currentMino.d.setX(rotateCheckMino.d.getX());
        currentMino.d.setY(rotateCheckMino.d.getY());
        currentMino.rotationState = rotateCheckMino.rotationState ;
    }

    private Shape getNewMino() {
        return new Shape((int) (Math.random() * 7));
    }
}
