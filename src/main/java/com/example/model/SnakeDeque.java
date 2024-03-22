package com.example.model;

import java.util.ArrayDeque;

import com.example.model.DrivingDirections.Direction;

public class SnakeDeque extends ArrayDeque<Coordinates>{
    int height, width;
    Coordinates deadTail;
    Direction directions = DrivingDirections.Direction.UP;

    SnakeDeque(int h, int w){
        height = h;
        width = w;

        this.addFirst(new Coordinates(h/2, w/2));
    }

    public SnakeUpdate update(){
        Coordinates head = this.getFirst().copy();
        switch (directions) {
            case UP:
                head.r--;
                break;
            case DOWN:
                head.r++;
                break;
            case LEFT:
                head.c--;
                break;
            case RIGHT:
                head.c++;
                break;
        }
        this.addFirst(head);

        deadTail = this.getLast();
        this.removeLast();
        return new SnakeUpdate(head, deadTail);
    }

    public Coordinates increaseSnake(){
        this.addLast(deadTail);
        return deadTail;
    }


}
