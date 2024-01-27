import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class MatrixMultiplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            if (args.length == 2) {

                String fileName1 = args[0];
                String fileName2 = args[1];
                int[][] matrix1 = readMatrixFromFile(fileName1);
                int[][] matrix2 = readMatrixFromFile(fileName2);
                multiplyAndSaveResult(matrix1, matrix2);

            } else if (args.length == 1 && isInteger(args[0])) {

                int size = Integer.parseInt(args[0]);
                int[][] matrix1 = generateRandomMatrix(size);
                int[][] matrix2 = generateRandomMatrix(size);
                saveMatrixToFile(matrix1, "matrix1.txt");
                saveMatrixToFile(matrix2, "matrix2.txt");
                multiplyAndSaveResult(matrix1, matrix2);

            } else {
 
                System.out.println("Enter 'file' to input file name or 'integer' to input an integer:");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("file")) {
                    System.out.println("Enter the first file name:");
                    String fileName1 = scanner.nextLine();
                    System.out.println("Enter the second file name:");
                    String fileName2 = scanner.nextLine();
                    int[][] matrix1 = readMatrixFromFile(fileName1);
                    int[][] matrix2 = readMatrixFromFile(fileName2);
                    multiplyAndSaveResult(matrix1, matrix2);

                } else if (userInput.equalsIgnoreCase("integer")) {
                    System.out.println("Enter the size of the square matrices:");
                    int size = scanner.nextInt();
                    int[][] matrix1 = generateRandomMatrix(size);
                    int[][] matrix2 = generateRandomMatrix(size);
                    saveMatrixToFile(matrix1, "matrix1.txt");
                    saveMatrixToFile(matrix2, "matrix2.txt");
                    multiplyAndSaveResult(matrix1, matrix2);

                } else {
                    System.out.println("Invalid input. Exiting program.");
                }
            }
        } catch (NumberFormatException e) {

            e.printStackTrace();
        }
    }

    private static int[][] readMatrixFromFile(String fileName) {
        int[][] matrix = null;
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            int rows = fileScanner.nextInt();
            int columns = fileScanner.nextInt();
            matrix = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = fileScanner.nextInt();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        return matrix;
    }

    private static void saveMatrixToFile(int[][] matrix, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            int rows = matrix.length;
            int columns = matrix[0].length;
            writer.println(rows + " " + columns);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    writer.print(matrix[i][j] + " ");
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }

    private static void multiplyAndSaveResult(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;

        if (columns1 != rows2) {
            System.out.println("Matrix multiplication not possible. Exiting program.");
            return;
        }

        int[][] result = new int[rows1][columns2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < columns1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        saveMatrixToFile(result, "matrix3.txt");
        System.out.println("Matrix multiplication result saved to matrix3.txt.");
    }

    private static int[][] generateRandomMatrix(int size) {
        int[][] matrix = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }

        return matrix;
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
