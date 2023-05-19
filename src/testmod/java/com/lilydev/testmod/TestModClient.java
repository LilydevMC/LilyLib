package com.lilydev.testmod;

import com.lilydev.lilylib.util.LilyParsing;
import com.lilydev.testmod.config.TestModConfig;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestModClient implements ClientModInitializer {

    public Logger LOGGER = LoggerFactory.getLogger("Test Mod");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello Fabric world! This is Test Mod!!!");

        TestModConfig config = new TestModConfig("Test Mod", "config", "test_mod");
        config.init();

        LOGGER.info("Test string: " + config.tomlData.getString("General.test_string"));

        String testParsedString = LilyParsing.parseStringWithVariable(
                config.tomlData.getString("General.test_string"),
                "a_placeholder",
                "BAAAAAAAAAAAAAAAAAAAAA"
        );

        LOGGER.info("Test string: " + testParsedString);
    }
}