package util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GetFileFromResources {

    public static GetFileFromResources INSTANCE = new GetFileFromResources();

    final ClassLoader classLoader = getClass().getClassLoader();

    public File getFileFromResources(String fileName) {

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    public Stream<String> getLineFileWithStream(String fileName) {
        URL resource = classLoader.getResource(fileName);
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(resource.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
