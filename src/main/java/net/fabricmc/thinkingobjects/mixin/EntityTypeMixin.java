package net.fabricmc.thinkingobjects.mixin;

import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityType.class)
public class EntityTypeMixin {
    @Mutable
    @Shadow @Final public int trackTickInterval;
}
