package net.fabricmc.thinkingobjects.classes;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ThoughtHelper {
    public static List<Integer> entitiesToRemove = new ArrayList();
    public static String getThoughtName(){
        Field[] items = Items.class.getDeclaredFields();
        String randomItemName = items[ThreadLocalRandom.current().nextInt(0, items.length)].getName();
        return randomItemName;
    }

    public static Thought GetThoughtFromName(String name){
        Item randomItem;
        try {
            randomItem = getItemFromName(name);
        }catch (Exception e){
            randomItem = Items.EGG;
        }
        return new Thought(name, randomItem);
    }

    private static Item getItemFromName(String name) throws NoSuchFieldException, IllegalAccessException {
        Items items = new Items();
        Field field = items.getClass().getField(name);
        field.setAccessible(true);
        Object value = field.get(items);

        return (Item) value;
    }
}
