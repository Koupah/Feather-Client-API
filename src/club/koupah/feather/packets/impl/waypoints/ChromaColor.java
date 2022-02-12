package club.koupah.feather.packets.impl.waypoints;

import java.awt.Color;

import com.google.gson.JsonObject;

/**
 * @author Koupah
 * @createdAt 12:34:40 am on 13 Feb 2022
 */

public class ChromaColor {

    private final boolean chroma;
    private final int color;

    public ChromaColor(boolean chroma, int color) {
        this.chroma = chroma;
        this.color = color;
    }

    public ChromaColor(boolean chroma, Color color) {
        this.chroma = chroma;
        this.color = color.getRGB();
    }

    public boolean isChroma() {
        return this.chroma;
    }

    public int getColor() {
        return this.color;
    }
    
    public JsonObject toJson() {
    	JsonObject obj = new JsonObject();
    	obj.addProperty("chroma", this.chroma);
    	obj.addProperty("color", this.color);
    	return obj;
    }
}