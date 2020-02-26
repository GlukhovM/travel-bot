package com.github.glukhovm.travel.configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BotProperties {
    private final String name;
    private final String token;

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public BotProperties(File propertiesFile) throws IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(propertiesFile)) {
            properties.load(reader);
        }
        this.name = properties.getProperty("bot.name");
        this.token = properties.getProperty("bot.token");
    }

}
