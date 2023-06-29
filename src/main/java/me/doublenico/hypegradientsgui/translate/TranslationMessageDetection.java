package me.doublenico.hypegradientsgui.translate;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.doublenico.hypegradients.api.MessageDetection;
import me.doublenico.hypegradients.api.detection.ChatDetectionConfiguration;
import me.doublenico.hypegradients.api.detection.ChatDetectionManager;
import me.doublenico.hypegradients.config.utils.DynamicConfigurationDirectory;
import org.bukkit.entity.Player;

public class TranslationMessageDetection implements MessageDetection {
    @Override
    public String getName() {
        return "translate";
    }

    @Override
    public String getJSON(Player player, String s) {
        return s;
    }

    @Override
    public String getPlainMessage(Player player, String s) {
        for (String key : LanguageManager.getPlayerLanguage(player).getConfig().getKeys(false)) {
            s = s.replace("{" + key + "}", LanguageManager.getPlayerLanguage(player).getConfig().getString(key, "not found"));
        }
        return s;
    }

    @Override
    public WrappedChatComponent getChatComponent(Player player, WrappedChatComponent wrappedChatComponent) {
        return wrappedChatComponent;
    }

    @Override
    public boolean isEnabled(Player player, String s, String s1, WrappedChatComponent wrappedChatComponent) {
        return true;
    }

    @Override
    public ChatDetectionConfiguration chatDetectionConfiguration(Player player, DynamicConfigurationDirectory dynamicConfigurationDirectory) {
        return ChatDetectionManager.getInstance().getConfiguration("language");
    }
}
