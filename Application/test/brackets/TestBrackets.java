package brackets;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBrackets {

    @Test
    public void testValid() {
        assertTrue(Brackets.isValidExpression("(123)"));
        assertTrue(Brackets.isValidExpression("[(123)(123)]"));
        assertTrue(Brackets.isValidExpression("[23(123)12(123)]"));
        assertTrue(Brackets.isValidExpression("{123[123(123)123(123)]23[123]2}"));
        assertTrue(Brackets.isValidExpression("[123]"));
        assertTrue(Brackets.isValidExpression("{123}"));
        
        assertTrue(Brackets.isValidExpression("[123(145)38(37)812]"));
        assertTrue(Brackets.isValidExpression("{125[2][(1)][3]125}"));
        assertTrue(Brackets.isValidExpression("[125()125()125()125]"));
    }   
    
    @Test
    public void testInvalid() {
        assertFalse(Brackets.isValidExpression("123(123)"));
        assertFalse(Brackets.isValidExpression("(123[123])"));
        assertFalse(Brackets.isValidExpression("[123(123])"));
        assertFalse(Brackets.isValidExpression("[123{123}]"));
        assertFalse(Brackets.isValidExpression("(123)(123)"));

        assertFalse(Brackets.isValidExpression("{125[12]{125}[12]125}"));
        assertFalse(Brackets.isValidExpression("{125[12(123]125}"));
        assertFalse(Brackets.isValidExpression("{125()125}"));
    }
    
    @Test
    public void testEvaluate() {
        assertEquals(500, Brackets.evaluate("[125()125()125()125]"));
        assertEquals(1337, Brackets.evaluate("[123(145)38(37)812]"));
        assertEquals(264, Brackets.evaluate("{125[2][(1)][3]125}"));
    }

}
