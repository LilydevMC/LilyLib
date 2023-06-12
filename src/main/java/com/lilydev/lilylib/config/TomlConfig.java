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

public class TomlConfig {

    public Toml tomlData;
    Path filePath;

    Logger CONFIG_LOGGER;

    public TomlConfig(String modName, String path, String fileName) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        filePath = Paths.get(currentPath.toString(), path, fileName + ".toml");

        CONFIG_LOGGER = LoggerFactory.getLogger(modName + " // LilyConfig");
    }

    public void init() {
        CONFIG_LOGGER.info("Initializing TOML config!");
        findOrCreateFile();
    }

    public void save(Map<String, Object> configData) {
        try {
            FileWriter writer = new FileWriter(new File(filePath.toUri()));
            writer.write(mapToTomlString(configData));
            writer.close();
            CONFIG_LOGGER.info("Wrote to " + filePath + "!");
            load();
        } catch (IOException exception) {
            CONFIG_LOGGER.error("Couldn't write to config file: " + exception);
        }
    }

    public void load() {
        tomlData = new Toml().read(new File(filePath.toUri()));
    }

    public void findOrCreateFile() {
        File configFile = new File(filePath.toUri());

        try {
            if (configFile.getParentFile().mkdirs()) {
                CONFIG_LOGGER.info("Creating config parent directories!");
            }

            if (configFile.createNewFile()) {
                CONFIG_LOGGER.info("Creating config file!");
                createFile(generateTomlMap());
            } else {
                CONFIG_LOGGER.info("Config file found!");
                load();
            }

        } catch (IOException exception) {
            CONFIG_LOGGER.error(String.valueOf(exception));
        }
    }

    public void createFile(Map<String, Object> baseTomlMap) {
        save(baseTomlMap);
    }

    public Map<String, Object> generateTomlMap() {
        Map<String, Object> configMap = new HashMap<>();
        Map<String, Object> categoryMap = new HashMap<>();

        categoryMap.put("message", "This is the default TOML file for LilyConfig! " +
                "If you're reading this, you're either a mod developer or someone messed up...");

        configMap.put("LilyConfig", categoryMap);

        return configMap;
    }

    private String mapToTomlString(Map<String, Object> map) {
        TomlWriter tomlWriter = new TomlWriter.Builder().build();
        return tomlWriter.write(map);
    }

}
