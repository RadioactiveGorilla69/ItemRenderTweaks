package com.radioactivegorilla.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(WorldRenderer.class)
public class SimpleItemGlowMixin {

	@ModifyArgs(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/OutlineVertexConsumerProvider;setColor(IIII)V"))
	public void modifyGlowColor(Args args, @Local Entity entity) {
		if(entity instanceof ItemEntity) {
			int color = 0x00FF00;
			args.set(0, ColorHelper.getRed(color));
			args.set(1, ColorHelper.getGreen(color));
			args.set(2, ColorHelper.getBlue(color));
			}
	}
}
