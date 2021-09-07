package com.whp.SimpleLogin;
import com.whp.SimpleLogin.action.LodingAction;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.sql.SQLException;

public final class SimpleLogin extends JavaPlugin {
    public static SimpleLogin instance;
    public static String version;
    LodingAction initOrDesctory;
    @Override
    public void onEnable() {
        instance = this;
        version = this.getDescription().getVersion();
        initOrDesctory=new LodingAction();
        try {
            try {
                initOrDesctory.Init();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        initOrDesctory.Desctory();
    }
}
