package br.com.tommiranda.blog.config;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author wwthi
 */
public final class ConfigBuilder {

    private static final Object lock = new Object();
    private static Config config;

    private ConfigBuilder() {
    }

    public static Config getConfiguration() throws IOException {
        if (config == null) {
            synchronized (lock) {
                config = configFromJsonFile();
            }
        }

        return config;
    }

    private static Config configFromJsonFile() throws IOException {
        try {
            Path config_path = Path.of("./config.json");
            String configuracoes = Files.readString(config_path);

            return new Gson().fromJson(configuracoes, Config.class);
        } catch (IOException e) {
            throw new IOException("Arquivo config.json não encontrado", e);
        }
    }
}
