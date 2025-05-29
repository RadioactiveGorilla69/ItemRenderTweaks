package com.radioactivegorilla.config;

import me.shedaniel.clothconfig2.api.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class ConfigScreen {
    public static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("Item Render Tweaks Config"))
                .setSavingRunnable(Config::save);

        ConfigCategory general = builder.getOrCreateCategory(Text.literal("Settings"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Glow"), Config.glowEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(val -> Config.glowEnabled = val)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("RGB Glow"), Config.animatedGlow)
                .setDefaultValue(false)
                .setSaveConsumer(val -> Config.animatedGlow = val)
                .build());
        general.addEntry(entryBuilder.startFloatField(Text.literal("RGB Glow Speed (0.1 - 5.0)"), Config.animatedGlowSpeed)
                .setDefaultValue(1.0f)
                .setMin(0.1f)
                .setMax(5.0f)
                .setSaveConsumer(val -> Config.animatedGlowSpeed = val)
                .build());
        general.addEntry(entryBuilder.startColorField(Text.literal("Glow Color"), Config.colorRGB.getRGB() & 0xFFFFFF)
                .setDefaultValue(0xFFFFFF)
                .setSaveConsumer(val -> Config.colorRGB = new Color(val & 0xFFFFFF))
                .build());
        general.addEntry(entryBuilder.startFloatField(Text.literal("Item Scale (0.5 - 10.0)"), Config.itemScale)
                .setDefaultValue(1.0f)
                .setMin(0.5f)
                .setMax(10.0f)
                .setSaveConsumer(val -> Config.itemScale = val)
                .build());
        general.addEntry(entryBuilder.startStrList(Text.literal("Affected Items (example: item ID - \"minecraft:pink_tulip\" or display name - \"Pink Tulip\")"), Config.affectedItemsList)
                .setDefaultValue(new ArrayList<>())
                .setSaveConsumer(val -> Config.affectedItemsList = val)
                .build());
        return builder.build();
    }
}
