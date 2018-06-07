package org.evwhite.algorithmapi.service;

import org.evwhite.algorithmapi.Coordinate;
import org.evwhite.algorithmapi.GameConfig;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CGOLService {

    private static boolean[][] startGrid;
    private static boolean[][] resultGrid;
    private static final Coordinate[] neighbors = new Coordinate[] {
            new Coordinate(-1, -1),
            new Coordinate(-1, 0),
            new Coordinate(-1, 1),
            new Coordinate(0, -1),
            new Coordinate(0, 1),
            new Coordinate(1, -1),
            new Coordinate(1, 0),
            new Coordinate(1, 1)
    };

    private int realHeight;
    private int realWidth;

    private int generations;

    private List<Coordinate> endingAlive;

    public List<Coordinate> playGameOfLife(GameConfig gameConfig) {

        configureGame(gameConfig);

        boolean lastGeneration = false;
        for (int generation = 1; generation <= generations; ++generation) {
            if (generation == generations) {
                lastGeneration = true;
            }

            makeNextGeneration(lastGeneration);

            if (!lastGeneration) {
                swapGrids();
            }
        }

        return endingAlive;
    }

    private void swapGrids() {
        boolean[][] holder = startGrid;
        startGrid = resultGrid;
        resultGrid = holder;
    }

    private void makeNextGeneration(boolean lastGeneration) {
        for (int row = 1; row <= realHeight; ++row) {
            for (int column = 1; column <= realWidth; ++column) {
                int aliveNeighbors = countNeighbors(row, column);
                resultGrid[row][column] = determineNextState(row, column, aliveNeighbors, lastGeneration);
            }
        }

    }

    private void configureGame(GameConfig gameConfig) {
        realHeight = gameConfig.getGridHeight();
        realWidth = gameConfig.getGridWidth();
        generations = gameConfig.getGenerations();

        if (realHeight <= 0 || realWidth <= 0) {
            throw new HttpMessageNotReadableException("The grid must have dimensions that are greater than 0");
        }

        int paddedHeight = realHeight + 2;
        int paddedWidth = realWidth + 2;

        startGrid = new boolean[paddedHeight][paddedWidth];
        resultGrid = new boolean[paddedHeight][paddedWidth];

        for (Coordinate coordinate : gameConfig.getStartingAlive()) {
            startGrid[coordinate.getRow() + 1][coordinate.getColumn() + 1] = true;
        }

        endingAlive = new ArrayList<>();
    }

    private int countNeighbors(int row, int column) {
        int alive = 0;

        for (Coordinate coordinate : neighbors) {
            if (startGrid[row + coordinate.getRow()][column + coordinate.getColumn()]) {
                ++alive;
            }
        }

        return alive;
    }

    private boolean determineNextState(int row, int column, int aliveNeighbors, boolean lastGeneration) {
        boolean currentState = startGrid[row][column];
        boolean nextState = false;

        if (currentState && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
            nextState = true;
        } else if (!currentState && aliveNeighbors == 3) {
            nextState = true;
        }

        if (lastGeneration && nextState) {
            endingAlive.add(new Coordinate(row - 1, column - 1));
        }

        return nextState;
    }

}
