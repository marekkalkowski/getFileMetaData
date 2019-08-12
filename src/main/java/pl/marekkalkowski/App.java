package pl.marekkalkowski;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.Properties;
import java.util.TimeZone;

public class App {
    public static final Logger LOG = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
        Instant start = Instant.now();
        LOG.info("Czas rozpoczęcia działania aplikacji: " + start);
        Properties properties = new Properties();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection;
        FileInfoDatabaseInsertation fileInfoDatabaseInsertation = new FileInfoDatabaseInsertation();

        try {
            properties.load(new FileInputStream(FileReaderProperties.APP_CONFIG_PATH));
            databaseConnection.gettingConnectToDatabase(properties);
            connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String sqlCheckIfTableExist = "exec UMG_checkIfExistTableUmgSkrytkiEpuap";
            statement.execute(sqlCheckIfTableExist);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        String catalogNumber = properties.getProperty("dirName");
        String pathToFolder = properties.getProperty("pathToDir") + catalogNumber;

        try {
            connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();

            Files.walk(Paths.get(pathToFolder))
                    .filter(path -> !Files.isDirectory(path))
                    .map(path -> {
                        return getFileInfo(path);
                    })
                    .filter(fileInfo -> LocalDate.parse(fileInfo.getCreationDate()).isAfter(LocalDate.parse(properties.getProperty("startDate"))))
                    .forEach(fileInfo -> {
                        fileInfoDatabaseInsertation.insertSkrytkiIntoDB(fileInfo, statement);
                    });

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        LOG.info("Czas zakończenia działania aplikacji: " + end);
        LOG.info("Czas wykonania:" +  Duration.between(start,end));
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
        int dotIndex = fileName.indexOf(".");
        if (dotIndex >= 0) {
            return fileName.substring(0, dotIndex);
        } else {
            LOG.debug("Plik bez rozszerzenia" + fileName);
            return fileName;
        }
    }

    private static String cleanFileDateInfo(String fileTime) {
        int dotIndex = fileTime.indexOf("T");
        if (dotIndex >= 0) {
            return fileTime.substring(0, dotIndex);
        } else {
            LOG.debug("Plik bez rozszerzenia" + fileTime);
            return fileTime;
        }
    }
}
