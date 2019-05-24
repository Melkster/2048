package src;

import java.util.*;

/*
*   Class to describe a Language which is created
*   from a List<Strings> which are sent into the
*   Constructor. The Language constructor follows the
*   structure of the language.txt file which is read
*   in the Lookup.java file.
*/
public class Language {

    private String language;
    private ArrayList<String> indexWordList;
    private ArrayList<String> translationList;

    /*
    *   Base Constructor
    */
    public Language(List<String> compFile, int index) {
        int maxLen = compFile.size();
        
        index++;
        this.language = compFile.get(index);
        index++;

        indexWordList = new ArrayList<String>();
        translationList = new ArrayList<String>();

        while (index < maxLen) {
            if (!(compFile.get(index).equals(""))) {
                this.indexWordList.add(compFile.get(index));
                index++;
                this.translationList.add(compFile.get(index));
            }
            else {
                break;
            }
            index++;
        }
    }

    /*
    *   Function to look up a specific word and find its corresponding
    *   index in the actual language file. The Index words remain
    *   the same for all languages.
    */
    public String lookupIndexWord(String check) {
        if (check.equals("language")) {
            return this.language;
        }
        
        int len = indexWordList.size();
        for (int i=0; i<len; i++) {
            if (indexWordList.get(i).equals(check)) {
                return translationList.get(i);
            }
        }

        return "";
    }

    /*
    *   Function to print the whole language.
    */
    public void printAll() {
        int len = translationList.size();

        System.out.println(this.language);

        for (int i=0; i<len; i++) {
            System.out.println(indexWordList.get(i));
            System.out.println(translationList.get(i));
        }
    }
}