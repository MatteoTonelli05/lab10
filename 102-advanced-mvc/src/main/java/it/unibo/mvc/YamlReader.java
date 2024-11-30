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
    private static final String FILESTARTERPATH = "src"
            .concat(File.separator).concat("main").concat(File.separator)
            .concat("resources").concat(File.separator);

    /**
     * constructor.
     * @param fileName only the name of the config file with its extension
     */
    public YamlReader(final String fileName) throws IOException {
        final String filePath = FILESTARTERPATH.concat(fileName);
        System.out.println(filePath); //NOPMD only for exercise purpose
        data = new HashMap<>();
        final String separ = ":";
        try (BufferedReader fr = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String tmp = fr.readLine();
            while (tmp != null) {
                data.put(tmp.split(separ)[0].trim(), Integer.parseInt(tmp.split(separ)[1].trim()));
                tmp = fr.readLine();
            }
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
