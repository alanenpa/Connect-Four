package utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static utils.Utils.validateInput;

public class UtilsTest {

    @Test
    public void validationWorksAsExpected() {
        int result = validateInput("asdf");
        assertEquals(-1, result);
        result = validateInput("");
        assertEquals(-1, result);
        result = validateInput("q");
        assertEquals(-2, result);
        result = validateInput("x");
        assertEquals(-1, result);
        result = validateInput("2");
        assertEquals(1, result);
        result = validateInput("5");
        assertEquals(4, result);
        result = validateInput("17");
        assertEquals(-1, result);
        result = validateInput("0");
        assertEquals(-1, result);
        result = validateInput("8");
        assertEquals(-1, result);
    }
}
