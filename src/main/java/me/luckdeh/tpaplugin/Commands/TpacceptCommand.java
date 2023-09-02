package me.luckdeh.tpaplugin.Commands;

import me.luckdeh.tpaplugin.TpaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpacceptCommand implements CommandExecutor {
    private final TpaPlugin plugin;

    public TpacceptCommand(TpaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player receiver = (Player) sender;

        if (TpaPlugin.TpaSender == null || TpaPlugin.TpaReceiver == null) {
            receiver.sendMessage(ChatColor.RED + "No teleport request pending.");
            return true;
        }

        long currentTime = System.currentTimeMillis();

        if (currentTime - TpaPlugin.requestTimestamp > 20 * 60 * 1000) {
            receiver.sendMessage(ChatColor.RED + "Teleport request has expired.");
            TpaPlugin.TpaSender.sendMessage(ChatColor.RED + "Teleport request to " + TpaPlugin.TpaReceiver.getDisplayName() + " has expired.");
            resetRequest();
            return true;
        }

        Player senderPlayer = TpaPlugin.TpaSender;

        if (senderPlayer.getLocation().distance(receiver.getLocation()) <= 5.0) {
            senderPlayer.teleport(receiver.getLocation());
            senderPlayer.sendMessage(ChatColor.YELLOW + "Accepted teleport request from " + receiver.getDisplayName());
            receiver.sendMessage(ChatColor.YELLOW + "Teleport request accepted by " + senderPlayer.getDisplayName());
        } else {
            senderPlayer.teleport(receiver.getLocation());
            senderPlayer.sendMessage(ChatColor.YELLOW + "Accepted teleport request from " + receiver.getDisplayName());
            receiver.sendMessage(ChatColor.YELLOW + "Teleport request accepted by " + senderPlayer.getDisplayName());
        }

        resetRequest();
        return true;
    }

    private void resetRequest() {
        TpaPlugin.TpaSender = null;
        TpaPlugin.TpaReceiver = null;
        TpaPlugin.requestTimestamp = 0;
    }
}
