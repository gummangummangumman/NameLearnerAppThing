package hotboys69.dat153.nameapp;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * Created by GuMMaN on 02.02.2018.
 */

@RunWith(AndroidJUnit4.class)
public class addTest {

    @Rule
    public ActivityTestRule<AddPersonActivity> addActivityRule =
            new ActivityTestRule(AddPersonActivity.class);


    @Test
    public void personsIncreasesSizeWhenAddingPerson()
    {
        int sizeBeforeAdding = Data.persons.size();
        onView(withId(R.id.nameToAdd)).perform(typeText("lol"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addButton)).perform(click());
        assertEquals(Data.persons.size(), sizeBeforeAdding + 1);
    }

}