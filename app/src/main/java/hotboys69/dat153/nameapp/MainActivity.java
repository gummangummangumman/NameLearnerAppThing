package hotboys69.dat153.nameapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private Uri ownerPicUri = null;
    public ImageView ownerImg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setContentView(R.layout.activity_main);
        TextView welcomeText = findViewById(R.id.textView3);
        ownerImg = findViewById(R.id.imageOwner);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!pref.contains("name")) {
            startActivity(new Intent(this, activity_create_owner.class));
        }else {
           String owner =  pref.getString("name",null);
            welcomeText.append("Welcome "+owner);

            try {
//                FileInputStream fos = openFileInput("owner_image");

                 InputStream is = new BufferedInputStream(new FileInputStream("owner_image"));

                Bitmap myBitmap = BitmapFactory.decodeStream(is);
                ownerImg.setImageBitmap(myBitmap);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        setUpMainMenu();
    }

    private void setUpMainMenu() {
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToListPage();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToGalleryPage();
            }
        });

        Button learnButton = findViewById(R.id.button3);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToLearningPage();
            }
        });


        FloatingActionButton fab = findViewById(R.id.addPersonButton);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAddPersonPage();
            }
        });
    }



    public void goToListPage()
    {
        Intent listPageIntent = new Intent(this, ListActivity.class);
        startActivity(listPageIntent);
    }

    public void goToGalleryPage()
    {
        Intent galleryPageIntent = new Intent(this, GalleryActivity.class);
        startActivity(galleryPageIntent);
    }

    public void goToLearningPage()
    {
        Intent learningPageIntent = new Intent(this, LearningActivity.class);
        startActivity(learningPageIntent);
    }

    public void goToAddPersonPage()
    {
        Intent addPersonPageIntent = new Intent(this, AddPersonActivity.class);
        startActivity(addPersonPageIntent);
    }



}
