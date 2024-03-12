package com.apple.memory_store;

import com.apple.memory_store.model.enums.Color;
import com.apple.memory_store.model.impl.ColorStore;
import com.apple.memory_store.model.interfaces.MemoryStore;

import java.util.Scanner;

public class MemoryStoreApplication {
    private static MemoryStore<String, Color> store = new ColorStore();

    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for reading input

        printHelp();
        while (isRunning) {
            System.out.print("Enter command: "); // Prompt for user input
            String input = scanner.nextLine(); // Read user input
            String[] commandArgs = input.split(" "); // Split input into command and arguments

            if (commandArgs.length > 0) {
                String command = commandArgs[0];
                switch (command) {
                    case "store":
                        if (commandArgs.length < 3) {
                            System.out.println("Invalid number of arguments for store command");
                            break;
                        }
                        String key = commandArgs[1];
                        try {
                            Color value = Color.valueOf(commandArgs[2].toUpperCase());
                            store.store(key, value);
                            System.out.println("Stored " + value + " for key " + key);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid color value. Please use one of the predefined colors.");
                        }
                        break;
                    case "retrieve":
                        if (commandArgs.length < 2) {
                            System.out.println("Invalid number of arguments for retrieve command");
                            break;
                        }
                        key = commandArgs[1];
                        Color color = store.get(key);
                        if (color != null) {
                            System.out.println(color);
                        } else {
                            System.out.println("No value found for key " + key);
                        }
                        break;
                    case "help":
                        printHelp();
                        break;
                    case "exit":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid command");
                        printHelp();
                }
            }
        }
        scanner.close(); // Close the scanner when the application exits
    }

    private static void printHelp() {
        System.out.println("Memory Store Application");
        System.out.println("Usage: ");
        System.out.println("  store <key> <value> - Store a value with a key");
        System.out.println("  retrieve <key> - Retrieve a value by key");
        System.out.println("  help - Print this help message");
        System.out.println("  exit - Exit the application");
    }
}
