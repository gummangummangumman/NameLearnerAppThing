package hotboys69.dat153.nameapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private final int CREATE_OWNER_INTENT_CODE = 0;

    private TextView nameView;
    private SharedPreferences pref;

    private Uri ownerPicUri = null;
    public ImageView ownerImg = null;
    private FusedLocationProviderClient mFusedLocationClient;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setContentView(R.layout.activity_main);
        TextView welcomeText = findViewById(R.id.nameView);
        ownerImg = findViewById(R.id.imageOwner);

         mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
// Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            TextView loc = findViewById(R.id.textView4);
                            loc.append(location.toString());
                            Log.i(null,location.toString());
// Logic to handle location object
                        }
                    }
                });

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

    private void CheckPreferences() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!pref.contains("name")) {
            gotoChangeNamePage();
        }
    }


    private void setUpMainMenu() {
        nameView = findViewById(R.id.nameView);

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

        GifImageView mario = findViewById(R.id.mario);
        mario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoChangeNamePage();
            }
        });


        FloatingActionButton fab = findViewById(R.id.addPersonButton);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAddPersonPage();
            }
        });
    }


    @Override
    protected void onActivityResult(int REQUEST_CODE, int RESULT_CODE, Intent intent) {
        switch (REQUEST_CODE) {
            case CREATE_OWNER_INTENT_CODE:
                switch (RESULT_CODE) {
                    case RESULT_OK:
                       // nameView.setText(pref.getString("name", "name"));
                        break;
                }
                break;
        }
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

    private void gotoChangeNamePage() {
        Intent ownerIntent = new Intent(this, activity_create_owner.class);
        startActivityForResult(ownerIntent, CREATE_OWNER_INTENT_CODE);
    }
}
