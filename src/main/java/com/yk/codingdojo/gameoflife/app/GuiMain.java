package com.yk.codingdojo.gameoflife.app;

import com.yk.codingdojo.gameoflife.utils.MatrixUtils;
import com.yk.codingdojo.gameoflife.utils.ThreadUtils;

import javax.swing.*;
import java.awt.*;

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


    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1000, 1000);


        //Display the window.
        frame.setVisible(true);
        final Graphics2D graphics2D = (Graphics2D) frame.getGraphics();
        execute(graphics2D);
    }


    public static void execute(final Graphics2D graphics2D) {
        final int areaSize = 1000;
        final int[] nonZeroElementsCoordinates = MatrixUtils.randomArray(200000, areaSize);
        final int[][] matrix = MatrixUtils.initSquareMatrix(areaSize);
        MatrixUtils.makeAliveElements(matrix, nonZeroElementsCoordinates);
        final GameOfLife gameOfLife = new GameOfLife(matrix);
        while (true) {
            graphics2D.fillRect(0, 0, areaSize, areaSize);
            drowMatrix(gameOfLife.getMatrix(), areaSize, graphics2D);
            ThreadUtils.sleep(50);
            gameOfLife.evolve();
        }
    }

    private static void drowMatrix(int [][] matrix, int areaSize, Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        for (int i = 0; i < areaSize; i++) {
            for (int j = 0; j < areaSize; j++) {
                if (matrix[i][j] == 1) {

                    graphics2D.drawLine(i, j, i + 5, j + 5);
                }
            }
        }
        graphics2D.setColor(Color.BLACK);
    }
}
