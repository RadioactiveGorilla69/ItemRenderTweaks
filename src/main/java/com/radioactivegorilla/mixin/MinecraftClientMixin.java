package com.radioactivegorilla.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    public void checkItem(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if(entity instanceof ItemEntity itemEntity) {
            if(itemEntity.getStack().getItem() == Items.TOTEM_OF_UNDYING) {
                cir.setReturnValue(true);
            }
        }
    }
}