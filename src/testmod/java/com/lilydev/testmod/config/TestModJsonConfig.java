package com.lilydev.testmod.config;

import com.lilydev.lilylib.config.JsonConfig;

import java.util.HashMap;
import java.util.Map;

public class TestModJsonConfig extends JsonConfig {

    public TestModJsonConfig(String modName, String path, String fileName) {
        super(modName, path, fileName);
    }

    @Override
    public Map<String, Object> generateJsonMap() {
        Map<String, Object> config = new HashMap<>();
        Map<String, Object> testSection = new HashMap<>();

        testSection.put("test_int", 42069);
        testSection.put("test_string", "a string! a test string!!!");

        config.put("general", testSection);

        return config;
    }
}
