package me.luckdeh.tpaplugin.Commands;

import me.luckdeh.tpaplugin.TpaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaHereCommand implements CommandExecutor {
    private final TpaPlugin plugin;

    public TpaHereCommand(TpaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player senderPlayer = (Player) sender;

        if (args.length == 0) {
            senderPlayer.sendMessage(ChatColor.YELLOW + "Please provide a player name.");
        } else {
            String playerToTP = args[0];
            Player target = Bukkit.getPlayerExact(playerToTP);

            if (target == null) {
                senderPlayer.sendMessage(ChatColor.RED + "Player is not online.");
            } else {
                TpaPlugin.TpaSender = senderPlayer;
                TpaPlugin.TpaReceiver = target;
                TpaPlugin.requestTimestamp = System.currentTimeMillis();

                senderPlayer.sendMessage(ChatColor.YELLOW + "Tp request sent to " + target.getDisplayName() + " to teleport to your location.");
                target.sendMessage(ChatColor.YELLOW + senderPlayer.getDisplayName() + ChatColor.stripColor(" wants to teleport to your location. Use /tpaccept to accept."));

                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    if (TpaPlugin.TpaSender == senderPlayer && TpaPlugin.TpaReceiver == target) {
                        senderPlayer.sendMessage(ChatColor.RED + "Teleport request to " + target.getDisplayName() + " has expired.");
                        TpaPlugin.TpaSender = null;
                        TpaPlugin.TpaReceiver = null;
                        TpaPlugin.requestTimestamp = 0;
                    }
                }, 20 * 60);
            }
        }
        return true;
    }
}
