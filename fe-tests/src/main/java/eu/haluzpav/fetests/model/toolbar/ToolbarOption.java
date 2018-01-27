package eu.haluzpav.fetests.model.toolbar;

public enum ToolbarOption {

    // TODO remove language dependency

    DONATE("menu_donate", "donate"),
    SETTINGS("menu_settings", "settings"),
    ABOUT("menu_about", "about"),
    SEARCH("menu_search", "search");

    public final String id, text;

    ToolbarOption(String id, String text) {
        this.id = id;
        this.text = text.toLowerCase();
    }
}
