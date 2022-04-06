import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

//letterImgmapping
public abstract class letterImgMapping {

    private static final String LEXICONPATH = "src/cmudict-0.7b.txt";
    private static final String VOWELPATH = "src/vowels";
    private static final String STROKECHARPATH = "src/shrtHandLetter";
    private static Set<Character> vowels = new HashSet<>();
    //hashmap containing character and its shorthand
    static Map<Character, shrtHand> strokeMap = new HashMap<>();
    private static Map<String, List<Character>> lexicon;
    //finds the pronounciation from list and adds it in array
    private static Character[] getWordSymbols(String s) throws IllegalArgumentException{
        List<Character> phoneList;
        phoneList = lexicon.getOrDefault(s.toUpperCase(), Collections.emptyList());
        return phoneList.toArray(new Character[phoneList.size()]);
    }
    //has all the entered words pronounciation in a 2d array
    public static Character[][] phones(String s) {
        String[] words = s.split(" ");
        Character[][] phones = new Character[words.length][];

        for (int i = 0; i < phones.length; i++) {
            try {
                phones[i] = getWordSymbols(words[i]);
            }
            catch (IllegalArgumentException e) {
            }
        }
        return phones;
    }
    //loads shorthand images at runtime
     static void load(){
        lexicon = new HashMap<>();
        String line;
        try(FileReader fr = new FileReader(LEXICONPATH);
            BufferedReader br = new BufferedReader(fr)) {
            for (int i = 0; i < 56; i++) {
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                mapLex(line);
            }
        }
        catch(IOException e){e.printStackTrace();}
        try(FileReader fr = new FileReader(VOWELPATH)) {
            int next;
             while ((next = fr.read()) != -1) {
                 vowels.add((char)next);
             }
        }
        catch(IOException e){e.printStackTrace(); }
     }
    static void loadImages(){
        String line;
         // Map characters to new strokes
        try(FileReader fr = new FileReader(STROKECHARPATH);
            BufferedReader br = new BufferedReader(fr)){
            while((line = br.readLine()) != null){
                mapStrokes(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void mapLex(String s){
        Scanner sc = new Scanner(s);
        List<Character> phones = new ArrayList<>();
        String word = sc.next();
        while(sc.hasNext()){
            phones.add(sc.next().charAt(0));
        }
        lexicon.put(word, phones);
    }
    static boolean isVowel(char c){
        return vowels.contains(c);
    }
    //for strokes to be continous in words
    private static void mapStrokes(String line) {
        String[] words = line.split(" ");
        char phoneme = words[0].charAt(0);
        String strokeFileName = words[1];
        int x1 = Integer.parseInt(words[2]);
        int y1 = Integer.parseInt(words[3]);
        int x2 = Integer.parseInt(words[4]);
        int y2 = Integer.parseInt(words[5]);
        Point start = new Point(x1, y1);
        Point end = new Point (x2, y2);
        shrtHand stroke = new shrtHand(strokeFileName, start, end);
        strokeMap.put(phoneme, stroke);
    }
}
