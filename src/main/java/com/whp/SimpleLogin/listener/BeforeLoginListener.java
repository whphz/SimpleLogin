package com.whp.SimpleLogin.listener;

import com.whp.SimpleLogin.action.CommandAction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import static com.whp.SimpleLogin.setting.MessageManager.info;

public class BeforeLoginListener implements Listener {
    @EventHandler
    public void BlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        if(player!=null)
            if(CommandAction.isLoginByToken(player))
            {
                info(player.getName()+"&2允许破坏方块！");
                return;
            }
            else
            {
                player.sendMessage("未登录，不允许破坏方块！");
                event.setCancelled(true);
            }
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        if(player!=null)
        {
            if(CommandAction.isLoginByToken(player))
                return;
            else
            {
                player.sendMessage("未登录不允许移动");
                event.setCancelled(true);
            }
        }
    }

}
