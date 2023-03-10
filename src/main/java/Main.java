import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {

         WordCount w=new WordCount();
         String testString = (args != null && args.length == 1) ? args[0] :"apple banana apple orange  /" ;
        System.out.println("Running word freq count with input : " + testString);
        HashMap<String, Integer> words= w.getWordCountMap(testString);
        System.out.println(words);
    }
}
