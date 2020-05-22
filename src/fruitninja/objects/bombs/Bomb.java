package fruitninja.objects.bombs;

import fruitninja.Game;
import fruitninja.objects.ObjectType;
import fruitninja.objects.GameObject;

public abstract class Bomb extends GameObject {

    protected Bomb(Game game) {
        super(game);
    }

    @Override
    public ObjectType getObjectType() {
        return ObjectType.BOMB;
    }

}
