package tetris.window;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tetris.game.GameManager;

public class Controller {
    @FXML
    private Stage stage;
    @FXML
    private Pane gamePane;
    @FXML
    private Pane nextMinoPane;

    private GameManager gameManager;

    public void initialize() {
        gameManager = new GameManager(gamePane, nextMinoPane);
        stage.setOnCloseRequest(event -> gameManager.stop());

        stage.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.LEFT) {
                gameManager.moveLeft();
            } else if(event.getCode() == KeyCode.RIGHT) {
                gameManager.moveRight();
            } else if(event.getCode() == KeyCode.UP) {
                gameManager.rotate();
            } else if(event.getCode() == KeyCode.DOWN){
                gameManager.moveDown();
            } else if(event.getCode() == KeyCode.SPACE) {
                gameManager.dropDown();
            }
        });
//        stage.requestFocus();
    }
}
