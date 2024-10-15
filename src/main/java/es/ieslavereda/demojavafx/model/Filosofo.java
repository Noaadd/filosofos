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

public class Filosofo extends Task<List<RegionColor>> {
    private Label label;
    private Tenedor tenedor1;
    private Tenedor tenedor2;
    private Thread thread;

    public Filosofo(Label label, Tenedor tenedor1, Tenedor tenedor2) {
        this.label = label;
        this.tenedor1 = tenedor1;
        this.tenedor2 = tenedor2;
    }

    public Thread getThread() {
        return thread;
    }

    public void comer(){
       thread = new Thread(new TaskCome());
       thread.setDaemon(true);
       thread.start();
    }

    @Override
    protected List<RegionColor> call() throws Exception {
        return List.of();
    }

    private class TaskCome extends Task<List<RegionColor>> {

        public TaskCome(){
            valueProperty().addListener((observableValue, regionColor, newValue) -> {
                for (RegionColor value : newValue) {
                    value.getRegion().setBackground(new Background(new BackgroundFill(value.getColor(),new CornerRadii(5),new Insets(-5))));
                }

            });
        }
        @Override
        protected List<RegionColor> call() throws Exception {
            while (true){
                updateValue(Arrays.asList(new RegionColor(label,Color.GREEN),new RegionColor(tenedor1.getLabel(),Color.RED),new RegionColor(tenedor2.getLabel(),Color.RED)));
                Thread.sleep(2000);
            }
        }
    }


}
