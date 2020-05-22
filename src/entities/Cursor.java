package entities;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Cursor {

    Scene scene;

    public Cursor(Scene scene) {
        this.scene = scene;
        System.out.println(scene);
    }

    public void changeCursor() {
        Image blade = new Image("/resources/images/effects/slice-effect.png");
        scene.setCursor(new ImageCursor(blade, blade.getWidth()/2, blade.getHeight()/2));
    }

}
