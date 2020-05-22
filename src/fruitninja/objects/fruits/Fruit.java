package fruitninja.objects.fruits;

import fruitninja.Game;
import fruitninja.objects.ObjectType;
import fruitninja.objects.GameObject;

public abstract class Fruit extends GameObject {
    private Game game;
    private int points;
    private boolean sliced;

    protected Fruit(Game game, int points) {
        super(game);
        this.game = game;
        this.points = points;
    }

    /**
     * @return the type of game object
     */
    @Override
    public ObjectType getObjectType() {
        return ObjectType.FRUIT;
    }

    /**
     * @return whether the object is sliced or not
     */
    @Override
    public boolean isSliced() {
        return sliced;
    }

    /**
     * it is used to slice the object
     */
    @Override
    public void slice() {
        if (sliced) return;
        sliced = true;
        game.increaseScore(points);
    }

}
