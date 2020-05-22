package fruitninja.objects.bombs.arcadebomb;

import fruitninja.gametypes.ArcadeGame;
import fruitninja.gametypes.ClassicGame;
import fruitninja.objects.bombs.Bomb;
import javafx.scene.image.Image;

public class DangerousScoreBomb extends Bomb {
    private boolean sliced;
    private static Image[] images;
    private ArcadeGame arcadeGame;
    public DangerousScoreBomb(ArcadeGame arcadeGame) {
        super(arcadeGame);
        this.arcadeGame = arcadeGame;
        setImage();
    }
    @Override
    public boolean isSliced() {
        return sliced;
    }

    private void setImage() {
        if (images == null) {
            images = new Image[2];
            images[0] = new Image("/resources/images/bombs/DangerousBomb.gif");
            images[1] = new Image ("/resources/images/bombs/DangerousBomb.gif");
        }
    }
    @Override
    public void slice() {
        if (sliced) return;
        sliced = true;
        arcadeGame.decreaseScore(1);
    }

    @Override
    public Image[] getBufferedImages() {
        return images;
    }
}
