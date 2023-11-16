package fr.picsou06.LinkDiscordMinecraft;

import fr.picsou06.LinkDiscordMinecraft.components.listener.ListenerManager;
import fr.picsou06.LinkDiscordMinecraft.mysql.DatabaseManager;
import fr.picsou06.LinkDiscordMinecraft.utils.Commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    FileConfiguration config = getConfig();
    public static Main INSTANCE;
    public DatabaseManager database;

    @Override
    public void onEnable(){
        INSTANCE = this;
        config.options().copyDefaults(true);
        saveConfig();
        database = new DatabaseManager(config.getString("Database.Host"), config.getInt("Database.Port"), config.getString("Database.Name"), config.getString("Database.ID"), config.getString("Database.MDP"));
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
