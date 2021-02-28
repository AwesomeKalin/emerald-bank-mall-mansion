package com.github.awesomekalin.emeraldbank;

import com.github.awesomekalin.emeraldbank.api.IsInt;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

        switch (args[0]) {
            case "help":
                if (p.hasPermission("emerald.help")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6EmeraldBank Help!"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/eb help: Opens this menu"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/eb new [name]: Creates a new bank"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/eb deposit [name] [optional: emeralds] [optional: iron]: Deposit emeralds or iron to the specified bank. If no amount is specified, then the amount of emeralds/iron you are holding is deposited"));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You do not have permission to run this command! If you think this is a mistake, please contact the server admin."));
                }
                return true;
            case "new":
                if (p.hasPermission("emerald.create")) {
                    if (args[1] == null || args[1].length() == 0) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires name, do /eb help"));
                        return true;
                    }
                    Main.banks.add(args[1]);
                    Main.emeralds.add(0);
                    Main.iron.add(0);
                    Main.save();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Bank created!"));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You do not have permission to run this command! If you think this is a mistake, please contact the server admin."));
                    return true;
                }
                return true;
            case "deposit":
                if (p.hasPermission("emerald.deposit")) {
                    if (args[1] == null || args[1].length() == 0) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires bank name, do /eb help"));
                        return true;
                    } else {
                        if (args[2] == null || args[2].length() == 0) {
                            ItemStack item = p.getInventory().getItemInMainHand();
                            if (item.getType().equals(Material.EMERALD)) {
                                int amount = item.getAmount();
                                int place = 0;
                                int i = 0;
                                boolean found = false;
                                while (!found) {
                                    if (Main.banks.get(i).equals(args[1])) {
                                        place = i;
                                        found = true;
                                    } else {
                                        i += 1;
                                    }
                                }
                                Main.emeralds.set(place, amount + Main.emeralds.get(place));
                                Main.save();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Emeralds added!"));
                                item.setType(Material.AIR);
                                return true;
                            } else if (item.getType().equals(Material.IRON_INGOT)) {
                                int amount = item.getAmount();
                                int place = 0;
                                int i = 0;
                                boolean found = false;
                                while (!found) {
                                    if (Main.banks.get(i).equals(args[1])) {
                                        place = i;
                                        found = true;
                                    } else {
                                        i += 1;
                                    }
                                }
                                Main.iron.set(place, amount + Main.iron.get(place));
                                Main.save();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Iron added!"));
                                item.setType(Material.AIR);
                                return true;
                            }
                        } else {
                            if (IsInt.isInt(args[2])) {
                                int emeralds = Integer.parseInt(args[2]);
                                int place = 0;
                                int i = 0;
                                boolean found = false;
                                while (!found) {
                                    if (Main.banks.get(i).equals(args[1])) {
                                        place = i;
                                        found = true;
                                    } else {
                                        i += 1;
                                    }
                                }
                                Main.emeralds.set(place, emeralds + Main.emeralds.get(place));
                                Main.save();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Emeralds added!"));
                            }
                            if (args[3] == null || args[3].length() == 0) {
                                return true;
                            } else {
                                if (IsInt.isInt(args[3])) {
                                    int iron = Integer.parseInt(args[3]);
                                    int place = 0;
                                    int i = 0;
                                    boolean found = false;
                                    while (!found) {
                                        if (Main.banks.get(i).equals(args[1])) {
                                            place = i;
                                            found = true;
                                        } else {
                                            i += 1;
                                        }
                                    }
                                    Main.iron.set(place, iron + Main.emeralds.get(place));
                                    Main.save();
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Iron added!"));
                                    return true;
                                }
                            }
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You do not have permission to run this command! If you think this is a mistake, please contact the server admin."));
                    return true;
                }
                break;
            default:
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Invalid command! Please do /eb help!"));
                return true;
        }
        return false;
    }
}
