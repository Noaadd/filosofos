package es.ieslavereda.demojavafx.model;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Tenedor {
    private Label label;
    private boolean disponible = true;


    public Tenedor(Label label) {
        this.label = label;
        this.label.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), new Insets(-5))));
    }

    public Label getLabel() {
        return label;
    }

    public boolean isDisponible() {
        return disponible;
    }

}
