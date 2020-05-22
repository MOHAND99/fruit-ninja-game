package gui.views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {

    private static Stage alertStage;

    static public void showMessage(String message) {

        alertStage = new Stage();

        Label messageLabel = new Label(message);

        StackPane stackPane = new StackPane(messageLabel);

        Scene messageScene = new Scene(stackPane, 300, 100);
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setScene(messageScene);
        alertStage.setTitle("Alert");
        alertStage.setResizable(false);
        alertStage.show();

    }

    public static Stage getStage() {
        return alertStage;
    }
}
