package gui.views;

import fruitninja.objects.GameObject;
import gui.customcontrols.GameObjectView;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

public class FruitRotatory {


    private FruitRotatory() {
    }

    public static void rotate(GameObjectView gameObjectView) {

        gameObjectView = gameObjectView;
        RotateTransition rotateFruit = new RotateTransition(Duration.seconds(3), gameObjectView);
        rotateFruit.setCycleCount(Animation.INDEFINITE);
        rotateFruit.setByAngle(360);
        rotateFruit.play();

    }
}
