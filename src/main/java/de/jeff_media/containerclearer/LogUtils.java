package de.jeff_media.containerclearer;

import org.bukkit.Chunk;

public class LogUtils {

    public static String getName(Chunk chunk) {
        return chunk.getX() + ", " + chunk.getZ() + " @ " + chunk.getWorld().getName();
    }
}
