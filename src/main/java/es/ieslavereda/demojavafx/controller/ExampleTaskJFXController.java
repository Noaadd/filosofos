package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.model.Filosofo;
import es.ieslavereda.demojavafx.model.RegionColor;
import es.ieslavereda.demojavafx.model.Tenedor;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ExampleTaskJFXController {
    @FXML
    private Label filosofo1;
    @FXML
    private Label filosofo2;
    @FXML
    private Label filosofo3;
    @FXML
    private Label filosofo4;
    @FXML
    private Label filosofo5;
    @FXML
    private Label tenedor1;
    @FXML
    private Label tenedor2;
    @FXML
    private Label tenedor3;
    @FXML
    private Label tenedor4;
    @FXML
    private Label tenedor5;

    private Thread thread;

    Tenedor t1;
    Tenedor t2;
    Tenedor t3;
    Tenedor t4;
    Tenedor t5;

    Filosofo f1;
    Filosofo f2;
    Filosofo f3;
    Filosofo f4;
    Filosofo f5;

    public ExampleTaskJFXController(){

    }
    @FXML
    public void initialize(){
        t1 = new Tenedor(tenedor1);
        t2 = new Tenedor(tenedor2);
        t3 = new Tenedor(tenedor3);
        t4 = new Tenedor(tenedor4);
        t5 = new Tenedor(tenedor5);

        f1 = new Filosofo(filosofo1, t1, t5);
        f2 = new Filosofo(filosofo2, t1, t2);
        f3 = new Filosofo(filosofo3, t2, t3);
        f4 = new Filosofo(filosofo4, t3, t4);
        f5 = new Filosofo(filosofo5, t4, t5);

        f1.comer();
        f2.comer();
        f3.comer();
        f4.comer();
        f5.comer();

    }

    @FXML
    protected void onHelloButtonClick() {
        if(!thread.isAlive())
            thread.start();

    }


}