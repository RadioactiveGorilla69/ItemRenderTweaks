package com.radioactivegorilla.mixin;

import com.radioactivegorilla.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    public void checkItem(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if(entity instanceof ItemEntity itemEntity && Config.glowEnabled) {
            Item item = itemEntity.getStack().getItem();
            if(item == (Items.TOTEM_OF_UNDYING) || item == (Items.MUSHROOM_STEM) || item == (Items.BROWN_STAINED_GLASS) || item == (Items.PURPLE_GLAZED_TERRACOTTA) || item == (Items.BEACON) || item == (Items.CLOCK)  || item == (Items.IRON_BLOCK) || item == (Items.IRON_NUGGET) || item == (Items.DIAMOND_AXE) || item == (Items.IRON_HORSE_ARMOR) || item == (Items.END_ROD) || item == (Items.BREWING_STAND) || item == (Items.PINK_TULIP)) {
                cir.setReturnValue(true);
            }
        }
    }
}