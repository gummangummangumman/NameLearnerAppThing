package hotboys69.dat153.nameapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by GuMMaN on 01.02.2018.
 */

public class RandomStringTest {

    @Test
    public void isRightLength() throws Exception {
        RandomString randomString = new RandomString(10);
        String lengthTen = randomString.nextString();
        assertTrue(lengthTen.length()==10);
    }

    @Test
    public void stringsAreDifferent() throws Exception {
        RandomString randomString = new RandomString(10);
        String first = randomString.nextString();
        String second = randomString.nextString();
        assertFalse(first.equals(second));
    }
}
