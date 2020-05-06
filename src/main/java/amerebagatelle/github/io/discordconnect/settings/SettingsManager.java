package amerebagatelle.github.io.discordconnect.settings;

import java.io.*;
import java.util.Properties;

public class SettingsManager {

    public static File settingsFile = new File("discordconnect.properties");

    public static void initSettings() {
        // Init settings file if it doesn't exist
        if (!settingsFile.exists()) {
            try {
                settingsFile.createNewFile();

                Properties prop = new Properties();
                prop.put("botToken", "");

                BufferedWriter writer = new BufferedWriter(new FileWriter(settingsFile));
                prop.store(writer, null);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Could not create settings file for DiscordConnect!");
            }
        }
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
