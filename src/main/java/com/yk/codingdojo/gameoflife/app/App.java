package com.yk.codingdojo.gameoflife.app;

import com.yk.codingdojo.gameoflife.core.GameOfLife;
import com.yk.codingdojo.gameoflife.utils.MatrixUtils;
import com.yk.codingdojo.gameoflife.utils.ThreadUtils;

public class App {

    public void execute() {
        final int areaSize = 15;
        final int[] nonZeroElementsCoordinates = {1, 1, 1, 2, 1, 3, 1, 4, 2, 3, 2, 5, 6, 7, 8, 1, 9, 3, 9, 5, 9, 7, 10,
                12, 11, 1, 11, 2,
                11,
                4};
        final int[][] matrix = MatrixUtils.initSquareMatrix(areaSize);
        MatrixUtils.makeAliveElements(matrix, nonZeroElementsCoordinates);
        final GameOfLife gameOfLife = new GameOfLife(matrix);
        while (true) {
            System.out.println(gameOfLife);
            System.out.println("Evolving...\n\n\n");
            gameOfLife.evolve();
            ThreadUtils.sleep(500);
        }
    }
}
