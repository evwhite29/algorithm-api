package org.evwhite.algorithmapi;

import java.util.List;

public class GameConfig {
    private List<Coordinate> startingAlive;
    private int generations;

    public List<Coordinate> getStartingAlive() {
        return startingAlive;
    }

    public void setStartingAlive(List<Coordinate> startingAlive) {
        this.startingAlive = startingAlive;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }
}
