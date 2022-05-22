package net.fabricmc.thinkingobjects.classes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Thought {
    public String Name;
    public Item Item;

    public Thought(String name, Item item){
        Name = name;
        Item = item;
    }

    public ItemStack GetStackItem(){
        ItemStack stack = new ItemStack(Item);

        return stack;
    }
}
