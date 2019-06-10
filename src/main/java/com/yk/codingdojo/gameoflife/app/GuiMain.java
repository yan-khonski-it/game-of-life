package com.yk.codingdojo.gameoflife.app;

import com.yk.codingdojo.gameoflife.core.GameOfLife;
import com.yk.codingdojo.gameoflife.core.GameOfLifeEx;
import com.yk.codingdojo.gameoflife.utils.MatrixUtils;
import com.yk.codingdojo.gameoflife.utils.ThreadUtils;

import javax.swing.*;
import java.awt.*;
import java.util.BitSet;

public class GuiMain {

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static final int AREA_SIZE = 1200;

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, AREA_SIZE, AREA_SIZE);


        //Display the window.
        frame.setVisible(true);
        final Graphics2D graphics2D = (Graphics2D) frame.getGraphics();
        execute(graphics2D);
    }


    public static void execute(final Graphics2D graphics2D) {
        final int areaSize = AREA_SIZE;
        final int[] nonZeroElementsCoordinates = MatrixUtils.randomArray(100000, areaSize);
        final int[][] matrix = MatrixUtils.initSquareMatrix(areaSize);
        MatrixUtils.makeAliveElements(matrix, nonZeroElementsCoordinates);
        //
       //  final GameOfLife gameOfLife = new GameOfLife(matrix);
        final GameOfLifeEx gameOfLife = new GameOfLifeEx(matrix);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, areaSize, areaSize);
        ThreadUtils.sleep(300);
        drowMatrix(gameOfLife.getMatrix(), areaSize, graphics2D);

        int generation = 1;
        while (true) {
            long nanoStart = System.nanoTime();
            gameOfLife.evolve();
            long nanoEnd = System.nanoTime();
            long nanoTotal = (nanoEnd - nanoStart) / 1000;
            int totalSurvivedCount = drowMatrix(gameOfLife.getMatrix(), areaSize, graphics2D);
           // ThreadUtils.sleep(20);
            System.out.println("Generation: " + generation++ + ", totalSurvivedCount: " + totalSurvivedCount + ", " +
                    "microseconds:" + nanoTotal);
        }
    }

    private static int drowMatrix(int[][] matrix, int areaSize, Graphics2D graphics2D) {
     //   graphics2D.setColor(Color.WHITE);

        int totalSurvivedCount = 0;
        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                if (matrix[i][j] == 1) {
                    totalSurvivedCount++;
                    graphics2D.setColor(Color.WHITE);
                    graphics2D.drawLine(i, j, i, j);
                } else {
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawLine(i, j, i, j);
                }
            }
        }

        return totalSurvivedCount;
    //   graphics2D.setColor(Color.BLACK);
    }

    private static int drowMatrix(BitSet matrix, int areaSize, Graphics2D graphics2D) {
        int totalSurvivedCount = 0;

        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                if (matrix.get(i * areaSize + j)) {
                    totalSurvivedCount++;
                    graphics2D.setColor(Color.WHITE);
                    graphics2D.drawLine(i, j, i, j);
                } else {
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawLine(i, j, i, j);
                }
            }
        }

        return totalSurvivedCount;
    }
}
