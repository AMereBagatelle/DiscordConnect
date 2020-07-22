package amerebagatelle.github.io.discordconnect.settings;

import java.io.*;
import java.util.Properties;

public class SettingsManager {

    public static File settingsFile = new File("config/discordconnect.properties");

    public static void initSettings() {
        // Init settings file if it doesn't exist
        if (!settingsFile.exists()) {
            try {
                boolean fileCreated = settingsFile.createNewFile();

                if (fileCreated) {
                    Properties prop = new Properties();
                    prop.put("chatLinkActive", "true");
                    prop.put("botToken", "");
                    prop.put("chatLinkChannelId", "");
                    prop.put("serverType", "SMP");
                    prop.put("commandPrefix", "/");
                    prop.put("onlineCommand", "true");

                    BufferedWriter writer = new BufferedWriter(new FileWriter(settingsFile));
                    prop.store(writer, null);
                    writer.flush();
                    writer.close();
                } else {
                    throw new IOException();
                }
            } catch (IOException e) {
                throw new RuntimeException("Could not create settings file for DiscordConnect!");
            }
        }

        if (loadSetting("botToken").length() == 0) throw new RuntimeException("Set a bot token!");
    }

    public static String loadSetting(String setting) {
        BufferedReader reader;
        Properties prop = new Properties();

        try {
            reader = new BufferedReader(new FileReader(settingsFile));
            prop.load(reader);
            reader.close();

            return prop.getProperty(setting);
        } catch (IOException e) {
            throw new RuntimeException("Can't read settings for DiscordConnect!");
        }
    }
}
