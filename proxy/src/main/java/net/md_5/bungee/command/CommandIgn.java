package net.md_5.bungee.command;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CommandIgn extends Command {
    public CommandIgn() {
        super("ign");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage( ChatColor.BLUE + "Spoofed IGN is " + ChatColor.WHITE + BungeeCord.getInstance().spoofedIgn);
            return;
        }
        BungeeCord.getInstance().spoofedIgn = args[0];
        sender.sendMessage( ChatColor.BLUE + "Spoofed IGN set to " + ChatColor.WHITE + args[0]);

    }
}
