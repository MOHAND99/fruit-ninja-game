package fruitninja.difficulties;

public class ArcadeDifficulty implements Difficulty {

    private static ArcadeDifficulty instance = null;
    private final int STATRING_VELOCITY = 200;
    private final int MAX_VELOCITY = 1600;
    private final int MAX_NUMBER_OF_OBJECT = 4;
    private final double MAX_BOMB_PROPABILITY = 0.4;
    private ArcadeDifficulty() {
    }
    public synchronized static ArcadeDifficulty getInstance() {
        // The method is synchronized to make this singleton a thread-safe one.
        // (Our application isn't a multi-threading one)
        if ( instance== null)
            instance= new ArcadeDifficulty();
        return instance;
    }
    @Override
    public int getVelocity(double time) {
        int min =(int) Math.min(timeInSec(time) * 26 + STATRING_VELOCITY, MAX_VELOCITY);
        //System.out.println( min);
        return  min;
    }

    @Override
    public int getNumberOfObjects(double time) {
        int min =(int)Math.min(timeInSec(time)*0.45,MAX_NUMBER_OF_OBJECT);
        //System.out.println(min);
        return min;
    }

    @Override
    public double getBombsProbability(double time) {
        double min = Math.min(timeInSec(time)*0.04,MAX_BOMB_PROPABILITY);
        //System.out.println(min);
        return min;
    }
    private double timeInSec(double time){
        return time/1000;
    }
}
