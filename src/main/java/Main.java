import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {

         WordCount w=new WordCount();
         String testString = (args != null && args.length == 1) ? args[0] :"apple banana apple orange \n /" ;
        System.out.println("Running word freq count with input : " + testString);
        HashMap<String, Integer> wordsMap = w.getWordCountMap(testString);
        System.out.println(wordsMap);
        System.out.println("Number of characters are :" + w.numOfCharacters);
        System.out.println("Number of lines are :"+ w.numberOfLines);

    }
}
