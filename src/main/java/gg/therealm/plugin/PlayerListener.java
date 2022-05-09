package gg.therealm.plugin;
/*
 * --------------------
 * Authored by: Carter
 * Timestamp: 5/9/2022
 * --------------------
 * Edit by: No one, yet.
 * Timestamp: nil
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerListener implements Listener {

    private ArrayList<String> badWords = new ArrayList<String>() {{
        add("shit");
        add("fuck");
        add("reyzr");
        add("damn");
        add("kevster");
    }};

    private boolean isBadMessage(String msg) {
        return badWords.stream().anyMatch(msg::contains);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        evt.setJoinMessage("Test test join message test");
    }

    @EventHandler
    public void onPlayerThrowEgg(PlayerEggThrowEvent evt) {
        Egg egg = evt.getEgg();
        Player reyzr = Bukkit.getServer().getPlayer("Reyzr");
        egg.setPassenger((Entity)reyzr);
        egg.setPassenger(evt.getPlayer());
        Bukkit.getServer().broadcastMessage("egg :3");
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        if (this.isBadMessage(e.getMessage().toLowerCase())) {
            e.getPlayer().getWorld().strikeLightning(e.getPlayer().getLocation());
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "This is a Mormon server, please no cursing :)");
            e.getPlayer().teleport(Bukkit.getWorld("world_nether").getSpawnLocation());
            Bukkit.getServer()
                    .getPlayer("Reyzr").teleport(Bukkit.getWorld("world_nether").getSpawnLocation());
        }
    }
}
