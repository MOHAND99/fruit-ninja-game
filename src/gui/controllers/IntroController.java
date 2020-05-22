package gui.controllers;

import com.jfoenix.controls.JFXButton;
import gui.customcontrols.PlaySoundCommand;
import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class IntroController{
    @FXML
    private JFXButton startButton;
    @FXML
    private JFXButton playButton;

    public void initialize() {

        Thread songThread = new Thread(this::run);
        songThread.setDaemon(true);
        songThread.start();
    }

    public void goToSignInScreen() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/gui/views/sign-in-screen.fxml"));

        Stage mainStage = (Stage) (startButton.getScene().getWindow());
        mainStage.setScene(new Scene(root));
        mainStage.setResizable(false);

        Windows.setOnCenter(mainStage);
    }

    public void goToModeScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/views/add_user_scene.fxml"));
        Scene scene = new Scene(root);
        Stage mainStage = (Stage) (playButton.getScene().getWindow());
        mainStage.setScene(scene);
        Windows.setOnCenter(mainStage);

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

    private void run() {
        new PlaySoundCommand("/resources/sound/fruit-ninja-music.mp3").execute();
    }
}
