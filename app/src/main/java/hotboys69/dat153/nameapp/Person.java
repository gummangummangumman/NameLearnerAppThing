package hotboys69.dat153.nameapp;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by GuMMaN on 18.01.2018.
 */

public class Person {

    private String name;
    private Uri pic;
    private Bitmap bitmap;

    public Person(String name, String pic){
        this.name = name;
        this.pic = Uri.parse(pic);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Person(String name, Bitmap bitmap){
        this.name = name;
        this.bitmap = bitmap;
    }


    public Person(String name, Uri pic){
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getPic() {
        return pic;
    }

    public void setPic(Uri pic) {
        this.pic = pic;
    }
}
