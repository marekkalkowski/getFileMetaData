package pl.marekkalkowski;

import java.nio.file.attribute.FileTime;

public class FileInfo {

    private String name;
    private long size;
    private FileTime creationDate;
    private FileTime lastModify;


    public FileInfo() {
    }

    public FileInfo(String name, int size, FileTime creationDate, FileTime lastModify) {
        this.name = name;
        this.size = size;
        this.creationDate = creationDate;
        this.lastModify = lastModify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(FileTime creationDate) {
        this.creationDate = creationDate;
    }

    public FileTime getLastModify() {
        return lastModify;
    }

    public void setLastModify(FileTime lastModify) {
        this.lastModify = lastModify;
    }

    @Override
    public String toString() {
        return name + ',' +
                size + ',' +
                creationDate + ',' +
                lastModify;
    }
}
