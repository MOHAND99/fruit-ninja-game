package fruitninja.difficulties;

public interface Difficulty {
    int getVelocity(double time);
    int getNumberOfObjects(double time);
    double getBombsProbability(double time);
}
