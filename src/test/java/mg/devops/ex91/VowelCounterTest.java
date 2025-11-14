package mg.devops.ex91;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class VowelCounterTest {
    @Test
    public void testBasicVowels() {
        Assertions.assertEquals(5, VowelCounter.countVowels("aeiou"));
    }
    @Test
    public void testMixedCase() {
        Assertions.assertEquals(4, VowelCounter.countVowels("AeiO"));
    }
    @Test
    public void testNoVowels() {
        Assertions.assertEquals(0, VowelCounter.countVowels("bcdfg"));
    }
    @Test
    public void testSentence() {
        Assertions.assertEquals(7, VowelCounter.countVowels("Hello this is a long text"));
    }
    @Test
    public void testNullInput() {
        Assertions.assertEquals(0, VowelCounter.countVowels(null));
    }
}