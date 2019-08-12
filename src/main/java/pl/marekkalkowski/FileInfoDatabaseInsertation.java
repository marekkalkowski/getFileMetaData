package pl.marekkalkowski;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;


public class FileInfoDatabaseInsertation {

    public static final Logger LOG = LogManager.getLogger(FileInfoDatabaseInsertation.class);

    public void insertSkrytkiIntoDB(FileInfo fileInfo, Statement statement) {
        int recordNumber = 0;
        try {
            String sqlWithAdresess = String.format("exec UMG_InsertFileInfo @name=\'%s\', @sizeByte=\'%s\', @sizeKB=\'%s\', @creationDate=\'%s\', @modificationDate=\'%s\'"
                    , fileInfo.getName()
                    , Long.toString(fileInfo.getSizeByte())
                    , Long.toString(fileInfo.getSizeKB())
                    , fileInfo.getCreationDate()
                    , fileInfo.getLastModify());

            statement.execute(sqlWithAdresess);
            recordNumber++;
            LOG.debug("{} Record inserted for: {}", recordNumber, fileInfo.toString());
        } catch (Exception e) {
            LOG.warn("{} Error Record not inserted for: {}", recordNumber, fileInfo.toString());
            e.printStackTrace();
        }
    }
}

