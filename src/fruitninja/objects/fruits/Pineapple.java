package fruitninja.objects.fruits;

import fruitninja.Game;
import javafx.scene.image.Image;

public class Pineapple extends Fruit {
    private static Image[] images;

    public Pineapple(Game game) {
        super(game, 2);
        setImage();
    }

    private void setImage() {
        if (images == null) {
            images = new Image[2];
            images[0] = new Image("/resources/images/fruits/pineapple.png");
            images[1] = new Image("/resources/images/fruits/pineapple_sliced.png");
        }
    }

    @Override
    public Image[] getBufferedImages() {
        return images;
    }
}
