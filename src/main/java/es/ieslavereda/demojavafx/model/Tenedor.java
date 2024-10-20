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
    private int id;

    public Tenedor(Label label, int id) {
        this.label = label;
        this.id = id;
        this.label.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), new Insets(-5))));
    }

    public synchronized void coger() throws InterruptedException {
        while (!disponible) {
            wait(); // Espera hasta que el tenedor esté disponible
        }
        disponible = false;
        label.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), new Insets(-5)))); // Cambia a rojo
    }

    public synchronized void soltar() {
        disponible = true;
        label.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), new Insets(-5)))); // Vuelve a ser transparente
        notifyAll(); // Notifica a los filósofos esperando
    }

    public Label getLabel() {
        return label;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getId() {
        return id;
    }
}
