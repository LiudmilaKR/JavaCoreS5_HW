package lesson5HW;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Backup {
     //Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup

    public static void main(String[] args) throws IOException {
        String destName = "./backup";
        Files.createDirectories(Paths.get(destName));
        String sourceName = "C:/Users/lidia/OneDrive/Desktop/Разное";
        copyFiles(sourceName, destName, sourceName, destName);
    }

    private static void copyFiles(String source, String destination, String prevSource, String prevDestination) throws IOException {
        prevSource = source;
        prevDestination = destination;
        Files.createDirectories(Paths.get(destination));
        File sourceDir = new File(source);
        File[] files = sourceDir.listFiles();
        if (files.length != 0) {
            for (File item : files) {
                if (item.isFile()) {
                    File n = new File(destination + "/" + item.getName());
                    Files.copy(item.toPath(), n.toPath(), REPLACE_EXISTING);
                } else if (item.isDirectory()) {
                    destination += "/" + item.getName();
                    source += "/" + item.getName();
                    copyFiles(source, destination, prevSource, prevDestination);
                    source = prevSource;
                    destination = prevDestination;
                }
            }
        }
    }
}
