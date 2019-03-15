package pl.marekkalkowski;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(FileReaderProperties.APP_CONFIG_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String catalogNumber = properties.getProperty("dirName");
        String pathToFolder = properties.getProperty("pathToDir") + catalogNumber;
        List<String> fileInfos;
        String outFileName = "filename"+ catalogNumber + ".txt";
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
            fileInfo.setCreationDate(cleanFileDateInfo(attr.creationTime().toString()));
            fileInfo.setName(cleanFileName(path.getFileName().toString()));
            fileInfo.setSizeByte(attr.size());
            fileInfo.setSizeKB(attr.size() / 1024);
            fileInfo.setLastModify(cleanFileDateInfo(attr.lastModifiedTime().toString()));

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

    private static String cleanFileDateInfo(String fileTime) {
        int i = 0;
        int dotIndex = fileTime.indexOf("T");
        if (dotIndex >= 0) {
            return fileTime.substring(0, dotIndex);
        } else {
            i++;
            System.out.println(i + ' ' + fileTime);
            return fileTime;
        }
    }
}
