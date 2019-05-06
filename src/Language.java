package src;

import java.util.List;
import java.util.ArrayList;

public class Language {

    private String language;
    private ArrayList<String> indexWordList;
    private ArrayList<String> translationList;

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

    public String lookupIndexWord(String check) {
        int len = indexWordList.size();
        for (int i=0; i<len; i++) {
            if (indexWordList.get(i).equals(check)) {
                return translationList.get(i);
            }
        }

        return "";
    }

    public void printAll() {
        int len = translationList.size();

        System.out.println(this.language);

        for (int i=0; i<len; i++) {
            System.out.println(translationList.get(i));
        }
    }
}