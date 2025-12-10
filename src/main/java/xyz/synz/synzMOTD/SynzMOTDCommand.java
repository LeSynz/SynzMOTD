package xyz.synz.synzMOTD;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SynzMOTDCommand implements CommandExecutor, TabCompleter {

    private final SynzMOTD plugin;
    private final MiniMessage mm = MiniMessage.miniMessage();

    public SynzMOTDCommand(SynzMOTD plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(mm.deserialize("""
                <yellow>SynzMOTD commands:
                <gray>/synzmotd reload
                <gray>/synzmotd reloadicon
            """));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {

            if (!sender.hasPermission("synzmotd.reload")) {
                sender.sendMessage(mm.deserialize("<red>You do not have permission."));
                return true;
            }

            plugin.reloadConfig();
            plugin.loadRotatingMotd();

            sender.sendMessage(mm.deserialize("<green>SynzMOTD config reloaded."));
            return true;
        }

        if (args[0].equalsIgnoreCase("reloadicon")) {

            if (!sender.hasPermission("synzmotd.reloadicon")) {
                sender.sendMessage(mm.deserialize("<red>You do not have permission."));
                return true;
            }

            boolean success = plugin.loadServerIcon();

            if (success) {
                sender.sendMessage(mm.deserialize("<green>Server icon reloaded successfully!"));
            } else {
                sender.sendMessage(mm.deserialize("<red>Failed to reload icon. Ensure server-icon.png exists in the SynzMOTD folder and is 64x64 PNG."));
            }
            return true;
        }

        sender.sendMessage(mm.deserialize("<red>Unknown subcommand. Use /synzmotd reload or /synzmotd reloadicon"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        if (args.length == 1) {
            List<String> completions = new ArrayList<>();

            if (sender.hasPermission("synzmotd.reload") &&
                    "reload".startsWith(args[0].toLowerCase())) {
                completions.add("reload");
            }

            if (sender.hasPermission("synzmotd.reloadicon") &&
                    "reloadicon".startsWith(args[0].toLowerCase())) {
                completions.add("reloadicon");
            }

            return completions;
        }

        return List.of();
    }
}
