package gui.controllers;

import fruitninja.gametypes.GameModeFactory;
import fruitninja.gametypes.ArcadeGame;
import fruitninja.gametypes.ClassicGame;
import com.jfoenix.controls.JFXButton;
import entities.Player;
import fruitninja.*;
import gui.views.ArcadePane;
import gui.views.ClassicPane;
import gui.views.GamePane;
import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModeSceneController implements Initializable {//TODO center the Screen

    @FXML
    JFXButton backButton;

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void scaleButton(JFXButton button, double scale) {
        ScaleTransition buttonScale = new ScaleTransition(Duration.millis(1), button);
        buttonScale.setToX(scale);
        buttonScale.setToY(scale);
        buttonScale.play();
    }


    public void enlargeButton(Event e) {
        scaleButton((JFXButton) e.getSource(), 1.3);
    }

    public void minimizeButton(Event e) {
        scaleButton((JFXButton) e.getSource(), 1);
    }

    public void goBackToMainScreen() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/gui/views/intro.fxml"));
        Scene scene = new Scene(root);

        Stage mainStage = (Stage) (backButton.getScene().getWindow());
        mainStage.setScene(scene);

    }

    public void goToGameMode(String gameMode) throws Exception {

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Fruit Ninja");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreen(true);
        GamePane gamePane = null;

        Dimensions dimensions = new Dimensions((int) Screen.getPrimary().getBounds().getHeight(), (int) Screen.getPrimary().getBounds().getWidth());

        Game game = GameModeFactory.getGame(gameMode, dimensions);


        if(game instanceof ClassicGame)
            gamePane = new ClassicPane((ClassicGame) game, player);
        else if(game instanceof ArcadeGame)
            gamePane = new ArcadePane((ArcadeGame) game, player);

        gamePane.prepareGame();

        Scene scene = new Scene(gamePane);
        gamePane.setGameScene(scene);

        primaryStage.setScene(scene);
        primaryStage.show();

        Stage mainStage = (Stage) (backButton.getScene().getWindow());

        mainStage.hide();

    }

    public void goToArcadeMode() throws Exception {
        goToGameMode("ArcadeGame");
    }

    public void goToClassicMode() throws Exception{
        goToGameMode("ClassicGame");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
