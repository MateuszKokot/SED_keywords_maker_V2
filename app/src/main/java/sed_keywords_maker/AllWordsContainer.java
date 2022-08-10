package sed_keywords_maker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class AllWordsContainer {

    public  final String SOURCE_FILE_NAME = "odm.txt";
    private File file;
    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private boolean fileLoaded = false;
    private boolean hasNextLine = true;
    private String lineRead;


    public void loadFile(){
        try{
            ClassLoader loader = AllWordsContainer.class.getClassLoader();
            file = new File(loader.getResource(SOURCE_FILE_NAME).getFile());
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            setFileLoaded(true);
        } catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean hasLineToRead(){
        try {
            lineRead = bufferedReader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        if (lineRead == null) hasNextLine = false;
        return hasNextLine;
    }

    public ArrayList<Word> getNextKeyWord(){

        String[] splitedLine = lineRead.split(",\\s+");
        ArrayList<Word> words = new ArrayList<>();
        for (String s : splitedLine) {
            Word word = new Word(replaceBadCharacterInString(s), replaceBadCharacterInString(splitedLine[0]), "0");
            words.add(word);
        }
        return  words;
    }

    private String replaceBadCharacterInString (String str){
        String cleanedString ="";

        for (char c : str.toCharArray()) {
            if (c!=39) {
                cleanedString += c;
            } else {
                cleanedString += " ";
            }
        }

//        for (char c : str.toCharArray()) {
//            if ( (c>64 && c<91) /*UTF8 *A-Z*/ || (c>96 && c<123) /*a-z*/ || (c>259 && c<264) /*Ą,ą,Ć,ć*/|| (c>279 && c<282) /*Ę,ę */ || (c>320 && c<325) /*Ł,ł,Ń,ń*/|| (c==211) /*Ó*/|| (c==243) /*ó*/|| (c>345 && c<348) /*Ś,ś*/ || (c>376 && c<381) /*Ź,ź,Ż,ż*/) {
//                cleanedString += c;
//            } else {
//                cleanedString += " ";
//                System.out.println(c);
//            }
//        }
        return cleanedString;
    }

    public void releaseResource (){

        if (bufferedReader != null){
            try {
                bufferedReader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        if (inputStreamReader != null){
            try {
                inputStreamReader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        if (fileInputStream != null){
            try {
                fileInputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public boolean isFileLoaded() {
        return fileLoaded;
    }

    private void setFileLoaded(boolean fileLoaded) {
        this.fileLoaded = fileLoaded;
    }

}
