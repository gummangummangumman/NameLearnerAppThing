package hotboys69.dat153.nameapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import java.io.*;
import android.graphics.*;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;


public class AddPersonActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    public static final int PICK_IMAGE = 1;
    public ImageView imageView = null;
    public EditText nameText = null;
    private Bitmap bitmap = null;
    private Uri selectedImage = null;

    //for filename generation
    RandomString randomString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        randomString = new RandomString(10);


        ImageButton addPictureButton = findViewById(R.id.imageButton);
        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE);
            }

        });



        ImageButton addPictureFromCameraButton = findViewById(R.id.cameraButton);
        addPictureFromCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }

        });



        nameText = findViewById(R.id.nameToAdd);
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                Toast.makeText(getBaseContext(), name + " added " ,
                        Toast.LENGTH_SHORT).show();

                //Person newPerson = new Person(name, bitmap);
                Person newPerson = new Person(name,selectedImage);
                Data.persons.add(newPerson);
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (data == null) {

                Toast.makeText(this, "You need to select a picture",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            selectedImage = data.getData();


            // Show the Selected Image on ImageView
            imageView = findViewById(R.id.imageView2);
            imageView.setImageURI(selectedImage);


        }
        else if(requestCode == CAMERA_REQUEST) {
            //you can easily get the bitmap from the image
            Bitmap bilde = (Bitmap) data.getExtras().get("data");
            selectedImage = saveToInternalStorage(bilde);

            imageView = findViewById(R.id.imageView2);

            if(selectedImage==null)
                Toast.makeText(this, "selectedImage er null", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, selectedImage.toString(), Toast.LENGTH_LONG).show();

            imageView.setImageURI(selectedImage);
        }

    }

    /**
     * https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
     */
    private Uri saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // generate a random file name
        String randomFileName = randomString.nextString();
        // Create file path
        File mypath = new File(directory,randomFileName + ".png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Uri.parse(mypath.getAbsolutePath());
    }

}

