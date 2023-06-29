package me.doublenico.hypegradientsgui.minimessage;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.doublenico.hypegradients.api.MessageDetection;
import me.doublenico.hypegradients.api.chat.ChatGradient;
import me.doublenico.hypegradients.api.detection.ChatDetectionConfiguration;
import me.doublenico.hypegradients.config.utils.DynamicConfigurationDirectory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MiniMessageDetection implements MessageDetection {
    @Override
    public String getName() {
        return "minimessage";
    }

    @Override
    public String getJSON(Player player, String s) {
        return s;
    }

    @Override
    public String getPlainMessage(Player player, String s) {
        Component component = MiniMessage.miniMessage().deserializeOrNull(ChatColor.stripColor(s));
        if (component == null) return null;
        return ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(component));
    }

    @Override
    public WrappedChatComponent getChatComponent(Player player, WrappedChatComponent wrappedChatComponent) {
        return wrappedChatComponent;
    }

    @Override
    public boolean isEnabled(Player player, String s, String s1, WrappedChatComponent wrappedChatComponent) {
        return !new ChatGradient(s).isGradient();
    }

    @Override
    public ChatDetectionConfiguration chatDetectionConfiguration(Player player, DynamicConfigurationDirectory dynamicConfigurationDirectory) {
        return new ChatDetectionConfiguration(dynamicConfigurationDirectory, "minimessage", true);
    }


}
