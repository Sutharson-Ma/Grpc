
package Main;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DirectoryChecker {
    public static String getValidDirectoryPath() {
        Path directoryPath;
        boolean directoryExists;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Please enter a directory path: ");
            String inputPath = scanner.nextLine().trim();
            directoryPath = Paths.get(inputPath, new String[0]);
            directoryExists = Files.isDirectory(directoryPath, new LinkOption[0]);
            if (directoryExists) continue;
            System.out.println("Directory does not exist or is not accessible: " + String.valueOf(directoryPath));
            System.out.println("Please try again.");
        } while (!directoryExists);
        System.out.println("Valid directory found: " + String.valueOf(directoryPath));
        return directoryPath.toString();
    }
}

