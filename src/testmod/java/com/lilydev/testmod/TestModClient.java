package com.lilydev.testmod;

import com.lilydev.testmod.config.TestModJsonConfig;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TestModClient implements ClientModInitializer {

    public Logger LOGGER = LoggerFactory.getLogger("Test Mod");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello Fabric world! This is Test Mod!!!");

        TestModJsonConfig config = new TestModJsonConfig("Test Mod", "config", "test_mod");
        config.init();

        LOGGER.info("Test string: " + config.data);

        Map<String, Object> generalData = (Map<String, Object>) config.data.get("general");

        generalData.replace("test_string", "a string! a modified test string!!!");

        config.save(config.data);

        LOGGER.info("Test string: " + config.data);
    }
}