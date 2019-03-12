package pl.marekkalkowski;





import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String pathToFolder = "C:\\\\video";

        FileInfo fileInfo = new FileInfo();

        List<String> fileInfos = new ArrayList<>();

        PrintWriter out = null;

        try {
           out = new PrintWriter("filename.txt");
             fileInfos = Files.list(Paths.get("C:\\\\video"))
                    .map(path -> {
                        try {
                            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                            fileInfo.setCreationDate(attr.creationTime());
                            fileInfo.setName(path.getFileName().toString());
                            fileInfo.setSize(attr.size()/1024/1024);
                            fileInfo.setLastModify(attr.lastModifiedTime());

                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                  return fileInfo;
                    }).map(fileInfo1 -> fileInfo1.toString()).collect(Collectors.toList());

            for (String file: fileInfos
            ) {
                out.println(file);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            out.close();
        }

//        System.out.println("-----------");
//        System.out.println(FileInfoRepository.getFileInfoList().size());






    }
}
