package me.luckdeh.tpaplugin;

import me.luckdeh.tpaplugin.Commands.TpDenyCommand;
import me.luckdeh.tpaplugin.Commands.TpaCommand;
import me.luckdeh.tpaplugin.Commands.TpaHereCommand;
import me.luckdeh.tpaplugin.Commands.TpacceptCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class TpaPlugin extends JavaPlugin {

    public static Player TpaSender;
    public static Player TpaReceiver;
    public static long requestTimestamp;
    static TpaPlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        TpaPlugin.instance = this;

        getCommand("tpa").setExecutor(new TpaCommand(this));
        getCommand("tpaccept").setExecutor(new TpacceptCommand(this));
        getCommand("tpdeny").setExecutor(new TpDenyCommand());
        getCommand("tpahere").setExecutor(new TpaHereCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return true;
    }
}
