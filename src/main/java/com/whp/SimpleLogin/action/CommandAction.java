package com.whp.SimpleLogin.action;

import com.whp.SimpleLogin.pojo.Explayer;
import com.whp.SimpleLogin.util.Token;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import static com.whp.SimpleLogin.action.LodingAction.getPlayerCache;
import static com.whp.SimpleLogin.database.DataHandler.*;
import static com.whp.SimpleLogin.setting.MessageManager.msg;

public class CommandAction {
    public boolean login(Player player, String password) throws SQLException {
        Explayer ep;
        if (getPlayerCache().atCache(player.getName()) != null) {
            ep = getPlayerCache().atCache(player.getName());
        } else {
            ep = search(player.getName());
        }
        if (ep == null) {
            msg(player, "&4还没有注册,先输入/sl register [密码] 注册!");
            return false;
        } else {
            getPlayerCache().addPlayer(ep);
        }
        isLoginByToken(player);
        if (ep.getIslogin().equals("true")) {
            msg(player, "&4当前玩家已经登录!");
            return false;
        } else if (ep.getPassword().equals(password)) {
            msg(player, "&2登录成功！");
            ep.setIslogin("true");
            ep.setPip(player.getAddress().getAddress().getHostAddress());
            if (!ep.getPip().equals(player.getAddress().getAddress().getHostAddress()))
                updatePlayer("pip", ep.getName(), player.getAddress().getAddress().getHostAddress());
            ep.setLastlogin();
            updatePlayer("lastlogin", ep.getName(), ep.getLastlogin());
            return true;
        } else {
            msg(player, "&4密码错误重新输入!");
        }
        return false;
    }

    public boolean register(Player player, String password) {
        Explayer explayer = new Explayer(player.getName(), null, password, player.getAddress().getAddress().getHostAddress(), "false");
        if (addPlayer(explayer)) {
            msg(player, "&2注册成功!输入/sl login [密码] 登录!");
            getPlayerCache().addPlayer(explayer);
        } else {
            msg(player, "&注册失败!");
            return false;
        }
        return true;
    }

    public boolean updatepassword(Player player,String oldpwd,String newpwd){
        Explayer ep=getPlayerCache().atCache(player.getName());
        if(ep.getPassword().equals(oldpwd))
        {
            ep.setPassword(newpwd);
            updatePlayer("password",player.getName(),newpwd);
            msg(player, "&2密码修改成功!");
            return true;
        }
        else {
            msg(player, "&2密码修改失败!(旧密码错误)");
        }
        return false;
    }
    public boolean updatenickname(Player player,String newnickname){
        Explayer ep=getPlayerCache().atCache(player.getName());
        ep.setName(newnickname);
        updatePlayer("nickname",player.getName(),newnickname);
        return true;
    }
    public void loginout(Player player) {
        Explayer ep = getPlayerCache().atCache(player.getName());
        ep.setIslogin("false");
    }

    public static boolean isLoginByToken(Player player) {
        Explayer ep = getPlayerCache().atCache(player.getName());
        if (ep != null) {
            if (!ep.getToken().equals("")) {
                if (ep.getPip().equals(player.getAddress().getAddress().getHostAddress())) {
                    if (Token.isOverTime(ep.getToken())) {
                        return true;
                    } else {
                        ep.setIslogin("false");
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkPlayer(Player player){
        Explayer ep;
        if (getPlayerCache().atCache(player.getName()) != null) {
            ep = getPlayerCache().atCache(player.getName());
        } else {
            ep = search(player.getName());
        }
        if (ep == null) {
            msg(player, "&4还没有注册,先输入/sl register [密码] 注册!");
            return false;
        }
        else {
            if(isLoginByToken(player))
            {
                msg(player, "&2登录成功!(无需校验)");
                return true;
            }
            else{
                return false;
            }
        }
    }
    public static void loginOutSetToken(Player player){
        Explayer ep = getPlayerCache().atCache(player.getName());
        if(ep!=null)
            ep.setToken(player.getName());
    }
}
