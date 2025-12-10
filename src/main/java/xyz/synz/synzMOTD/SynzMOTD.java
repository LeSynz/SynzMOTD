package xyz.synz.synzMOTD;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.CachedServerIcon;

import java.io.File;
import java.util.List;

public final class SynzMOTD extends JavaPlugin implements Listener {

    private final MiniMessage mm = MiniMessage.miniMessage();
    private List<String> rotatingMotd;
    private int index = 0;

    private CachedServerIcon serverIcon;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadRotatingMotd();
        loadServerIcon();
        getServer().getPluginManager().registerEvents(this, this);

        SynzMOTDCommand command = new SynzMOTDCommand(this);
        getCommand("synzmotd").setExecutor(command);
        getCommand("synzmotd").setTabCompleter(command);

        getLogger().info("SynzMOTD (rotating MOTD + icon support) enabled.");
    }

    public void loadRotatingMotd() {
        rotatingMotd = getConfig().getStringList("motd.rotating");

        if (rotatingMotd.isEmpty() || rotatingMotd.contains("SynzMOTD")) {
            rotatingMotd = List.of(
                    "<gradient:#ffcc00:#ff8800><bold>SynzMOTD</bold></gradient>\n<gray>A stylish default MOTD for your server</gray>"
            );
        }
    }

    public boolean loadServerIcon() {
        File iconFile = new File(getDataFolder(), "server-icon.png");

        if (!iconFile.exists()) {
            getLogger().warning("No server-icon.png found in plugin folder.");
            return false;
        }

        try {
            serverIcon = getServer().loadServerIcon(iconFile);
            getLogger().info("Successfully loaded custom server icon.");
            return true;
        } catch (Exception e) {
            getLogger().severe("Failed to load custom server icon:");
            e.printStackTrace();
            return false;
        }
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {

        if (rotatingMotd.isEmpty()) {
            event.motd(mm.deserialize("<red>No MOTD entries configured.</red>"));
            return;
        }

        if (index >= rotatingMotd.size()) index = 0;


        String entry = rotatingMotd.get(index++);
        String[] lines = entry.split("\\n");

        Component motd;

        if (lines.length >= 2) {
            motd = mm.deserialize(lines[0])
                    .append(Component.newline())
                    .append(mm.deserialize(lines[1]));
        } else {
            motd = mm.deserialize(lines[0]);
        }

        event.motd(motd);

        if (serverIcon != null) {
            event.setServerIcon(serverIcon);
        }
    }
}
