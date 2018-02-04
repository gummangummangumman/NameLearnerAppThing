package hotboys69.dat153.nameapp;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.*;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by Jonas on 01.02.2018.
 */

@RunWith(AndroidJUnit4.class)
public class quizTest {


    @Rule
    public ActivityTestRule<LearningActivity> quizActivityRule =
            new ActivityTestRule(LearningActivity.class);



    @Before
    public void setup()
    {

    }

    @After
    public void cleanup()
    {

    }

    @Test
    //Open the quiz without going via the main activity
    public void openQuiz(){
        onView(withId(R.id.learning)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void scoreIsSameWhenGuessingWrong(){
        int scoreBeforeClick = quizActivityRule.getActivity().score;
        onView(withId(R.id.guessButton)).perform(click());
        assertEquals(quizActivityRule.getActivity().score, scoreBeforeClick);
    }

    @Test
    public void scoreIncreases()
    {
        int scoreBeforeClick = quizActivityRule.getActivity().score;
        Person p = quizActivityRule.getActivity().p;
        onView(withId(R.id.nameGuess)).perform(typeText(p.getName()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.guessButton)).perform(click());
        assertEquals(quizActivityRule.getActivity().score, scoreBeforeClick+1);
    }

    @Test
    public void ignoresCase()
    {
        int scoreBeforeClick = quizActivityRule.getActivity().score;
        Person p = quizActivityRule.getActivity().p;
        onView(withId(R.id.nameGuess)).perform(typeText(p.getName().toUpperCase()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.guessButton)).perform(click());
        assertEquals(quizActivityRule.getActivity().score, scoreBeforeClick+1);
    }

}