package com.yk.codingdojo.gameoflife.utils;

import java.util.Random;

public final class MatrixUtils {

    public static String arrayToString(int [] array) {
        final String separator = "";
        final int separatorSize = separator.length();

        final StringBuilder stringBuilder = new StringBuilder();
        for (int value : array) {
            stringBuilder.append(value).append(separator);
        }

        return stringBuilder.substring(0, stringBuilder.length() - separatorSize);
    }

    public static String matrixToString(int[][] matrix) {
        final String lineSeparator = "\n";
        final int separatorSize = lineSeparator.length();

        StringBuilder res = new StringBuilder();

        for (int [] array : matrix) {
            res.append(arrayToString(array)).append(lineSeparator);
        }

        return res.substring(0, res.length() - separatorSize);
    }

    public static int[][] initSquareMatrix(final int size) {
        return new int[size][size];
    }

    public static void makeAliveElements(final int[][] matrix, final int ... coordinates) {
        int coordinatesSize = coordinates.length;
        if (coordinatesSize % 2 != 0) {
            coordinatesSize = coordinatesSize - 1;
        }
        if (coordinatesSize <= 2) {
            throw new RuntimeException("You did not provide coordinates.");
        }

        for (int i = 0; i < coordinatesSize; i = i + 2) {
            matrix[coordinates[i]][coordinates[i + 1]] = 1;
        }
    }

    public static int[] randomArray(int size, int maxElement) {
        int[] res = new int[size];
        final Random random = new Random();
        for (int i = 0; i < size; i++) {
            res[i] = random.nextInt(maxElement);
        }

        return res;
    }

    private MatrixUtils() {
        throw new IllegalStateException();
    }
}
