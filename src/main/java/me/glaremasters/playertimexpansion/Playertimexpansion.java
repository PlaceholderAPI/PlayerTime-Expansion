package me.glaremasters.playertimexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.glaremasters.playertime.PlayerTime;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Playertimexpansion extends PlaceholderExpansion {


    private PlayerTime playerTime;

    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin("PlayerTime") != null;
    }

    @Override
    public boolean register() {
        playerTime = (PlayerTime) Bukkit.getPluginManager().getPlugin(getPlugin());
        if (playerTime != null) {
            return super.register();
        }
        return false;
    }

    @Override
    public String getAuthor() {
        return "blockslayer22";
    }

    @Override
    public String getIdentifier() {
        return "playertime";
    }

    @Override
    public String getPlugin() {
        return "PlayerTime";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        if (player == null) return "";

        if (identifier.equals("time")) {
            String time = DurationFormatUtils.formatDuration(Integer.valueOf(PlayerTime.getI().getDatabase().getTime(player.getUniqueId().toString())), "dd:HH:mm:ss");
            String[] parts = time.split(":");
            String days = parts[0];
            String hours = parts[1];
            String minutes = parts[2];
            String sec = parts[3];
            String format = "{days}D{hours}H{minutes}M{seconds}S";
            return format.replace("{days}", days).replace("{hours}", hours).replace("{minutes}", minutes).replace("{seconds}", sec);
        }


        return null;
    }


}
