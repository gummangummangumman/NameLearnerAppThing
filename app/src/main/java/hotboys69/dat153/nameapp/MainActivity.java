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
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private final int CREATE_OWNER_INTENT_CODE = 0;

    private TextView nameView;
    private SharedPreferences pref;

    private Uri ownerPicUri = null;
    public ImageView ownerImg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setContentView(R.layout.activity_main);
        ownerImg = findViewById(R.id.imageOwner);

        setUpMainMenu();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {
            //SetOwnerText();
        }
    }

    private void SetOwnerText() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (!pref.contains("name")) {
            startActivity(new Intent(this, activity_create_owner.class));
        } else {
            String owner = pref.getString("name", null);

            TextView welcomeText = findViewById(R.id.nameTextView);
            welcomeText.setText("Welcome " + owner);
        }
    }

    private void ShowOwnerImage() {
        final String FILENAME = "owner_image.png";
        File img = getBaseContext().getFileStreamPath("owner_image.png");
        if(img != null && img.exists()) {
            try {
                FileInputStream fis = openFileInput(FILENAME);
                Bitmap b = BitmapFactory.decodeStream(fis);
                ownerImg.setImageBitmap(b);
            } catch(Exception e) {
                e.printStackTrace();
                Toast.makeText(this,
                        "Something went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,
                    "Image does not exist",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void CheckPreferences() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!pref.contains("name")) {
            gotoChangeNamePage();
        }
    }

    private void setUpMainMenu() {
        SetOwnerText();

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
                        try {
                            SetOwnerText();
                            ShowOwnerImage();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
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
