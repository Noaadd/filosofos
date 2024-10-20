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

    private class TaskCome extends Task<List<RegionColor>> {

        public TaskCome(){
            valueProperty().addListener((observableValue, regionColor, newValue) -> {
                for (RegionColor value : newValue) {
                    value.getRegion().setBackground(new Background(new BackgroundFill(value.getColor(), new CornerRadii(5), new Insets(-5))));
                }
            });
        }
        @Override
        protected List<RegionColor> call() throws Exception {
            while (true) {
                // Intentar coger el tenedor izquierdo
                synchronized (tenedorIzquierdo) {
                    if (tenedorIzquierdo.isDisponible()) {
                        tenedorIzquierdo.coger();
                        updateValue(Arrays.asList(
                                new RegionColor(label, Color.BLUE), // Azul al coger el tenedor izquierdo
                                new RegionColor(tenedorIzquierdo.getLabel(), Color.RED) // Tenedor izquierdo a rojo
                        ));

                        // Intentar coger el tenedor derecho
                        synchronized (tenedorDerecho) {
                            if (tenedorDerecho.isDisponible()) {
                                tenedorDerecho.coger();

                                // Ambos tenedores están cogidos, cambiar a verde
                                updateValue(Arrays.asList(
                                        new RegionColor(label, Color.GREEN), // Filósofo a verde
                                        new RegionColor(tenedorIzquierdo.getLabel(), Color.GREEN),
                                        new RegionColor(tenedorDerecho.getLabel(), Color.GREEN)
                                ));

                                // Tiempo de comer
                                Thread.sleep(3000); // Simula el tiempo de comer

                                // Liberar tenedores
                                tenedorDerecho.soltar();
                                tenedorIzquierdo.soltar();
                            } else {
                                // Si no se puede coger el derecho, se queda en azul
                                updateValue(Arrays.asList(
                                        new RegionColor(label, Color.BLUE), // Filósofo en azul
                                        new RegionColor(tenedorIzquierdo.getLabel(), Color.RED) // Tenedor izquierdo en rojo
                                ));

                                // Mantener el tenedor izquierdo mientras espera
                                while (!tenedorDerecho.isDisponible()) {
                                    Thread.sleep(100); // Esperar un tiempo antes de volver a intentar
                                }

                                // Intentar coger de nuevo el tenedor derecho
                                synchronized (tenedorDerecho) {
                                    tenedorDerecho.coger();
                                    updateValue(Arrays.asList(
                                            new RegionColor(tenedorDerecho.getLabel(), Color.RED) // Tenedor derecho a rojo
                                    ));

                                    // Ambos tenedores están cogidos, cambiar a verde
                                    updateValue(Arrays.asList(
                                            new RegionColor(label, Color.GREEN), // Filósofo a verde
                                            new RegionColor(tenedorIzquierdo.getLabel(), Color.GREEN),
                                            new RegionColor(tenedorDerecho.getLabel(), Color.GREEN)
                                    ));

                                    // Tiempo de comer
                                    Thread.sleep(3000); // Simula el tiempo de comer

                                    // Liberar tenedores
                                    tenedorDerecho.soltar();
                                    tenedorIzquierdo.soltar();
                                }
                            }
                        }
                    }
                }

                // Volver a rojo para la siesta
                updateValue(Arrays.asList(
                        new RegionColor(label, Color.RED), // Filósofo a rojo
                        new RegionColor(tenedorIzquierdo.getLabel(), Color.RED),
                        new RegionColor(tenedorDerecho.getLabel(), Color.RED)
                ));

                // Tiempo de siesta
                Thread.sleep(5000); // Simula el tiempo de siesta
            }
        }
    }
}
