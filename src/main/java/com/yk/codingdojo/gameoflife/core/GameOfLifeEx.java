package com.yk.codingdojo.gameoflife.core;

import java.util.BitSet;

public class GameOfLifeEx {

    private BitSet matrix;
    private int size;

    public GameOfLifeEx(int [][] numberMatrix) {
        size = numberMatrix.length;
        this.matrix = new BitSet(size * size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (numberMatrix[i][j] == 1) {
                    setTrue(i, j);
                }
            }
        }
    }

    public boolean get(int i, int j) {
        return matrix.get(i * size + j);
    }

    public BitSet getMatrix() {
        return matrix;
    }

    private void setTrue(int i, int j) {
        matrix.set(i * size + j);
    }

    private void setFalse(int i, int j) {
        matrix.set(i * size + j, false);
    }

    public void evolve() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int livingNeighborCellsNumber = calculateLivingNeighborCells(i, j);
                if (livingNeighborCellsNumber == 0 && !get(i, j)) {
                    j = j + 1;
                    continue;
                }
                if (livingNeighborCellsNumber < 2 || livingNeighborCellsNumber >= 4 ) {
                    setFalse(i, j);
                } else if (livingNeighborCellsNumber == 3) {
                    setTrue(i, j);
                }/* else if (livingNeighborCellsNumber == 2 && get(i, j)) {
                    setTrue(i, j); // Cell becomes alive.
                }*/

                /*
                if (get(i, j) == false) { // Dead cell
                    if (livingNeighborCellsNumber == 3) {
                        setTrue(i, j); // Cell becomes alive.
                    }
                } else { // Alive cell
                    if (livingNeighborCellsNumber != 2 && livingNeighborCellsNumber != 3) {
                       setFalse(i, j); // Cell dies.
                    }
                }*/
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

                if (getCellValue(i, j)) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private boolean getCellValue(int x, int y) {
        if (isCellCoordinateValid(x) && isCellCoordinateValid(y)) {
            return get(x, y);
        } else {
            return false;
        }
    }

    private boolean isCellCoordinateValid(int cellCoordinate) {
        return cellCoordinate >= 0 && cellCoordinate < size;
    }
}
