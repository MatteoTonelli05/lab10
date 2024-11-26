package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class YamlReader<K,V> {
    private Map<K,V> data;

    /**
     * constructor.
     * @param filePath file to read
     */
    @SuppressWarnings("unchecked")
    public YamlReader(String filePath){
        try(final BufferedReader fr=new BufferedReader(new FileReader(filePath));){
            String tmp;
            while((tmp = fr.readLine()) != null)
            {
                data.put((K)tmp.split(":")[0], (V)tmp.split(":")[1]);
            }
        }catch(Exception e)
        {
            e.printStackTrace(); //NOPMD only for exercise
        }
    }

    public V getParameter(K s)
    {
        return data.get(s);
    }

}