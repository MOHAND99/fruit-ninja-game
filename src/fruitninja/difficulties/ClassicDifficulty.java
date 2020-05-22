package fruitninja.difficulties;

public class ClassicDifficulty implements Difficulty {
    private static ClassicDifficulty instance = null;
    private final int STATRING_VELOCITY = 170;
    private final int MAX_VELOCITY = 1500;
    private final int MAX_NUMBER_OF_OBJECT = 5;
    private final double MAX_BOMB_PROPABILITY = 0.3;
    private ClassicDifficulty() {
    }
    public synchronized static ClassicDifficulty getInstance() {
        // The method is synchronized to make this singleton a thread-safe one.
        // (Our application isn't a multi-threading one)
        if (instance == null)
            instance = new ClassicDifficulty();
        return instance;
    }
    @Override
    public int getVelocity(double time) {
        int min = (int) Math.min(Math.pow(timeInSec(time),1.7)+STATRING_VELOCITY,MAX_VELOCITY);
        //System.out.println(min);
        return min;
    }

    @Override
    public int getNumberOfObjects(double time) {
        //Approximately increse by 1 every 4 sec
        int min = (int) Math.min(timeInSec(time)*0.4,MAX_NUMBER_OF_OBJECT);
        //System.out.println(min);
        return min;
    }

    @Override
    public double getBombsProbability(double time) {
        //increse 0.06 every 2 sec
        double min =  Math.min(timeInSec(time)*0.015,MAX_BOMB_PROPABILITY);
        //System.out.println(min);
        return min;
    }

    private double timeInSec(double time){
        return time/1000;
    }
}
