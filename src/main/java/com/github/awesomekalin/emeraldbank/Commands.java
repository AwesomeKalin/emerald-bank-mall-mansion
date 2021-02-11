package com.github.awesomekalin.emeraldbank;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof Player)) {
            System.out.println("Must be run as player!!!");
            return false;
        }

        Player p = (Player) sender;
        if(!command.getName().equalsIgnoreCase("emeraldbank")) {
            return false;
        }

        if (args == null || args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires arguments, do /eb for help"));
            return true;
        }

        if (args[0].equals("help")) {
            if(p.hasPermission("help")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6EmeraldBank Help!"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Currently nothing"));
                return true;
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You do not have permission to run this command! If you think this is a mistake, please contact the server admin."));
            }
        } else if (args[0].equals("new")) {
            if(p.hasPermission("create")) {
                if (args[1] == null || args[1].length() == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires name, do /eb for help"));
                    return true;
                }

            }
        }
        else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Invalid command!"));
            return true;
        }
        return false;
    }
}
