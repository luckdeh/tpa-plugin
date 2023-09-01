package me.luckdeh.tpaplugin.Commands;

import me.luckdeh.tpaplugin.TpaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpDenyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player receiver = (Player) TpaPlugin.TpaReceiver;
            Player senderPlayer = (Player) sender;

            if (receiver != null) {
                receiver.sendMessage(ChatColor.YELLOW + senderPlayer.getDisplayName() + " denied your tp request.");
            }

            sender.sendMessage(ChatColor.YELLOW + "Denied the tp request.");

            TpaPlugin.TpaSender = null;
            TpaPlugin.TpaReceiver = null;
            TpaPlugin.requestTimestamp = 0;
        }

        return true;
    }
}
