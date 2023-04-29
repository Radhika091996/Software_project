import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {

         WordProcessor w=new WordProcessor();
         String testString = " this apple is like banana \n /";
         String replaceword = "";
         String replacewithword = "";

         if(args!= null){
             if(args.length >= 1){
                 testString = args[0];
             }
            if(args.length >= 2){
                replaceword = args[1];
            }
             if(args.length >= 3){
                 replacewithword = args[2];
             }
         }

//         String testString = (args != null && args.length == 1) ? args[0] :" the apple banana apple orange \n /"  ;
//         String replaceword=(args != null && args.length > 2 ) ? args[2]: "this";
//         String replacewithword=args[2];

        System.out.println("Running word freq count with input : " + testString);
        HashMap<String, Integer> wordsMap = w.getWordCountMap(testString);
        System.out.println(wordsMap);
        System.out.println("Number of characters are :" + w.numOfCharacters);
        System.out.println("Number of lines are :"+ w.numberOfLines);
//        String.out.p

        System.out.println("replaced string is  :"+ w.replaceWords(testString,replaceword,replacewithword));

    }
}
