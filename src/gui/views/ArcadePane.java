package gui.views;

import fruitninja.gametypes.ArcadeGame;

import entities.Player;

import fruitninja.Game;
import fruitninja.objects.GameObject;
import gui.customcontrols.GameObjectView;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.xml.bind.JAXBException;
import java.util.Observable;
import java.util.Observer;

public class ArcadePane extends GamePane implements Observer {

    private ArcadeGame game;
    private HBox timeBox;
    private int gameTime;
    private Label timeLabel;
    private final int remainingLength = Game.getDimensions().getWidth()-(scoreLabel.getText().length()*55);


    public ArcadePane(ArcadeGame game, Player player) {

        super(game, player);

        gameTime = game.getTime();
        this.game = game;
        bestScore = new Label("Best Score : " + player.getBestArcadeScore());
        bestScore.setStyle("-fx-text-fill: linear-gradient( #ffa31a, #e68a00); -fx-font-size: 45; -fx-font-weight: bold");
        game.addObserver(this);
        game.addGameObserver(this);
        timeBox = new HBox();
        timeLabel = new Label(String.valueOf(gameTime));
        setStyle("-fx-background-image: url('/resources/images/cover.jpg'); -fx-background-size: cover");//TODO put in css file

    }

    public void prepareGame() {//TODO put the prepare game in a new Arcade and Classic classes

        new GameTimeController(game, timeLabel);

        scoreLabel.setText("Score : " + game.getScore());
        timeLabel.setStyle("-fx-text-fill: linear-gradient(#00cc00, #009900); -fx-font-size: 50; -fx-font-weight: bold");
        timeBox.setMinWidth(remainingLength);
        timeBox.getChildren().add(timeLabel);
        timeBox.setAlignment(Pos.CENTER);
        HBox gameDataBox = new HBox(bestScore, timeBox);
        wrappingBox.getChildren().addAll(gameDataBox, scoreBox);
        gameDataBox.setPadding(new Insets(15, 0, 0, 15));
        gameDataBox.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(wrappingBox);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {

            GameObjectView gameObjectView;

            if(arg instanceof GameObject) {

                gameObjectView = new GameObjectView((GameObject) arg, this);
                this.getChildren().add(gameObjectView);
            }

            scoreLabel.setText("Score : " + game.getScore());

            if(game.isGameOver() && arg instanceof ArcadeGame) {
                GameOverGui gameOverGui = new GameOverGui(this);
                try {
                    gameOverGui.prepareGameOverLabel(this.getWidth(), this.getHeight());
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                StackPane stackPane = gameOverGui.getStackPane();
                getChildren().add(stackPane);

            }
        });
    }

}
