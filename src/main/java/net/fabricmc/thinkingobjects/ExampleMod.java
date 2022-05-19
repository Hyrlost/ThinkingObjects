package net.fabricmc.thinkingobjects;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.thinkingobjects.classes.ThoughtHelper;
import net.fabricmc.thinkingobjects.classes.ThoughtObjectEntity;
import net.minecraft.entity.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class ExampleMod implements ModInitializer {
	public static final EntityType<ThoughtObjectEntity> THOUGHT_OBJECT = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("thinkingthoughts", "thinkingobject"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ThoughtObjectEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STOPPING.register((server -> {
			for (var serverWorld:server.getWorlds()) {
				for (var id : ThoughtHelper.entitiesToRemove) {
					var entity = serverWorld.getEntityById(id);
					if (entity != null){
						entity.kill();
					}
				}

			}
		}));
	}
}
