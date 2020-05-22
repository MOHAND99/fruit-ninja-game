package gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Player;
import gui.views.Alert;
import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import javax.xml.bind.JAXBException;

public class SignInController {

    @FXML
    JFXTextField userNameField;

    @FXML
    JFXPasswordField passwordField;
    
  

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

    private void goToAnyScreen(String path, Event e) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        ModeSceneController modeSceneController = loader.getController();
        Parent root = loader.load(getClass().getResource(path));
        Scene scene = ((JFXButton)e.getSource()).getScene();

        Stage mainStage = (Stage) (scene.getWindow());
        mainStage.setScene(new Scene(root));
        Windows.setOnCenter(mainStage);
    }

    public void goToModeScreen(Event e) throws IOException, JAXBException {
        Authentication validateUser = new Authentication();
        String userName = userNameField.getText();
        String password = passwordField.getText();
        if(validateUser.userExists(userName, password)) {
            Stage alert = Alert.getStage();
            Windows.setOnCenter(alert);
            alert.setOnCloseRequest(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/views/mode_scene.fxml"));
                    Parent root = loader.load();
                    ModeSceneController modeSceneController = loader.getController();
                    modeSceneController.setPlayer(validateUser.getData().getPlayers().parallelStream().
                                    filter(p->p.getUserName().equals(userName)).findFirst().get());
                    Scene scene = ((JFXButton)e.getSource()).getScene();
                    Stage mainStage = (Stage) (scene.getWindow());
                    if(mainStage != null)
                    mainStage.setScene(new Scene(root));
                    Windows.setOnCenter(mainStage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    public void goToMainScreen(Event e) throws IOException {
        goToAnyScreen("/gui/views/intro.fxml", e);
    }

}
