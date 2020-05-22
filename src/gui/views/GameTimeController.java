package gui.views;


import fruitninja.gametypes.ArcadeGame;


import gui.customcontrols.PlaySoundCommand;
import javafx.animation.Animation;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class GameTimeController {

    private Timeline timeline;
    private Integer gameTime;
    private Label gameTimeLabel;
    private ArcadeGame game;
    private boolean styleChanged;

    public GameTimeController(ArcadeGame game, Label gameTimeLabel) {
        this.game = game;
        this.gameTime = game.getTime();
        this.gameTimeLabel = gameTimeLabel;
        styleChanged = false;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e-> updateTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void updateTime() {

        if(game.getTime() == 0) return;

        if(gameTime <= 10 && !styleChanged) {
            styleChanged = true;
            gameTimeLabel.setStyle("-fx-text-fill: linear-gradient(#ff1a1a, #cc0000); -fx-font-size: 50; -fx-font-weight: bold");
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), gameTimeLabel);
            scaleTransition.setToX(1.5);
            scaleTransition.setToY(1.5);
            scaleTransition.setAutoReverse(true);
            scaleTransition.setCycleCount(Animation.INDEFINITE);
            scaleTransition.play();
        }

        if(gameTime > 0) {

            if(gameTime <= 10) {
                new PlaySoundCommand("/resources/sound/count-down.mp3").execute();
            }
            gameTime--;
        }

        gameTimeLabel.setText(gameTime.toString());
    }
}
