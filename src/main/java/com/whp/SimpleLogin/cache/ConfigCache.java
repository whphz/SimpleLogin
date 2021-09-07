package com.whp.SimpleLogin.cache;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfigCache {
    public static HashMap<String, FileConfiguration> configsCache=new<String,FileConfiguration> HashMap();
        public FileConfiguration getConfig(String filename){
            FileConfiguration config;
            config=configsCache.get(filename);
            return config==null?null:config;
        }
        public boolean setConfig(String fileName,FileConfiguration config){
            configsCache.put(fileName,config);
            return true;
        }
}
