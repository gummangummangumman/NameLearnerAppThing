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
                Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setType("image/*");
                int result = 0;
                startActivityForResult(pickPhoto, PICK_IMAGE);
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
                Person newPerson = new Person(name, selectedImage);
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
            // generate a random file name
            String randomFileName = randomString.nextString();

            //you can easily get the bitmap from the image
            Bitmap bilde = (Bitmap) data.getExtras().get("data");
            selectedImage = HelperClass.saveToInternalStorage(getBaseContext(), bilde, randomFileName);

            imageView = findViewById(R.id.imageView2);

            if(selectedImage==null)
                Toast.makeText(this, "selectedImage er null", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, selectedImage.toString(), Toast.LENGTH_LONG).show();

            imageView.setImageURI(selectedImage);
        }

    }



}

