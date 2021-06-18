package de.jeff_media.containerclearer;

import de.jeff_media.containerclearer.commands.ClearContainersCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ContainerClearer extends JavaPlugin {


    private static ContainerClearer instance;

    public static ContainerClearer getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Objects.requireNonNull(getCommand("clearcontainers")).setExecutor(new ClearContainersCommand());
    }

}
