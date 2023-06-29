package me.doublenico.hypegradientsgui.translate.languages;

import me.doublenico.hypegradientsgui.translate.LanguageManager;

public class EnglishLanguage extends LanguageManager {
    public EnglishLanguage(String file, String language, String dir) {
        super(file, language, dir);
        setString("welcome", "Welcome to the server!");
    }
}
