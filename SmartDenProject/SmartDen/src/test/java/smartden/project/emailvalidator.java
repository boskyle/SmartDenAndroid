package smartden.project;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class emailvalidator {
    private Log_in log;

    @Before

    public void setUp()
    {
   log= new Log_in();
    }

    @Test
    public void testIsValid()
    {
        assertTrue(log.isValid("Samrwriter@gmail.com"));

    }


}
