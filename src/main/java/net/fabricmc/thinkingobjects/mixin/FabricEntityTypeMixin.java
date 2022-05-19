package net.fabricmc.thinkingobjects.mixin;

import net.fabricmc.fabric.impl.object.builder.FabricEntityType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FabricEntityType.class)
public class FabricEntityTypeMixin extends EntityTypeMixin{

    public String Thought;
    public String ParentId;

    public void SetThought(String thought){
        Thought = thought;
        this.trackTickInterval = 2;
    }

    public void SetParentId(String parentId){
        ParentId = parentId;
        this.trackTickInterval = 2;
    }
}
