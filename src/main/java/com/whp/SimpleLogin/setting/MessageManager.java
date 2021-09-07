package com.whp.SimpleLogin.setting;

import com.whp.SimpleLogin.cache.ConfigCache;
import com.whp.SimpleLogin.color.ColorManage;
import org.bukkit.entity.Player;

import java.util.List;

import static com.whp.SimpleLogin.SimpleLogin.instance;


public class MessageManager {
    private static ColorManage manager=new ColorManage();
    public static void msg(Player player,String message){
        player.sendMessage(manager.haveTempleText(message));
    }
    private static void sendWelcomeMessage(Player player){
        player.sendMessage(manager.haveTempleText(getPathValue("welcome.msg")));
    }

    public static void info(String message){
        instance.getLogger().info(manager.haveTempleText(message));
    }


    private static final String getPathValue(String path){
        return ConfigCache.configsCache.get("message").getString(path);
    }

    public static void getHelp(Player player){
        String help="";
        List<String> list = (List<String>) ConfigCache.configsCache.get("message").getList("help");
        for(String ms:list)
            help=help+manager.haveTempleText(ms)+"\n";
        msg(player,help);
    }

    public static String before(){
        return manager.haveTempleText(getPathValue("before"));
    }

}
