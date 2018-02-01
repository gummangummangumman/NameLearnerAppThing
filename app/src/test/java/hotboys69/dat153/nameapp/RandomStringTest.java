package hotboys69.dat153.nameapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by GuMMaN on 01.02.2018.
 */

public class RandomStringTest {

    RandomString randomString;

    @Before
    public void setup()
    {
        randomString = new RandomString(10);
    }


    @Test
    public void isRightLength() throws Exception {
        String lengthTen = randomString.nextString();
        assertTrue(lengthTen.length()==10);
    }

    @Test
    public void stringsAreDifferent() throws Exception {
        String first = randomString.nextString();
        String second = randomString.nextString();
        assertFalse(first.equals(second));
    }
}
