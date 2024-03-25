package com.example.myPanes;

import java.io.FileNotFoundException;

import com.example.Resources;
import com.example.fructs.Fruct;
import com.example.model.Coordinates;
import com.example.model.ModelInfo;
import com.example.model.ModelUpdate;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FieldGrid extends GridPane{
    final int height;
    final int width;
    final String style1;
    final String style2;
    Pane[][] fieldPanes;

    FieldGrid(int h, int w, String s1, String s2){
        this.height = h;
        this.width = w;
        this.style1 = s1;
        this.style2 = s2;
        fieldPanes = new Pane[h][w];
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

    public FieldGrid(int h, int w){
        this(h,w,Resources.styleCell1, Resources.styleCell2);
    }

    public void update(ModelUpdate modelUpdate){

    }

    public void redraw(ModelInfo info) throws FileNotFoundException{
        for(int r=0;r < height; r++){
            for(int c=0;c<width; c++){
                if(!fieldPanes[r][c].getChildren().isEmpty())fieldPanes[r][c].getChildren().remove(0);
            }
        }

        for(Fruct i:info.fructs){
            System.out.println(i.symbol + " " + i.getCoordinates().toString());
            fieldPanes[i.getRow()][i.getCol()].getChildren().add(i.getImageView());
        }

        System.out.println("snake: ");
        for(Coordinates i:info.snake){
            System.out.println(i.toString());
            fieldPanes[i.r][i.c].getChildren().add(
                new SnakePane(info.isProtected)
            );
        }
    }
}
