package fruitninja.objects.bombs.classicbomb;

import fruitninja.gametypes.ClassicGame;
import fruitninja.objects.bombs.Bomb;
import javafx.scene.image.Image;

public class DangerousLiveBomb extends Bomb {
    private boolean sliced;
    private static Image[] images;
    private ClassicGame classicGame;
    public DangerousLiveBomb(ClassicGame classicGame) {
        super(classicGame);
        this.classicGame =classicGame;
        setImage();
    }


    @Override
    public boolean isSliced() {
        return sliced;
    }

    @Override
    public void slice() {
        if (sliced) return;
        sliced = true;
        classicGame.decreaseLives(1);
        setChanged();
        notifyObservers();
    }

    private void setImage() {
        if (images == null) {
            images = new Image[2];
            images[0] = new Image("/resources/images/bombs/DangerousBomb.gif");
            images[1] = new Image ("/resources/images/bombs/DangerousBomb.gif");
        }
    }

    @Override
    public Image[] getBufferedImages() {
        return images;
    }


}

