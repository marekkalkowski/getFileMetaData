package pl.marekkalkowski;

import java.nio.file.attribute.FileTime;

public class FileInfo {

    private String name;
    private long sizeByte;
    private long sizeKB;
    private FileTime creationDate;
    private FileTime lastModify;


    public FileInfo() {
    }

    public FileInfo(String name, long sizeByte, long sizeKB, FileTime creationDate, FileTime lastModify) {
        this.name = name;
        this.sizeByte = sizeByte;
        this.sizeKB = sizeKB;
        this.creationDate = creationDate;
        this.lastModify = lastModify;
    }

    public long getSizeKB() {
        return sizeKB;
    }

    public void setSizeKB(long sizeKB) {
        this.sizeKB = sizeKB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSizeByte() {
        return sizeByte;
    }

    public void setSizeByte(long sizeByte) {
        this.sizeByte = sizeByte;
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
                sizeByte + ',' +
                sizeKB + ',' +
                creationDate + ',' +
                lastModify;
    }
}
