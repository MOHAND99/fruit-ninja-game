package gui.customcontrols;

import fruitninja.gametypes.ClassicGame;
import fruitninja.Game;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

public class GameLivesGUI{
    private ArrayList<ImageView> livesIcons;
    private HBox heartsBox;
    private int lives;

    public GameLivesGUI() {
        heartsBox = new HBox();
        livesIcons = new ArrayList<>();
    }


    public void prepareLives(int lives) {
        this.lives = lives;
        for(int i = 0; i < lives; i++) {
            livesIcons.add(new ImageView(new Image("/resources/images/heart.png")));
        }
        heartsBox.getChildren().setAll(livesIcons);

        heartsBox.setSpacing(10);
    }

    public HBox getHeartsBox() {
        return heartsBox;
    }

    public void updateLives(int lives) {
        livesIcons.clear();
        prepareLives(lives);
    }

}
