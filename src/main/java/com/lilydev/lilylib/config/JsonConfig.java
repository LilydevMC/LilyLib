package com.lilydev.lilylib.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JsonConfig {

    public Map<String, Object> data;

    public ObjectMapper objectMapper;

    Path filePath;

    Logger CONFIG_LOGGER;

    public JsonConfig(String modName, String path, String fileName) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        filePath = Paths.get(currentPath.toString(), path, fileName + ".json");

        objectMapper = new ObjectMapper();

        CONFIG_LOGGER = LoggerFactory.getLogger(modName + " // LilyConfig");
    }

    public void init() {
        CONFIG_LOGGER.info("Initializing JSON config!");
        findOrCreateFile();
    }

    public void save(Map<String, Object> configData) {
        try {
            FileWriter writer = new FileWriter(new File(filePath.toUri()));
            writer.write(mapToJsonString(configData));
            writer.close();
            CONFIG_LOGGER.info("Wrote to '" + filePath + "'!");
            load();
        } catch (IOException exception) {
            CONFIG_LOGGER.error("Couldn't write to file '" + filePath.toString() + "':" + exception);
        }
    }

    public void load() {
        try {
            data = objectMapper.reader().readValue(new File(filePath.toUri()), Map.class);
            CONFIG_LOGGER.info("Loading '" + filePath.toString() + "'!");
        } catch (IOException exception) {
            CONFIG_LOGGER.error("Couldn't load file '" + filePath.toString() + "':" + exception);
        }
    }

    public void findOrCreateFile() {
        File configFile = new File(filePath.toUri());

        try {
            if (configFile.getParentFile().mkdirs()) {
                CONFIG_LOGGER.info("Creating config parent directories!");
            }

            if (configFile.createNewFile()) {
                CONFIG_LOGGER.info("Creating config file!");
                createFile(generateJsonMap());
            } else {
                CONFIG_LOGGER.info("Config file found!");
                load();
            }

        } catch (IOException exception) {
            CONFIG_LOGGER.error(String.valueOf(exception));
        }
    }

    public void createFile(Map<String, Object> data) {
        save(data);
    }

    public Map<String, Object> generateJsonMap() {
        Map<String, Object> configMap = new HashMap<>();
        Map<String, Object> categoryMap = new HashMap<>();

        categoryMap.put("message", "This is the default TOML file for LilyConfig! " +
                "If you're reading this, you're either a mod developer or someone messed up...");

        configMap.put("LilyConfig", categoryMap);
        return configMap;
    }

    public String mapToJsonString(Map<String, Object> map) {
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();

        DefaultIndenter indenter = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);

        prettyPrinter.indentArraysWith(indenter);
        prettyPrinter.indentObjectsWith(indenter);

        try {
            return objectMapper
                    .setDefaultPrettyPrinter(prettyPrinter)
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(map);
        } catch (JsonProcessingException exception) {
            CONFIG_LOGGER.error(String.valueOf(exception));
            return "Error processing JSON.";
        }
    }

}
