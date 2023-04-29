import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordProcessorTest {
    WordProcessor wordProcessor;
    @BeforeEach
    void beforeEach(){
        wordProcessor = new WordProcessor();
    }

    @Test
    void emptyString_shouldReturnEmptyMap() throws Exception {
        Map actual, expected;
        expected = Collections.emptyMap();
        actual = wordProcessor.getWordCountMap("");
        assertEquals(expected,actual);
    }

    @Test
    void onlyOneWordString_shouldReturnCorrectMap() throws Exception {
        Map actual, expected;
        expected = Map.of("apple", 1);
        actual = wordProcessor.getWordCountMap("apple");
        assertEquals(expected,actual);
    }

    @Test
    void twoWordString_shouldReturnCorrectMap() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1);
        actual = wordProcessor.getWordCountMap("apple  banana");
        assertEquals(expected,actual);
    }
    @Test
    void largeString_shouldReturnCorrectMap() throws Exception {
        Map actual, expected;
        expected = Map.of("apple", 3,"1234",1,"banana",1,"mango", 1, "grapes", 2,"orange",1,"cherry",1);
        actual = wordProcessor.getWordCountMap("apple  banana 1234 apple mango grapes cherry grapes orange apple");
        assertEquals(expected,actual);
    }

    @Test
    void nullString_shouldReturnEmptyMap() throws Exception {
        Map actual, expected;
        expected = Collections.emptyMap();
        actual = wordProcessor.getWordCountMap(null);
        assertEquals(expected,actual);
    }

    @Test
    void stringWithNewLine_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1);
        actual = wordProcessor.getWordCountMap("apple \n banana");
        assertEquals(expected,actual);
    }

    @Test
    void stringWithMultipleNewLine_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1);
        actual = wordProcessor.getWordCountMap("apple \n\n banana");
        assertEquals(expected,actual);
    }

    @Test
    void stringWithTab_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1);
        actual = wordProcessor.getWordCountMap("apple \t banana");
        assertEquals(expected,actual);
    }

    @Test
    void stringWithMultipleWhiteSpace_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("apple", 1,"banana",1 );
        actual = wordProcessor.getWordCountMap("apple           banana");
        assertEquals(expected,actual);
    }
    @Test
    void stringWithPunctuation_shouldReturnExpectedResult() throws Exception{
        Map actual, expected;
        expected = Map.of("aPPlE", 1,"banana",1,"/", 1 , "," ,1, "APPLE",1);
        actual = wordProcessor.getWordCountMap("APPLE  aPPlE   /   ,   banana");
        assertEquals(expected,actual);
    }
    // Test Number of Lines
    @Test
    void numOfLine_moreThenOneLine_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("abc xyz \n   \n pqr");
        assertEquals(3, wordProcessor.numberOfLines);
    }

    @Test
    void numOfLine_emptyFile_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("");
        assertEquals(0, wordProcessor.numberOfLines);
    }

    @Test
    void numOfLine_singleLine_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap(" this is apple. this is banana ");
        assertEquals(1, wordProcessor.numberOfLines);
    }
    @Test
    void numOfLine_countEmptyLine_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("this is apple \n \n \n this is banana" );
        assertEquals(4, wordProcessor.numberOfLines);
    }

    // Test Number of Characters
    @Test
    void numOfChar_validString_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("abs test");
        assertEquals(7, wordProcessor.numOfCharacters);
    }


    @Test
    void numOfChar_oneWord_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("r");
        assertEquals(1, wordProcessor.numOfCharacters);

    }
     @Test
    void numOfChar_ingnoreSpecialCharcter_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("@#^*)this");
        assertEquals(4, wordProcessor.numOfCharacters);

    }
    @Test
    void numOfChar_emptyWordCount_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("");
        assertEquals(0, wordProcessor.numOfCharacters);
    }


    @Test
    void numOfChar_ignoreNumbers_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("123software");
        assertEquals(8, wordProcessor.numOfCharacters);
    }

    @Test
    void numOfChar_whiteSpace_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("                     ");
        assertEquals(0, wordProcessor.numOfCharacters);
    }

    @Test
    void numOfChar_multipleLine_shouldReturnExpectedResult()throws Exception{
        wordProcessor.getWordCountMap("      abc   \n    xyz        ");
        assertEquals(6, wordProcessor.numOfCharacters);
    }
//  PHASE -3 TESTCASES
    @Test
    void wordReplacement_singleOccurrence_shouldReturnExpectedResult()throws Exception{
        String actual = wordProcessor.replaceWords("      abc       xyz        ","abc","pqr");
        assertEquals("      pqr       xyz        ", actual);
    }

    @Test
    void wordReplacement_emptyreplacewithString_shouldReturnExpectedResult() throws Exception{
        String actual=wordProcessor.replaceWords( "abc xyz","pqr","");
        assertEquals("abc xyz",actual);


    }
    @Test
    void wordReplacement_emptyreplaceString_shouldReturnExpectedResult() throws Exception{
        String actual=wordProcessor.replaceWords( "abc pqr","","pqr");
        assertEquals("abc pqr",actual);
    }

    @Test

    void wordReplacement_multipleOccurence_shouldReturnExpectedResult() throws Exception{
        String actual=wordProcessor.replaceWords( "abc pqr abc","abc","pqr");
        assertEquals("pqr pqr pqr",actual);

    }

    @Test
    void wordReplacement_emptydata_shouldReturnExpectedResult() throws Exception{
        String actual=wordProcessor.replaceWords( "","abc","pqr");
        assertEquals("",actual);

    }
    @Test
    void wordReplacement_moreThanTwoReplaceWithWord_shouldReturnExpectedResult() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> {
            wordProcessor.replaceWords( "abc ravi","abc","pqr xyz");
        });

    }
    @Test
    void wordReplacement_emptyToreplaceAndReplacewith_shouldReturnExpectedResult() throws Exception{
        String actual=wordProcessor.replaceWords( "abc xyz pqr","","");
        assertEquals("abc xyz pqr",actual);

    }
    @Test
    void wordReplacement_zeroOccuranceOfReplacementWord_shouldReturnExpectedResult() throws Exception{
        String actual=wordProcessor.replaceWords( "abc xyz pqr","mno","xyz");
        assertEquals("abc xyz pqr",actual);

    }

    @Test
    void wordReplacement_partialWordMatch_shouldNorReplaceWord() throws Exception{
        String actual=wordProcessor.replaceWords( "abdpqr","abd","xyz");
        assertEquals("abdpqr",actual);

    }

    @Test
    void wordReplacement_firstAndLastWord_shouldReturnExpectedResult() throws Exception{
        String actual=wordProcessor.replaceWords( "abc pqr abc","abc","xyz");
        assertEquals("xyz pqr xyz",actual);

    }

}

