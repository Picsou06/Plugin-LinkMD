package fr.picsou06.LinkDiscordMinecraft.components.listener;

import fr.picsou06.LinkDiscordMinecraft.components.listener.joinPlayer.JoinPlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager {

    public ListenerManager(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new JoinPlayerListener(), plugin);
    }
}
