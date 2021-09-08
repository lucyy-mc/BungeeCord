package net.md_5.bungee.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.net.InetSocketAddress;

public class CommandProxy extends Command
{
    public CommandProxy() {
        super("proxy");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /proxy ip:port");
            return;
        }
        try {
            String[] splitIp = args[0].split(":");
            InetSocketAddress socketAddress;
            if (splitIp.length == 1) socketAddress = new InetSocketAddress(splitIp[0], 25565);
            else socketAddress = new InetSocketAddress(splitIp[0], Integer.parseInt(splitIp[1]));
            ServerInfo info = ProxyServer.getInstance().constructServerInfo(null, socketAddress, "bungeespoof", false);
            ProxyServer.getInstance().getServers().put("bungeespoof", info);
            sender.sendMessage(ChatColor.GREEN + "Proxy target set");
            if (sender instanceof ProxiedPlayer) ((ProxiedPlayer) sender).connect(info);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Error - check log for details");
            e.printStackTrace();
        }
    }
}
