package fruitninja.objects.bombs.classicbomb;

import fruitninja.gametypes.ClassicGame;
import fruitninja.objects.bombs.Bomb;
import javafx.scene.image.Image;

public class FatalBomb extends Bomb {
    private static Image[] images;
    private boolean slice;
    private ClassicGame classicGame;

    public FatalBomb(ClassicGame classicGame) {
        super(classicGame);
        this.classicGame=classicGame;
        setImage();
    }


    @Override
    public boolean isSliced() {
        return slice;
    }

    @Override
    public void slice() {
        if (slice) return;
        slice = true;
        classicGame.decreaseLives(classicGame.getLives() + 1); // Set lives to -1 to indicate player loss.
    }

    private void setImage() {
        if (images == null) {
            images = new Image[2];
            images[0] = new Image("/resources/images/bombs/fatal-bomb.png");
            images[1] = new Image ("/resources/images/bombs/fatal-bomb.png");
        }
    }

    @Override
    public Image[] getBufferedImages() {
        return images;
    }
}
