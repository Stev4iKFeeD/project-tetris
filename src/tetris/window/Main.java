package tetris.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Launch class
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = FXMLLoader.load(getClass().getResource("tetris.fxml")); // Load from tetris.fxml
        primaryStage.getIcons().add(new Image("assets/icon.png"));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
