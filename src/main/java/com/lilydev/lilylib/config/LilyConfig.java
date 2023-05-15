package com.lilydev.lilylib.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LilyConfig {
    Path filePath;


    public LilyConfig(String path, String fileName) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));

        filePath = Paths.get(currentPath.toString(), path, fileName);
    }

}
