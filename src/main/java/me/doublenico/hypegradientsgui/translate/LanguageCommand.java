package me.doublenico.hypegradientsgui.translate;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LanguageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (LanguageManager.isLanguage(args[0])) {
            Player player = (Player) commandSender;
            LanguageManager.changePlayerLanguage(player, args[0]);
            player.sendMessage("Changed to " + args[0]);
        }
        return true;
    }
}
