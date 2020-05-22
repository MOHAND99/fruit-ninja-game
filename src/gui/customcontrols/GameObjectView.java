package gui.customcontrols;

import fruitninja.objects.GameObject;
import gui.views.GamePane;
import gui.views.FruitRotatory;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Observable;
import java.util.Observer;

/**
 * The {@code GameObjectView} is an {@code ImageView} used to show and track the movement of a {@code GameObject}.
 */
public class GameObjectView extends ImageView implements Observer {
    private GameObject object; // volatile is important for this object to be accessed from another thread.
    private GamePane gamePane;

    public void renderEffect() throws InterruptedException {
        gamePane.getSliceEffect().setX(object.getXlocation());
        gamePane.getSliceEffect().setY(object.getYlocation());
        gamePane.getChildren().add(gamePane.getSliceEffect());
        FadeTransition fadeSlice = new FadeTransition(Duration.seconds(1), gamePane.getSliceEffect());
        fadeSlice.setFromValue(1);
        fadeSlice.setToValue(0);
        fadeSlice.play();
        Thread.sleep(1000);
        gamePane.getChildren().remove(gamePane.getSliceEffect());
    }

    public void renderImage() {
        setImage(object.getBufferedImages()[object.isSliced() ? 1 : 0]);

    }

    public GameObjectView(GameObject object, GamePane gamePane) {

        super();
        this.gamePane = gamePane;
        this.object = object;
        this.object.addObserver(this);
        setLayoutX(object.getXlocation());
        setLayoutY(object.getYlocation());
        renderImage();
        setOnMouseDragEntered(event -> {
            object.slice();
            renderImage();
//            new Thread(()-> {
//                    Platform.runLater(()-> {
//                        try {
//                            renderEffect();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    });
//
//            }).start();
        });
        FruitRotatory.rotate(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            setLayoutX(object.getXlocation());
            setLayoutY(object.getYlocation());
            if(object.hasMovedOffScreen()) setVisible(false);
        });

    }

}

