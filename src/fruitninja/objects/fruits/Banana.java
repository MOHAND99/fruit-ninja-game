package fruitninja.objects.fruits;

import fruitninja.Game;
import javafx.scene.image.Image;

public class Banana extends Fruit {
    private static Image[] images;

    public Banana(Game game) {
        super(game, 1);
        setImage();
    }


    private void setImage()  {
        if (images == null) {
            images = new Image[2];
            images[0] = new Image("/resources/images/fruits/banana.png");
            images[1] = new Image("/resources/images/fruits/banana_sliced.png");
        }
    }

    @Override
    public Image[] getBufferedImages() {
        return images;
    }
}
