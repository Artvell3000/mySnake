package com.example;

import javafx.scene.layout.GridPane;

public class FieldGrid extends GridPane{
    final int height;
    final int width;
    final String style1;
    final String style2;
    FieldPane[][] fieldPanes;

    FieldGrid(int h, int w, String s1, String s2){
        this.height = h;
        this.width = w;
        this.style1 = s1;
        this.style2 = s2;
        fieldPanes = new FieldPane[h][w];
        this.setGridLinesVisible(true);

        for(int r=0;r<height;r++){
            for(int c=0;c<width;c++){
                String s = (r%2==0 || c%2==0)? s1 : s2;
                fieldPanes[r][c] = new FieldPane(s);
                this.add(fieldPanes[r][c], r, c);
            }
        }
    }

    FieldGrid(int h, int w){
        this(h,w,Resoueces.styleCell1, Resoueces.styleCell2);
    }

    public boolean isFreeCell(int r, int c){
        return fieldPanes[r][c].isEmpty();
    }
}
