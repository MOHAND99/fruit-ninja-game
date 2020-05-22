package gui.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.adapted.Data;
import gui.controllers.Authentication;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

public class SignInScreen {

    //Singleton Design Pattern
    private static SignInScreen instance;
    private Scene signInScene;
    private Data data ;

    private SignInScreen() {

    }

    public void prepareScene(){

        //Panes

        VBox signInLayout = new VBox();
        Region region = new Region();
        Region region1 = new Region();
        Region region2 = new Region();
        HBox backButtonBox = new HBox();

        region.setMinHeight(15);
        region2.setMinHeight(5);

        //Labels

        Label title = new Label("Sign In");

        title.setStyle("-fx-font-size: 40");

        //Fields

        JFXTextField userNameField = new JFXTextField();
        JFXPasswordField passwordField = new JFXPasswordField();

        userNameField.setPromptText("user name");
        userNameField.setAlignment(Pos.CENTER);
        userNameField.setMaxWidth(200);
        
        passwordField.setPromptText("password");
        passwordField.setAlignment(Pos.CENTER);
        passwordField.setMaxWidth(200);

        //Buttons

        JFXButton signIn = new JFXButton("sign in");
        JFXButton backButton = new JFXButton("");
        backButton.setGraphic(new ImageView(new Image("resources/images/back-arrow.png")));
        backButtonBox.getChildren().add(backButton);
        backButtonBox.setPadding(new Insets(20));

        signInLayout.getChildren().addAll(title, region2, userNameField, passwordField, region, signIn, backButtonBox);
        signInLayout.setSpacing(20);
        signInLayout.setAlignment(Pos.CENTER);
        signInLayout.setPadding(new Insets(50, 0, 50, 0));


        signInScene = new Scene(signInLayout, 350, 350);
        signInScene.getStylesheets().add("/gui/views/add-user-style.css");

        //controls

        backButton.setOnMouseEntered(event -> {
            scaleButton(backButton, 1.3);
        });

        backButton.setOnMouseExited(event -> {
            scaleButton(backButton, 1);
        });

        signIn.setOnMouseEntered(event -> {
            scaleButton(signIn, 1.3);
        });

        signIn.setOnMouseExited(event -> {
            scaleButton(signIn, 1);
        });


        signIn.setOnAction(event -> {
            try {
                String userName = userNameField.getText();
                String password = passwordField.getText();
                
                Authentication validateUser = new Authentication();
                
                if(validateUser.userExists(userName, password)) {
                    Stage alertStage = Alert.getStage();
                    alertStage.setOnCloseRequest(e -> {
                        try {
                            goToAnyScreen("/gui/views/mode_scene.fxml");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            } catch (JAXBException ex) {
                Logger.getLogger(SignInScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        backButton.setOnAction(event -> {
            try {
                goToAnyScreen("/gui/views/intro.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static SignInScreen getInstance() {
        if(instance == null) {
            instance = new SignInScreen();
        }
        return instance;
    }

    private void goToAnyScreen(String path) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);

        Stage mainStage = (Stage) (getScene().getWindow());
        mainStage.setScene(scene);

    }

    private void scaleButton(JFXButton button, double scale) {
        ScaleTransition buttonScale = new ScaleTransition(Duration.millis(1), button);
        buttonScale.setToX(scale);
        buttonScale.setToY(scale);
        buttonScale.play();
    }

    public Scene getScene() {
        return signInScene;
    }
}
