package entities;

import gui.views.GamePane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Blade {

    private Canvas canvas;
    private GraphicsContext swordStrike;
    private Point2D[] points;
    private int index = 0;

    public Blade(GamePane gamePane) {
        points = new Point2D[20];
        canvas = gamePane.getCanvas();
        swordStrike = canvas.getGraphicsContext2D();

        gamePane.setOnMousePressed(e-> {
            swordStrike.beginPath();

            swordStrike.lineTo(e.getSceneX(), e.getSceneY());
            swordStrike.stroke();
        });

        gamePane.setOnMouseDragged(e -> {

            if(index < points.length) {
                points[index++] = new Point2D(e.getSceneX(), e.getSceneY());
                swordStrike.lineTo(e.getSceneX(), e.getSceneY());
                swordStrike.stroke();
            } else {
                swordStrike.clearRect(points[0].x-10, points[0].y-10, 20, 20);
                swordStrike.beginPath();
                for(int i = 0; i < points.length-1; i++) {
                    points[i] = points[i+1];
                }
                points[index-1].setCoordinates(e.getSceneX(), e.getSceneY());
                swordStrike.lineTo(e.getSceneX(), e.getSceneY());
                swordStrike.stroke();

            }

        });

        gamePane.setOnMouseDragReleased(e-> {
            index = 0;
            swordStrike.clearRect(0,0, gamePane.getWidth(), gamePane.getHeight());
            swordStrike.beginPath();
            swordStrike.appendSVGPath("");
        });

    }

    public void customizeSword(Color color, int width) {
        swordStrike.setStroke(color);
        swordStrike.setLineWidth(width);
    }

}
