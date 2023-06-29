package me.doublenico.hypegradientsgui;

import me.doublenico.hypegradients.api.MessageDetectionManager;
import me.doublenico.hypegradients.api.animations.CustomAnimations;
import me.doublenico.hypegradients.api.detection.ChatDetection;
import me.doublenico.hypegradientsgui.minimessage.MiniMessageDetection;
import me.doublenico.hypegradientsgui.translate.LanguageCommand;
import me.doublenico.hypegradientsgui.translate.LanguageManager;
import me.doublenico.hypegradientsgui.translate.TranslationMessageDetection;
import me.doublenico.hypegradientsgui.translate.languages.EnglishLanguage;
import me.doublenico.hypegradientsgui.translate.languages.RomanianLanguage;
import me.doublenico.hypegradientsgui.uwu.UwUMessageDetection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class HypeGradientsGUI extends JavaPlugin implements Listener {

    @Override
    public void onLoad() {
        new ChatDetection("language", true);
    }

    @Override
    public void onEnable() {
        new EnglishLanguage("english", "English", getDataFolder().getPath());
        new RomanianLanguage("romania", "Romanian", getDataFolder().getPath());
        LanguageManager.changeDefaultLanguage("English");
        this.getCommand("language").setExecutor(new LanguageCommand());
        MessageDetectionManager.getInstance().addMessageDetection(new TranslationMessageDetection());
        MessageDetectionManager.getInstance().addMessageDetection(new MiniMessageDetection());
        MessageDetectionManager.getInstance().addMessageDetection(new UwUMessageDetection());
        CustomAnimations.getInstance().createAnimation("beep", Arrays.asList("<rainbow>%text%</rainbow>", "<rainbow:!>%text%</rainbow>", "<rainbow:2>%text%</rainbow>"));
        System.out.println(CustomAnimations.getInstance().getCustomAnimations());
    }
}
