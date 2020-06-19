package tetris.game;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {

    private final int OFFSET = 36;
    private final Pane gamePane;
    private final Pane nextMinoPane;
    private Shape currentMino;
    private Shape nextMino;
    private Shape fallPosMino;
    private Timer minoFall;

    private final int FIELD_SIZE_X = 10;
    private final int FIELD_SIZE_Y = 20;
    private final boolean[][] field = new boolean[FIELD_SIZE_X][FIELD_SIZE_Y];

    private int currentLevel = 2;
    private boolean gameIsRunning;

    public boolean gameIsRunning() {
        return gameIsRunning;
    }

    public GameManager(Pane gamePane, Pane nextMinoPane) {
        this.gamePane = gamePane;
        this.nextMinoPane = nextMinoPane;

        start();
    }

    public void start() {
        nextMino = getNewMino();
        nextMino.initNextPos();
        this.nextMinoPane.getChildren().addAll(nextMino.a, nextMino.b, nextMino.c, nextMino.d);
        currentMino = getNewMino();
        currentMino.initPos();
        initFallPos();
        gamePane.getChildren().addAll(currentMino.a, currentMino.b, currentMino.c, currentMino.d);
        minoFall = new Timer();
        minoFall.schedule(getFallingTask(), 420 - currentLevel * 10, 420 - currentLevel * 10);
        gameIsRunning = true;
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
                        removeFallPos();

                        field[(int) currentMino.a.getX() / currentMino.SIZE][(int) currentMino.a.getY() / currentMino.SIZE] = true;
                        field[(int) currentMino.b.getX() / currentMino.SIZE][(int) currentMino.b.getY() / currentMino.SIZE] = true;
                        field[(int) currentMino.c.getX() / currentMino.SIZE][(int) currentMino.c.getY() / currentMino.SIZE] = true;
                        field[(int) currentMino.d.getX() / currentMino.SIZE][(int) currentMino.d.getY() / currentMino.SIZE] = true;

                        clearLines();
                        currentMino = nextMino;
                        nextMinoPane.getChildren().clear();
                        currentMino.initPos();
                        if (checkGameOver()) {
                            gameOver();
                            return;
                        }
                        initFallPos();
                        gamePane.getChildren().addAll(currentMino.a, currentMino.b, currentMino.c, currentMino.d);
                        nextMino = getNewMino();
                        nextMino.initNextPos();
                        nextMinoPane.getChildren().addAll(nextMino.a, nextMino.b, nextMino.c, nextMino.d);
                    }
                });
            }
        };
    }

    public boolean checkGameOver() {
        if (currentMino.a.getY() < 0) {
            return true;
        }
        if (currentMino.b.getY() < 0) {
            return true;
        }
        if (currentMino.c.getY() < 0) {
            return true;
        }
        if (currentMino.d.getY() < 0) {
            return true;
        }

        if (field[(int) currentMino.a.getX() / currentMino.SIZE][(int) currentMino.a.getY() / currentMino.SIZE] ||
            field[(int) currentMino.b.getX() / currentMino.SIZE][(int) currentMino.b.getY() / currentMino.SIZE] ||
            field[(int) currentMino.c.getX() / currentMino.SIZE][(int) currentMino.c.getY() / currentMino.SIZE] ||
            field[(int) currentMino.d.getX() / currentMino.SIZE][(int) currentMino.d.getY() / currentMino.SIZE]) {
            currentMino.moveY(-OFFSET);
            return checkGameOver();
        }

        return false;
    }

    public void gameOver() {
        minoFall.cancel();
        removeFallPos();
        gameIsRunning = false;
        Text gameOverText = new Text(100, 200, "GAME OVER");
        gameOverText.setFill(Color.WHITE);
        gameOverText.setFont(Font.font(25));
        gamePane.getChildren().add(gameOverText);
    }

    private void initFallPos() {
        fallPosMino = currentMino.copy();
        fallPosMino.ghostize();

        gamePane.getChildren().addAll(fallPosMino.a, fallPosMino.b, fallPosMino.c, fallPosMino.d);
        updateFallPos();
    }

    private void updateFallPos() {
        fallPosMino.a.setX(currentMino.a.getX());
        fallPosMino.a.setY(currentMino.a.getY());
        fallPosMino.b.setX(currentMino.b.getX());
        fallPosMino.b.setY(currentMino.b.getY());
        fallPosMino.c.setX(currentMino.c.getX());
        fallPosMino.c.setY(currentMino.c.getY());
        fallPosMino.d.setX(currentMino.d.getX());
        fallPosMino.d.setY(currentMino.d.getY());
        while (true) {
            if (fallPosMino.a.getY() + OFFSET >= gamePane.getPrefHeight() - 1 || field[(int) fallPosMino.a.getX() / fallPosMino.SIZE][(int) (fallPosMino.a.getY() + OFFSET) / fallPosMino.SIZE]) {
                break;
            }
            if (fallPosMino.b.getY() + OFFSET >= gamePane.getPrefHeight() - 1 || field[(int) fallPosMino.b.getX() / fallPosMino.SIZE][(int) (fallPosMino.b.getY() + OFFSET) / fallPosMino.SIZE]) {
                break;
            }
            if (fallPosMino.c.getY() + OFFSET >= gamePane.getPrefHeight() - 1 || field[(int) fallPosMino.c.getX() / fallPosMino.SIZE][(int) (fallPosMino.c.getY() + OFFSET) / fallPosMino.SIZE]) {
                break;
            }
            if (fallPosMino.d.getY() + OFFSET >= gamePane.getPrefHeight() - 1 || field[(int) fallPosMino.d.getX() / fallPosMino.SIZE][(int) (fallPosMino.d.getY() + OFFSET) / fallPosMino.SIZE]) {
                break;
            }

            fallPosMino.moveY(OFFSET);
        }
    }

    private void removeFallPos() {
        gamePane.getChildren().removeAll(fallPosMino.a, fallPosMino.b, fallPosMino.c, fallPosMino.d);
    }

    public void dropDown() {
        while (moveDown());
        removeFallPos();
        minoFall.cancel();
        minoFall = new Timer();
        minoFall.schedule(getFallingTask(), 0, 420 - currentLevel * 10);
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
        updateFallPos();
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
        updateFallPos();
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

        // Top edge (just don't rotate)
        if (rotateCheckMino.a.getY() < 0 ||
                rotateCheckMino.b.getY() < 0 ||
                rotateCheckMino.c.getY() < 0 ||
                rotateCheckMino.d.getY() < 0) {
            return;
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
        currentMino.rotationState = rotateCheckMino.rotationState;
        updateFallPos();
    }

    private void clearLines() {
        for (int i = 0; i < FIELD_SIZE_Y; i++) {
            boolean fullyFilled = true;
            for (int j = 0; j < FIELD_SIZE_X; j++) {
                if (!field[j][i]) {
                    fullyFilled = false;
                    break;
                }
            }
            if (!fullyFilled) {
                continue;
            }

            ArrayList<Node> deletionNodes = new ArrayList<>();
            for (Node node : gamePane.getChildren()) {
                if (((ImageView) node).getY() == i * OFFSET) {
                    deletionNodes.add(node);
                }
            }
            for (Node node : deletionNodes) {
                gamePane.getChildren().remove(node);
            }
            for (int k = 0; k <= i; k++) {
                for (int l = 0; l < FIELD_SIZE_X; l++) {
                    field[l][k] = false;
                }
            }
            for (Node node : gamePane.getChildren()) {
                ImageView mino = (ImageView) node;
                if (mino.getY() < i * OFFSET) {
                    mino.setY(mino.getY() + OFFSET);
                    field[(int) mino.getX() / OFFSET][(int) mino.getY() / OFFSET] = true;
                }
            }
        }
    }

    private Shape getNewMino() {
        return new Shape((int) (Math.random() * 7));
    }
}
