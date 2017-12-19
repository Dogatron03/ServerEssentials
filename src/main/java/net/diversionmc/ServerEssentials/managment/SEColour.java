package net.diversionmc.ServerEssentials.managment;

public enum SEColour {
    BLACK("§0"),
    DARK_BLUE("§1"),
    DARK_GREEN("§2"),
    CYAN("§3"),
    DARK_RED("§4"),
    DARK_PURPLE("§5"),
    GOLD("§6"),
    LIGHT_GREY("§7"),
    DARK_GREY("§8"),
    BLUE("§9"),
    GREEN("§a"),
    AQUA("§b"),
    RED("§c"),
    PINK("§d"),
    YELLOW("§e"),
    WHITE("§f"),
    OBFUSCATED("§k"),
    BOLD("§l"),
    STRIKETHROUGH("§m"),
    UNDERLINE("§n"),
    ITALIC("§o"),
    RESET("§r");


    private String code;

    private SEColour(String code) {
        this.code = code;
    }

    public static String translate(String message){
        return message.replace("&0", "§0").replace("&1", "§1").replace("&2", "§2").replace("&3", "§3").replace("&4", "§4").replace("&5", "§5").replace("&6", "§6").replace("&7", "§7").replace("&8", "§8").replace("&9", "§9").replace("&a", "§a").replace("&b", "§b").replace("&c", "§c").replace("&d", "§d").replace("&e", "§e").replace("&f", "§f").replace("&k", "§k").replace("&l", "§l").replace("&m", "§m").replace("&n", "§n").replace("&o", "§o").replace("&r", "§r");
    }

    public static String strip(String message){
        String newmessage = message;
        for (SEColour seColour : values()) {
            newmessage = newmessage.replace(seColour.code, "");
        }
        return newmessage;
    }

    public String toString() {
        return code;
    }



}