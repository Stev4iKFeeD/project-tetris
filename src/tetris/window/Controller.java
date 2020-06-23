package tetris.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tetris.game.GameManager;

/**
 * Window control class. Controls keystrokes, exit
 */
public class Controller {
    @FXML
    private Stage stage;
    @FXML
    private Pane gamePane;
    @FXML
    private Pane nextMinoPane;
    @FXML
    private StackPane startMenu;
    @FXML
    private StackPane gameOverMenu;
    @FXML
    private StackPane pauseMenu;
    @FXML
    private Label linesLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Label totalScoreLabel;
    @FXML
    private Label startLevelLabel;
    @FXML
    private Label soundLabel;
    @FXML
    private Label musicLabel;

    private GameManager gameManager;

    public void initialize() {
        Font.loadFont(getClass().getClassLoader().getResourceAsStream("assets/fonts/RotondaC-Bold.ttf"), 36); // Register font for labels
        startMenu.setVisible(true);
        gameOverMenu.setVisible(false);
        pauseMenu.setVisible(false);
        gameManager = new GameManager(gamePane, nextMinoPane, gameOverMenu, totalScoreLabel, linesLabel, scoreLabel, levelLabel);
        stage.setOnCloseRequest(event -> gameManager.stop());

        stage.getScene().setOnKeyPressed(event -> {
            if (gameManager.gameIsRunning && !gameManager.paused) {
                if (event.getCode() == KeyCode.LEFT) {
                    gameManager.moveLeft();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    gameManager.moveRight();
                } else if (event.getCode() == KeyCode.UP) {
                    gameManager.rotate();
                } else if (event.getCode() == KeyCode.DOWN) {
                    gameManager.moveDown(true);
                } else if (event.getCode() == KeyCode.SPACE) {
                    gameManager.dropDown();
                }
                gameManager.updateData();
            }
            if (gameManager.gameIsRunning) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    pause();
                }
            }
        });
        // Pause game when window loses focus
        stage.focusedProperty().addListener(observable -> {
            if (!gameManager.paused && gameManager.gameIsRunning) {
                pause();
            }
        });
    }

    // The following methods is used in buttons. For more details see tetris.fxml

    @FXML
    void changeLevel() {
        startLevelLabel.setText("LEVEL: " + gameManager.changeStartLevel());
        gameManager.updateData();
    }

    @FXML
    void play() {
        startMenu.setVisible(false);
        gameManager.updateData();
        gameManager.start();
    }

    @FXML
    void pause() {
        pauseMenu.setVisible(!gameManager.paused);
        gameManager.pauseGame();
    }

    @FXML
    void quit() {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void newGame() {
        gameManager.resetGame();
        gameOverMenu.setVisible(false);
        startMenu.setVisible(true);
    }

    @FXML
    void musicToggle() {
        gameManager.musicOn = !gameManager.musicOn;
        musicLabel.setText(gameManager.musicOn ? "MUSIC: ON" : "MUSIC: OFF");
    }

    @FXML
    void soundToggle() {
        gameManager.soundOn = !gameManager.soundOn;
        soundLabel.setText(gameManager.soundOn ? "SOUND: ON" : "SOUND: OFF");
    }
}
