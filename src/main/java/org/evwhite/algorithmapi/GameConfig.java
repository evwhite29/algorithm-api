package org.evwhite.algorithmapi;

import java.util.List;

public class GameConfig {
    private int gridHeight;
    private int gridWidth;
    private List<Coordinate> startingAlive;
    private int generations;

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

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
