package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class YamlReader{
    private Map<String, Integer> data;

    /**
     * constructor.
     * @param filePath file to read
     */
    public YamlReader(String filePath){
        data = new HashMap<>();
        String separ = ":";
        try(final BufferedReader fr=new BufferedReader(new FileReader(filePath));){
            String tmp;
            while((tmp = fr.readLine()) != null)
            {
                data.put(tmp.split(separ)[0].trim(), Integer.parseInt(tmp.split(separ)[1].trim()));
            }
        }catch(Exception e)
        {
            e.printStackTrace(); //NOPMD only for exercise
        }
    }

    public Integer getParameter(String s)
    {
        return data.get(s);
    }

}