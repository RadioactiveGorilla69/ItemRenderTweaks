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
			int time = (int)(System.currentTimeMillis() / 10) % 360; // cycle hue
			int rgb = java.awt.Color.HSBtoRGB(time / 360f, 1.0f, 1.0f);

			args.set(0, (rgb >> 16) & 0xFF);
			args.set(1, (rgb >> 8) & 0xFF);
			args.set(2, rgb & 0xFF);

		}
	}
}
