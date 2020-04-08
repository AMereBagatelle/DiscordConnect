package amerebagatelle.github.io.discordconnect.settings;

import java.io.*;
import java.util.Properties;

public class SettingsManager {
    // * Settings File. DO NOT CHANGE UNLESS YOU KNOW WHAT YOU ARE DOING
    public static final File settingsFilePath = new File("discordconnect.properties");

    // * Storage Variables
    public static String token;
    public static String guildLinkId;
    public static String channelLinkId;

    // * Settings
    public static boolean sendToDiscord;
    public static boolean sendToMinecraft;
    public static boolean onlineCommand;

    public static void init() {
        if(!settingsFilePath.exists()) {
            try {
                boolean newFileCreated = settingsFilePath.createNewFile();

                if(newFileCreated) {
                    Properties prop = new Properties();
                    BufferedReader inputStream = new BufferedReader(new FileReader(settingsFilePath));
                    prop.load(inputStream);
                    inputStream.close();

                    BufferedWriter outputStream = new BufferedWriter(new FileWriter(settingsFilePath));
                    prop.setProperty("token", "");
                    prop.setProperty("guildId", "");
                    prop.setProperty("channelId", "");
                    prop.setProperty("sendToDiscord", "true");
                    prop.setProperty("sendToMinecraft", "true");
                    prop.setProperty("onlineCommand", "true");
                    prop.store(outputStream, null);
                    outputStream.flush();
                    outputStream.close();
                } else {
                    throw new IOException("Could not create settings file, do I have write access?");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadSettings();
    }

    public static void loadSettings() {
        BufferedReader inputStream;

        try {
            Properties prop = new Properties();

            inputStream = new BufferedReader(new FileReader(settingsFilePath));
            prop.load(inputStream);
            inputStream.close();

            token = prop.getProperty("token");
            guildLinkId = prop.getProperty("guildId");
            channelLinkId = prop.getProperty("channelId");
            sendToDiscord = Boolean.getBoolean(prop.getProperty("sendToDiscord"));
            sendToMinecraft = Boolean.getBoolean(prop.getProperty("sendToMinecraft"));
            onlineCommand = Boolean.getBoolean(prop.getProperty("onlineCommand"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeSetting(String setting, String setpoint) throws IOException {
        BufferedReader inputStream;
        BufferedWriter outputStream;
        Properties prop = new Properties();

        System.out.println(settingsFilePath.getAbsolutePath());

        inputStream = new BufferedReader(new FileReader(settingsFilePath));
        prop.load(inputStream);
        inputStream.close();

        outputStream = new BufferedWriter(new FileWriter(settingsFilePath));
        prop.setProperty(setting, setpoint);
        prop.store(outputStream, null);
        outputStream.flush();
        outputStream.close();

        loadSettings();
    }
}
