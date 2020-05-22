package fruitninja;

import entities.Player;
import fruitninja.difficulties.Difficulty;
import java.util.ArrayList;
import fruitninja.objects.GameObject;
import javafx.stage.Screen;

import java.util.Observable;
import java.util.Observer;

public abstract class Game extends Observable {
    private static Dimensions dimensions =  new Dimensions((int) Screen.getPrimary().getBounds().getHeight(), (int) Screen.getPrimary().getBounds().getWidth());
    private final Difficulty difficulty;
    protected final ObjectsGenerator generator;
    protected Player player;
    private int score;
    private long startingTime;
    private ArrayList<GameObject> gameObjects;

    // A usage of some Dependency Injection framework might be better.
    protected Game(Dimensions dimensions, Difficulty difficulty) {
        this.difficulty = difficulty;
        Game.dimensions = dimensions;
        this.startingTime = System.currentTimeMillis();
        this.generator = new ObjectsGenerator(this);
        startGame();
    }//TODO move the start game here

    private void startGame() {

        Thread gameThread = new Thread(this::run);
        gameThread.setDaemon(true);
        gameThread.start();

    }

    private void run()  {
        while (!isGameOver()) {
            try {
                generator.generate();
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setChanged();
        notifyObservers(this);
    }

    public void addGameObserver(Observer observer) {
        super.addObserver(observer);
    }

    public void addObserver(Observer observer) {
        generator.addObserver(observer);
    }


    public abstract boolean isGameOver();


    public long getPassedTime() {
        return System.currentTimeMillis() - startingTime;
    }

    public static Dimensions getDimensions() {
        return dimensions;
    }


    // BAD.
    // THIS SHOULDN'T BE PUBLIC BECAUSE THE GUI SHOULDN'T BE ABLE TO SET SCORE!!
    // TODO: Discuss the usage of Command Pattern, where the command class will be in the same package
    // with Game class. Hence, protected can be used.
    public void increaseScore(int points) {
        if (points <= 0) {
            throw new IllegalArgumentException("points must be positive.");
        }
        setChanged();
        score += points;
        notifyObservers(this);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    
    public ArrayList<GameObject> getGameObjects() {
        filterGameObjects();
        return gameObjects;
    }
    
    private void filterGameObjects(){
        gameObjects.parallelStream().filter((GameObject g)->{
            return g.isSliced()||g.hasMovedOffScreen();
        }).forEach(g -> gameObjects.remove(g));
        
    }

}
