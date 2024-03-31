package com.example.model;

import java.util.ArrayDeque;
import java.util.ArrayList;

import com.example.model.DrivingDirections.Direction;

public class SnakeDeque extends ArrayDeque<Coordinates>{
    int height, width;
    Coordinates deadTail;
    Direction direction = DrivingDirections.Direction.UP;

    SnakeDeque(int h, int w){
        height = h;
        width = w;

        this.addFirst(new Coordinates(h/2, w/2));
    }

    ArrayDeque<Coordinates> plan = new ArrayDeque<>();

    public void addToPlan(Coordinates c){
        plan.addLast(c);
    }

    public SnakeUpdate update(){
        Coordinates head = getHead();
        return update(head);
    }

    private Coordinates getHead(){
        if(plan.isEmpty()){
            Coordinates head = this.getFirst().copy();

            switch (direction) {
                case UP:
                    head.r--;
                    if(head.r==-1) head.r = height-1;
                    break;
                case DOWN:
                    head.r++;
                    if(head.r==height) head.r = 0;
                    break;
                case LEFT:
                    head.c--;
                    if(head.c==-1) head.c = width-1;
                    break;
                case RIGHT:
                    head.c++;
                    if(head.c==width) head.c = 0;
                    break;
            }
            return head;
        }

        Coordinates head = plan.getFirst();
        plan.removeFirst();
        return head;
    }

    public SnakeUpdate update(Coordinates head){
        //Coordinates head = this.getFirst().copy();
        
        this.addFirst(head);

        deadTail = this.getLast();
        this.removeLast();
        return new SnakeUpdate(head, deadTail);
    }

    public Coordinates increaseSnake(){
        this.addLast(deadTail);
        return deadTail;
    }

    public void setDirection(Direction d) {
        direction = d;
    }

    public ArrayList<Coordinates> toArrayList() {
        ArrayList<Coordinates> tempSnake = new ArrayList<>(this);
        return tempSnake;
    }


}
