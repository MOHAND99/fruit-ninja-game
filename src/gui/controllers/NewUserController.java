package gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Player;
import gui.StoreDataMethods;
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


public class NewUserController {

    @FXML JFXTextField userNameField = new JFXTextField();
    @FXML JFXTextField nameField = new JFXTextField();
    @FXML JFXPasswordField passwordField = new JFXPasswordField();
    @FXML JFXPasswordField confirmPasswordField = new JFXPasswordField();
    @FXML JFXButton signUp = new JFXButton();
    @FXML JFXButton backButton = new JFXButton();
    

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

    public void addNewPlayer() throws JAXBException {

        Authentication validateInput = new Authentication();
        String name = nameField.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();
        String confirmationPassword = confirmPasswordField.getText();

        if (validateInput.isValidAccount(name, userName, password, confirmationPassword)) {
            validateInput.getData().getPlayers().add(new Player(name, userName, password));
            StoreDataMethods.saveDataToXml("Data.xml",validateInput.getData());
        }
    }

    public void goBackToMainScreen() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/gui/views/intro.fxml"));
        Scene scene = new Scene(root);

        Stage mainStage = (Stage) (backButton.getScene().getWindow());
        mainStage.setScene(scene);

    }

}
