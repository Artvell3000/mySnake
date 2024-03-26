package com.example.myPanes;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.Resources;
import com.example.fructs.Fruct;
import com.example.model.Coordinates;
import com.example.model.Model;
import com.example.model.ModelInfo;
import com.example.model.ModelUpdate;
import com.example.model.SnakeDeque;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FieldGrid extends GridPane{
    private Model model;
    final int height;
    final int width;
    final String style1;
    final String style2;
    Pane[][] fieldPanes;

    FieldGrid(Model model, String s1, String s2){
        model.registerGridPane(this);
        this.height = model.height;
        this.width = model.width;
        this.style1 = s1;
        this.style2 = s2;
        fieldPanes = new Pane[height][width];

        this.setGridLinesVisible(true);

        for(int r=0;r<height;r++){
            for(int c=0;c<width;c++){
                String s = ((r%2==0 && c%2==0) || (r%2==1 && c%2==1))? s1 : s2;

                fieldPanes[r][c] = new Pane();
                fieldPanes[r][c].setPrefWidth(40);
                fieldPanes[r][c].setPrefHeight(40);
                fieldPanes[r][c].setStyle(s);
                this.add(fieldPanes[r][c], r, c);
            }
        }
    }

    public FieldGrid(Model model){
        this(model,Resources.styleCell1, Resources.styleCell2);
    }

    public void update(ArrayList<Coordinates> snake, boolean isProtected){
        System.out.println("update shield " + isProtected);
        
        for(Coordinates i:snake){
            fieldPanes[i.r][i.c].getChildren().remove(0);
            fieldPanes[i.r][i.c].getChildren().add(new SnakePane(isProtected));
        }
    }

    public void update(ModelUpdate modelUpdate) throws FileNotFoundException{
    
        Coordinates head = modelUpdate.snakeUpdate.newHead;
        if(!fieldPanes[head.r][head.c].getChildren().isEmpty())fieldPanes[head.r][head.c].getChildren().remove(0);
        SnakePane sPane = new SnakePane(modelUpdate.isProtected);
        fieldPanes[head.r][head.c].getChildren().add(sPane);

        Coordinates dead = modelUpdate.snakeUpdate.deadTail;
        fieldPanes[dead.r][dead.c].getChildren().removeIf(x -> x instanceof SnakePane);
        
        for(Fruct i:modelUpdate.deadFructs){
            Coordinates cord = i.getCoordinates();
            fieldPanes[cord.r][cord.c].getChildren().removeIf(x -> x instanceof ImageView);
        }

        for(Fruct i:modelUpdate.newFructs){
            Coordinates cord = i.getCoordinates();
            fieldPanes[cord.r][cord.c].getChildren().add(i.getImageView());
        }
    }

    public void redraw(ModelInfo info) throws FileNotFoundException{
        for(int r=0;r < height; r++){
            for(int c=0;c<width; c++){
                if(!fieldPanes[r][c].getChildren().isEmpty())fieldPanes[r][c].getChildren().remove(0);
            }
        }

        for(Fruct i:info.fructs){
            fieldPanes[i.getRow()][i.getCol()].getChildren().add(i.getImageView());
        }

        for(Coordinates i:info.snake){
            SnakePane sPane = new SnakePane(info.isProtected);
            fieldPanes[i.r][i.c].getChildren().add(
                sPane
            );
        }
    }
}
