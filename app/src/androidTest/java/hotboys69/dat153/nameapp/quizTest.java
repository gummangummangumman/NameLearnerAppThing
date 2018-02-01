package hotboys69.dat153.nameapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.*;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by Jonas on 01.02.2018.
 */

public class quizTest {


    @Rule
    public ActivityTestRule<LearningActivity> mActivityRule =
            new ActivityTestRule(LearningActivity.class);


    @Test
    public void openQuiz(){
        onView(withId(R.layout.activity_learning)).perform(click()).check(matches(isDisplayed()));

    }


}
