package org.evwhite.algorithmapi.service;

import org.evwhite.algorithmapi.Coordinate;
import org.evwhite.algorithmapi.GameConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CGOLService {

    private static boolean startGrid[][];
    private static boolean resultGrid[][];
    private static final Coordinate[] NEIGHBORS = {
            new Coordinate(-1, -1),
            new Coordinate(-1, 0),
            new Coordinate(-1, 1),
            new Coordinate(0, -1),
            new Coordinate(0, 1),
            new Coordinate(1, -1),
            new Coordinate(1, 0),
            new Coordinate(1, 1),
    };

    private int realHeight;
    private int realWidth;

    public List<Coordinate> playGameOfLife(GameConfig gameConfig) {
        configureGrid(gameConfig);

        int generationsToSimulate = gameConfig.getGenerations();
        for (int generations = 1; generations <= generationsToSimulate; ++generations) {
            createNextGeneration();
            if (generations != generationsToSimulate) {
                swapGrids();
            }
        }

        return pollAlive();
    }

    private void swapGrids() {
        boolean[][] holder = startGrid;
        startGrid = resultGrid;
        resultGrid = holder;
    }

    private void configureGrid(GameConfig gameConfig) {
        realHeight = gameConfig.getGridHeight();
        realWidth = gameConfig.getGridWidth();
        int paddedHeight = realHeight + 2;
        int paddedWidth = realWidth + 2;

        startGrid = new boolean[paddedHeight][paddedWidth];
        resultGrid = new boolean[paddedHeight][paddedWidth];

        List<Coordinate> cellsStartingAlive = gameConfig.getStartingAlive();
        for (Coordinate cell: cellsStartingAlive) {
            startGrid[cell.getRow() + 1][cell.getColumn() + 1] = true;
        }
    }

    private void createNextGeneration() {
        for (int row = 1; row <= realHeight; ++row) {
            for (int col = 1; col <= realWidth; ++col) {

                int aliveNeighbors = countLiveNeighbors(row, col);
                resultGrid[row][col] = determineState(row, col, aliveNeighbors);

            }
        }
    }

    private int countLiveNeighbors(int row, int col) {
        int foundAlive = 0;

        for (Coordinate neighbor: NEIGHBORS) {
            if (startGrid[row + neighbor.getRow()][col + neighbor.getColumn()]) {
                ++foundAlive;
            }
        }

        return foundAlive;
    }

    private boolean determineState(int row, int col, int aliveNeighbors) {
        boolean alive = false;
        boolean currentCell = startGrid[row][col];

        if (currentCell && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
            alive = true;
        } else if (!currentCell && aliveNeighbors == 3) {
            alive = true;
        }

        return alive;
    }

    private List<Coordinate> pollAlive() {
        List<Coordinate> aliveCells = new ArrayList<>();

        for (int row = 1; row <= realHeight; ++row) {
            for (int col = 1; col <= realWidth; ++col) {

                if (resultGrid[row][col]) {
                    aliveCells.add(new Coordinate(row - 1, col - 1));
                }

            }
        }

        return aliveCells;
    }
}
