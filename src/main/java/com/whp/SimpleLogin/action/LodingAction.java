package com.whp.SimpleLogin.action;

import com.whp.SimpleLogin.cache.ConfigCache;
import com.whp.SimpleLogin.cache.PlayerCache;
import com.whp.SimpleLogin.database.ConfigConnection;
import com.whp.SimpleLogin.listener.BeforeLoginListener;
import com.whp.SimpleLogin.listener.MessageListener;
import com.whp.SimpleLogin.setting.MessageSettings;
import com.whp.SimpleLogin.xcommand.LoginCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.sql.SQLException;

import static com.whp.SimpleLogin.database.DataHandler.*;
import static com.whp.SimpleLogin.setting.MessageManager.info;
import static com.whp.SimpleLogin.SimpleLogin.instance;
import static org.bukkit.Bukkit.getServer;

public class LodingAction {
    public static PlayerCache playerCache;
    public static CommandAction commandAction;
    public static ConfigCache configCache;
    public void Init() throws ClassNotFoundException, IOException, InvalidConfigurationException, SQLException {
        MessageSettings settings = new MessageSettings();
        createDataBase();
        if (ConfigConnection.getDataSource())
            info("&4已获取数据库连接");
        load();
        createTable();
        info("&4登录&5插件已生效 &5by[whp]");
    }
    public void load(){
        loadingEvents();
        loadingCommands();
        loadingConfigs();
        loadingPlayerCache();
        loadingCommandAction();
    }
    public void Desctory() {
        info("&4登录插件&5已卸载");
    }

    public void loadingEvents() {
        getServer().getPluginManager().registerEvents(new MessageListener(), instance);
        getServer().getPluginManager().registerEvents(new BeforeLoginListener(), instance);
    }

    public void loadingCommands() {
        instance.getCommand("simple").setExecutor(new LoginCommand());
        instance.getCommand("sl").setExecutor(new LoginCommand());
    }

    public void loadingConfigs() {
        configCache=new ConfigCache();
        instance.saveDefaultConfig();
        configCache.setConfig("message", new MessageSettings().getContentconfig());
    }
    public static void reloadConfigs(){
        configCache=new ConfigCache();
        instance.reloadConfig();
        configCache.setConfig("message", new MessageSettings().getContentconfig());
    }

    public void loadingPlayerCache() {
        playerCache = new PlayerCache();
    }
    public void loadingCommandAction() {
        commandAction = new CommandAction();
    }

    public static PlayerCache getPlayerCache() {
        return playerCache;
    }

    public static CommandAction getCommandAction() {
        return commandAction;
    }

    public FileConfiguration getConfigCache(String fileName) {
        return configCache.getConfig(fileName);
    }
}
