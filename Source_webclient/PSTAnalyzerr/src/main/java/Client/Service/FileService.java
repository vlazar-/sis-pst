package Client.Service;

import java.io.File;

/**
 * Created by Viktor on 08/01/2015.
 */
public class FileService {

    public String getFile(String file) {
        reportException(file);
        String fileName = file;
        System.out.println("WEB CLIENT - .pst file path: " + fileName);
        return fileName;
    }

    private void reportException(String file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("You must select .pst file for analysis");
        }
    }
}
