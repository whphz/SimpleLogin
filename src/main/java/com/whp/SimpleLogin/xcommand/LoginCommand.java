package com.whp.SimpleLogin.xcommand;

import com.whp.SimpleLogin.SimpleLogin;
import com.whp.SimpleLogin.action.LodingAction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import static com.whp.SimpleLogin.action.LodingAction.getCommandAction;
import static com.whp.SimpleLogin.action.LodingAction.getPlayerCache;
import static com.whp.SimpleLogin.setting.MessageManager.*;
import static com.whp.SimpleLogin.util.Token.isOverTime;

public class LoginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int length = args.length;
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (length == 0) {
                msg(player, before() + "&alogin &7" + SimpleLogin.version + " &eBy whp");
                msg(player, before() + "&a登录插件 &7" + SimpleLogin.version + " &e作者whp");
                msg(player, before() + "&f查看帮助请使用&a/simple help");
                msg(player, before() + "&f插件问题反馈请在邮箱反馈,邮箱地址： ");
                msg(player, before() + "&f2362673809@QQ.com");
                return true;
            } else if (length == 1) {
                if (args[0].equals("help"))
                    getHelp(player);
                else if (args[0].equals("login"))
                    msg(player, "输入/sl login [密码]登录!");
                else if (args[0].equals("loginout"))
                    getCommandAction().loginout(player);
            } else if (length == 2) {
                if (args[0].equals("login")) {
                    try {
                        getCommandAction().login(player, args[1]);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if (args[0].equals("register"))
                    getCommandAction().register(player, args[1]);
                else if (args[0].equals("reload") && player.hasPermission("simple-reload")) {
                    LodingAction.reloadConfigs();
                }
            } else if (length == 3) {
                if (args[0].equals("set"))
                    if (args[1].equals("nickname") && player.hasPermission("simple-setnickname"))
                        getCommandAction().updatenickname(player, args[2]);

            } else if (length == 4) {
                if (args[0].equals("set"))
                    if (args[1].equals("password") && getPlayerCache().atCache(player.getName()) != null && isOverTime(getPlayerCache().atCache(player.getName()).getToken()) == false)
                        getCommandAction().updatepassword(player, args[2], args[3]);
                    else {
                        msg(player,"修改失败，还没有登录");
                    }
            }
        }
        return false;
    }
}
