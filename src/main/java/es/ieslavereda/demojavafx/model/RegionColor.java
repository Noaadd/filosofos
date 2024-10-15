package es.ieslavereda.demojavafx.model;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class RegionColor {
    private Region region;
    private Color color;

    public RegionColor(Region region, Color color) {
        this.region = region;
        this.color = color;
    }

    public Region getRegion() {
        return region;
    }

    public Color getColor() {
        return color;
    }
}
