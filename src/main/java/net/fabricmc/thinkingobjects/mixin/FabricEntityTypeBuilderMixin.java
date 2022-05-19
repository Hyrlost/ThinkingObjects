package net.fabricmc.thinkingobjects.mixin;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FabricEntityTypeBuilder.class)
public class FabricEntityTypeBuilderMixin {
    @Shadow private int trackedUpdateRate;

    @Shadow private Boolean forceTrackedVelocityUpdates;

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/entity/EntityType$EntityFactory;)V")
    private void SetTrackedUpdateRate(SpawnGroup spawnGroup, EntityType.EntityFactory factory, CallbackInfo ci) {
        this.trackedUpdateRate = 2;
        this.forceTrackedVelocityUpdates = true;
    }
}
