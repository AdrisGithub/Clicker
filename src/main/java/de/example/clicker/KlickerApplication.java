package de.example.clicker;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class KlickerApplication extends Application {

    private static final Image circle = new Image(new File("src/main/resources/de/example/clicker/whiteCircle.png").toURI().toString());
    private int x = 0;
    private int y = 0;
    private int score = 0;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Runtime.getRuntime().exit(score);
            }
        },10000);
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Click Me");
        Group root = new Group();
        Canvas canvas = new Canvas(primaryStage.getWidth(), primaryStage.getHeight());
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                t -> {
                    if (t.getX() >= x && t.getY() >= y && t.getX() <= x+ circle.getWidth() && t.getY() <= y+circle.getHeight() ) {
                        score++;
                        reset(canvas, Color.BLACK,primaryStage.getWidth(), primaryStage.getHeight());
                    }else{
                        Runtime.getRuntime().exit(score);
                    }
                });
        reset(canvas, Color.BLACK,primaryStage.getWidth(), primaryStage.getHeight());
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    private void reset(Canvas canvas, Color color,double stageX,double stageY) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        do{
            x = (int) (Math.random() * canvas.getWidth());
            y = (int) (Math.random() * canvas.getHeight());
        }while(isOutOfWindow(x,y,stageX,stageY));
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillText("Score: "+score,10,15);
        gc.drawImage(circle,x,y);
    }

    private boolean isOutOfWindow(int x, int y,double stageX, double stageY) {
        return !(x + circle.getWidth() <= stageX && y + circle.getHeight() <= stageY);
    }
}