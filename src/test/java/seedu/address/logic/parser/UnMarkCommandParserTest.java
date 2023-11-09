package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnMarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnMarkCommandParserTest {
    private UnMarkCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new UnMarkCommandParser();
    }

    @Test
    public void parse_validInputWithDifferentGroupAndTaskIndices_success() throws ParseException {
        assertEquals(parser.parse("gr/2 ti/3"), new UnMarkCommand(2, 2, null));
        assertEquals(parser.parse("gr/3 ti/1"), new UnMarkCommand(3, 0, null));
        assertEquals(parser.parse("gr/4 ti/4"), new UnMarkCommand(4, 3, null));
    }

    @Test
    public void parse_invalidGroupNumber_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("gr/0 ti/2"));
        assertThrows(ParseException.class, () -> parser.parse("gr/-1 ti/2"));
        assertThrows(ParseException.class, () -> parser.parse("gr/not_a_number ti/2"));
    }

    @Test
    public void parse_invalidTaskIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("gr/1 ti/0"));
        assertThrows(ParseException.class, () -> parser.parse("gr/1 ti/-1"));
        assertThrows(ParseException.class, () -> parser.parse("gr/1 ti/not_a_number"));
    }

    @Test
    public void parse_missingGroupOrTaskPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1 ti/2"));
        assertThrows(ParseException.class, () -> parser.parse("gr/1 2"));
    }
}

