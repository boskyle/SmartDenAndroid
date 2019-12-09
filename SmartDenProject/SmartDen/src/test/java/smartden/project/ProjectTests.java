package smartden.project;


import org.junit.Before;
import org.junit.Test;
import org.junit.internal.Classes;

import static junit.framework.TestCase.assertTrue;
import static smartden.project.Log_in.isValid;

public class ProjectTests {

    private MainMenu Mainmenu = null;

    @Before
    public void SetUp(){ new Log_in();
    }


    @Test
    public void testisValid() {
        assertTrue(isValid("Samrwriter@gmail.com"));
    }






}
