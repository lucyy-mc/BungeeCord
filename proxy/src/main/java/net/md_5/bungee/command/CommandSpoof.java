package net.md_5.bungee.command;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CommandSpoof extends Command {
    public CommandSpoof() {
        super("spoof");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage( ChatColor.BLUE + "Spoofed IP address is " + ChatColor.WHITE + BungeeCord.getInstance().spoofedAddress);
            return;
        }
        BungeeCord.getInstance().spoofedAddress = args[0];
        sender.sendMessage( ChatColor.BLUE + "Spoofed IP address set to " + ChatColor.WHITE + args[0]);

    }
}
