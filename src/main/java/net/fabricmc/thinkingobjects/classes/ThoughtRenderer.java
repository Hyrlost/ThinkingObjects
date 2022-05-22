//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.fabricmc.thinkingobjects.classes;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public abstract class ThoughtRenderer<T extends Entity, M extends EntityModel<T>> extends EntityRenderer<T> implements FeatureRendererContext<T, M> {
    protected M model;

    public ThoughtRenderer(EntityRendererFactory.Context ctx, M entityModel) {
        super(ctx);
        this.model = entityModel;
    }

    public M getModel() {
        return this.model;
    }
}
