package com.yk.codingdojo.gameoflife.app;

import com.yk.codingdojo.gameoflife.utils.MatrixUtils;

public class GameOfLife {

    private final int [][] matrix;
    private final int size;

    public GameOfLife(int[][] matrix) {
        this.matrix = matrix;
        size = matrix.length;
    }

    public void evolve() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int livingNeighborCellsNumber = calculateLivingNeighborCells(i, j);
                if (matrix[i][j] == 0) { // Dead cell
                    if (livingNeighborCellsNumber == 3) {
                        matrix[i][j] = 1; // Cell becomes alive.
                    }
                } else { // Alive cell
                    if (livingNeighborCellsNumber != 2 && livingNeighborCellsNumber != 3) {
                        matrix[i][j] = 0; // Cell dies.
                    }
                }
            }
        }
    }

    private int calculateLivingNeighborCells(int x, int y) {
        int counter = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }

                if (getCellValue(i, j) == 1) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private int getCellValue(int x, int y) {
        if (isCellCoordinateValid(x) && isCellCoordinateValid(y)) {
            return matrix[x][y];
        } else {
            return 0;
        }
    }

    private boolean isCellCoordinateValid(int cellCoordinate) {
        return cellCoordinate >= 0 && cellCoordinate < size;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        return MatrixUtils.matrixToString(matrix);
    }
}
