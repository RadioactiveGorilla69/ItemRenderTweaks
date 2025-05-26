package com.radioactivegorilla.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.entity.state.ItemEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        ItemStack itemStack = itemEntity.getStack();
        if (itemStack.isOf(Items.TOTEM_OF_UNDYING) || itemStack.isOf(Items.MUSHROOM_STEM) || itemStack.isOf(Items.BROWN_STAINED_GLASS) || itemStack.isOf(Items.PURPLE_GLAZED_TERRACOTTA) || itemStack.isOf(Items.BEACON) || itemStack.isOf(Items.CLOCK)  || itemStack.isOf(Items.IRON_BLOCK)) {
            matrixStack.scale(7.0F, 7.0F, 7.0F);
        }
    }
}
