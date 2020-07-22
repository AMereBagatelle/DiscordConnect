package amerebagatelle.github.io.discordconnect;

public class MessageFormatting {

    /**
     * Removes the color code formatting from a string.
     *
     * @param string Raw string, with formatting.
     * @return String without formatting.
     */
    public static String removeFormattingFromString(String string) {
        return string.replaceAll("§[4c6e2ab319d5f780lmnor]", "");
    }
}
