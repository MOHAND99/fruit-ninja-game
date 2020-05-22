package fruitninja.objects.fruits;

import fruitninja.Game;
import javafx.scene.image.Image;

public class Watermelon extends Fruit {
    private static Image[] images;

    public Watermelon(Game game) {
        super(game, 3);
        setImage();
    }

    private void setImage() {
        if (images == null) {
            images = new Image[2];
            images[0] = new Image("/resources/images/fruits/watermelon.png");
            images[1] = new Image("/resources/images/fruits/watermelon_sliced.png");
        }
    }

    @Override
    public Image[] getBufferedImages() {
        return images;
    }
}
