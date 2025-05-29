package com.radioactivegorilla.mixin;

import com.radioactivegorilla.config.Config;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.entity.state.ItemEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityRendererMixin {
    @Unique
    private static final ThreadLocal<ItemEntity> currentItemEntity = new ThreadLocal<>();

    @Inject(method = "updateRenderState", at = @At("HEAD"))
    public void modifyUpdateRenderState(ItemEntity itemEntity, ItemEntityRenderState itemEntityRenderState, float f, CallbackInfo ci) {
        currentItemEntity.set(itemEntity);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void scaleItems(ItemEntityRenderState itemEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        ItemEntity itemEntity = currentItemEntity.get();
        if (itemEntity == null) return;

        String itemIdString = Registries.ITEM.getId(itemEntity.getStack().getItem()).toString();
        String displayName = itemEntity.getStack().getName().getString();

        if (Config.affectedItemsList.contains(itemIdString) || Config.affectedItemsList.contains(displayName)) {
            matrixStack.scale(Config.itemScale, Config.itemScale, Config.itemScale);
        }
    }
}
