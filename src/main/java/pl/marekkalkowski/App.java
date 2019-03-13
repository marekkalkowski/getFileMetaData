package pl.marekkalkowski;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        String pathToFolder = "\\\\10.16.5.14\\e$\\STORAGE\\21";
        List<String> fileInfos;
        String outFileName = "filename21.txt";
        PrintWriter out = null;

        try {
            out = new PrintWriter(outFileName);
            fileInfos = Files.walk(Paths.get(pathToFolder))
                    .filter(path -> !Files.isDirectory(path))
                    .map(path -> {
                        return getFileInfo(path);
                    })
                    .map(fileInfo1 -> fileInfo1.toString())
                    .collect(Collectors.toList());

            for (String file : fileInfos
            ) {
                out.println(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private static FileInfo getFileInfo(Path path) {
        FileInfo fileInfo = new FileInfo();
        try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            fileInfo.setCreationDate(attr.creationTime());
            fileInfo.setName(cleanFileName(path.getFileName().toString()));
            fileInfo.setSizeByte(attr.size());
            fileInfo.setSizeKB(attr.size() / 1024);
            fileInfo.setLastModify(attr.lastModifiedTime());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInfo;
    }

    private static String cleanFileName(String fileName) {
        int i = 0;
        int dotIndex = fileName.indexOf(".");
        if (dotIndex >= 0) {
            return fileName.substring(0, dotIndex);
        } else {
            i++;
            System.out.println(i + ' ' + fileName);
            return fileName;
        }
    }
}
