package gui.views;

import fruitninja.gametypes.ArcadeGame;
import fruitninja.gametypes.ClassicGame;
import gui.StoreDataMethods;
import gui.controllers.Authentication;
import gui.controllers.Windows;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class GameOverGui {

    private StackPane stackPane;
    private GamePane gamePane;

    public GameOverGui(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public void prepareGameOverLabel(double width, double height) throws JAXBException {
        this.stackPane = new StackPane();
        Label gameOver = new Label("GAME OVER");
        gameOver.setStyle("-fx-font-size: 110 pt; -fx-text-fill: linear-gradient(#ff1a1a, #cc0000); -fx-font-family: 'Gang of Three'");
        Font font = Font.loadFont(ClassicPane.class.getResourceAsStream("/resources/fonts/Gang of Three.ttf"), 110);
        gameOver.setFont(font);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1.5), gameOver);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setAutoReverse(true);
        stackPane.getChildren().add(gameOver);
        stackPane.setMinWidth(width);
        stackPane.setMinHeight(height);
        scaleTransition.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/gui/views/intro.fxml"));
                Stage stage = (Stage) gamePane.getScene().getWindow();
                if (stage != null) {
                    stage.setScene(new Scene(root));
                    Windows.setOnCenter(stage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        scaleTransition.play();
        saveMaxScore();
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    private void saveMaxScore() throws JAXBException {
        Authentication authentication = new Authentication();
        int gameScore = gamePane.getGame().getScore();
        if (gamePane.getGame() instanceof ClassicGame) {
            if (gameScore > gamePane.getPlayer().getBestClassicScore()) {
                authentication.getData().getPlayers().parallelStream().filter(p -> p.getUserName().
                        equals(gamePane.getPlayer().getUserName())).findFirst().get().
                        setBestClassicScore(gameScore);
                StoreDataMethods.saveDataToXml("Data.xml", authentication.getData());
            }
        } else if (gamePane.getGame() instanceof ArcadeGame) {
            if (gameScore > gamePane.getPlayer().getBestArcadeScore()) {
                authentication.getData().getPlayers().parallelStream().filter(p -> p.getUserName().
                        equals(gamePane.getPlayer().getUserName())).findFirst().get().
                        setBestArcadeScore(gameScore);
                StoreDataMethods.saveDataToXml("Data.xml", authentication.getData());
            }
        }

    }
}
