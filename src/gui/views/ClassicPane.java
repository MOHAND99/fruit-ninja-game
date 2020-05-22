package gui.views;


import fruitninja.gametypes.ClassicGame;

import entities.Player;

import fruitninja.Game;
import fruitninja.objects.GameObject;
import gui.customcontrols.GameLivesGUI;
import gui.customcontrols.GameObjectView;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javax.xml.bind.JAXBException;
import java.util.Observable;
import java.util.Observer;

public class ClassicPane extends GamePane implements Observer {

    private ClassicGame game;
    private GameLivesGUI gameLivesGUI;
    private final int remainingLength = Game.getDimensions().getWidth()-(scoreLabel.getText().length()*37);

    public ClassicPane(ClassicGame game, Player player) {

        super(game, player);

        this.game = game;
        bestScore = new Label("Best Score : " + player.getBestClassicScore());
        bestScore.setStyle("-fx-text-fill: linear-gradient( #ffa31a, #e68a00); -fx-font-size: 45; -fx-font-weight: bold");
        game.addObserver(this);
        game.addGameObserver(this);
        setStyle("-fx-background-image: url('/resources/images/cover.jpg'); -fx-background-size: cover");//TODO put in css sheet

    }

    public void prepareGame(){//TODO put the prepare game in a new Arcade and Classic classes

        gameLivesGUI = new GameLivesGUI();
        gameLivesGUI.prepareLives(game.getLives());
        heartsBox = gameLivesGUI.getHeartsBox();
        heartsBox.setMinWidth(remainingLength);
        heartsBox.setAlignment(Pos.TOP_RIGHT);
        HBox gameDataBox = new HBox(bestScore, heartsBox);
        wrappingBox.getChildren().addAll(gameDataBox, scoreBox);
        gameDataBox.setPadding(new Insets(15, 0, 0, 15));
        gameDataBox.setAlignment(Pos.TOP_CENTER);
        getChildren().add(wrappingBox);

    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            GameObjectView gameObjectView;
            if(arg instanceof GameObject) {
                gameObjectView = new GameObjectView((GameObject) arg, this);
                this.getChildren().add(gameObjectView);
            }
            gameLivesGUI.updateLives(game.getLives());
            scoreLabel.setText("Score : " + game.getScore());
            if(game.isGameOver() && arg instanceof ClassicGame) {

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
