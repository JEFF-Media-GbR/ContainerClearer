package de.jeff_media.containerclearer;

import org.bukkit.block.Container;

import java.util.Locale;

public class ClassUtils {

    public static Class<? extends Container> getContainerType(String name) throws IllegalArgumentException {
        try {
            switch (name.toLowerCase(Locale.ROOT)) {
                case "all":
                    return Container.class;
                case "barrel":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.Barrel");
                case "blastfurnace":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.BlastFurnace");
                case "brewingstand":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.BrewingStand");
                case "chest":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.Chest");
                case "dispenser":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.Dispenser");
                case "dropper":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.Dropper");
                case "furnace":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.Furnace");
                case "hopper":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.Hopper");
                case "shulkerbox":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.ShulkerBox");
                case "smoker":
                    return (Class<? extends Container>) Class.forName("org.bukkit.block.Smoker");
            }
        } catch (ClassNotFoundException ignored) {

        }

        throw new IllegalArgumentException("Unknown container type: " + name);
    }

}
