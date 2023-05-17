package com.lilydev.lilylib.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LilyConfig {

    String defaultTomlString = generateToml();

    protected String fileContents = defaultTomlString;

    Path filePath;
    FileOutputStream outputConfigFile;


    Logger CONFIG_LOGGER;

    public LilyConfig(String modName, String path, String fileName) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        filePath = Paths.get(currentPath.toString(), path, fileName + ".toml");

        CONFIG_LOGGER = LoggerFactory.getLogger(modName + " // LilyConfig");
    }

    public void init() {
        findOrCreateFile();
    }

    public void findOrCreateFile() {
        File configFile = new File(filePath.toUri());

        try {
            if (configFile.getParentFile().mkdirs()) {
                CONFIG_LOGGER.info("Creating config parent directories!");
            }

            if (configFile.createNewFile()) {
                CONFIG_LOGGER.info("Creating config file!");
                createFile(configFile, fileContents);
            } else {
                CONFIG_LOGGER.info("Config file found!");
            }

        } catch (IOException exception) {
            CONFIG_LOGGER.error(String.valueOf(exception));
        }
    }

    public void createFile(File file, String baseFileContents) {
        try {
            outputConfigFile = new FileOutputStream(file, false);

            FileWriter writer = new FileWriter(file);
            writer.write(baseFileContents);
            writer.close();
            CONFIG_LOGGER.info("Wrote to " + filePath + "!");
        }
        catch (FileNotFoundException exception) {
            CONFIG_LOGGER.error("File not found: " + exception);
        }
        catch (IOException exception) {
            CONFIG_LOGGER.error(String.valueOf(exception));
        }
    }

    public String generateToml() {
        return generateDefaultToml();
    }

    private String generateDefaultToml() {
        Toml toml = new Toml();

        Map<String, Object> configMap = toml.toMap();
        Map<String, Object> categoryMap = new HashMap<>();

        categoryMap.put("message", "This is the default toml file for LilyConfig! " +
                "If you're reading this, you're either a mod dev or someone messed up.");

        configMap.put("LilyConfig", categoryMap);

        TomlWriter writer = new TomlWriter.Builder().build();

        return writer.write(configMap);
    }

}
