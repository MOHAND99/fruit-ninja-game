package fruitninja.objects.factories;

import fruitninja.Game;
import fruitninja.objects.fruits.Fruit;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitFactory {
    private static final Map<String, Class<? extends Fruit>> fruitTypes = new HashMap<>();

    static {
        Reflections reflections = new Reflections("fruitninja.objects.fruits");
        for (Class<? extends Fruit> fruitClass : reflections.getSubTypesOf(Fruit.class)) {
            registerFruit(fruitClass.getSimpleName().toLowerCase(), fruitClass);
        }
    }

    private static void registerFruit(String fruitType, Class<? extends Fruit> fruitClass) {
        fruitTypes.put(fruitType, fruitClass);
    }

    public static Fruit getFruit(String fruitType, Game ninja) throws Exception {
        try {
            fruitType = fruitType.toLowerCase();
            if (fruitTypes.containsKey(fruitType)) {
                return fruitTypes.get(fruitType).getConstructor(Game.class).newInstance(ninja);
            }
            return null;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new Exception("Application bug in FruitFactory.\n" + e.getMessage());
        }
    }

    public static int createdFruits(){
        return fruitTypes.size();
    }

    public static List<String> keys(){
        return Arrays.asList(fruitTypes.keySet().toArray(new String[0]));
}

}
