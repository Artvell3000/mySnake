package com.example;

import java.io.FileNotFoundException;

import com.example.fructs.Fruct;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FieldPane extends Pane{
    public final String style;
    private Fruct fruct;

    public boolean isEmpty(){
        return this.getChildren().size() == 0;
    }

    public void updateGameObject(){
        this.getChildren().removeAll();
    }

    public void updateGameObject(Fruct f) throws FileNotFoundException{
        this.fruct = f;
        this.getChildren().removeAll();
        this.getChildren().add(f.getImage());
    }

    FieldPane(String style){
        this.style = style;
    }
}
