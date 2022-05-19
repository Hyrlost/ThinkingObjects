package net.fabricmc.thinkingobjects.mixin;

import net.fabricmc.thinkingobjects.ExampleMod;
import net.fabricmc.thinkingobjects.classes.ThoughtObjectEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends EntityMixin {
	public boolean showThought = true;
	public int ThoughtId = -1;

	@Inject(at = @At("HEAD"), method = "setTarget(Lnet/minecraft/entity/LivingEntity;)V")
	private void setThinkingObject(@Nullable LivingEntity target, CallbackInfo info) {
	}

	public void SetThoughtObject(MovementType movementType, Vec3d movement, @Nullable CallbackInfo info){
		if (showThought){
			var closestPlayer = MinecraftClient.getInstance().world == null ? null : MinecraftClient.getInstance().world.getClosestPlayer(((MobEntity)(Object)this), 20);
			if (closestPlayer != null) {

				showThought = false;
				ThoughtObjectEntity thought = new ThoughtObjectEntity(ExampleMod.THOUGHT_OBJECT, this.getWorld());
				thought.ParentEntityId = this.getId();
				thought.moveTo((MobEntity)(Object)this);
				ThoughtId = thought.getId();
				this.getWorld().spawnEntity(thought);
			}
		}
	}
}
