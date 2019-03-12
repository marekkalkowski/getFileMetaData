package pl.marekkalkowski;

import java.util.ArrayList;
import java.util.List;

public class FileInfoRepository {

    private static List<FileInfo> fileInfoList = new ArrayList<>();

    public static List<FileInfo> getFileInfoList() {
        return fileInfoList;
    }

    public static void setFileInfoList(List<FileInfo> fileInfoList) {
        FileInfoRepository.fileInfoList = fileInfoList;
    }

    public static void add(FileInfo fileInfo){
        fileInfoList.add(fileInfo);

    }
}
