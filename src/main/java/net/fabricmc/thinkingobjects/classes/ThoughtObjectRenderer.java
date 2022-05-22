package net.fabricmc.thinkingobjects.classes;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ThoughtObjectRenderer extends EntityRenderer<ThoughtObjectEntity> {
    ItemRenderer ItemRenderer;

    public ThoughtObjectRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        ItemRenderer = ctx.getItemRenderer();
    }

    @Override
    public Identifier getTexture(ThoughtObjectEntity entity) {
        Identifier identifier;
        if (entity.IsBlockItem()) {
            identifier = new Identifier("minecraft", "textures/block/" + entity.getThoughtName() + ".png");
        } else {
            identifier = new Identifier("minecraft", "textures/item/" + entity.getThoughtName() + ".png");
        }
        return identifier;
    }

    @Override
    public void render(ThoughtObjectEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (entity.Thought != null){
            ItemStack itemStack = entity.Thought.GetStackItem();
            matrixStack.push();
            matrixStack.translate(0,0.3,0);
            this.ItemRenderer.renderItem(itemStack, ModelTransformation.Mode.FIXED, i, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, entity.getId());
            matrixStack.pop();
        }
    }
}
