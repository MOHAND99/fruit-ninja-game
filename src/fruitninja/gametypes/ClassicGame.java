package fruitninja.gametypes;

import fruitninja.Dimensions;
import fruitninja.Game;
import fruitninja.difficulties.ClassicDifficulty;
import fruitninja.objects.GameObject;
import fruitninja.objects.fruits.Fruit;

import java.util.Observable;
import java.util.Observer;


public class ClassicGame extends Game implements Observer {

    private int lives = 3;

    public ClassicGame(Dimensions dimensions) {
        super(dimensions, ClassicDifficulty.getInstance());
    }


    public void decreaseLives(int lives) {//TODO decorator or setLives method lives is positive or negative
        this.lives -= lives;
        setChanged();
        notifyObservers(lives);
    }


    @Override
    public boolean isGameOver() {
        return this.getLives() <= 0;
    }

    public int getLives() {
        return lives;
    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg instanceof Fruit) {
            GameObject gameObject = (GameObject) arg;
            if(gameObject.isMovedOffScreen() && !gameObject.isSliced()) {
                decreaseLives(1);
            }
        }
    }
}
