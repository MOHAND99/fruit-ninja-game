package fruitninja.gametypes;

import fruitninja.Dimensions;
import fruitninja.Game;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class GameModeFactory {

    private static HashMap<String, Class<? extends Game>> gameModes = new HashMap<>();

    private GameModeFactory() {
    }

    static {
        Reflections reflection = new Reflections("fruitninja");
        for(Class<? extends Game> gameMode : reflection.getSubTypesOf(Game.class)) {
            addGameMode(gameMode.getSimpleName().toLowerCase(), gameMode);
        }
    }

    private static void addGameMode(String gameType, Class<? extends Game> gameClass) {
        gameModes.put(gameType, gameClass);
    }

    public static Game getGame(String gameMode, Dimensions dimensions) throws Exception {
        try {
            gameMode = gameMode.toLowerCase();
            if(gameModes.containsKey(gameMode))
                return gameModes.get(gameMode).getConstructor(Dimensions.class).newInstance(dimensions);

            return null;
        }

        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            throw new Exception("Game mode bug\n" + e.getMessage());
        }

    }

}
