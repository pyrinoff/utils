import com.github.pyrinoff.utils.CollectionUtil;
import com.github.pyrinoff.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest {

    @Test
    void startsWithAnyOfTest() {
        assertTrue(StringUtil.startsWithAnyOf("привет медвед", "привет"));
        assertTrue(StringUtil.startsWithAnyOf("привет медвед", "привет", "123"));
        assertTrue(StringUtil.startsWithAnyOf("привет медвед", "123", "привет"));
        assertFalse(StringUtil.startsWithAnyOf("привет медвед", "123", "приветы"));
        assertFalse(StringUtil.startsWithAnyOf("привет медвед", "приветы"));
        assertFalse(StringUtil.startsWithAnyOf("привет медвед", "приветы", "123"));
        assertTrue(StringUtil.startsWithAnyOf("приветы медвед", "привет", "123"));
        assertTrue(StringUtil.startsWithAnyOf("приветы медвед", "123", "привет"));
        assertFalse(StringUtil.startsWithAnyOf(" приветы медвед", "привет", "123"));
        assertFalse(StringUtil.startsWithAnyOf(" приветы медвед", "123", "привет"));
        assertFalse(StringUtil.startsWithAnyOf("прИветы медвед", "привет", "123"));
        assertFalse(StringUtil.startsWithAnyOf("привЕты медвед", "123", "привет"));
    }

    @Test
    void startsWithAnyOfInsensitiveTest() {
        assertTrue(StringUtil.startsWithAnyOfInsensitive("привет медвед", "привет"));
        assertTrue(StringUtil.startsWithAnyOfInsensitive("привЕт медвед", "привЕт", "123"));
        assertTrue(StringUtil.startsWithAnyOfInsensitive("привЕт медвед", "123", "привЕт"));
        assertFalse(StringUtil.startsWithAnyOfInsensitive("привЕт медвед", "123", "привЕты"));
        assertFalse(StringUtil.startsWithAnyOfInsensitive("привЕт медвед", "привЕты"));
        assertFalse(StringUtil.startsWithAnyOfInsensitive("привЕт медвед", "привЕты", "123"));
        assertTrue(StringUtil.startsWithAnyOfInsensitive("привЕт медвед", "привЕт", "123"));
        assertTrue(StringUtil.startsWithAnyOfInsensitive("привЕты медвед", "123", "привЕт"));
        assertFalse(StringUtil.startsWithAnyOfInsensitive(" привЕты медвед", "привЕт", "123"));
        assertFalse(StringUtil.startsWithAnyOfInsensitive(" привЕты медвед", "123", "привЕт"));
        assertTrue(StringUtil.startsWithAnyOfInsensitive("привЕты медвед", "привЕт", "123"));
        assertTrue(StringUtil.startsWithAnyOfInsensitive("привЕты медвед", "123", "привЕт"));
    }

    @Test
    void toLowerCase() {
        assertEquals("привет", StringUtil.toLowerCase("пРиВет"));
        assertTrue(Arrays.deepEquals(
                new String[]{"привет медвед", "хохо"},
                StringUtil.toLowerCase("пРиВет мЕдвЕд", "хоХо")
        ));
        assertLinesMatch(
                List.of("привет медвед", "хохо"),
                StringUtil.toLowerCase(List.of("пРиВет мЕдвЕд", "хоХо"))
        );
    }

    @Test
    void startsWithAnyOfWordsTest() {
        assertTrue(StringUtil.startsWithAnyOf("привет медвед", "привет"));
        assertTrue(StringUtil.startsWithAnyOfWords("привет, медвед", "привет"));
        assertTrue(StringUtil.startsWithAnyOfWords("привет,медвед", "привет"));
        assertFalse(StringUtil.startsWithAnyOfWords("приветы медвед", "привет"));
        assertFalse(StringUtil.startsWithAnyOfWords("опривет медвед", "привет"));
        assertFalse(StringUtil.startsWithAnyOfWords("опривет медвед", "привет"));
        assertFalse(StringUtil.startsWithAnyOfWords(" привет медвед", "привет"));
        assertFalse(StringUtil.startsWithAnyOfWords(" приветы медвед", "привет"));
    }

    @Test
    void startWith() {
        assertTrue(StringUtil.startsWithAnyOfWords("алина гифку", "алина"));
    }

    @Test
    void containsAnyOf() {
        assertTrue(StringUtil.containsAnyOfWords("а,наш карл, то, украл кораллы", "кораллы"));
        assertTrue(StringUtil.containsAnyOfWords("а,наш карл, то, украл кораллы", "то"));
        assertFalse(StringUtil.containsAnyOfWords("а,наш карл, то, украл кораллы", "укра"));
        assertFalse(StringUtil.containsAnyOfWords("а,наш карл, то, украл кораллы", "коралл"));
    }

    @Test
    void testCount() {
        Map<String, Integer> stringIntegerMap = StringUtil.countWordsContainsInsideString(
                //List.of("один два три", "один два три четыре", "пять шесть семь"),
                List.of("один.два.три", "один.два.три.четыре", "пять.шесть.семь"),
                List.of("один", "четыре")
        );
        System.out.println(stringIntegerMap);
        List<String> keysWithMax = CollectionUtil.getKeysWithMaxValueOfValue(stringIntegerMap);
        System.out.println(keysWithMax);
    }
}
