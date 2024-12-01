package console;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class Command {

    private static final Scanner scanner = new Scanner(System.in);
    private static String currentPath = System.getProperty("user.dir");

    public void startCommandLoop() throws IOException {
        while (true) {
            System.out.print(currentPath + ">");
            String command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "help":
                    displayHelp();
                    break;
                case "clone":
                    executeClone();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "dir":
                    listDirectory(currentPath);
                    break;
                default:
                    if (command.startsWith("cd ")) {
                        changeDirectory(command.substring(3).trim());
                    } else {
                        System.out.println("Unknown command: " + command);
                    }
            }
        }
    }

    private void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("\t1. clone");
        System.out.println("\t2. dir - list files in current directory");
        System.out.println("\t3. cd <directory> - change current directory");
        System.out.println("\t4. exit - exit the program");
    }

    private void executeClone() throws IOException {
        System.out.print("Enter first project path: ");
        String project1 = scanner.nextLine().trim();
        System.out.print("Enter second project path: ");
        String project2 = scanner.nextLine().trim();

        if (!isValidDirectory(project1) || !isValidDirectory(project2)) {
            System.out.println("One or both project paths are invalid.");
            return;
        }

        // Replace with actual CloneCheck call
        System.out.println("Cloning between: " + project1 + " and " + project2);
    }

    private void listDirectory(String path) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            System.out.println("Invalid directory: " + path);
            return;
        }

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println((file.isDirectory() ? "<DIR>" : "") + "\t" + file.getName());
            }
        }
    }

    private void changeDirectory(String newPath) {
        File newDir = new File(newPath);
        if (newDir.isDirectory()) {
            currentPath = newDir.getAbsolutePath();
        } else {
            System.out.println("Invalid directory: " + newPath);
        }
    }

    private boolean isValidDirectory(String path) {
        return path != null && Files.isDirectory(Paths.get(path));
    }

    public static void main(String[] args) throws IOException {
        Command command = new Command();
        command.startCommandLoop();
    }
}
