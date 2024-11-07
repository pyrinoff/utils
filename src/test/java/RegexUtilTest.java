import com.github.pyrinoff.utils.RegexUtil;
import com.github.pyrinoff.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RegexUtilTest {

    @Test
    void charTest() {
        assertTrue(RegexUtil.hasAtLeastOneMatch(' ', RegexUtil.ANY_NON_CYR_OR_LAT_CHARS));
    }

    @Test
    void alinaRegexTest() {
        assertTrue(RegexUtil.hasAtLeastOneMatch("да,алина,да", "([^а-я]|^)алин[а-я]{0,2}"));
    }

}
