package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class YamlReader {
    private final Map<String, Integer> data;
    private static final String FILEPATH = "src".concat(File.separator).concat("main").concat(File.separator)
            .concat("resources").concat(File.separator).concat("config.yml");

    /**
     * constructor.
     */
    public YamlReader() {
        data = new HashMap<>();
        final String separ = ":";
        try (BufferedReader fr = new BufferedReader(new FileReader(FILEPATH, StandardCharsets.UTF_8));) {
            String tmp = fr.readLine();
            while (tmp != null) {
                data.put(tmp.split(separ)[0].trim(), Integer.parseInt(tmp.split(separ)[1].trim()));
                tmp = fr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // NOPMD only for exercise
        }
    }

    /**
     * ignore.
     * 
     * @param s
     * @return the value of the parameter
     */
    public Integer getParameter(final String s) {
        return data.get(s);
    }
}
