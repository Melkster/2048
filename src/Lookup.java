package src;

import java.util.*;
import java.io.*;

/*
*   Class to Lookup text from the available Languages
*   which are read from the languages.txt
*/
public class Lookup {

    private ArrayList<Language> languages;

    /*
    *   Base Constructor
    */
    public Lookup() {
        List<String> completeFile = readFile("./src/languages.txt");
        this.languages = new ArrayList<Language>();

        int lenCompFile = completeFile.size();

        for (int i=0; i<lenCompFile; i++) {
            if (completeFile.get(i).equals("Lang")) {
                i++;
                Language temp = new Language(completeFile, i);
                this.languages.add(temp);
            }
        }
    }

    /*
    *   Function to lookup specific keyword from the
    *   lang specified.
    */
    public String lookupLang(int lang, String keyword) {
        return this.languages.get(lang).lookupIndexWord(keyword);
    }

    /*
    *   Function to read a whole file with the languages.txt
    *   layout and create languages from it.
    */
    private List<String> readFile(String filename)
    {
        List<String> fullFile = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] splited = line.split("\" \"");
                
                for(int i=0; i<splited.length; i++) {
                    fullFile.add(splited[i]);
                }
            }
            reader.close();
            return fullFile;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
}