package net.fabricmc.thinkingobjects.classes;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.lang.reflect.Field;

public class ThoughtObjectEntity extends Entity {

    public Thought Thought;
    public int ParentEntityId = -1;
    public ThoughtObjectEntity(EntityType type, World world) {
        super(type, world);
        String thoughtName = "";
        try {
            Field field = type.getClass().getField("Thought");
            field.setAccessible(true);
            Object value = field.get(type);

            thoughtName = (String) value;
        }catch (Exception e){
        }

        if (thoughtName == "" || thoughtName == null || !world.isClient){
            thoughtName = ThoughtHelper.getThoughtName();
        }
        Thought = ThoughtHelper.GetThoughtFromName(thoughtName);
    }
    public void spawnItem(Item thought) {
        ItemStack stack = new ItemStack(thought);
        ItemEntity itemEntity = new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), stack);
        itemEntity.setThrower(this.getUuid());
        this.world.spawnEntity(itemEntity);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new ThoughtPacket(this, Thought.Name);
    }

    public String getThoughtName() {
        return Thought.Name.toLowerCase();
    }

    public boolean IsBlockItem(){
        return ((BlockItem)Thought.Item).getBlock() != null;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        spawnItem(Thought.Item);
        this.kill();
        return ActionResult.PASS;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public boolean collides() {
        return !this.isRemoved();
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        if (!ThoughtHelper.entitiesToRemove.contains(this.getId())){
            ThoughtHelper.entitiesToRemove.add(this.getId());
        }
    }

    @Override
    public void baseTick(){
        if (ParentEntityId != -1){
            Entity parentEntity = this.world.getEntityById(ParentEntityId);
            if (parentEntity == null){
                this.kill();
            } else {
                moveTo(parentEntity);
            }
        }
    }

    public void moveTo(Entity entity){
        this.setPos(entity.getX(), entity.getBodyY(1.1), entity.getZ());
    }
}
