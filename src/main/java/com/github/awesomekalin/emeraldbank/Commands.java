package com.github.awesomekalin.emeraldbank;

import com.github.awesomekalin.emeraldbank.api.GetBankPlace;
import com.github.awesomekalin.emeraldbank.api.IsInt;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<String> tempArgs = new ArrayList<>(Arrays.asList(args));
        int argsLength = tempArgs.size();

        if (argsLength < 1) {
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
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/eb atm [name] [emeralds] [optional: iron]: Gives you the specified amount of emeralds/iron from the specified bank. If you don't want emeralds, put 0"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/eb amount [name]: Shows the amount of emeralds and iron a bank has"));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You do not have permission to run this command! If you think this is a mistake, please contact the server admin."));
                }
                return true;
            case "new":
                if (p.hasPermission("emerald.create")) {
                    if (argsLength < 2) {
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
                }
                return true;
            case "deposit":
                if (p.hasPermission("emerald.deposit")) {
                    if (argsLength < 2) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires bank name, do /eb help"));
                        return true;
                    } else {
                        if (argsLength < 3) {
                            ItemStack item = p.getInventory().getItemInMainHand();
                            if (item.getType().equals(Material.EMERALD)) {
                                int amount = item.getAmount();
                                int place = GetBankPlace.getBankPlace(args[1]);
                                if (place == -1) {
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Bank doesn't exist!"));
                                    return true;
                                }
                                Main.emeralds.set(place, amount + Main.emeralds.get(place));
                                Main.save();
                                p.getInventory().getItemInMainHand().setAmount(0);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Emeralds added!"));
                                return true;
                            } else if (item.getType().equals(Material.IRON_INGOT)) {
                                int amount = item.getAmount();
                                int place = GetBankPlace.getBankPlace(args[1]);
                                if (place == -1) {
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Bank doesn't exist!"));
                                    return true;
                                }
                                Main.iron.set(place, amount + Main.iron.get(place));
                                Main.save();
                                p.getInventory().getItemInMainHand().setAmount(0);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Iron added!"));
                                return true;
                            }
                        } else {
                            if (IsInt.isInt(args[2])) {
                                int emeralds = Integer.parseInt(args[2]);
                                int place = GetBankPlace.getBankPlace(args[1]);
                                if (place == -1) {
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Bank doesn't exist!"));
                                    return true;
                                }
                                Main.emeralds.set(place, emeralds + Main.emeralds.get(place));
                                Main.save();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Emeralds added!"));
                            }
                            if (argsLength < 4) {
                                return true;
                            } else {
                                if (IsInt.isInt(args[3])) {
                                    int iron = Integer.parseInt(args[3]);
                                    int place = GetBankPlace.getBankPlace(args[1]);
                                    if (place == -1) {
                                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Bank doesn't exist!"));
                                        return true;
                                    }
                                    Main.iron.set(place, iron + Main.iron.get(place));
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
            case "atm":
                if(p.hasPermission("emerald.atm")) {
                    if (argsLength < 2) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires bank name, do /eb help"));
                        return true;
                    }
                    if (argsLength < 3) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires emerald amount, do /eb help"));
                        return true;
                    }
                    if (!IsInt.isInt(args[2])) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires integer, do /eb help"));
                        return true;
                    }
                    int place = GetBankPlace.getBankPlace(args[1]);
                    if (place == -1) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Bank doesn't exist!"));
                        return true;
                    }
                    Main.emeralds.set(place, Main.emeralds.get(place) - Integer.parseInt(args[2]));
                    Main.save();
                    ItemStack stack = new ItemStack(Material.EMERALD, Integer.parseInt(args[2]));
                    p.getInventory().addItem(stack);

                    if (argsLength < 4) {
                        if (!IsInt.isInt(args[3])) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires integer, do /eb help"));
                            return true;
                        }
                        place = GetBankPlace.getBankPlace(args[1]);
                        if (place == -1) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Bank doesn't exist!"));
                            return true;
                        }
                        Main.iron.set(place, Main.iron.get(place) - Integer.parseInt(args[3]));
                        Main.save();
                        ItemStack stack2 = new ItemStack(Material.IRON_INGOT, Integer.parseInt(args[3]));
                        p.getInventory().addItem(stack2);
                    }
                    return true;
                }
            case "amount":
                if (p.hasPermission("emerald.amount")) {
                    if (argsLength < 2) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cRequires bank name, do /eb help"));
                        return true;
                    }
                    int place = GetBankPlace.getBankPlace(args[1]);
                    if (place == -1) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Bank doesn't exist!"));
                        return true;
                    }
                    Integer emeralds = Main.emeralds.get(place);
                    Integer iron = Main.iron.get(place);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aInformation for bank, " + args[1] + ":"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEmeralds: " + emeralds));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aIron: " + iron));
                    return true;
                }
            default:
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Invalid command! Please do /eb help!"));
                return true;
        }
        return false;
    }
}
