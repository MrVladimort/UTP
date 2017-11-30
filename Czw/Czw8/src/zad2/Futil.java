package zad2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Futil {
    static List<String> filesData;

    public static void processDir(String dirName, String resultFileName) {
        filesData = new ArrayList<>();
        File resultFile = new File(dirName + "/" + resultFileName);
        resultFile.delete();
    }

    private static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

}
