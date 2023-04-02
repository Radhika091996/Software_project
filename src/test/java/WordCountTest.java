import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

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
        expected = Map.of("aPPlE", 1,"banana",1,"/", 1 , "," ,1, "APPLE",1);
        actual = wordCount.getWordCountMap("APPLE  aPPlE   /   ,   banana");
        assertEquals(expected,actual);
    }
    // Test Number of Lines
    @Test
    void numOfLine_moreThenOneLine_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("abc xyz \n   \n pqr");
        assertEquals(3,wordCount.numberOfLines);
    }

    @Test
    void numOfLine_emptyFile_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("");
        assertEquals(0,wordCount.numberOfLines);
    }

    @Test
    void numOfLine_singleLine_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap(" this is apple. this is banana ");
        assertEquals(1,wordCount.numberOfLines);
    }
    @Test
    void numOfLine_countEmptyLine_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("this is apple \n \n \n this is banana" );
        assertEquals(4, wordCount.numberOfLines);
    }

    // Test Number of Characters
    @Test
    void numOfChar_validString_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("abs test");
        assertEquals(7,wordCount.numOfCharacters);
    }


    @Test
    void numOfChar_oneWord_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("r");
        assertEquals(1,wordCount.numOfCharacters);

    }
     @Test
    void numOfChar_ingnoreSpecialCharcter_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("@#^*)this");
        assertEquals(4,wordCount.numOfCharacters);

    }
    @Test
    void numOfChar_emptyWordCount_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("");
        assertEquals(0,wordCount.numOfCharacters);
    }


    @Test
    void numOfChar_ignoreNumbers_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("123software");
        assertEquals(8,wordCount.numOfCharacters);
    }

    @Test
    void numOfChar_whiteSpace_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("                     ");
        assertEquals(0,wordCount.numOfCharacters);
    }

    @Test
    void numOfChar_multipleLine_shouldReturnExpectedResult()throws Exception{
        wordCount.getWordCountMap("      abc   \n    xyz        ");
        assertEquals(6,wordCount.numOfCharacters);
    }
}