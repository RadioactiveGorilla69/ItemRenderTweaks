package com.radioactivegorilla;

import com.radioactivegorilla.config.Config;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemRenderTweaks implements ModInitializer {
    public static final String MOD_ID = "simpleitemglow";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
		//Also, if any of the code formatting looks different to how I usually code, it's because of the IntelliJ auto indenting that I did
        Config.load();
        LOGGER.info("SimpleItemGlow initialized.");
    }
    //Credit to ____Iron______ (Wynncraft Big Items) and Spyxar (GlowUp)
}