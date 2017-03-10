package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DateTest {

    @Test
    public void isValidDate() {
        // invalid date numbers
        assertFalse(DateTask.isValidDate("")); // empty string
        assertFalse(DateTask.isValidDate(" ")); // spaces only
        assertFalse(DateTask.isValidDate("date")); // non-numeric
        assertFalse(DateTask.isValidDate("9011p041")); // alphabets within digits
        assertFalse(DateTask.isValidDate("9312 1534")); // spaces within digits

        // valid date numbers
        assertTrue(DateTask.isValidDate("93121534"));
        assertTrue(DateTask.isValidDate("4")); // short date numbers
        assertTrue(DateTask.isValidDate("124293842033123")); // long date numbers
    }
}
