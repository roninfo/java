package Util;

import java.io.File;
import java.net.URL;

public class GetFileFromResources {

    public static GetFileFromResources INSTANCE = new GetFileFromResources();

    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

}
