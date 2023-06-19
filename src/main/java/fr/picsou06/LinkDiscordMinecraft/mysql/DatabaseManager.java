package fr.picsou06.LinkDiscordMinecraft.mysql;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {

    private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;

    private int port;
    private static Connection connection;

    public DatabaseManager(String host, Integer port, String database, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.database = database;
    }
    public static Connection getConnection() {
        return connection;
    }

    public void connection() {
        if (!isOnline()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" +this.database, this.userName, this.password);
                Bukkit.getConsoleSender().sendMessage("[§eDanganronpaManager§6] §aON");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addAccount(UUID uuid, int code, String pseudo){
        if (!hasaccount(uuid)){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Link (UUID_Player, code, Pseudo_Minecraft) VALUES (?, ?, ?)");
                preparedStatement.setString(1, uuid.toString());
                preparedStatement.setString(2, String.valueOf(code));
                preparedStatement.setString(3, pseudo);
                preparedStatement.execute();
                preparedStatement.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean hasaccount(UUID uuid){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT UUID_Player FROM Link WHERE UUID_Player = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean CodeUse(int code){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT code FROM Link WHERE code = ?");
            preparedStatement.setString(1, String.valueOf(code));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int IsLink(UUID uuid){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT IsLink FROM Link WHERE UUID_Player = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return rs.getInt("IsLink");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int GetCode(UUID uuid){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT code FROM Link WHERE UUID_Player = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return rs.getInt("code");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void deconnexion() {
        if (isOnline())
            try {
                connection.close();
                Bukkit.getConsoleSender().sendMessage("[§eDanganronpaManager§6] §cOFF");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public boolean isOnline() {
        try {
            if (connection == null || connection.isClosed())
                return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}