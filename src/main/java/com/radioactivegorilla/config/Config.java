package com.radioactivegorilla.config;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.*;

public class Config {
    public static boolean glowEnabled = true;
    public static boolean animatedGlow = false;
    public static float animatedGlowSpeed = 1.0f;
    public static @NotNull Color colorRGB = new Color(0xFFFFFF);
    public static float itemScale = 1.0F;

    private static final File configFile = new File("config/simpleitemglow.txt");

    public static void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            writer.write(glowEnabled + "\n");
            writer.write(animatedGlow + "\n");
            writer.write(animatedGlowSpeed + "\n");
            writer.write(String.format("0x%06X", colorRGB.getRGB() & 0xFFFFFF) + "\n");
            writer.write(itemScale + "\n");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        if(!configFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            glowEnabled = Boolean.parseBoolean(reader.readLine());
            animatedGlow = Boolean.parseBoolean(reader.readLine());
            animatedGlowSpeed = Float.parseFloat(reader.readLine());
            colorRGB = new Color(Integer.decode(reader.readLine()));
            itemScale = Float.parseFloat(reader.readLine());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
