import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCountTest {
    WordCount wordCount;
    @BeforeEach
    void beforeEach(){
        wordCount = new WordCount();
    }

    @Test
    void emptyString_shouldReturnEmptyMap() throws Exception {
        Map actual, expected;
        expected = Collections.emptyMap();
        actual = wordCount.getWordCountMap("");
        assertEquals(expected,actual);
    }

    @Test
    void onlyOneWordString_shouldReturnCorrectMap() throws Exception {
        Map actual, expected;
        expected = Map.of("apple", 1);
        actual = wordCount.getWordCountMap("apple");
        assertEquals(expected,actual);
    }

    @Test
    void twoWordString_shouldReturnCorrectMap() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1);
        actual = wordCount.getWordCountMap("apple  banana");
        assertEquals(expected,actual);
    }
    @Test
    void largeString_shouldReturnCorrectMap() throws Exception {
        Map actual, expected;
        expected = Map.of("apple", 3,"1234",1,"banana",1,"mango", 1, "grapes", 2,"orange",1,"cherry",1);
        actual = wordCount.getWordCountMap("apple  banana 1234 apple mango grapes cherry grapes orange apple");
        assertEquals(expected,actual);
    }

    @Test
    void nullString_shouldReturnEmptyMap() throws Exception {
        Map actual, expected;
        expected = Collections.emptyMap();
        actual = wordCount.getWordCountMap(null);
        assertEquals(expected,actual);
    }

    @Test
    void stringWithNewLine_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1);
        actual = wordCount.getWordCountMap("apple \n banana");
        assertEquals(expected,actual);
    }

    @Test
    void stringWithMultipleNewLine_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1);
        actual = wordCount.getWordCountMap("apple \n\n banana");
        assertEquals(expected,actual);
    }

    @Test
    void stringWithTab_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1);
        actual = wordCount.getWordCountMap("apple \t banana");
        assertEquals(expected,actual);
    }

    @Test
    void stringWithMultipleWhiteSpace_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1 );
        actual = wordCount.getWordCountMap("apple           banana");
        assertEquals(expected,actual);
    }
    @Test
    void stringWithPunctuation_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1,"/", 1 , "," ,1);
        actual = wordCount.getWordCountMap("apple     /   ,   banana");
        assertEquals(expected,actual);
    }
}