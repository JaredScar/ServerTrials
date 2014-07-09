package com.core.wolfbadger.trials.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 7/9/14
 * Time: 9:25 AM
 */
public class Main extends JavaPlugin implements Listener {
    private FileConfiguration settings;
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.settings = getConfig();
        this.settings.options().copyDefaults(true);
        this.saveDefaultConfig();
        switch (TrialType.valueOf(this.settings.getString("Trial-Type"))) {
            case DAY:
                Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(Bukkit.getOnlinePlayers().length >= 1) {
                        for(Player players : Bukkit.getOnlinePlayers()) {
                            if(!settings.getList("Excluded-Players").contains(players.getUniqueId().toString()) && !players.hasPermission("ServerTrials.bypass")) {
                                if(settings.contains("Players."+players.getUniqueId().toString()+".CurrentTime")) {
                                    Integer i = settings.getInt("Players."+players.getUniqueId().toString()+".CurrentTime");
                                    settings.set("Players."+players.getUniqueId().toString()+".CurrentTime", i+1);
                                    saveConfig();
                                } else {
                                    settings.set("Players."+players.getUniqueId().toString()+".CurrentTime", 1);
                                    saveConfig();
                                }
                                if(settings.contains("Players."+players.getUniqueId().toString()+".CurrentTime")) {
                                    if(settings.getInt("Players."+players.getUniqueId().toString()+".CurrentTime") >= settings.getInt("TimeAllowed")*86400) {
                                        //TODO Ban and Kick Player due to their time being up
                                        List<String> list = settings.getStringList("Banned-Players");
                                        list.add(players.getUniqueId().toString());
                                        settings.set("Banned-Players", list);
                                        saveConfig();
                                        players.kickPlayer(ChatColor.translateAlternateColorCodes('&', settings.getString("Messages.KickMessage")));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }, 0, 20);
                break;
            case HOUR:
                Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(Bukkit.getOnlinePlayers().length >= 1) {
                        for(Player players : Bukkit.getOnlinePlayers()) {
                            if(!settings.getList("Excluded-Players").contains(players.getUniqueId().toString()) && !players.hasPermission("ServerTrials.bypass")) {
                                if(settings.contains("Players."+players.getUniqueId().toString()+".CurrentTime")) {
                                    Integer i = settings.getInt("Players."+players.getUniqueId().toString()+".CurrentTime");
                                    settings.set("Players."+players.getUniqueId().toString()+".CurrentTime", i+1);
                                    saveConfig();
                                } else {
                                    settings.set("Players."+players.getUniqueId().toString()+".CurrentTime", 1);
                                    saveConfig();
                                }
                                if(settings.contains("Players."+players.getUniqueId().toString()+".CurrentTime")) {
                                    if(settings.getInt("Players."+players.getUniqueId().toString()+".CurrentTime") >= settings.getInt("TimeAllowed")*3600) {
                                        //TODO Ban and Kick Player due to their time being up
                                        List<String> list = settings.getStringList("Banned-Players");
                                        list.add(players.getUniqueId().toString());
                                        settings.set("Banned-Players", list);
                                        saveConfig();
                                        players.kickPlayer(ChatColor.translateAlternateColorCodes('&', settings.getString("Messages.KickMessage")));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }, 0, 20);
                break;
            case MINUTE:
                Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(Bukkit.getOnlinePlayers().length >= 1) {
                        for(Player players : Bukkit.getOnlinePlayers()) {
                            if(!settings.getList("Excluded-Players").contains(players.getUniqueId().toString()) && !players.hasPermission("ServerTrials.bypass")) {
                                if(settings.contains("Players."+players.getUniqueId().toString()+".CurrentTime")) {
                                    Integer i = settings.getInt("Players."+players.getUniqueId().toString()+".CurrentTime");
                                    settings.set("Players."+players.getUniqueId().toString()+".CurrentTime", i+1);
                                    saveConfig();
                                } else {
                                    settings.set("Players."+players.getUniqueId().toString()+".CurrentTime", 1);
                                    saveConfig();
                                }
                                if(settings.contains("Players."+players.getUniqueId().toString()+".CurrentTime")) {
                                    if(settings.getInt("Players."+players.getUniqueId().toString()+".CurrentTime") >= settings.getInt("TimeAllowed")*60) {     System.out.print(settings.getInt("Players."+players.getUniqueId().toString()+".CurrentTime"));
                                        //TODO Ban and Kick Player due to their time being up
                                        List<String> list = settings.getStringList("Banned-Players");
                                        list.add(players.getUniqueId().toString());
                                        settings.set("Banned-Players", list);
                                        saveConfig();
                                        players.kickPlayer(ChatColor.translateAlternateColorCodes('&', settings.getString("Messages.KickMessage")));
                                    }
                                }
                            }
                        }
                    }
                }
            }, 0, 20);
                break;
        }
    }
    public void unbanPlayer(String arg) {
        if(Bukkit.getPlayer(arg) == null) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(arg);
        if(this.settings.getList("Banned-Players").contains(offlinePlayer.getUniqueId().toString())) {
            List<String> list = this.settings.getStringList("Banned-Players");
            list.remove(offlinePlayer.getUniqueId().toString());
            this.settings.set("Banned-Players", list);
            this.settings.set("Players."+offlinePlayer.getUniqueId().toString()+".CurrentTime", 0);
            saveConfig();
            }
        } else {
            //Online
            Player offlinePlayer = Bukkit.getPlayer(arg);
            if(this.settings.getList("Banned-Players").contains(offlinePlayer.getUniqueId().toString())) {
                List<String> list = this.settings.getStringList("Banned-Players");
                list.remove(offlinePlayer.getUniqueId().toString());
                this.settings.set("Banned-Players", list);
                this.settings.set("Players."+offlinePlayer.getUniqueId().toString()+".CurrentTime", 0);
                saveConfig();
            }
        }
    }
    public void setExcluded(String arg) {
        if(Bukkit.getPlayer(arg) == null) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(arg);
        List<String> list = this.settings.getStringList("Excluded-Players");
        if(!list.contains(offlinePlayer.getUniqueId().toString())) {
            list.add(offlinePlayer.getUniqueId().toString());
        this.settings.set("Excluded-Players", list);
        saveConfig();
            }
        } else {
            //Online
            Player offlinePlayer = Bukkit.getPlayer(arg);
            List<String> list = this.settings.getStringList("Excluded-Players");
            if(!list.contains(offlinePlayer.getUniqueId().toString())) {
                list.add(offlinePlayer.getUniqueId().toString());
                this.settings.set("Excluded-Players", list);
                saveConfig();
            }
        }
    }
    public void unExclude(String arg) {
        if(Bukkit.getPlayer(arg) == null) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(arg);
        List<String> list = this.settings.getStringList("Excluded-Players");
        if(list.contains(offlinePlayer.getUniqueId().toString())) {
        list.remove(offlinePlayer.getUniqueId().toString());
        this.settings.set("Excluded-Players", list);
        saveConfig();
            }
        } else {
            //Online
            Player offlinePlayer = Bukkit.getPlayer(arg);
            List<String> list = this.settings.getStringList("Excluded-Players");
            if(list.contains(offlinePlayer.getUniqueId().toString())) {
                list.remove(offlinePlayer.getUniqueId().toString());
                this.settings.set("Excluded-Players", list);
                saveConfig();
            }
        }
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("trial")) {
            //Help Menu
            /* ./trial - Open help menu
./trial exclude <player> - Exclude this player from the trial.
./trial unban <player> - Unban the player who was banned because of their trial ending.
./trial unexclude <player> - Unexclude this player from the trial. */
            if(args.length == 0) {
            if(!(sender instanceof Player)) {
                System.out.print("./trial - Open help menu\n" +
                        "./trial exclude <player> - Exclude this player from the trial.\n" +
                        "./trial unban <player> - Unban the player who was banned because of their trial ending.\n" +
                        "./trial unexclude <player> - Unexclude this player from the trial.");
            } else
            if(sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.GRAY+"./trial - Open help menu\n" +
                        "./trial exclude <player> - Exclude this player from the trial.\n" +
                        "./trial unban <player> - Unban the player who was banned because of their trial ending.\n" +
                        "./trial unexclude <player> - Unexclude this player from the trial.");
                }
            }
            switch (args.length) {
                case 2:
                    if(args[0].equalsIgnoreCase("exclude")) {
                        this.setExcluded(args[1]);
                        System.out.print("Console attempting to set player excluded...");
                    } else
                    if(args[0].equalsIgnoreCase("unban")) {
                        this.unbanPlayer(args[1]);
                        System.out.print("Console attempting to set player unbanned...");
                    } else
                    if(args[0].equalsIgnoreCase("unexclude")) {
                        this.unExclude(args[1]);
                        System.out.print("Console attempting to set player un-excluded...");
                    }
                    break;
            }
        }
        return true;
    }
    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent e) {
        if(this.settings.getStringList("Banned-Players").contains(e.getUniqueId().toString())) {
            e.setKickMessage(ChatColor.translateAlternateColorCodes('&', this.settings.getString("Messages.BannedMessage")));
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
        }
    }
}
