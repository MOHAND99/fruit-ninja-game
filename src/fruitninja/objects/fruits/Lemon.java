package fruitninja.objects.fruits;

import fruitninja.Game;
import javafx.scene.image.Image;


public class Lemon extends Fruit {
    private static Image[] images;

    public Lemon(Game game) {
        super(game, 1);
        setImage();
    }

    private void setImage() {
        if (images == null) {
            images = new Image[2];
            images[0] = new Image("/resources/images/fruits/lemon.png");
            images[1] = new Image ("/resources/images/fruits/lemon_sliced.png");
        }
    }

    @Override
    public Image[] getBufferedImages() {
        return images;
    }
}
