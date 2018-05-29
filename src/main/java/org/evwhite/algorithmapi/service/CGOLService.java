package org.evwhite.algorithmapi.service;

import org.evwhite.algorithmapi.Coordinate;
import org.evwhite.algorithmapi.GameConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CGOLService {

    private static boolean startingGrid[][];
    private static boolean resultingGrid[][];
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

    public List<Coordinate> playGameOfLife(GameConfig gameConfig) {
        int realHeight = gameConfig.getGridHeight();
        int realWidth = gameConfig.getGridWidth();
        int paddedHeight = realHeight + 2;
        int paddedWidth = realWidth + 2;

        startingGrid = new boolean[paddedHeight][paddedWidth];
        resultingGrid = new boolean[paddedHeight][paddedWidth];

        List<Coordinate> cellsStartingAlive = gameConfig.getStartingAlive();
        for (Coordinate cell: cellsStartingAlive) {
            startingGrid[cell.getRow() + 1][cell.getColumn() + 1] = true;
        }

        for (int row = 1; row <= realHeight; ++row) {
            for (int col = 1; col <= realWidth; ++ col) {

                int aliveNeighbors = countLiveNeighbors(row, col);
                resultingGrid[row][col] = determineState(row, col, aliveNeighbors);

            }
        }

        return pollAlive(realHeight, realWidth);
    }

    private int countLiveNeighbors(int row, int col) {
        int foundAlive = 0;

        for (Coordinate neighbor: NEIGHBORS) {
            if (startingGrid[row + neighbor.getRow()][col + neighbor.getColumn()]) {
                ++foundAlive;
            }
        }

        return foundAlive;
    }

    private boolean determineState(int row, int col, int aliveNeighbors) {
        boolean alive = false;

        if (startingGrid[row][col] && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
            alive = true;
        } else if (!startingGrid[row][col] && aliveNeighbors == 3) {
            alive = true;
        }

        return alive;
    }

    private List<Coordinate> pollAlive(int realHeight, int realWidth) {
        List<Coordinate> aliveCells = new ArrayList<>();

        for (int row = 1; row <= realHeight; ++row) {
            for (int col = 1; col <= realWidth; ++ col) {

                if (resultingGrid[row][col]) {
                    aliveCells.add(new Coordinate(row - 1, col - 1));
                }

            }
        }

        return aliveCells;
    }
}
