package net.fabricmc.thinkingobjects.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract World getWorld();

    @Shadow public abstract Vec3d getPos();

    @Shadow public abstract double getBodyY(double heightScale);

    @Shadow public abstract int getId();

    @Shadow public World world;

    @Shadow public abstract double getX();

    @Shadow public abstract double getZ();

    @Inject(
            // the method this function is called in
            method = "move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V",
            // target the invocation of System.out.println
            at = @At(
                    value = "HEAD"
            )
    )
    public void SetThoughtObject(MovementType movementType, Vec3d movement, @Nullable CallbackInfo info){
        var asd = "asd";
    }

    @Inject(
            // the method this function is called in
            method = "interact(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;",
            // target the invocation of System.out.println
            at = @At(
                    value = "HEAD"
            )
    )
    public void respawnThoughtObject(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
    }
}
