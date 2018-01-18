package hotboys69.dat153.nameapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import java.io.*;
import java.net.URI;
import android.graphics.*;
import 	android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;


public class AddPersonActivity extends AppCompatActivity {


    public int PICK_IMAGE = 1;
    public ImageView imageView = null;
    public EditText nameText = null;

    private Uri imageURI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        ImageButton addPictureButton = findViewById(R.id.imageButton);
        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE);
            }
        });

        nameText = (EditText) findViewById(R.id.nameToAdd);
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                Toast.makeText(getBaseContext(), name,
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (data == null) {

                Toast.makeText(this, "You need to select a picture",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Uri selectedImage = data.getData();
            imageURI = selectedImage;
            try {
                Bitmap bitmapImage = decodeBitmap(selectedImage );
                // Show the Selected Image on ImageView
                imageView = (ImageView) findViewById(R.id.imageView2);
                imageView.setImageBitmap(bitmapImage);
        }
             catch (FileNotFoundException e) {
             Log.i("TAG", "Some exception " + e);
             e.printStackTrace();
            }


        }

    }

    public  Bitmap decodeBitmap(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 100;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
    }
}

