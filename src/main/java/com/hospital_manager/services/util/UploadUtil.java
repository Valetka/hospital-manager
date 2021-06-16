package com.hospital_manager.services.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class UploadUtil {

    private static final String PATH_TO_STORAGE = ResourceBundle.getBundle("application").getString("application.path_to_storage_in_server");
    private static final String PATH_TO_STORAGE_IN_USER = ResourceBundle.getBundle("application").getString("application.path_to_storage_in_db");
    private static final String PATH_TO_STORAGE_DEFAULT = ResourceBundle.getBundle("application").getString("application.path_to_default_image");

    public static String upload(Part part){
        String path;
        if(part == null){
            path = PATH_TO_STORAGE_DEFAULT;
        }else {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            File uploads = new File(PATH_TO_STORAGE);
            File file = new File(uploads, fileName);
            if(!file.exists()) {
                try (InputStream fileContent = part.getInputStream()) {
                    Files.copy(fileContent, file.toPath());
                } catch (IOException e) {
                    throw new UtilException(e);
                }
            }
            path = PATH_TO_STORAGE_IN_USER+fileName;
        }
        return path;
    }
}
