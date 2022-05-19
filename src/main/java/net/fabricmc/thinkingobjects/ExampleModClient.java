package net.fabricmc.thinkingobjects;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.thinkingobjects.classes.ThoughtObjectRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class ExampleModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ThinkingObject");
    public void onInitializeClient() {
        EntityRendererRegistry.register(ExampleMod.THOUGHT_OBJECT, (context) -> {
            return new ThoughtObjectRenderer(context);
        });
    }
}