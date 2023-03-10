import java.io.*;

import java.util.HashMap;

class WordCount {
    HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();

    public HashMap<String, Integer> getWordCountMap(String data)  throws Exception{
        String[] words=null;
        if(data == null) {
            return wordCountMap;
        }
        words = data.split("\\s+|\\t|\\n");
        updateCount(words);
        return wordCountMap;
    }

    private void updateCount( String[] words) {
        for (String word: words) {
            if(word.isEmpty()) continue;
            if (wordCountMap.containsKey(word)){
                int count = wordCountMap.get(word);
                count++;
                wordCountMap.put(word,count);
            } else {
                wordCountMap.put(word,1);
            }
        }
    }
}