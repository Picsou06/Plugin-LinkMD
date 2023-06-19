package fr.picsou06.LinkDiscordMinecraft.components.listener.joinPlayer;

import fr.picsou06.LinkDiscordMinecraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;
import java.util.UUID;


public class JoinPlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Main.INSTANCE.database.hasaccount(player.getUniqueId())) {
            if (Main.INSTANCE.database.IsLink(player.getUniqueId()) == 1) {

            } else {
                player.kickPlayer("§4Merci de lier votre compte Discord à votre compte minecraft! \n Code: §b" + Main.INSTANCE.database.GetCode(player.getUniqueId()) + "\n §4pour plus d'information: §bhttps://discord.gg/dreamin");
            }

        } else {
            Random random = new Random();
            int code = random.nextInt(99999 - 10000 + 1) + 10000;
            while (Main.INSTANCE.database.CodeUse(code)) {
                code = random.nextInt(99999 - 10000 + 1) + 10000;
            }
            Main.INSTANCE.database.addAccount(player.getUniqueId(), code, player.getName());
            player.kickPlayer("§4Merci de lier votre compte Discord à votre compte minecraft! \n Code: §b" + Main.INSTANCE.database.GetCode(player.getUniqueId()) + "\n §4pour plus d'information: §bhttps://discord.gg/dreamin");

        }
    }
    }