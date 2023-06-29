package me.doublenico.hypegradientsgui.translate;

import me.doublenico.hypegradients.api.chat.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class ConfigManager {
    private YamlConfiguration yml;
    private File config;
    private String fileName;
    private boolean firstTime = false;

    public ConfigManager(String name, String dir) {
        File d = new File(dir);
        if (!d.exists() && !d.mkdirs()) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not create " + d.getPath());
        } else {
            this.config = new File(dir, name + ".yml");
            if (!this.config.exists()) {
                this.firstTime = true;
                Bukkit.getLogger().log(Level.INFO, "Creating " + this.config.getPath());

                try {
                    if (!this.config.createNewFile()) {
                        Bukkit.getLogger().log(Level.SEVERE, "Could not create " + this.config.getPath());
                        return;
                    }
                } catch (IOException var5) {
                    var5.printStackTrace();
                }
            }

            this.yml = YamlConfiguration.loadConfiguration(this.config);
            this.yml.options().copyDefaults(true);
            this.fileName = name;
        }
    }

    public void save() {
        try {
            this.yml.save(this.config);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void reload() {
        this.yml = YamlConfiguration.loadConfiguration(this.config);
    }

    public YamlConfiguration getConfig() {
        return this.yml;
    }

    public String getFileName() {
        return this.fileName;
    }

    public boolean isFirstTime() {
        return this.firstTime;
    }

    public int getInt(String path) {
        return this.yml.getInt(path);
    }

    public boolean getBoolean(String path) {
        return this.yml.getBoolean(path);
    }

    public double getDouble(String path) {
        return this.yml.getDouble(path);
    }

    public void set(String path, Object value, boolean save) {
        if (save) {
            this.yml.set(path, value);
            this.save();
        } else {
            this.yml.set(path, value);
        }
    }

    public void set(String path, Object value) {
        this.set(path, value, true);
    }

    public void setString(String path, String value) {
        this.yml.set(path, value);
    }

    public void setStringList(String path, List<String> value) {
        this.yml.set(path, value);
    }

    public void setInt(String path, int value) {
        this.yml.set(path, value);
    }

    public void setBoolean(String path, boolean value) {
        this.yml.set(path, value);
    }

    public void setDouble(String path, double value) {
        this.yml.set(path, value);
    }

    public void remove(String path) {
        this.yml.set(path, (Object) null);
    }

    public void setValue(String path, Object value, boolean save) {
        String[] split = path.split("\\.");
        String currentPath = "";
        for (String s : split) {
            if (currentPath.equals("")) {
                currentPath = s;
            } else {
                currentPath = currentPath + "." + s;
            }

            if (this.yml.get(currentPath) == null) {
                this.yml.set(currentPath, value);
            }
        }

        if (save) {
            this.save();
        }

    }

    public void setValue(String path, Object value) {
        this.setValue(path, value, true);
    }

    public boolean contains(String path) {
        return this.yml.get(path) != null;
    }

    public String getString(String path) {
        return this.yml.getString(new ColorChat(path).toFormattedString());
    }
}
