package com.radioactivegorilla.mixin;

import com.radioactivegorilla.config.Config;
import net.minecraft.client.render.Frustum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.client.render.entity.EntityRenderDispatcher.class)
public class EntityRenderMixin {
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    public void increaseRenderDistance(Entity entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof ItemEntity) {
            String itemIdString = Registries.ITEM.getId(((ItemEntity) entity).getStack().getItem()).toString();
            String displayName = ((ItemEntity) entity).getStack().getName().getString();

            if (Config.affectedItemsList.contains(itemIdString) || Config.affectedItemsList.contains(displayName)) {
                //TRYING to show item as far away as possible, if it doesn't work then let me know.
                cir.setReturnValue(true);
            }
        }
    }
}