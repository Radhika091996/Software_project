import java.util.HashMap;


class WordProcessor {
    HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
    int numberOfLines =0;
    int numOfCharacters=0;

    public HashMap<String, Integer> getWordCountMap(String data)  throws Exception{
        String[] words=null;
        if(data == null) {
            return wordCountMap;
        }
        if(data.isEmpty()){
            return wordCountMap;

        }
        numberOfLines=1;
        words = data.split("\\s+|\\t|\\n");
        // calculate new line data
        for (char character:data.toCharArray()){
            if(character  == '\n' )
                numberOfLines+=1;
        }
        updateCount(words);
        return wordCountMap;
    }

    private void updateCount(String[] words) {
        for (String word: words) {
            if(word.isEmpty()) continue;
            // count number of character
            for (char character:word.toCharArray()){
                if((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z'))
                numOfCharacters+=1;
            }
            if (wordCountMap.containsKey(word)){
                int count = wordCountMap.get(word);
                count++;
                wordCountMap.put(word,count);
            } else {
                wordCountMap.put(word,1);
            }
        }
    }

    public String replaceWords(String data, String toReplace, String replaceWith){
        if(toReplace.isEmpty()) return data;
        if(toReplace.contains(" ") || replaceWith.contains(" ")){
            throw new IllegalArgumentException(replaceWith);
        }
        return data.replaceAll("\\b"+ toReplace+ "\\b",replaceWith);
    }
}
