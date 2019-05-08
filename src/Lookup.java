package src;

import java.util.*;
import java.io.*;

public class Lookup {

    private ArrayList<Language> languages;

    public Lookup() {
        List<String> completeFile = readFile("./languages.txt");
        this.languages = new ArrayList<Language>();

        int lenCompFile = completeFile.size();

        for (int i=0; i<lenCompFile; i++) {
            if (completeFile.get(i).equals("Lang")) {
                i++;
                Language temp = new Language(completeFile, i);
                this.languages.add(temp);
            }
        }

        for (int k=0; k<this.languages.size(); k++) {
            this.languages.get(k).printAll();
            System.out.println();
        }

        System.out.println(this.languages.get(0).lookupIndexWord("info1"));
        System.out.println(this.languages.get(1).lookupIndexWord("info1"));
    }

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
                    //System.out.println(splited[i]);
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

    public static void main(String[]args) {
        Lookup lu = new Lookup();
    }
}