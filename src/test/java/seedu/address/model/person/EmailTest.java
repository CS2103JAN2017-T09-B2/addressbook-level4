package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailTest {

    @Test
    public void isValidEmail() {
        // blank email
        assertFalse(Time.isValidEmail("")); // empty string
        assertFalse(Time.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Time.isValidEmail("@webmail.com")); // missing local part
        assertFalse(Time.isValidEmail("peterjackwebmail.com")); // missing '@' symbol
        assertFalse(Time.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Time.isValidEmail("-@webmail.com")); // invalid local part
        assertFalse(Time.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Time.isValidEmail("peter jack@webmail.com")); // spaces in local part
        assertFalse(Time.isValidEmail("peterjack@web mail.com")); // spaces in domain name
        assertFalse(Time.isValidEmail("peterjack@@webmail.com")); // double '@' symbol
        assertFalse(Time.isValidEmail("peter@jack@webmail.com")); // '@' symbol in local part
        assertFalse(Time.isValidEmail("peterjack@webmail@com")); // '@' symbol in domain name

        // valid email
        assertTrue(Time.isValidEmail("PeterJack_1190@WEB.Mail.com"));
        assertTrue(Time.isValidEmail("a@b"));  // minimal
        assertTrue(Time.isValidEmail("test@localhost"));   // alphabets only
        assertTrue(Time.isValidEmail("123@145"));  // numeric local part and domain name
        assertTrue(Time.isValidEmail("a1@sg50.org"));  // mixture of alphanumeric and dot characters
        assertTrue(Time.isValidEmail("_user_@_do_main_.com_"));    // underscores
        assertTrue(Time.isValidEmail("peter_jack@a_very_long_domain_AVLD.com"));   // long domain name
        assertTrue(Time.isValidEmail("if.you.dream.it_you.can.do.it@youth_email.com"));    // long local part
    }
}
