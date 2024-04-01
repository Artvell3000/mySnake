package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import com.example.ObserverPanes.FieldGrid;
import com.example.ObserverPanes.GameObserver;
import com.example.ObserverPanes.GameOverObserver;
import com.example.ObserverPanes.GridObserver;
import com.example.ObserverPanes.ScoreObserver;
import com.example.model.DrivingDirections.Direction;

public class App extends Application {
    final int height = 41, width = 21;
    final double delay = 150;
    boolean isPaused = true;

    Game game;
    
    private static Scene scene;

    private Label getLabelForScore(){
        Label scoreLabel = new Label("0");
        scoreLabel.setStyle(Resources.styleScore);
        return scoreLabel;
    }

    private Label getLabelForPause(){
        Label pLabel = new Label(Resources.textPause);
        pLabel.setStyle(Resources.stylePause);
        pLabel.setVisible(false);
        return pLabel;
    }

    private Label getLabelForGameOver(){
        Label gameOverLabel = new Label(Resources.textGameOver);
        gameOverLabel.setStyle(Resources.styleGameOver);
        gameOverLabel.setVisible(false);
        return gameOverLabel;
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws FileNotFoundException{

        game = new Game();

        Label lScore = getLabelForScore();
        Label lPause = getLabelForPause();
        Label lGameOver = getLabelForGameOver();
        
        FieldGrid grid = new FieldGrid(game.model);

        GameObserver gameObserver = new GameObserver(game.model, game);
        ScoreObserver scoreObserver = new ScoreObserver(game.model, lScore);
        GameOverObserver gameOverObserver = new GameOverObserver(game.model, lGameOver);
        GridObserver gridObserver = new GridObserver(game.model, grid);

        VBox vbox = new VBox();
        vbox.getChildren().add(lScore);
        vbox.getChildren().add(grid);

        StackPane root = new StackPane();
        
        root.getChildren().add(vbox);
        root.getChildren().add(lGameOver);
        root.getChildren().add(lPause);
        
        scene = new Scene(root);
        grid.redraw(game.model.getInfo());

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            switch (keyCode.getChar()) {
                case "A":
                    game.model.setDirection(Direction.UP);
                    break;
                case "D":
                    game.model.setDirection(Direction.DOWN);
                    break;
                case "S":
                    game.model.setDirection(Direction.RIGHT);
                    break;
                case "W":
                    game.model.setDirection(Direction.LEFT);
                    break;
                case "O":
                    if(isPaused){
                        game.timeline.stop();
                        lPause.setVisible(true);
                    }
                    else {
                        game.timeline.play();
                        lPause.setVisible(false);
                    }
                    isPaused = !isPaused;
                    break;
                case "T":
                    game.timeline.setRate(2.0);
                    break;
            }
        });

        stage.setTitle(Resources.textTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}