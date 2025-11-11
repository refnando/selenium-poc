package com.refnando.poc.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnvReader {
    private static final Map<String, String> ENV = new HashMap<>();

    static {
        loadEnv();
    }

    private static void loadEnv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int idx = line.indexOf('=');
                if (idx > 0) {
                    String key = line.substring(0, idx).trim();
                    String value = line.substring(idx + 1).trim();
                    ENV.put(key, value);
                }
            }
        } catch (IOException e) {
            System.err.println("[EnvReader] No se encontró el archivo .env o no se pudo leer.");
        }
    }

    public static String get(String key) {
        // Prioriza System Properties, luego variables del entorno, luego .env
        if (System.getProperty(key) != null && !System.getProperty(key).isEmpty())
            return System.getProperty(key);
        if (System.getenv(key) != null && !System.getenv(key).isEmpty())
            return System.getenv(key);
        return ENV.getOrDefault(key, "");
    }
}
