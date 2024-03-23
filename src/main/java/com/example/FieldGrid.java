package com.example;

import java.io.FileNotFoundException;

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

    FieldGrid(int h, int w){
        this(h,w,Resoueces.styleCell1, Resoueces.styleCell2);
    }

    public void update(ModelUpdate modelUpdate){

    }

    public void redraw(ModelInfo info) throws FileNotFoundException{
        for(int r=0;r < height; r++){
            for(int c=0;c<width; c++){
                fieldPanes[r][c].getChildren().removeAll();
            }
        }

        for(Fruct i:info.fructs){
            System.out.println(i.symbol + " " + i.getCoordinates().toString());
            fieldPanes[i.getRow()][i.getCol()].getChildren().add(i.getImageView());
        }

        for(Coordinates i:info.snake){
            fieldPanes[i.r][i.c].getChildren().add(
                new SnakePane(info.isProtected)
            );
        }
    }
}
