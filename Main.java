package com.example.gameoflife;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.util.Random;

import static com.example.gameoflife.GoLMethods.*;

public class Main extends Application {


        public void start(Stage ikkuna) {
            ikkuna.setResizable(false);
            BorderPane root = new BorderPane();
            GridPane grid = new GridPane();
            VBox box = new VBox(10);
            grid.setGridLinesVisible(true);


            Rectangle[][] neliot = new Rectangle[100][100];


            int col = 0;
            int row = 0;
            for (int i = 0; i < 100; i++) {
               for (int j = 0; j < 100; j++) {
                   neliot[i][j] = new Rectangle(12, 12, Color.WHITE);
                   Random rand = new Random();
                   int r = rand.nextInt(10);
                   if (r == 1)  {neliot[i][j].setFill(Color.BLACK);}
                   grid.add(neliot[i][j], col, row);
                   col++;
                   if (col == 99) {
                       row++;
                       col = 0;
                   }
               }
             }

            Slider nopeus = new Slider(0.01, 5, 1);
            nopeus.setMaxWidth(400);
            nopeus.setMajorTickUnit(1);
            nopeus.setMinorTickCount(10);
            nopeus.setSnapToTicks(true);
            nopeus.setShowTickMarks(true);
            nopeus.setShowTickLabels(true);

            Text nopeusTeksti = new Text("Nopeus: " + nopeus.getValue() + " ");

            System.out.println(laskeNaapurit(neliot, 10, 10));

            box.getChildren().addAll(nopeus, nopeusTeksti);
            root.setTop(box);
            root.setCenter(grid);
            BorderPane.setAlignment(box, Pos.TOP_LEFT);

            EventHandler<ActionEvent> tk = e -> {
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        int naapurit = laskeNaapurit(neliot, i, j);
                        switch (naapurit) {
                            case 2 -> {

                            }
                            case 3 -> {
                                if (elossa(neliot[i][j])) {
                                    break;
                                }
                                elama(neliot[i][j]);
                            }
                            default -> kuolema(neliot[i][j]);
                        }
                        }
                    }

            };


            nopeus.setOnMouseClicked(e -> nopeusTeksti.setText("Nopeus: " + new DecimalFormat("0.00").format(nopeus.getValue()) + " "));

            Timeline animaatio = new Timeline(new KeyFrame(Duration.millis(500 / nopeus.getValue()), tk));
            animaatio.setCycleCount(Animation.INDEFINITE);
            animaatio.play();

            Scene scene = new Scene(root, 1200, 1300);
            scene.setFill(Color.GREY);
            ikkuna.setScene(scene);
            ikkuna.setTitle("Conway's game of life");
            ikkuna.show();


        }




        public static void main(String[] args) {
            launch(args);
        }
}