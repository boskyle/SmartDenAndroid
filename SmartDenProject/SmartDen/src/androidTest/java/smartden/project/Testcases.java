package smartden.project;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static smartden.project.Log_in.isValid;

public class Testcases {

        private String namestring;
        private String passwordstring;
        private String emailstring;
        @Rule
        public ActivityTestRule<Registration> mActivityTestRule = new ActivityTestRule<Registration>(Registration.class);


        @Before
                public void addstring()
        {
                namestring="Sam Fatuga";
                passwordstring = "samuel221";
                emailstring ="Sam@gmail.com";

        }
        @Test
        public void testLaunch(){
               onView(withId(R.id.fullname)).perform(typeText(namestring));
                onView(withId(R.id.password)).perform(typeText(passwordstring));
                onView(withId(R.id.email)).perform(typeText(emailstring),closeSoftKeyboard());

                onView(withId(R.id.button2)).perform(click());

        }
}


