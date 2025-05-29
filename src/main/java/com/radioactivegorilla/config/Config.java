package com.radioactivegorilla.config;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public static boolean glowEnabled = true;
    public static boolean animatedGlow = false;
    public static float animatedGlowSpeed = 1.0f;
    public static @NotNull Color colorRGB = new Color(0xFFFFFF);
    public static float itemScale = 1.0F;
    public static List<String> affectedItemsList = new ArrayList<>();

    private static final File configFile = new File("config/itemrendertweaks.txt");

    public static void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            writer.write(glowEnabled + "\n");
            writer.write(animatedGlow + "\n");
            writer.write(animatedGlowSpeed + "\n");
            writer.write(String.format("0x%06X", colorRGB.getRGB() & 0xFFFFFF) + "\n");
            writer.write(itemScale + "\n");
            writer.write(String.join(",", affectedItemsList) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        if (!configFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            glowEnabled = Boolean.parseBoolean(reader.readLine());
            animatedGlow = Boolean.parseBoolean(reader.readLine());
            animatedGlowSpeed = Float.parseFloat(reader.readLine());
            colorRGB = new Color(Integer.decode(reader.readLine()));
            itemScale = Float.parseFloat(reader.readLine());
            String line = reader.readLine();
            if (line != null && !line.isBlank()) {
                affectedItemsList = new ArrayList<>();
                for (String id : line.trim().split("\\s*,\\s*")) {
                    affectedItemsList.add(id);
                }
            } else {
                affectedItemsList = new ArrayList<>();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}
