package hotboys69.dat153.nameapp;

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
}
