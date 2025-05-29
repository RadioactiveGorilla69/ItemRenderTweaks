package com.radioactivegorilla.mixin;

import com.radioactivegorilla.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    public void checkItem(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof ItemEntity itemEntity && Config.glowEnabled) {
            String itemIdString = Registries.ITEM.getId(itemEntity.getStack().getItem()).toString();
            String displayName = itemEntity.getStack().getName().getString();

            if (Config.affectedItemsList.contains(itemIdString) || Config.affectedItemsList.contains(displayName)) {
                cir.setReturnValue(true);
            }
        }
    }
}