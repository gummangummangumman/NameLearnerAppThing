package hotboys69.dat153.nameapp;

import android.content.Intent;
import android.media.Image;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.hamcrest.Matcher;
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
    public ActivityTestRule<LearningActivity> learningRule =
            new ActivityTestRule(LearningActivity.class);


    @Test
    //Open the quiz without going via the main activity
    public void openQuiz(){
        onView(withId(R.id.learning)).perform(click()).check(matches(isDisplayed()));
    }

    //Tests if name matches correct image and score increase
    @Test
    public void scoreTest(){
        ImageView img = learningRule.getActivity().findViewById(R.id.imageView);
        img.setImageResource(R.drawable.emilracerbil);

        String emil = Data.init().get(0).getName();

        //final TextView scoreView = findViewById(R.id.score);
        //onView(withId(R.id.imageView)).check(matches(withId(R.drawable.emilracerbil)));
        onView(withId(R.id.nameGuess)).perform(typeText("emil"));
        onView(withId(R.id.guessButton)).perform(click());
        onView(withId(R.id.learning)).check(matches(withText(emil)));

        //onView(withId(R.id.passwordResult)).check(matches(withText(R.string.passwords_match_notice)));
    }


}
