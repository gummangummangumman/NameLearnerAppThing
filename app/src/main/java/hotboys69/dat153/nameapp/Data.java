package hotboys69.dat153.nameapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by GuMMaN on 18.01.2018.
 */

public class Data {

    public static ArrayList<Person> persons = init();

    public static ArrayList<Person> init(){
        persons = new ArrayList<Person>();
        Person emil = new Person("emil", "android.resource://hotboys69.dat153.nameapp/drawable/emilracerbil");
        persons.add(emil);
        Person jonas = new Person("jonas", "android.resource://hotboys69.dat153.nameapp/drawable/ekrof");
        persons.add(jonas);
        Person trygve = new Person("trygve", "android.resource://hotboys69.dat153.nameapp/drawable/gumman");
        persons.add(trygve);

        return persons;
    }

    /**
     * gets a random person. used in the learning thing
     * @return random person
     */
    public static Person getRandomPerson(){
        return persons.get((int) Math.floor(Math.random() * persons.size()));
    }

}
