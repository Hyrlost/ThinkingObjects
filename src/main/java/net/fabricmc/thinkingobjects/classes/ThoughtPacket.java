package net.fabricmc.thinkingobjects.classes;

import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import java.lang.reflect.Method;

public class ThoughtPacket extends EntitySpawnS2CPacket {
    String ThoughtName;

    public ThoughtPacket(ThoughtObjectEntity entity, String thoughtName){
        this(entity, 0);
        ThoughtName = thoughtName;
        EntityType oldEntityType = entity.getType();

        try{
            Method method = oldEntityType.getClass().getDeclaredMethod("SetThought", String.class);
            method.setAccessible(true);
            method.invoke(oldEntityType, ThoughtName);
        }catch (Exception e){
        }
    }

    public ThoughtPacket(ThoughtObjectEntity entity, int entityData) {
        super(entity.getId(), entity.getUuid(), entity.getX(), entity.getY(), entity.getZ(), entity.getPitch(), entity.getYaw(), entity.getType(), entityData, entity.getVelocity());
    }
}
