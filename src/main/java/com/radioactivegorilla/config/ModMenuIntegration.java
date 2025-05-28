package com.radioactivegorilla.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // This returns the screen that gets shown when the user clicks the config button
        return ConfigScreen::getConfigScreen;
    }
}
