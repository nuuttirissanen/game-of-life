package com.example.gameoflife;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class GoLMethods extends Rectangle {

    public static final String musta = "0x000000ff";

    public static void elama(Rectangle r) {
        r.setFill(Color.BLACK);
    }

    public static boolean elossa(Rectangle r) {
        return Objects.equals(r.getFill().toString(), musta);
    }

    public static void kuolema(Rectangle r) {
        r.setFill(Color.WHITE);
    }

    public static int laskeNaapurit(Rectangle[][] taulu, int x, int y) {
        int naapurit = 0;
        int xMin = Math.max(0, x - 1);
        int xMax = Math.min(99, x + 1);
        int yMin = Math.max(0, y - 1);
        int yMax = Math.min(99, y + 1);
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                if (i == x && j == y) {
                    continue; // skip the center cell
                }
                if (Objects.equals(taulu[i][j].getFill().toString(), musta)) {
                    naapurit++;
                }
            }
        }
        return naapurit;
    }
}



