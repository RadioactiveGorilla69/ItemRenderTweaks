package com.radioactivegorilla.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.awt.*;

public class ConfigScreen {
    public static Screen getConfigScreen(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Simple Item Glow Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Settings"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Enable Glow"))
                                .description(OptionDescription.of(Text.literal("Toggle the glow effect")))
                                .binding(true, () -> Config.glowEnabled, val -> Config.glowEnabled = val)
                                .controller(BooleanControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("RGB Glow"))
                                .description(OptionDescription.of(Text.literal("Chroma RGB glow")))
                                .binding(false, () -> Config.animatedGlow, val -> Config.animatedGlow = val)
                                .controller(BooleanControllerBuilder::create)
                                .build())
                        .option(Option.<Float>createBuilder()
                                .name(Text.literal("Glow Speed"))
                                .description(OptionDescription.of(Text.literal("Adjust speed of Chroma RGB glow (0.1x to 5.0x)")))
                                .binding(1.0f, () -> Config.animatedGlowSpeed, val -> Config.animatedGlowSpeed = val)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .range(0.1f, 5.0f)
                                        .step(0.1f)
                                        .formatValue(val -> Text.literal(String.format("%.1fx", val))))
                                .build())
                        .option(Option.<Color>createBuilder()
                                .name(Text.literal("Glow Color"))
                                .description(OptionDescription.of(Text.literal("Choose a static glow color")))
                                .binding(new Color(0xFFFFFF), () -> Config.colorRGB, val -> Config.colorRGB = val)
                                .controller(ColorControllerBuilder::create)
                                .build())
                        .option(Option.<Float>createBuilder()
                                .name(Text.literal("Item Scale"))
                                .description(OptionDescription.of(Text.literal("Scale factor for items (0.5x to 10.0x)")))
                                .binding(1.0f, () -> Config.itemScale, val -> Config.itemScale = val)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .range(0.5f, 10.0f)
                                        .step(0.1f)
                                        .formatValue(val -> Text.literal(String.format("%.1fx", val))))

                                .build())
                        .build())
                .save(Config::save)
                .build()
                .generateScreen(parent);
    }
}
