package com.example.ObserverPanes;

import java.io.FileNotFoundException;

public interface Observer {
    public abstract void update(Object b) throws FileNotFoundException;
}
