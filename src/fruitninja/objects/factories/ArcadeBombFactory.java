package fruitninja.objects.factories;

import fruitninja.gametypes.ArcadeGame;
import fruitninja.objects.bombs.Bomb;
import org.reflections.Reflections;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArcadeBombFactory {
    private final static Map<String, Class<? extends Bomb>> bombsMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections("fruitninja.objects.bombs.arcadebomb");
        for (Class<? extends Bomb> bombClass : reflections.getSubTypesOf(Bomb.class)) {
            registerBombs(bombClass.getSimpleName().toLowerCase(), bombClass);
        }
    }

    private static void registerBombs(String bomb, Class<? extends Bomb> bombClass) {
        bombsMap.put(bomb, bombClass);
    }

    public static Bomb getBomb(String bombType, ArcadeGame arcadeGame) throws Exception {
        bombType = bombType.toLowerCase();
        if (bombsMap.containsKey(bombType)) {
            return bombsMap.get(bombType).getConstructor(ArcadeGame.class).newInstance(arcadeGame);
        }
        return null;
        //        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            throw new Exception("Application bug in BombFactory.\n" + e.getMessage());
//        }

    }

    public static int createdBombs(){
        return bombsMap.size();
    }

    public static List<String> keys(){
        return Arrays.asList(bombsMap.keySet().toArray(new String[0]));
    }
}
