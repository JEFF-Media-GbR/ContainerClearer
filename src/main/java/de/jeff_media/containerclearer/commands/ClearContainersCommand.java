package de.jeff_media.containerclearer.commands;

import de.jeff_media.containerclearer.ClassUtils;
import de.jeff_media.containerclearer.ContainerClearer;
import de.jeff_media.containerclearer.handlers.ClearManager;
import org.bukkit.block.Container;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClearContainersCommand implements CommandExecutor, TabCompleter {

    private final ContainerClearer main = ContainerClearer.getInstance();

    public ClearContainersCommand() {
        Objects.requireNonNull(main.getCommand("clearcontainers")).setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        // Number of arguments has to be either one or two
        if (args.length != 1 && args.length != 2) {
            return false;
        }

        // If no container type is specified, assume they wanna clear "all" containers
        if(args.length==1) {
            args = new String[]{args[0], "all"};
        }

        // Only players can run this command, except if the whole server should be cleared
        if (!(commandSender instanceof Player) && !args[0].equals("server")) {
            commandSender.sendMessage("This command can only be run by players.");
            return true;
        }

        Player player = (Player) commandSender;
        int clearedContainers = 0;
        Class<? extends Container> containerClass;

        try {
            containerClass = ClassUtils.getContainerType(args[1]);
        } catch (IllegalArgumentException exception) {
            commandSender.sendMessage("Unknown container type: " + args[1]);
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "server":
                clearedContainers = ClearManager.clearLoadedChunksInAllLoadedWorlds(containerClass);
                break;

            case "chunk":
                clearedContainers = ClearManager.clearChunk(player.getLocation().getChunk(), containerClass);
                break;

            case "world":
                clearedContainers = ClearManager.clearLoadedChunksInWorld(player.getWorld(), containerClass);
                break;



            default:
                return false;
        }

        player.sendMessage("Cleared " + clearedContainers + " containers of type \"" + containerClass.getSimpleName() + "\"");
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length<2) {
            return Arrays.asList("chunk", "world", "server");
        }
        if(args.length==2) {
            return Arrays.asList("all","barrel", "blastfurnace", "brewingstand", "chest", "dispenser", "dropper", "furnace", "hopper", "shulkerbox", "smoker");
        }
        return null;
    }
}
