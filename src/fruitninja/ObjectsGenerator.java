package fruitninja;

import fruitninja.Game;
import fruitninja.gametypes.ArcadeGame;
import fruitninja.gametypes.ClassicGame;
import fruitninja.objects.GameObject;
import fruitninja.objects.GameObject;
import fruitninja.objects.factories.ArcadeBombFactory;
import fruitninja.objects.factories.ClassicBombFactory;
import fruitninja.objects.factories.FruitFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class ObjectsGenerator extends Observable {

    private final Game game;
    private static final Random RANDOM = new Random();

    public ObjectsGenerator(Game game) {
        this.game = game;
    }

    public void generate() throws Exception {
        if (game.isGameOver()) return;
        int count = game.getDifficulty().getNumberOfObjects(game.getPassedTime());
        for (int i = 0; i < count; i++) {
            setChanged();
            notifyObservers(generateObject());
        }
    }


    private boolean getObjectType() {
        double bombsProbability = game.getDifficulty().getBombsProbability(game.getPassedTime());
        return RANDOM.nextFloat() > bombsProbability;
        // Assume bombsProbability = 0.3.
        // If random is > 0.3, then generate fruit. Otherwise, generate bomb.
        // If the method returned true, means fruit. If false, means bomb.
    }

    private GameObject generateObject() throws Exception {
        int fruitsCount = FruitFactory.createdFruits();
        int bombsCount;
        ArrayList<String> fruitsKeys = new ArrayList<>(fruitsCount);
        fruitsKeys.addAll(FruitFactory.keys());

        if (game instanceof ClassicGame) {
            bombsCount = ClassicBombFactory.createdBombs();
            ArrayList<String> bombsKeys = new ArrayList<>(bombsCount);
            bombsKeys.addAll(ClassicBombFactory.keys());
            if (!getObjectType()) {
                int r = ThreadLocalRandom.current().nextInt(0, bombsCount);
                return ClassicBombFactory.getBomb(bombsKeys.get(r), (ClassicGame) game);//TODO:we need to add it to the screen
            }
        } else if (game instanceof ArcadeGame) {
            bombsCount = ArcadeBombFactory.createdBombs();
            ArrayList<String> bombsKeys = new ArrayList<>(bombsCount);
            bombsKeys.addAll(ArcadeBombFactory.keys());
            if (!getObjectType()) {
                int r = ThreadLocalRandom.current().nextInt(0, bombsCount);
                return ArcadeBombFactory.getBomb(bombsKeys.get(r), (ArcadeGame) game);//TODO:we need to add it to the screen
            }
        }
        int r = ThreadLocalRandom.current().nextInt(0, fruitsCount);

        return FruitFactory.getFruit(fruitsKeys.get(r), game);//TODO:same bombs todo

    }


}
