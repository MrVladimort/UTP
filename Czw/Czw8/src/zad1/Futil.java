package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Futil {
    private static List<String> filesData;
    public static void processDir(String dirName, String resultFileName) {
        filesData = new ArrayList<>();
        try {
            File resultFile = new File(dirName + "/" + resultFileName);
            resultFile.delete();
            Files.walkFileTree(Paths.get(dirName), new MyFileVisitor());
            Files.write(Paths.get(resultFile.getAbsolutePath()), filesData, Charset.forName("UTF-8"));
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void getFileData(File file) throws IOException {
        filesData.addAll(Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.forName("cp1250")));
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    private static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (!file.toFile().isDirectory() && getFileExtension(file.toFile()).equals("txt")) getFileData(file.toFile());
            return FileVisitResult.CONTINUE;
        }
    }
}
