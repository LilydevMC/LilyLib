package com.lilydev.testmod;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestModClient implements ClientModInitializer {

    public Logger LOGGER = LoggerFactory.getLogger("testmod");
    @Override
    public void onInitializeClient() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world! This is Test Mod!!!");
    }
}