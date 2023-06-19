package fr.picsou06.LinkDiscordMinecraft;

import fr.picsou06.LinkDiscordMinecraft.components.listener.ListenerManager;
import fr.picsou06.LinkDiscordMinecraft.mysql.DatabaseManager;
import fr.picsou06.LinkDiscordMinecraft.utils.Commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main INSTANCE;
    public DatabaseManager database;

    @Override
    public void onEnable(){
        INSTANCE = this;
        database = new DatabaseManager("185.157.247.93", 3306, "MinecraftLink", "pluginLink", "P@ssword1"); //tema le mdp de qualité
        database.connection();
       // createCommand(new SimpleCommand("resetlink", "", new CommandResetLink()));
        new ListenerManager(this);
    }

    @Override
    public void onDisable(){
        database.deconnexion();
    }

    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer();
        server.getCommandMap().register(getName(), command);
    }


}