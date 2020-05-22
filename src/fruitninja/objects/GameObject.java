package fruitninja.objects;

import fruitninja.gametypes.ClassicGame;
import fruitninja.Game;
import java.util.Observable;
import java.util.Random;

public abstract class GameObject extends Observable implements fruitninja.GameObject {

    private GameObject gameObject;
    private boolean movedOffScreen;
    private boolean firstAppear = true;

    class GameObjectMovement implements Runnable {

        @Override
        public void run() {
            int newOriginX = x;
            int newOriginY = y;

            int velocity = game.getDifficulty().getVelocity(getGame().getPassedTime());
            double angle = Math.PI / 2 + ((x > game.getDimensions().getWidth() / 2) ? 0.3 : -0.3);

            double currentMaxHeight = getMaxHeight() - RANDOM.nextInt(getMaxHeight() / 5);
            while (!hasMovedOffScreen() || firstAppear) {
                firstAppear = false;
                double time = (game.getPassedTime() - timeOnCreation) / 1000.0; // Convert to seconds.
                // g = v^2 sin^2(alpha) / (2 * HEIGHT);
                double g = velocity * velocity * Math.sin(angle) * Math.sin(angle) / (2 * currentMaxHeight);
                x = (int) (velocity * Math.cos(angle) * time) + newOriginX;
                y = newOriginY - (int) (velocity * Math.sin(angle) * time - 0.5 * g * time * time);
                setChanged();
                notifyObservers();
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            setMovedOffScreen(true);
        }
    }

    private static final Random RANDOM = new Random();
    private volatile int x;
    private volatile int y;
    private volatile Game game;
    private volatile long timeOnCreation;

    public void setMovedOffScreen(boolean movedOffScreen) {
        if(game instanceof ClassicGame) {
            this.movedOffScreen = movedOffScreen;
            setChanged();
            notifyObservers(this);
        }

    }

    protected GameObject(Game game) {
        this.game = game;
        this.x = RANDOM.nextInt(game.getDimensions().getWidth());
        this.y = game.getDimensions().getHeight();
        this.timeOnCreation = game.getPassedTime();
        if(game instanceof ClassicGame) {
            addObserver((ClassicGame)game);
        }
        Thread thread = new Thread(new GameObjectMovement());
        thread.setDaemon(true);
        thread.start();
        gameObject = this;
    }

    public boolean isMovedOffScreen() {
        return movedOffScreen;
    }

    public Game getGame() {
        return game;
    }

    /**
     * @return X location of game object
     */
    @Override
    public int getXlocation() {
        return x;
    }

    /**
     * @return Y location of game object
     */
    @Override
    public int getYlocation() {
        return y;
    }

    /**
     * @return max Y location that the object can reach on the screen
     */
    @Override
    public int getMaxHeight() {
        return game.getDimensions().getHeight();
    }

    /**
     * @return velocity at which game object is thrown
     */
    @Override
    public int getInitialVelocity() {
        return 1;
    }

    /**
     * @return whether the object is dropped off the screen or not
     */
    @Override
    public boolean hasMovedOffScreen() {
        return (getYlocation() > game.getDimensions().getHeight());
    }


}
