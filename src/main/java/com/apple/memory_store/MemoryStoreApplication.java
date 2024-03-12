package com.apple.memory_store;

import com.apple.memory_store.model.enums.Color;
import com.apple.memory_store.model.impl.ColorStore;
import com.apple.memory_store.model.interfaces.MemoryStore;

import java.util.Scanner;

/**
 * The {@code MemoryStoreApplication} class provides a command-line interface to interact with
 * a memory store that maps string keys to {@link Color} values. Users can store colors with associated
 * keys, retrieve colors by keys, and receive guidance on using the application through a help menu.
 * <p>
 * Supported commands include:
 * <ul>
 *     <li>store &lt;key&gt; &lt;value&gt; - Stores a color with the specified key.</li>
 *     <li>retrieve &lt;key&gt; - Retrieves and displays the color associated with the key.</li>
 *     <li>help - Displays a help message with available commands.</li>
 *     <li>exit - Exits the application.</li>
 * </ul>
 * </p>
 * <p>
 * This application demonstrates basic usage of the {@link MemoryStore} interface and its implementation
 * through the {@link ColorStore} class.
 * </p>
 */
public class MemoryStoreApplication {
    private static MemoryStore<String, Color> store = new ColorStore();

    /**
     * Main method that provides a command-line interface for the memory store application.
     * It accepts commands from the user, processes them, and provides feedback.
     *
     * @param args Command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for reading input

        printHelp();
        while (isRunning) {
            System.out.print("Enter command: "); // Prompt for user input
            String input = scanner.nextLine(); // Read user input
            String[] commandArgs = input.split(" "); // Split input into command and arguments

            processCommand(commandArgs);

            if ("exit".equals(commandArgs[0])) {
                isRunning = false;
            }
        }
        scanner.close(); // Close the scanner when the application exits
    }

    /**
     * Processes the user input commands and executes the corresponding actions.
     * 
     * @param commandArgs Array of strings representing the command and its arguments.
     */
    private static void processCommand(String[] commandArgs) {
        switch (commandArgs[0]) {
            case "store":
                if (commandArgs.length == 3) {
                    store.store(commandArgs[1], Color.valueOf(commandArgs[2].toUpperCase()));
                    System.out.println("Stored color " + commandArgs[2].toUpperCase() + " with key " + commandArgs[1]);
                } else {
                    System.out.println("Invalid command. Usage: store <key> <value>");
                }
                break;
            case "retrieve":
                if (commandArgs.length == 2) {
                    Color color = store.get(commandArgs[1]);
                    System.out.println("Retrieved color: " + (color != null ? color : "GREY"));
                } else {
                    System.out.println("Invalid command. Usage: retrieve <key>");
                }
                break;
            case "help":
                printHelp();
                break;
            case "exit":
                System.out.println("Exiting application...");
                break;
            default:
                System.out.println("Invalid command. Type 'help' for available commands.");
        }
    }

    /**
     * Prints the help message to the console, detailing the usage and available commands
     * for the application.
     */
    private static void printHelp() {
        System.out.println("Memory Store Application");
        System.out.println("Available commands:");
        System.out.println("store <key> <value> - Stores a color with the specified key.");
        System.out.println("retrieve <key> - Retrieves and displays the color associated with the key.");
        System.out.println("help - Displays this help message.");
        System.out.println("exit - Exits the application.");
    }
}
