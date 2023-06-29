package me.doublenico.hypegradientsgui.translate;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LanguageManager extends ConfigManager {

    private static final HashMap<UUID, LanguageManager> playerLanguage = new HashMap<>();
    private static final List<LanguageManager> languages = new ArrayList<>();
    private static LanguageManager defaultLanguage;
    private String language;


    public LanguageManager(String file, String language, String dir) {
        super(file, dir);
        this.language = language;
        languages.add(this);
    }

    public static LanguageManager getDefaultLanguage() {
        return defaultLanguage;
    }

    public static LanguageManager getPlayerLanguage(Player player) {
        return playerLanguage.getOrDefault(player.getUniqueId(), getDefaultLanguage());
    }

    public static LanguageManager getPlayerLanguage(UUID uuid) {
        return playerLanguage.getOrDefault(uuid, getDefaultLanguage());
    }

    public static boolean isLanguage(String language) {
        return languages.stream().anyMatch(lang -> lang.getLanguage().equalsIgnoreCase(language));
    }

    public static LanguageManager getLanguage(String language) {
        return languages.stream().filter(lang -> lang.getLanguage().equalsIgnoreCase(language)).findFirst().orElse(null);
    }

    public static void changePlayerLanguage(Player player, String language) {
        if (isLanguage(language)) {
            playerLanguage.put(player.getUniqueId(), getLanguage(language));
        }
    }

    public static void changePlayerLanguage(UUID uuid, String language) {
        if (isLanguage(language)) {
            playerLanguage.put(uuid, getLanguage(language));
        }
    }

    public static void changeDefaultLanguage(String language) {
        if (isLanguage(language)) {
            defaultLanguage = getLanguage(language);
        }
    }

    public static void addLanguage(LanguageManager language) {
        languages.add(language);
    }

    public static void removeLanguage(LanguageManager language) {
        languages.remove(language);
    }

    public static void removeLanguage(String language) {
        languages.removeIf(lang -> lang.getLanguage().equalsIgnoreCase(language));
    }

    public static void removePlayerLanguage(Player player) {
        playerLanguage.remove(player.getUniqueId());
    }

    public static void removePlayerLanguage(UUID uuid) {
        playerLanguage.remove(uuid);
    }

    public static void clearPlayerLanguages() {
        playerLanguage.clear();
    }

    public static void clearLanguages() {
        languages.clear();
    }

    public static void clearDefaultLanguage() {
        defaultLanguage = null;
    }

    public static void clear() {
        clearPlayerLanguages();
        clearLanguages();
        clearDefaultLanguage();
    }

    public static boolean hasLanguage(Player player) {
        return playerLanguage.containsKey(player.getUniqueId());
    }

    public static boolean hasLanguage(UUID uuid) {
        return playerLanguage.containsKey(uuid);
    }

    public static boolean hasDefaultLanguage() {
        return defaultLanguage != null;
    }

    public static List<LanguageManager> getLanguages() {
        return languages;
    }

    public String getLanguage() {
        return language;
    }

    public String getMessage(String path) {
        return getDefaultLanguage().getString(path);
    }

    public String getMessage(Player player, String path) {
        if (hasLanguage(player)) return getPlayerLanguage(player).getString(path);
        return getMessage(path);
    }

    public String getMessage(UUID uuid, String path) {
        if (hasLanguage(uuid)) return getPlayerLanguage(uuid).getString(path);
        return getMessage(path);
    }
}
