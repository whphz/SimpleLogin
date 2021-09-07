package com.whp.SimpleLogin.setting;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.whp.SimpleLogin.setting.MessageManager.info;
import static com.whp.SimpleLogin.SimpleLogin.instance;

public class MessageSettings {
    private static File file;
    private static FileConfiguration Contentconfig;

    /**
     * 用于加载message.yml中的配置文件.
     */
    public MessageSettings(){
        saveDefaultStoreConfig();
        Contentconfig=YamlConfiguration.loadConfiguration(file);
        loadDefaults(file.getName());
    }

    private void loadDefaults(String filename) {
        InputStream in = instance.getResource(filename);
        if(in==null)
            info("&c加载message.yml失败");
        Reader resource=new InputStreamReader(in, StandardCharsets.UTF_8);
        Contentconfig.setDefaults(YamlConfiguration.loadConfiguration(resource));
    }

    public void reload(){
        saveDefaultStoreConfig();
        loadDefaults(file.getName());
    }

    public static void saveDefaultStoreConfig() {
        if (file == null) file = new File(instance.getDataFolder(), "message.yml");
        info(instance.getDataFolder().getName());
        if (!file.exists()) {
            instance.saveResource("message.yml", false);
        }
    }
    public  FileConfiguration getContentconfig(){
        if(Contentconfig==null)
            reload();
        return Contentconfig;
    }
}
