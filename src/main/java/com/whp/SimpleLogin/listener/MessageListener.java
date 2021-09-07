package com.whp.SimpleLogin.listener;
import com.whp.SimpleLogin.action.CommandAction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.whp.SimpleLogin.setting.MessageManager.msg;


public class MessageListener implements Listener{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage(event.getPlayer().getName()+"进入服务器");
        if(!CommandAction.checkPlayer(event.getPlayer()))
            msg(event.getPlayer(),"&4请输入密码 格式：/sl login xxxxx");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        CommandAction.loginOutSetToken(event.getPlayer());
        event.setQuitMessage("玩家:"+event.getPlayer().getName()+"已退出游戏");
    }

}
