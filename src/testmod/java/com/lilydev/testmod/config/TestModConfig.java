package com.lilydev.testmod.config;

import com.lilydev.lilylib.config.LilyConfig;

import java.util.HashMap;
import java.util.Map;

public class TestModConfig extends LilyConfig {

    public TestModConfig(String modName, String path, String fileName) {
        super(modName, path, fileName);
    }

    @Override
    public Map<String, Object> generateTomlMap() {
        Map<String, Object> config = new HashMap<>();
        Map<String, Object> testSection = new HashMap<>();

        testSection.put("test_int", 42069);
        testSection.put("test_string", "a string! a test string!!!");

        config.put("General", testSection);

        return config;
    }
}
