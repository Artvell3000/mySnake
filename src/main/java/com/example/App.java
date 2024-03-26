package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

import com.example.model.Model;
import com.example.model.DrivingDirections.Direction;
import com.example.myPanes.FieldGrid;

public class App extends Application {
    final int height = 41, width = 21;
    final double delay = 100;
    boolean isPaused = false;
    Model model = null;
    Timeline timeline = new Timeline();
    
    private static Scene scene;

    private Label getLabelForScore(){
        Label scoreLabel = new Label("0");
        scoreLabel.setStyle(Resources.styleScore);
        return scoreLabel;
    }

    private Label getLabelForGameOver(){
        Label gameOverLabel = new Label(Resources.textGameOver);
        gameOverLabel.setStyle(Resources.styleGameOver);
        gameOverLabel.setVisible(false);
        return gameOverLabel;
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws FileNotFoundException{

        model = new Model(height, width);
        
        FieldGrid grid = new FieldGrid(model);

        VBox vbox = new VBox();
        vbox.getChildren().add(getLabelForScore());
        vbox.getChildren().add(grid);

        StackPane root = new StackPane();
        
        root.getChildren().add(vbox);
        root.getChildren().add(getLabelForGameOver());
        
        scene = new Scene(root);
        grid.redraw(model.getInfo());

        timeline.getKeyFrames().add(
            new KeyFrame(Duration.millis(delay), e -> {
                try {
                    model.getNextState();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            switch (keyCode.getChar()) {
                case "A":
                    model.setDirection(Direction.UP);
                    break;
                case "D":
                    model.setDirection(Direction.DOWN);
                    break;
                case "S":
                    model.setDirection(Direction.RIGHT);
                    break;
                case "W":
                    model.setDirection(Direction.LEFT);
                    break;
                case "O":
                    if(isPaused)timeline.stop();
                    else timeline.play();
                    isPaused = !isPaused;
                    break;
            }
            //System.out.println("Key Pressed: " + keyCode.getChar());
        });

        stage.setTitle(Resources.textTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}