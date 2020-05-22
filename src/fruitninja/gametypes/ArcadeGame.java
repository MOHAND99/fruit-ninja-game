package fruitninja.gametypes;

import fruitninja.Dimensions;
import fruitninja.Game;
import fruitninja.difficulties.ArcadeDifficulty;

public class ArcadeGame extends Game {
    private static final int GAME_TIME_IN_SECONDS = 60;

    public ArcadeGame(Dimensions dimensions) {
        super(dimensions, ArcadeDifficulty.getInstance());
    }


    @Override
    public boolean isGameOver() {
        return this.getPassedTime() >= GAME_TIME_IN_SECONDS * 1000;
    }

    public void decreaseScore(int num) {
        setScore(getScore() - num);
        if (getScore() < 0) setScore(0);
        setChanged();
        notifyObservers(this);
    }
    
    public int getTime() {
        return GAME_TIME_IN_SECONDS;
    }

}
