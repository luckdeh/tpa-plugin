package me.luckdeh.tpaplugin.Commands;

import me.luckdeh.tpaplugin.TpaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
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

        if (TpaPlugin.TpaSender == null) {
            receiver.sendMessage(ChatColor.RED + "No teleport request pending.");
            return true;
        }

        Player senderPlayer = TpaPlugin.TpaSender;


        senderPlayer.teleport(receiver.getLocation());

        senderPlayer.sendMessage(ChatColor.YELLOW + "Accepted teleport request from " + receiver.getDisplayName());
        receiver.sendMessage(ChatColor.YELLOW + "Teleport request accepted by " + senderPlayer.getDisplayName());
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(-0.5, 1, -0.5), 100);
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(-0.4, 1, -0.4), 100);
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(-0.3, 1, -0.3), 100);
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(-0.2, 1, -0.2), 100);
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(-0.1, 1, -0.1), 100);

        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(0.5, 1, 0.5), 100);
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(0.4, 1, 0.4), 100);
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(0.3, 1, 0.3), 100);
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(0.2, 1, 0.2), 100);
        receiver.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, receiver.getLocation().add(0.1, 1, 0.1), 100);

        TpaPlugin.TpaSender = null;

        return true;
    }
}
