package com.apple.memory_store;

import com.apple.memory_store.model.enums.Color;
import com.apple.memory_store.model.impl.ColorStore;
import com.apple.memory_store.model.interfaces.MemoryStore;

public class MemoryStoreApplication {
    private static MemoryStore<String, Color> store = new ColorStore();

    public static void main(String[] args) {
        boolean isRunning = true;

        printHelp();
        while (isRunning) {

            if (args.length > 0) {
                String command = args[0];
                switch (command) {
                    case "store":
                        if (args.length < 3) {
                            System.out.println("Invalid number of arguments for store command");
                            break;
                        }
                        String key = args[1];
                        Color value = Color.valueOf(args[2]);
                        store.store(key, value);
                        break;
                    case "retrieve":
                        if (args.length < 2) {
                            System.out.println("Invalid number of arguments for retrieve command");
                            break;
                        }
                        key = args[1];
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

    }

    private static void printHelp() {
        System.out.println("Memory Store Application");
        System.out.println("Usage: ");
        System.out.println("  store <key> <value> - Store a value with a key");
        System.out.println("  retrieve <key> - Retrieve a value by key");
        System.out.println("  help - Print this help message");
    }
}
