import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {

    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101);  // 0 and 100
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int value : array) {
                writer.write(value + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int[] array = new int[1000];  
            int index = 0;

            while ((line = reader.readLine()) != null) {
                array[index++] = Integer.parseInt(line);
            }

            // Trim the array to actual size
            int[] result = new int[index];
            System.arraycopy(array, 0, result, 0, index);
            return result;
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return new int[0]; //Failure
    }

    // Bubble Sort 
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Main function 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1. Generate a random array and store it in a file.");
        System.out.println("2. Read an array from a file, sort it, and save it to another file.");

        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter the length of the array: ");
            int length = scanner.nextInt();
            int[] randomArray = createRandomArray(length);

            System.out.print("Enter the filename to save the array: ");
            String filename = scanner.next();
            writeArrayToFile(randomArray, filename);

            System.out.println("Random array has been generated and saved to " + filename);
        } else if (choice == 2) {
            System.out.print("Enter the filename to read the array from: ");
            String inputFilename = scanner.next();
            int[] array = readFileToArray(inputFilename);

            bubbleSort(array);  // Sort the array

            System.out.print("Enter the filename to save the sorted array: ");
            String outputFilename = scanner.next();
            writeArrayToFile(array, outputFilename);

            System.out.println("The array has been sorted and saved to " + outputFilename);
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }
}
