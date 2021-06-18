package de.jeff_media.containerclearer.handlers;

import de.jeff_media.containerclearer.ContainerClearer;
import de.jeff_media.containerclearer.LogUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;

public class ClearManager {

    private static final ContainerClearer plugin = ContainerClearer.getInstance();

    public static int clearChunk(Chunk chunk, Class<? extends Container> containerType) {

        // The number of containers cleared in this chunk
        int clearedContainers = 0;

        // Loop of all TileEntities in this chunk
        for (BlockState blockState : chunk.getTileEntities()) {

            // Only continue if the TileEntity is an instanceof the given containerType
            if(!containerType.isInstance(blockState)) continue;

            // Cast the BlockState to Container
            Container container = (Container) blockState;

            // Clear the inventory of this Container
            container.getInventory().clear();

            // Increment the counter
            clearedContainers++;

        }

        return clearedContainers;
    }

    public static int clearLoadedChunksInWorld(World world, Class<? extends Container> containerType) {

        int clearedContainers = 0;

        for (Chunk chunk : world.getLoadedChunks()) {
            clearedContainers += clearChunk(chunk, containerType);
        }

        return clearedContainers;
    }

    public static int clearLoadedChunksInAllLoadedWorlds(Class<? extends Container> containerType) {

        int clearedContainers = 0;

        for (World world : Bukkit.getWorlds()) {
            clearedContainers += clearLoadedChunksInWorld(world, containerType);
        }

        return clearedContainers;
    }
}
