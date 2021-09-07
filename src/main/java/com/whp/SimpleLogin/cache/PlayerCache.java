package com.whp.SimpleLogin.cache;

import com.whp.SimpleLogin.pojo.Explayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerCache {
    public int PlayerNum = 0;
    public List<Explayer> PlayerCacheList = new ArrayList<Explayer>();
    public PlayerCache() {
        PlayerNum = PlayerCacheList.size();
    }

    /**
     * 添加已经在线的玩家进入到列表中
     *
     * @param player
     * @return boolean
     * @author whp
     */
    public boolean addPlayer(Explayer player) {
        PlayerCacheList.add(player);
        PlayerNum++;
        return true;
    }
    /**
     * 检查当前玩家是否已在线
     *
     * @param playerName
     * @return boolean
     * @author whp
     */
    public Explayer atCache(String playerName) {
        for (Explayer p : PlayerCacheList) {
            if (p.getName().equals(playerName))
                return p;
        }
        return null;
    }

}
