package es.ieslavereda.demojavafx.model;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class Filosofo {
    private Label label;
    private Tenedor tenedorIzquierdo;
    private Tenedor tenedorDerecho;
    private Thread thread;

    public Filosofo(Label label, Tenedor tenedorIzquierdo, Tenedor tenedorDerecho) {
        this.label = label;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
    }

    public void comer() {
        thread = new Thread(new TaskCome());
        thread.setDaemon(true);
        thread.start();
    }

    private class TaskCome extends Task<RegionColor> {

        public TaskCome() {
            valueProperty().addListener((observableValue, regionColor, newValue) -> {
                if (newValue != null) {
                    newValue.getRegion().setBackground(new Background(new BackgroundFill(newValue.getColor(), new CornerRadii(5), new Insets(-5))));
                }
            });

        }

        @Override
        protected RegionColor call() throws Exception {
            while (true) {
                synchronized (tenedorIzquierdo) {
                    updateValue(new RegionColor(label, Color.RED));
                    thread.sleep(1);
                    updateValue(new RegionColor(tenedorIzquierdo.getLabel(), Color.RED));
                    thread.sleep(1);


                    synchronized (tenedorDerecho) {
                        updateValue(new RegionColor(label, Color.GREEN));
                        thread.sleep(1);
                        updateValue(new RegionColor(tenedorIzquierdo.getLabel(), Color.GREEN));
                        thread.sleep(1);

                        updateValue(new RegionColor(tenedorDerecho.getLabel(), Color.GREEN));
                        thread.sleep(1);

                        Thread.sleep(3000);
                        updateValue(new RegionColor(label, Color.TRANSPARENT));
                        thread.sleep(1);
                        updateValue(new RegionColor(tenedorIzquierdo.getLabel(), Color.TRANSPARENT));
                        thread.sleep(1);
                        updateValue(new RegionColor(tenedorDerecho.getLabel(), Color.TRANSPARENT));
                        thread.sleep(1);
                    }
                }
                thread.sleep(5000);
            }
        }
    }
}

