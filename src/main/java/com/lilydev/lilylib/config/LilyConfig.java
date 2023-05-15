package com.lilydev.lilylib.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LilyConfig {
    Path filePath;

    Logger CONFIG_LOGGER;

    public LilyConfig(String modName, String path, String fileName) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        filePath = Paths.get(currentPath.toString(), path, fileName + ".toml");

        CONFIG_LOGGER = LoggerFactory.getLogger(modName + " // LilyConfig");
    }

    public FileOutputStream getOrCreateFile() {
        File configFile = new File(filePath.toUri());

        try {
            if (configFile.getParentFile().mkdirs()) {
                CONFIG_LOGGER.info("Creating config parent directories!");
            }

            if (configFile.createNewFile()) {
                CONFIG_LOGGER.info("Creating config file!");
            } else {
                CONFIG_LOGGER.info("Config file found!");
            }

        } catch (IOException exception) {
            CONFIG_LOGGER.error(String.valueOf(exception));
        }

        FileOutputStream outputFile = null;

        try {
            outputFile = new FileOutputStream(configFile, false);
        } catch (FileNotFoundException exception) {
            CONFIG_LOGGER.error("File not found: " + exception);
        }

        return outputFile;
    }

}
