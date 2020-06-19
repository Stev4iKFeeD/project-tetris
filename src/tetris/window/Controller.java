package tetris.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tetris.game.GameManager;

public class Controller {
    @FXML
    private Stage stage;
    @FXML
    private Pane gamePane;
    @FXML
    private Pane nextMinoPane;
    @FXML
    private Label linesLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;

    private GameManager gameManager;

    public void initialize() {
        Font.loadFont(getClass().getClassLoader().getResourceAsStream("assets/fonts/RotondaC-Bold.ttf"), 36);
        gameManager = new GameManager(gamePane, nextMinoPane, linesLabel, scoreLabel, levelLabel);
        stage.setOnCloseRequest(event -> gameManager.stop());

        stage.getScene().setOnKeyPressed(event -> {
            if (gameManager.gameIsRunning()) {
                if (event.getCode() == KeyCode.LEFT) {
                    gameManager.moveLeft();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    gameManager.moveRight();
                } else if (event.getCode() == KeyCode.UP) {
                    gameManager.rotate();
                } else if (event.getCode() == KeyCode.DOWN) {
                    gameManager.score++;
                    gameManager.moveDown();
                } else if (event.getCode() == KeyCode.SPACE) {
                    gameManager.dropDown();
                }
                gameManager.updateData();
            }
        });
//        stage.requestFocus();
    }
}
