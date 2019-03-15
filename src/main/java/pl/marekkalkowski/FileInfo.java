package pl.marekkalkowski;

public class FileInfo {

    private String name;
    private long sizeByte;
    private long sizeKB;
    private String creationDate;
    private String lastModify;


    public FileInfo() {
    }

    public FileInfo(String name, long sizeByte, long sizeKB, String creationDate, String lastModify) {
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
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
